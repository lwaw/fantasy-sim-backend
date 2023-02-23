package com.le.fantasy_sim_backend.UserCharacterInventory;

import com.le.fantasy_sim_backend.UserCharacter.UserCharacter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class UserCharacterInventory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne
	private UserCharacter userCharacter;
	
	@Column(columnDefinition = "int default 0")
	private int rawFood;
	
	@Column(columnDefinition = "int default 0")
	private int rawWeapon;
	
	@Column(columnDefinition = "int default 0")
	private int q1Food;
	
	@Column(columnDefinition = "int default 0")
	private int q1Weapon;

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

	public int getRawFood() {
		return rawFood;
	}

	public void setRawFood(int rawFood) {
		this.rawFood = rawFood;
	}

	public int getRawWeapon() {
		return rawWeapon;
	}

	public void setRawWeapon(int rawWeapon) {
		this.rawWeapon = rawWeapon;
	}

	public int getQ1Food() {
		return q1Food;
	}

	public void setQ1Food(int q1Food) {
		this.q1Food = q1Food;
	}

	public int getQ1Weapon() {
		return q1Weapon;
	}

	public void setQ1Weapon(int q1Weapon) {
		this.q1Weapon = q1Weapon;
	}
	
	
}
