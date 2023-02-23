package com.le.fantasy_sim_backend.UserCharacter;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.le.fantasy_sim_backend.Company.Company;
import com.le.fantasy_sim_backend.Nation.Nation;
import com.le.fantasy_sim_backend.Region.Region;
import com.le.fantasy_sim_backend.UserCurrency.UserCurrency;
import com.le.fantasy_sim_backend.Users.UserAccount;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class UserCharacter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private UserAccount userAccount;
	
	@Column(length = 200, nullable = false, unique = true)
	private String name;
	
	@Column(columnDefinition = "int default 0")
	private int strength;
	
	@Column(nullable = true)
	private LocalDateTime lastTrained;
	
	@OneToOne
	private Nation citizenship;
	
	@OneToOne
	private Nation locationNation;
	
	@OneToOne
	private Region locationRegion;
	
	@OneToMany
	@JsonIgnore
	private List<UserCurrency> usercurrencies;
		
	@Column(nullable = true)
	private LocalDateTime lastWorked;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Nation getCitizenship() {
		return citizenship;
	}

	public void setCitizenship(Nation citizenship) {
		this.citizenship = citizenship;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public LocalDateTime getLastTrained() {
		return lastTrained;
	}

	public void setLastTrained(LocalDateTime lastTrained) {
		this.lastTrained = lastTrained;
	}

	public List<UserCurrency> getUsercurrencies() {
		return usercurrencies;
	}

	public void setUsercurrencies(List<UserCurrency> usercurrencies) {
		this.usercurrencies = usercurrencies;
	}

	public Nation getLocationNation() {
		return locationNation;
	}

	public void setLocationNation(Nation locationNation) {
		this.locationNation = locationNation;
	}

	public Region getLocationRegion() {
		return locationRegion;
	}

	public void setLocationRegion(Region locationRegion) {
		this.locationRegion = locationRegion;
	}

	public LocalDateTime getLastWorked() {
		return lastWorked;
	}

	public void setLastWorked(LocalDateTime lastWorked) {
		this.lastWorked = lastWorked;
	}
	
	
}
