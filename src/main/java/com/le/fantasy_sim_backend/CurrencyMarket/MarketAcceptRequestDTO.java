package com.le.fantasy_sim_backend.CurrencyMarket;

public class MarketAcceptRequestDTO {

	private Long offerId;
	
	private Long userCharacterId;
	
	private double buyCurrencyAmount;

	public Long getOfferId() {
		return offerId;
	}

	public void setOfferId(Long offerId) {
		this.offerId = offerId;
	}

	public Long getUserCharacterId() {
		return userCharacterId;
	}

	public void setUserCharacterId(Long userCharacterId) {
		this.userCharacterId = userCharacterId;
	}

	public double getBuyCurrencyAmount() {
		return buyCurrencyAmount;
	}

	public void setBuyCurrencyAmount(double buyCurrencyAmount) {
		this.buyCurrencyAmount = buyCurrencyAmount;
	}
	
	
}
