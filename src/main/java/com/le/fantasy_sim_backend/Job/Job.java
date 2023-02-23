package com.le.fantasy_sim_backend.Job;

import com.le.fantasy_sim_backend.Company.Company;
import com.le.fantasy_sim_backend.Currency.Currency;
import com.le.fantasy_sim_backend.UserCharacter.UserCharacter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private Company company;
	
	@OneToOne
	private UserCharacter employee;
	
	@OneToOne
	private Currency salaryCurrency;
	
	@Column(columnDefinition = "Decimal(10,2) default '0.00'")
	private double salary;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public UserCharacter getEmployee() {
		return employee;
	}

	public void setEmployee(UserCharacter employee) {
		this.employee = employee;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	
}
