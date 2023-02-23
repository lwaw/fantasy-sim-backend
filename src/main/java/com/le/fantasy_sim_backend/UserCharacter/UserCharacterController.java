package com.le.fantasy_sim_backend.UserCharacter;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.le.fantasy_sim_backend.Currency.ICurrencyRepository;
import com.le.fantasy_sim_backend.Services.AddCurrencyService;
import com.le.fantasy_sim_backend.Services.IsLoggedInService;
import com.le.fantasy_sim_backend.Services.IsUserCharacterService;
import com.le.fantasy_sim_backend.Services.UserCharacterChecksService;
import com.le.fantasy_sim_backend.Services.UserIsAuthorizedChecksService;

@RestController
@CrossOrigin(maxAge = 3600)
public class UserCharacterController {

	@Autowired
	private IUserCharacterRepository userCharacterRepo;
	
	@Autowired
	private ICurrencyRepository currencyRepo;
	
	@Autowired
	private UserIsAuthorizedChecksService userIsAuthorizedChecksService;
	
	@Autowired 
	private AddCurrencyService addCurrencyService;
	
	@Autowired
	private UserCharacterChecksService userCharacterChecksService;
	
	//perform database checks when logging in and when changing characters
	@PostMapping(value = "UserCharacter/changeCharacter")
	public ChangeResponseDTO train(@RequestHeader("Authentication") String token, @RequestBody ChangeRequestDTO dto) {
		
		if(!userIsAuthorizedChecksService.userIsAuthorizedChecks(dto.getcharacterId(), token)) {
			return new ChangeResponseDTO(false, "Wrong login");
		}
		
		if(userCharacterChecksService.userCharacterChecks(dto.getcharacterId())) {
			return new ChangeResponseDTO(true, "");
		}
		
		return new ChangeResponseDTO(false, "Checks failed");
	}
	
	@PostMapping(value = "UserCharacter/train")
	public TrainResponseDTO train(@RequestHeader("Authentication") String token, @RequestBody TrainRequestDTO dto) {
		if(!userIsAuthorizedChecksService.userIsAuthorizedChecks(dto.getcharacterId(), token)) {
			return new TrainResponseDTO(false, 0, "Wrong login");
		}
		
		Optional<UserCharacter> optional = userCharacterRepo.findById(dto.getcharacterId());
		if (optional.isEmpty()) {
			return new TrainResponseDTO(false, 0, "No character");
		}
		
		UserCharacter userCharacter = optional.get();
		
		if(LocalDateTime.now().isAfter(userCharacter.getLastTrained().plusDays(1))) {
			userCharacter.setLastTrained(LocalDateTime.now());
			userCharacter.setStrength(userCharacter.getStrength() + 5);
			userCharacterRepo.save(userCharacter);
			
			if(userCharacter.getStrength() % 25 == 0) {
				addCurrencyService.addCurrency(currencyRepo.findByName("gold").get().getId(), userCharacter.getId(), 5.0);
				return new TrainResponseDTO(true, userCharacter.getStrength(), "Trained succesfully and earned 5 gold");
			} else {
				return new TrainResponseDTO(true, userCharacter.getStrength(), "Trained succesfully");
			}
			
		}
				
		return new TrainResponseDTO(false, 0, "err");
	}
}
