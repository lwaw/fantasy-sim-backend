package com.le.fantasy_sim_backend.Users;

public class CreateUserAccountResponseDTO {
	private boolean success;
	
	private String err;

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

	public CreateUserAccountResponseDTO(boolean success, String err) {
		super();
		this.success = success;
		this.err = err;
	}
	
	
}
