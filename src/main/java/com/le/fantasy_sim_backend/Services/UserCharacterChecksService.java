package com.le.fantasy_sim_backend.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCharacterChecksService {

	@Autowired
	private AddUserCurrencyService addUserCurrency;
	
	@Autowired
	private AddUserCharacterInventoryService addUserCharacterInventory;
	
	public boolean userCharacterChecks(Long userCharacterId) {
		
		if(!addUserCurrency.addUserCurrency(userCharacterId)) {
			return false;
		}
		
		if(!addUserCharacterInventory.addUserCharacterInventory(userCharacterId)) {
			return false;
		}
		
		return true;
	}
}
