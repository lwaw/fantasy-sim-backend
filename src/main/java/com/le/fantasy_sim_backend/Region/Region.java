package com.le.fantasy_sim_backend.Region;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.le.fantasy_sim_backend.Nation.Nation;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Region {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length = 200, nullable = false, unique = true)
	private String name;
	
	@ManyToOne
	private Nation nation;
	
	@OneToMany
	@JsonIgnore
	private List<Region> borders;

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

	public Nation getNation() {
		return nation;
	}

	public void setNation(Nation nation) {
		this.nation = nation;
	}

	public List<Region> getBorders() {
		return borders;
	}

	public void setBorders(List<Region> borders) {
		this.borders = borders;
	}
	
	
}
