package com.le.fantasy_sim_backend.UserCharacter;

public class ChangeResponseDTO {

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

	public ChangeResponseDTO(boolean success, String err) {
		super();
		this.success = success;
		this.err = err;
	}
	
	
}
