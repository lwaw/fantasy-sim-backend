package com.le.fantasy_sim_backend.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.le.fantasy_sim_backend.Roles.IRoleRepository;
import com.le.fantasy_sim_backend.Roles.Role;
import com.le.fantasy_sim_backend.UserCharacter.IUserCharacterRepository;
import com.le.fantasy_sim_backend.UserCharacter.UserCharacter;

@Service
public class IsRoleService {

	@Autowired
	private IUserCharacterRepository userCharacterRepo;
	
	@Autowired
	private IRoleRepository roleRepo;
	
	public boolean isRoleService(Long id, String isRole) {
		
		Optional<UserCharacter> optional = userCharacterRepo.findById(id);
		if (optional.isEmpty()) {
			return false;
		}
		UserCharacter userCharacter = optional.get();
		
		List<Role> roles = userCharacter.getUserAccount().getRoles();
		
		Optional<Role> roleOption = roleRepo.findByName(isRole);
		if (roleOption.isEmpty()) {
			return false;
		}
		
		if(roles.contains(roleOption.get())) {
			return true;
		}
		
		return false;
	}
}
