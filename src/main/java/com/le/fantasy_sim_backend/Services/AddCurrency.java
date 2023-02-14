package com.le.fantasy_sim_backend.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.le.fantasy_sim_backend.UserCurrency.IUserCurrencyRepository;
import com.le.fantasy_sim_backend.UserCurrency.UserCurrency;

@Service
public class AddCurrency {
	
	@Autowired
	private IUserCurrencyRepository userCurrencyRepo;

	public boolean addCurrency(Long currencyId, Long userCharacterId, double amount) {
		
		Optional<UserCurrency> optional = userCurrencyRepo.findByCurrencyAndUserCharacter(currencyId, userCharacterId);
		if (optional.isEmpty()) {
			return false;
		}
		
		UserCurrency usercurrency = optional.get();
		usercurrency.setAmount(usercurrency.getAmount() + amount);
		
		userCurrencyRepo.save(usercurrency);
		
		return true;
	}
}
