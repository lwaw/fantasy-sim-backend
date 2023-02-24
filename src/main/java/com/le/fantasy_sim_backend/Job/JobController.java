package com.le.fantasy_sim_backend.Job;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.le.fantasy_sim_backend.Services.UserIsAuthorizedChecksService;
import com.le.fantasy_sim_backend.StandardDTO.StandardResponseDTO;
import com.le.fantasy_sim_backend.UserCharacter.IUserCharacterRepository;
import com.le.fantasy_sim_backend.UserCharacter.UserCharacter;

@RestController
@CrossOrigin(maxAge = 3600)
public class JobController {

	@Autowired
	IJobRepository jobRepo;
	
	@Autowired
	IUserCharacterRepository userCharacterRepo;
	
	@Autowired
	private UserIsAuthorizedChecksService userIsAuthorizedChecksService;
	
	@PostMapping(value = "Company/acceptJobOffer")
	public StandardResponseDTO acceptJobOffer(@RequestHeader("Authentication") String token, AcceptJobOfferRequestDTO dto) {
		
		if(!userIsAuthorizedChecksService.userIsAuthorizedChecks(dto.getUserCharacterId(), token)) {
			return new StandardResponseDTO(false, "login error");
		}
		
		Optional<Job> jobOption = jobRepo.findById(dto.getJobId());
		if(jobOption.isEmpty()) {
			return new StandardResponseDTO(false, "wrong job");
		}
		Job job = jobOption.get();
		
		UserCharacter userCharacter = userCharacterRepo.findById(dto.getUserCharacterId()).get();
		
		if(job.getEmployee() != null) {
			return new StandardResponseDTO(false, "Job already has an employee");
		}
		
		if(job.getCompany().getLocationRegion().getNation() != userCharacter.getLocationNation()) {
			return new StandardResponseDTO(false, "Not in same nation as job");
		}
		
		if(jobRepo.findByEmployee(userCharacter).isPresent()){
			return new StandardResponseDTO(false, "You already have a job");
		}
		
		job.setEmployee(userCharacter);
		jobRepo.save(job);
		
		return new StandardResponseDTO(true, "");
		
	}
}
