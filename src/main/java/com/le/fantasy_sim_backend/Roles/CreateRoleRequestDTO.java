package com.le.fantasy_sim_backend.Roles;

public class CreateRoleRequestDTO {
	
	private Long characterId;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCharacterId() {
		return characterId;
	}

	public void setCharacterId(Long characterId) {
		this.characterId = characterId;
	}
	
	
}
