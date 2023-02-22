package com.le.fantasy_sim_backend.CurrencyMarket;

import java.time.LocalDateTime;

import com.le.fantasy_sim_backend.Currency.Currency;
import com.le.fantasy_sim_backend.Nation.Nation;
import com.le.fantasy_sim_backend.UserCharacter.UserCharacter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class CurrencyMarket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	//owner type of offer (user, country)
	@Column(length = 50)
	private String type;
	
	//currency to sell
	@ManyToOne
	private Currency offerCurrency;
	
	//currency to buy
	@ManyToOne
	private Currency requestCurrency;
	
	@Column(columnDefinition = "Decimal(10,2) default '0.00'")
	private double offerCurrencyAmount;
	
	//An exchange rate GBP/USD of two, for example, indicates that one pound will buy two U.S. dollars
	@Column(columnDefinition = "Decimal(10,2) default '0.00'")
	private double exchangeRate;
	
	@ManyToOne
	private Nation marketNation;
	
	@ManyToOne
	private UserCharacter ownerUserCharacter;
	
	//if owner is nation
	@ManyToOne
	private Nation ownerNation;
	
	@Column(nullable = true)
	private LocalDateTime offerCreated;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Currency getOfferCurrency() {
		return offerCurrency;
	}

	public void setOfferCurrency(Currency offerCurrency) {
		this.offerCurrency = offerCurrency;
	}

	public Currency getRequestCurrency() {
		return requestCurrency;
	}

	public void setRequestCurrency(Currency requestCurrency) {
		this.requestCurrency = requestCurrency;
	}

	public double getOfferCurrencyAmount() {
		return offerCurrencyAmount;
	}

	public void setOfferCurrencyAmount(double offerCurrencyAmount) {
		this.offerCurrencyAmount = offerCurrencyAmount;
	}

	public double getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public Nation getMarketNation() {
		return marketNation;
	}

	public void setMarketNation(Nation marketNation) {
		this.marketNation = marketNation;
	}

	public UserCharacter getOwnerUserCharacter() {
		return ownerUserCharacter;
	}

	public void setOwnerUserCharacter(UserCharacter ownerUserCharacter) {
		this.ownerUserCharacter = ownerUserCharacter;
	}

	public Nation getOwnerNation() {
		return ownerNation;
	}

	public void setOwnerNation(Nation ownerNation) {
		this.ownerNation = ownerNation;
	}

	public LocalDateTime getOfferCreated() {
		return offerCreated;
	}

	public void setOfferCreated(LocalDateTime offerCreated) {
		this.offerCreated = offerCreated;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	
	
}
