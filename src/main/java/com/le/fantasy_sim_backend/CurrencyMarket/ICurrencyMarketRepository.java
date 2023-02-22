package com.le.fantasy_sim_backend.CurrencyMarket;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ICurrencyMarketRepository extends JpaRepository <CurrencyMarket, Long>{
	Optional<CurrencyMarket> findById(Long id);
}
