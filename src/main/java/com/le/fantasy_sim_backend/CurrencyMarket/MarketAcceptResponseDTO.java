package com.le.fantasy_sim_backend.CurrencyMarket;

public class MarketAcceptResponseDTO {

	private boolean succes;
	
	private String err;

	public boolean isSucces() {
		return succes;
	}

	public void setSucces(boolean succes) {
		this.succes = succes;
	}

	public String getErr() {
		return err;
	}

	public void setErr(String err) {
		this.err = err;
	}

	public MarketAcceptResponseDTO(boolean succes, String err) {
		super();
		this.succes = succes;
		this.err = err;
	}
	
	
}
