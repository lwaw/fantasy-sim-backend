package com.le.fantasy_sim_backend.Company;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.le.fantasy_sim_backend.Nation.Nation;
import com.le.fantasy_sim_backend.Region.Region;
import com.le.fantasy_sim_backend.UserCharacter.UserCharacter;
import com.le.fantasy_sim_backend.Job.Job;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length = 200)
	private String name;
	
	@Column(length = 200)
	private String type;
	
	@ManyToOne
	private UserCharacter owner;
	
	@OneToMany
	@JsonIgnore
	private List<Job> jobs;
		
	@OneToOne
	private Nation locationNation;
	
	@OneToOne
	private Region locationRegion;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public UserCharacter getOwner() {
		return owner;
	}

	public void setOwner(UserCharacter owner) {
		this.owner = owner;
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
	
	
}
