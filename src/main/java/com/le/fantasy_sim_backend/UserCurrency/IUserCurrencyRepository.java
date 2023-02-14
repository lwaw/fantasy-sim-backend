package com.le.fantasy_sim_backend.UserCurrency;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface IUserCurrencyRepository extends JpaRepository<UserCurrency, Long>{

	Optional<UserCurrency> findById(Long id);
	Optional<UserCurrency> findByCurrencyAndUserCharacter(Long currencyId, Long characterId);
}
