package com.le.fantasy_sim_backend.Currency;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ICurrencyRepository extends JpaRepository<Currency, Long>{
	
	Optional<Currency> findById(Long id);
	Optional<Currency> findByName(String name);
}
