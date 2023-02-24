package com.le.fantasy_sim_backend.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.le.fantasy_sim_backend.UserCharacter.IUserCharacterRepository;
import com.le.fantasy_sim_backend.UserCharacter.UserCharacter;

@Service
public class AddEnergyService {

	@Autowired
	private IUserCharacterRepository userCharacterRepo;

	public boolean AddEnergy(Long userCharacterId, int amount, boolean onlyVerify) {
		
		Optional<UserCharacter> optional = userCharacterRepo.findById(userCharacterId);
		if (optional.isEmpty()) {
			return false;
		}
		
		UserCharacter userCharacter = optional.get();
		
		if(userCharacter.getEnergy() + amount <= 100) {
			if(onlyVerify == false) {
				userCharacter.setEnergy(userCharacter.getEnergy() + amount);;
				
				userCharacterRepo.save(userCharacter);
			}
		}else {
			return false;
		}
		
		return true;
	}
}
