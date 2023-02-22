package com.le.fantasy_sim_backend.Currency;


import com.le.fantasy_sim_backend.Nation.Nation;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Currency {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length = 200, nullable = false, unique = true)
	private String name;
	
	/*
	@OneToMany
	private List<UserCurrency> userCurrencies;
	*/
	
	@OneToOne
	private Nation nation;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*
	public List<UserCurrency> getUserCurrencies() {
		return userCurrencies;
	}

	public void setUserCurrencies(List<UserCurrency> userCurrencies) {
		this.userCurrencies = userCurrencies;
	}
	*/
	
}
