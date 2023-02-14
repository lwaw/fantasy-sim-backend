package com.le.fantasy_sim_backend.Roles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(maxAge = 3600)
public class RoleController {

	@Autowired
	private IRoleRepository RoleRepo;
	
	@PostMapping(value = "Role/create")
	public void CreateRole(@RequestBody CreateRoleRequestDTO requestDTO) {
		
		Role role = new Role();
		
		role.setName(requestDTO.getName());
		
		RoleRepo.save(role);
	}
}
