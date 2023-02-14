package com.le.fantasy_sim_backend.UserCharacter;

public class TrainResponseDTO {
	private boolean success;
	
	private int strength;
	
	private String err;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public String getErr() {
		return err;
	}

	public void setErr(String err) {
		this.err = err;
	}

	public TrainResponseDTO(boolean success, int strength, String err) {
		super();
		this.success = success;
		this.strength = strength;
		this.err = err;
	}
	
	
}
