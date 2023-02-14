package com.le.fantasy_sim_backend.Users;

public class LoginRequestDTO {
	
	private String emailAdress;
	
	private String password;

	public String getEmailAdress() {
		return emailAdress;
	}

	public void setEmailAdress(String emailAdress) {
		this.emailAdress = emailAdress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
