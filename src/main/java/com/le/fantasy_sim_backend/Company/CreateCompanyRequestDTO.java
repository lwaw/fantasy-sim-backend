package com.le.fantasy_sim_backend.Company;

public class CreateCompanyRequestDTO {

	private Long userCharacterId;
	
	private String name;
	
	private String type;

	public Long getUserCharacterId() {
		return userCharacterId;
	}

	public void setUserCharacterId(Long userCharacterId) {
		this.userCharacterId = userCharacterId;
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
	
	
}
