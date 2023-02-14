package com.le.fantasy_sim_backend.Users;

public class LoginResponseDTO {

	private boolean success;
	
	private String err;
	
	private String token;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErr() {
		return err;
	}

	public void setErr(String err) {
		this.err = err;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LoginResponseDTO(boolean success, String err, String token) {
		super();
		this.success = success;
		this.err = err;
		this.token = token;
	}
	
	
}
