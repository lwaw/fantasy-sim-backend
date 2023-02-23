package com.le.fantasy_sim_backend.CurrencyMarket;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.le.fantasy_sim_backend.Currency.Currency;
import com.le.fantasy_sim_backend.Currency.ICurrencyRepository;
import com.le.fantasy_sim_backend.Nation.INationRepository;
import com.le.fantasy_sim_backend.Nation.Nation;
import com.le.fantasy_sim_backend.Services.AddCurrencyService;
import com.le.fantasy_sim_backend.Services.IsLoggedInService;
import com.le.fantasy_sim_backend.Services.IsUserCharacterService;
import com.le.fantasy_sim_backend.Services.SubtractCurrencyService;
import com.le.fantasy_sim_backend.Services.UserCharacterChecksService;
import com.le.fantasy_sim_backend.Services.UserIsAuthorizedChecksService;
import com.le.fantasy_sim_backend.UserCharacter.ChangeResponseDTO;
import com.le.fantasy_sim_backend.UserCharacter.IUserCharacterRepository;
import com.le.fantasy_sim_backend.UserCharacter.TrainResponseDTO;
import com.le.fantasy_sim_backend.UserCharacter.UserCharacter;

@RestController
@CrossOrigin(maxAge = 3600)
public class CurrencyMarketController {

	@Autowired
	private ICurrencyMarketRepository currencyMarketRepo;
	
	@Autowired
	private ICurrencyRepository currencyRepo;
	
	@Autowired
	private IUserCharacterRepository characterRepo;
	
	@Autowired
	private INationRepository nationRepo;
	
	@Autowired
	private UserIsAuthorizedChecksService userIsAuthorizedChecksService;
	
	@Autowired 
	private SubtractCurrencyService subtractcurrency;
	
	@Autowired
	private AddCurrencyService addcurrency;
	
	@PostMapping(value = "CurrencyMarket/create")
	public MarketCreateResponseDTO createCurrencyMarket(@RequestHeader("Authentication") String token, @RequestBody MarketCreateRequestDTO dto) {
		
		if(!userIsAuthorizedChecksService.userIsAuthorizedChecks(dto.getUserCharacterId(), token)) {
			return new MarketCreateResponseDTO(false, "Wrong login");
		}
		
		Optional<Currency> requestCurrencyOption = currencyRepo.findById(dto.getRequestCurrencyId());
		Optional<Currency> offerCurrencyOption = currencyRepo.findById(dto.getOfferCurrencyId());
		
		if(requestCurrencyOption.isEmpty() || offerCurrencyOption.isEmpty()) {
			return new MarketCreateResponseDTO(false, "Wrong currency");
		}
		
		Currency requestCurrency = requestCurrencyOption.get();
		Currency offerCurrency = offerCurrencyOption.get();
		
		Optional<UserCharacter> userCharacterOption = characterRepo.findById(dto.getUserCharacterId());
		
		if(userCharacterOption.isEmpty()) {
			return new MarketCreateResponseDTO(false, "No character found");
		}
		
		UserCharacter userCharacter = userCharacterOption.get();
		
		CurrencyMarket currencyMarket = new CurrencyMarket();
		
		if(dto.getNationOfferId() == null) {
			
			if(subtractcurrency.subtractCurrency(offerCurrency.getId(), dto.getUserCharacterId(), dto.getOfferCurrencyAmount())) {
				
				currencyMarketRepo.save(currencyMarket);
				
				currencyMarket.setMarketNation(userCharacter.getLocationNation());
				currencyMarket.setOfferCreated(LocalDateTime.now());
				currencyMarket.setOfferCurrency(offerCurrency);
				currencyMarket.setOfferCurrencyAmount(dto.getOfferCurrencyAmount());
				currencyMarket.setOwnerUserCharacter(userCharacter);
				currencyMarket.setRequestCurrency(requestCurrency);
				currencyMarket.setExchangeRate(dto.getExchangeRate());
				currencyMarket.setType("user");
				
				currencyMarketRepo.save(currencyMarket);
			}
			
		}else {
			return new MarketCreateResponseDTO(false, "country currrency market not implemented");
		}
		

		
		return new MarketCreateResponseDTO(false, "err");
	}
	
	@PostMapping(value = "CurrencyMarket/accept")
	public MarketAcceptResponseDTO acceptCurrencyMarket(@RequestHeader("Authentication") String token, @RequestBody MarketAcceptRequestDTO dto) {
		if(!userIsAuthorizedChecksService.userIsAuthorizedChecks(dto.getUserCharacterId(), token)) {
			return new MarketAcceptResponseDTO(false, "Wrong login");
		}
		
		Optional<CurrencyMarket> currencyMarketOption = currencyMarketRepo.findById(dto.getOfferId());
		if(currencyMarketOption.isEmpty()) {
			return new MarketAcceptResponseDTO(false, "No offer found");
		}
		
		CurrencyMarket currencyMarket = currencyMarketOption.get();
		
		if(currencyMarket.getOfferCurrencyAmount() - dto.getBuyCurrencyAmount() < 0) {
			return new MarketAcceptResponseDTO(false, "Not enough currency for sale");
		}
		
		//An exchange rate GBP/USD of two, for example, indicates that one pound will buy two U.S. dollars
		double price = dto.getBuyCurrencyAmount() * currencyMarket.getExchangeRate();
		
		Optional<UserCharacter> userCharacterOption = characterRepo.findById(dto.getUserCharacterId());
		
		if(userCharacterOption.isEmpty()) {
			return new MarketAcceptResponseDTO(false, "No character found");
		}
		
		UserCharacter userCharacter = userCharacterOption.get();
		
		
		if(currencyMarket.getType() == "user") {
			
			//remove price from buyer account
			if(subtractcurrency.subtractCurrency(currencyMarket.getRequestCurrency().getId(), userCharacter.getId(), price)) {
				//add currency to buyer account
				addcurrency.addCurrency(currencyMarket.getOfferCurrency().getId(), userCharacter.getId(), dto.getBuyCurrencyAmount());
				
				//add currency to owner account
				addcurrency.addCurrency(currencyMarket.getRequestCurrency().getId(), currencyMarket.getOwnerUserCharacter().getId(), price);
				
				//remove from offer
				currencyMarket.setOfferCurrencyAmount(currencyMarket.getOfferCurrencyAmount() - dto.getBuyCurrencyAmount());
				currencyMarketRepo.save(currencyMarket);
				
				if(currencyMarket.getOfferCurrencyAmount() == 0.00) {
					currencyMarketRepo.deleteById(currencyMarket.getId());
				}
			}else {
				return new MarketAcceptResponseDTO(false, "You do not have enough currency in your account");
			}
		}
		
		return new MarketAcceptResponseDTO(false, "err");
	}
	
	
	@PostMapping(value = "CurrencyMarket/delete")
	public MarketDeleteResponseDTO deleteCurrencyMarket(@RequestHeader("Authentication") String token, @RequestBody MarketDeleteRequestDTO dto) {
		if(!userIsAuthorizedChecksService.userIsAuthorizedChecks(dto.getUserCharacterId(), token)) {
			return new MarketDeleteResponseDTO(false, "Wrong login");
		}
		
		Optional<CurrencyMarket> currencyMarketOption = currencyMarketRepo.findById(dto.getOfferId());
		if(currencyMarketOption.isEmpty()) {
			return new MarketDeleteResponseDTO(false, "No offer found");
		}
		
		CurrencyMarket currencyMarket = currencyMarketOption.get();
		
		Optional<UserCharacter> userCharacterOption = characterRepo.findById(dto.getUserCharacterId());
		
		if(userCharacterOption.isEmpty()) {
			return new MarketDeleteResponseDTO(false, "No character found");
		}
		
		UserCharacter userCharacter = userCharacterOption.get();
		
		if(addcurrency.addCurrency(currencyMarket.getOfferCurrency().getId(), userCharacter.getId(), currencyMarket.getOfferCurrencyAmount())) {
			
			currencyMarketRepo.deleteById(currencyMarket.getId());
			
			return new MarketDeleteResponseDTO(true, "");
		}else {
			return new MarketDeleteResponseDTO(false, "Could not add currency to character account");
		}
	
		
	}
	
}
