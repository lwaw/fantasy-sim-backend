package com.le.fantasy_sim_backend.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.le.fantasy_sim_backend.UserCharacter.IUserCharacterRepository;
import com.le.fantasy_sim_backend.UserCharacter.UserCharacter;
import com.le.fantasy_sim_backend.Users.IUserAccountRepository;
import com.le.fantasy_sim_backend.Users.UserAccount;

@Service
public class IsUserCharacter {

	@Autowired
	private IUserCharacterRepository userCharacterRepo;
	
	@Autowired
	private IUserAccountRepository userAccountRepo;
	
	public boolean isUserCharacter(Long id, String token) {
		
		Optional<UserCharacter> optional = userCharacterRepo.findById(id);
		Optional<UserAccount> optional2 = userAccountRepo.findByToken(token);
		if (optional.isEmpty() || optional2.isEmpty()) {
			return false;
		}
		
		if(optional.get().getUserAccount() == optional2.get()) {
			return true;
		}
		
		return false;
	}
}
