package com.le.fantasy_sim_backend.Roles;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToMany;

import com.le.fantasy_sim_backend.Users.UserAccount;

@Entity
public class Role {
	@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length = 50, nullable = false, unique = true)
	private String name;
	
	@ManyToMany(mappedBy = "roles")
	@JsonIgnore
	private List<UserAccount> UserAccounts;

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

	public List<UserAccount> getUserAccounts() {
		return UserAccounts;
	}

	public void setUserAccounts(List<UserAccount> userAccounts) {
		UserAccounts = userAccounts;
	}
	
	
}
