package com.le.fantasy_sim_backend.Roles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.le.fantasy_sim_backend.Services.IsRoleService;
import com.le.fantasy_sim_backend.Services.UserIsAuthorizedChecksService;
import com.le.fantasy_sim_backend.UserCharacter.ChangeResponseDTO;

@RestController
@CrossOrigin(maxAge = 3600)
public class RoleController {

	@Autowired
	private IRoleRepository RoleRepo;
	
	@Autowired
	private UserIsAuthorizedChecksService userIsAuthorizedChecksService;
	
	@Autowired
	private IsRoleService isRoleService;
	
	@PostMapping(value = "Role/create")
	public void CreateRole(@RequestHeader String token, @RequestBody CreateRoleRequestDTO requestDTO) {
		
		if(!userIsAuthorizedChecksService.userIsAuthorizedChecks(requestDTO.getCharacterId(), token)) {
			return;
		}
		
		if(isRoleService.isRoleService(requestDTO.getCharacterId(), "admin")) {
			Role role = new Role();
			
			role.setName(requestDTO.getName());
			
			RoleRepo.save(role);
		}
		
		return;		

	}
}
