package com.le.fantasy_sim_backend.Company;

public class CreateJobResponseDTO {

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

	public CreateJobResponseDTO(boolean success, String err) {
		super();
		this.success = success;
		this.err = err;
	}
	
	
}