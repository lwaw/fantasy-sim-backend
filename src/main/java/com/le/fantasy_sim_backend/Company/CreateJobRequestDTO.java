package com.le.fantasy_sim_backend.Company;

public class CreateJobRequestDTO {

	private Long userCharacterId;
	
	private Long CompanyId;
	
	private double salary;

	public Long getUserCharacterId() {
		return userCharacterId;
	}

	public void setUserCharacterId(Long userCharacterId) {
		this.userCharacterId = userCharacterId;
	}

	public Long getCompanyId() {
		return CompanyId;
	}

	public void setCompanyId(Long companyId) {
		CompanyId = companyId;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	
}
