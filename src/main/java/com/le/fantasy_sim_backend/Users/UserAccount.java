package com.le.fantasy_sim_backend.Users;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.le.fantasy_sim_backend.Roles.Role;
import com.le.fantasy_sim_backend.UserCharacter.UserCharacter;

@Entity
public class UserAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length = 200, nullable = false, unique = true)
	private String username;
	
	@Column(length = 200, nullable = false)
	private String password;
	
	@Column(length = 200, nullable = false, unique = true)
	private String emailAdress;
	
	@Column(nullable = true)
	private LocalDateTime accountCreated;
	
	@Column(nullable = true)
	private LocalDateTime accountDeleted;
	
	@Column(length = 200, nullable = true)
	private String lastLogin;
	
	@Column(length = 200, nullable = true)
	private String token;
	
	@ManyToMany
	@JsonIgnore
	private List<Role> roles;
	
	@OneToMany
	@JsonIgnore
	private List<UserCharacter> userCharacters;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailAdress() {
		return emailAdress;
	}

	public void setEmailAdress(String emailAdress) {
		this.emailAdress = emailAdress;
	}

	public LocalDateTime getAccountCreated() {
		return accountCreated;
	}

	public void setAccountCreated(LocalDateTime accountCreated) {
		this.accountCreated = accountCreated;
	}

	public LocalDateTime getAccountDeleted() {
		return accountDeleted;
	}

	public void setAccountDeleted(LocalDateTime accountDeleted) {
		this.accountDeleted = accountDeleted;
	}

	public String getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<UserCharacter> getUserCharacters() {
		return userCharacters;
	}

	public void setUserCharacters(List<UserCharacter> userCharacters) {
		this.userCharacters = userCharacters;
	}
	
	
	
	
}
