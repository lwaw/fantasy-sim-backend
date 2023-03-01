package com.le.fantasy_sim_backend.UserCharacter;

public class CreateRequestDTO {
	
	private Long characterId;
	
	private String name;
	
	private String race;

	public Long getCharacterId() {
		return characterId;
	}

	public void setCharacterId(Long characterId) {
		this.characterId = characterId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}
	
	
}
