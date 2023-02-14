package com.le.fantasy_sim_backend.UserCurrency;

import com.le.fantasy_sim_backend.Currency.Currency;
import com.le.fantasy_sim_backend.UserCharacter.UserCharacter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class UserCurrency {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private UserCharacter userCharacter;
	
	@ManyToOne
	private Currency currency;
	
	@Column(columnDefinition = "Decimal(10,2) default '0.00'")
	private double amount;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserCharacter getUserCharacter() {
		return userCharacter;
	}

	public void setUserCharacter(UserCharacter userCharacter) {
		this.userCharacter = userCharacter;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}
