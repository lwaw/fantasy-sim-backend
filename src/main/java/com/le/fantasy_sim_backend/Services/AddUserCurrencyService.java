package com.le.fantasy_sim_backend.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.le.fantasy_sim_backend.Currency.Currency;
import com.le.fantasy_sim_backend.Currency.ICurrencyRepository;
import com.le.fantasy_sim_backend.UserCharacter.IUserCharacterRepository;
import com.le.fantasy_sim_backend.UserCharacter.UserCharacter;
import com.le.fantasy_sim_backend.UserCurrency.IUserCurrencyRepository;
import com.le.fantasy_sim_backend.UserCurrency.UserCurrency;

/*adds all currencies to userCurrency */
@Service
public class AddUserCurrencyService {

	@Autowired
	private IUserCurrencyRepository userCurrencyRepo;
	
	@Autowired
	private ICurrencyRepository currencyRepo;
	
	@Autowired
	private IUserCharacterRepository userCharacterRepo;
	
	public boolean addUserCurrency(Long userCharacterId) {
		List<Currency> currencies = currencyRepo.findAll();
	
		Optional<UserCharacter> userCharacterOption = userCharacterRepo.findById(userCharacterId);
		if(userCharacterOption.isEmpty()) {
			return false;
		}
		
		UserCharacter userCharacter = userCharacterOption.get();
		
		for (Currency currency : currencies) {
			if(userCurrencyRepo.findByCurrencyAndUserCharacter(currency.getId(), userCharacterId).isEmpty()) {
				UserCurrency userCurrency = new UserCurrency();
				userCurrencyRepo.save(userCurrency);
				
				userCurrency.setCurrency(currency);
				userCurrency.setUserCharacter(userCharacter);
				userCurrencyRepo.save(userCurrency);
			}
		}
		return true;
	}
}
