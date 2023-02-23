package com.le.fantasy_sim_backend.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserIsAuthorizedChecksService {

	@Autowired
	private IsLoggedInService isLoggedInService;
	
	@Autowired
	private IsUserCharacterService IsUserCharacterService;
	
	public boolean userIsAuthorizedChecks(Long id, String token) {
		
		if(!isLoggedInService.isLoggedIn(token)) {
			return false;
		}
		
		if(!IsUserCharacterService.isUserCharacter(id, token)) {
			return false;
		}
		
		return true;
	}
}
