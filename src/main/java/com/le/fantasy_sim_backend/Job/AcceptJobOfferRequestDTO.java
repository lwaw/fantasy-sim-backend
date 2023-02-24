package com.le.fantasy_sim_backend.Job;

public class AcceptJobOfferRequestDTO {

	private Long userCharacterId;
	
	private Long jobId;

	public Long getUserCharacterId() {
		return userCharacterId;
	}

	public void setUserCharacterId(Long userCharacterId) {
		this.userCharacterId = userCharacterId;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}
	
	
}
