package com.le.fantasy_sim_backend.Nation;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.le.fantasy_sim_backend.Currency.Currency;
import com.le.fantasy_sim_backend.Region.Region;
import com.le.fantasy_sim_backend.UserCharacter.UserCharacter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Nation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length = 200, nullable = false, unique = true)
	private String name;
	
	@OneToOne
	private UserCharacter leader;
	
	@OneToMany
	@JsonIgnore
	private List<Region> regions;
	
	@OneToOne
	private Currency currency;

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

	public UserCharacter getLeader() {
		return leader;
	}

	public void setLeader(UserCharacter leader) {
		this.leader = leader;
	}

	public List<Region> getRegions() {
		return regions;
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	
	
	
	
}
