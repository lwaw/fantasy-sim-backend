package com.le.fantasy_sim_backend.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.le.fantasy_sim_backend.UserCurrency.IUserCurrencyRepository;
import com.le.fantasy_sim_backend.UserCurrency.UserCurrency;

@Service
public class SubtractCurrencyService {

	@Autowired
	private IUserCurrencyRepository userCurrencyRepo;

	public boolean subtractCurrency(Long currencyId, Long userCharacterId, double amount, boolean onlyVerify) {
		
		Optional<UserCurrency> optional = userCurrencyRepo.findByCurrencyAndUserCharacter(currencyId, userCharacterId);
		if (optional.isEmpty()) {
			return false;
		}
		
		UserCurrency usercurrency = optional.get();
		
		if(usercurrency.getAmount() - amount >= 0.00) {
			if(onlyVerify == false) {
				usercurrency.setAmount(usercurrency.getAmount() - amount);
				
				userCurrencyRepo.save(usercurrency);
			}
		}else {
			return false;
		}
		
		
		return true;
	}
}
