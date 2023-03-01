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
import com.le.fantasy_sim_backend.Nation.INationRepository;
import com.le.fantasy_sim_backend.Nation.Nation;
import com.le.fantasy_sim_backend.Race.IRaceRepository;
import com.le.fantasy_sim_backend.Race.Race;
import com.le.fantasy_sim_backend.Services.AddCurrencyService;
import com.le.fantasy_sim_backend.Services.IsLoggedInService;
import com.le.fantasy_sim_backend.Services.IsUserCharacterService;
import com.le.fantasy_sim_backend.Services.SubtractEnergyService;
import com.le.fantasy_sim_backend.Services.UserCharacterChecksService;
import com.le.fantasy_sim_backend.Services.UserIsAuthorizedChecksService;
import com.le.fantasy_sim_backend.StandardDTO.StandardResponseDTO;
import com.le.fantasy_sim_backend.Users.IUserAccountRepository;

@RestController
@CrossOrigin(maxAge = 3600)
public class UserCharacterController {

	@Autowired
	private IUserCharacterRepository userCharacterRepo;
	
	@Autowired
	private ICurrencyRepository currencyRepo;
	
	@Autowired
	private IUserAccountRepository userRepo;
	
	@Autowired
	private INationRepository nationRepo;
	
	@Autowired
	private IRaceRepository raceRepo;
	
	@Autowired
	private UserIsAuthorizedChecksService userIsAuthorizedChecksService;
	
	@Autowired 
	private AddCurrencyService addCurrencyService;
	
	@Autowired
	private UserCharacterChecksService userCharacterChecksService;
	
	@Autowired
	private SubtractEnergyService subtractEnergyService;
	
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
	
	@PostMapping(value = "UserCharacter/create")
	public StandardResponseDTO createCharacter(@RequestHeader("Authentication") String token, @RequestBody CreateRequestDTO dto) {
		
		if(!userIsAuthorizedChecksService.userIsAuthorizedChecks(dto.getCharacterId(), token)) {
			return new StandardResponseDTO(false, "Wrong login");
		}
		
		Optional<Race> raceOptional = raceRepo.findByName(dto.getRace());
		
		if(!raceOptional.isPresent()) {
			return new StandardResponseDTO(false, "Race not present");
		}
		
		Nation nation = nationRepo.findByRace(raceRepo.findByName(dto.getRace()).get()).get();
		
		UserCharacter userCharacter = new UserCharacter();
		userCharacterRepo.save(userCharacter);
		
		userCharacter.setUserAccount(userRepo.findById(dto.getCharacterId()).get());
		userCharacter.setName(dto.getName());
		userCharacter.setCitizenship(nation);
		userCharacter.setRace(raceRepo.findByName(dto.getRace()).get());
		userCharacter.setLocationNation(nation);
		userCharacter.setLocationRegion(nation.getCapital());
		
		userCharacterRepo.save(userCharacter);
		
		if(!userCharacterChecksService.userCharacterChecks(userCharacter.getId())) {
			return new StandardResponseDTO(false, "create user error");
		}
		
		return new StandardResponseDTO(true, "");
		
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
		
		if(userCharacter.getLastTrained() != null && LocalDateTime.now().isAfter(userCharacter.getLastTrained().plusDays(1))) {
			if(subtractEnergyService.subtractEnergy(userCharacter.getId(), 10, false)) {
				userCharacter.setLastTrained(LocalDateTime.now());
				userCharacter.setStrength(userCharacter.getStrength() + 5);
				userCharacterRepo.save(userCharacter);
				
				if(userCharacter.getStrength() % 25 == 0) {
					addCurrencyService.addCurrency(currencyRepo.findByName("gold").get().getId(), userCharacter.getId(), 5.0, false);
					return new TrainResponseDTO(true, userCharacter.getStrength(), "Trained succesfully and earned 5 gold");
				} else {
					return new TrainResponseDTO(true, userCharacter.getStrength(), "Trained succesfully");
				}
			}else {
				//not enough energy
			}
			
		}
				
		return new TrainResponseDTO(false, 0, "err");
	}
}
