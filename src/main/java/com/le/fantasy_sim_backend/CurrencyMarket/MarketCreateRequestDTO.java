package com.le.fantasy_sim_backend.CurrencyMarket;

public class MarketCreateRequestDTO {

	private Long requestCurrencyId;
	
	private Long offerCurrencyId;
	
	private Long userCharacterId;
	
	private double offerCurrencyAmount;
	
	private double exchangeRate;
	
	//offer from nation
	private Long nationOfferId;

	public Long getRequestCurrencyId() {
		return requestCurrencyId;
	}

	public void setRequestCurrencyId(Long requestCurrencyId) {
		this.requestCurrencyId = requestCurrencyId;
	}

	public Long getOfferCurrencyId() {
		return offerCurrencyId;
	}

	public void setOfferCurrencyId(Long offerCurrencyId) {
		this.offerCurrencyId = offerCurrencyId;
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

	public Long getUserCharacterId() {
		return userCharacterId;
	}

	public void setUserCharacterId(Long userCharacterId) {
		this.userCharacterId = userCharacterId;
	}

	public Long getNationOfferId() {
		return nationOfferId;
	}

	public void setNationOfferId(Long nationOfferId) {
		this.nationOfferId = nationOfferId;
	}
	
	
}
