package com.le.fantasy_sim_backend.UserCharacter;

import java.time.LocalDateTime;

import com.le.fantasy_sim_backend.Nation.Nation;
import com.le.fantasy_sim_backend.Users.UserAccount;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
	
	
}
