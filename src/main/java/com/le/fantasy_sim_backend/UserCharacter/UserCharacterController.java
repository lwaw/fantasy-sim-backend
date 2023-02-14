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
import com.le.fantasy_sim_backend.Services.AddCurrency;
import com.le.fantasy_sim_backend.Services.IsLoggedInService;
import com.le.fantasy_sim_backend.Services.IsUserCharacter;

@RestController
@CrossOrigin(maxAge = 3600)
public class UserCharacterController {

	@Autowired
	private IUserCharacterRepository userCharacterRepo;
	
	@Autowired
	private ICurrencyRepository currencyRepo;
	
	@Autowired
	private IsLoggedInService isLoggedInService;
	
	@Autowired 
	private IsUserCharacter isUserCharacter;
	
	@Autowired 
	private AddCurrency addCurrency;
	
	@PostMapping(value = "UserCharacter/train")
	public TrainResponseDTO train(@RequestHeader("Authentication") String token, @RequestBody TrainRequestDTO dto) {
		if(!isLoggedInService.isLoggedIn(token)) {
			return new TrainResponseDTO(false, 0, "Not logged in");
		}
		
		if(!isUserCharacter.isUserCharacter(dto.getcharacterId(), token)) {
			return new TrainResponseDTO(false, 0, "Character id does not belong to account");
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
				addCurrency.addCurrency(currencyRepo.findByName("gold").get().getId(), userCharacter.getId(), 5.0);
				return new TrainResponseDTO(true, userCharacter.getStrength(), "Trained succesfully and earned 5 gold");
			} else {
				return new TrainResponseDTO(true, userCharacter.getStrength(), "Trained succesfully");
			}
			
		}
				
		return new TrainResponseDTO(false, 0, "err");
	}
}
