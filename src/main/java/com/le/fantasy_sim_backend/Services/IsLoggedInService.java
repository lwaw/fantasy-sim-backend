package com.le.fantasy_sim_backend.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.le.fantasy_sim_backend.Users.IUserAccountRepository;
import com.le.fantasy_sim_backend.Users.UserAccount;

@Service
public class IsLoggedInService {

	@Autowired
	private IUserAccountRepository userAccountRepo;
	
	public boolean isLoggedIn(String token) {
		
		Optional<UserAccount> optional = userAccountRepo.findByToken(token);
		if (optional.isEmpty()) {
			return false;
		}
		
		return true;
	}
}
