package com.le.fantasy_sim_backend.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.le.fantasy_sim_backend.UserCharacter.IUserCharacterRepository;
import com.le.fantasy_sim_backend.UserCharacter.UserCharacter;
import com.le.fantasy_sim_backend.UserCharacterInventory.IUserCharacterInventoryRepository;
import com.le.fantasy_sim_backend.UserCharacterInventory.UserCharacterInventory;

@Service
public class AddUserCharacterInventoryService {
	
	@Autowired
	private IUserCharacterRepository userCharacterRepo;

	@Autowired
	private IUserCharacterInventoryRepository userCharacterInventoryRepo;
	
	public boolean addUserCharacterInventory(Long userCharacterId) {
		
		Optional<UserCharacter> userCharacterOption = userCharacterRepo.findById(userCharacterId);
		if(userCharacterOption.isEmpty()) {
			return false;
		}
		
		UserCharacter userCharacter = userCharacterOption.get();
		
		Optional<UserCharacterInventory> userCharacterInventoryOption = userCharacterInventoryRepo.findByUserCharacter(userCharacter);
		if(userCharacterInventoryOption.isEmpty()) {
			
			UserCharacterInventory userCharacterInventory = new UserCharacterInventory();
			userCharacterInventoryRepo.save(userCharacterInventory);
			userCharacterInventory.setUserCharacter(userCharacter);
			userCharacterInventoryRepo.save(userCharacterInventory);
		}
		
		
		return true;
	}
}
