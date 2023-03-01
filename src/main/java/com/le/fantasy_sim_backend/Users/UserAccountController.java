package com.le.fantasy_sim_backend.Users;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.RandomStringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.le.fantasy_sim_backend.Roles.IRoleRepository;
import com.le.fantasy_sim_backend.Roles.Role;

import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController
@CrossOrigin(maxAge = 3600)
public class UserAccountController {
	
	@Autowired
	private IUserAccountRepository IUserAccountRepo;
	
	@Autowired
	private IRoleRepository IRoleRepo;

	@PostMapping(value = "UserAccount/create")
	public CreateUserAccountResponseDTO CreateUserAccount(@RequestBody CreatUserAccountRequestDTO requestDTO) {
		Optional<UserAccount> optional = IUserAccountRepo.findByUsername(requestDTO.getUsername());
		if (!optional.isEmpty()) {
			return new CreateUserAccountResponseDTO(false, "Account with this username already exists");
		}
		
		optional = IUserAccountRepo.findByEmailAdress(requestDTO.getEmailAdress());
		if (!optional.isEmpty()) {
			return new CreateUserAccountResponseDTO(false, "Account with this email adress already exists");
		}
		
		UserAccount userAccount = new UserAccount();
		
		userAccount.setUsername(requestDTO.getUsername());
		userAccount.setEmailAdress(requestDTO.getEmailAdress());
		userAccount.setAccountCreated(LocalDateTime.now());
		userAccount.setAccountActivated(false);
		
		String newPass = requestDTO.getPassword();
		String pwHash = BCrypt.withDefaults().hashToString(12, newPass.toCharArray());
		userAccount.setPassword(pwHash);
		
		Optional<Role> r1 = IRoleRepo.findByName("user");
		Optional<Role> r2 = IRoleRepo.findByName("admin");
		Role r1r = r1.get();
		Role r2r = r2.get();
		
		List<Role> roles = new ArrayList<Role>();
		roles.add(r1r);
		roles.add(r2r);
		userAccount.setRoles(roles);
		
		IUserAccountRepo.save(userAccount);
		
		return new CreateUserAccountResponseDTO(true, "");
	}
	
	@PostMapping(value = "UserAccount/login")
	public LoginResponseDTO login(@RequestBody LoginRequestDTO requestDTO) {
		
		Optional<UserAccount> optional = IUserAccountRepo.findByEmailAdress(requestDTO.getEmailAdress());
		
		if (!optional.isEmpty()) {
			return new LoginResponseDTO(false, "No account was found", "");
		}
		
		UserAccount userAccount = optional.get();
		
		BCrypt.Result result = BCrypt.verifyer().verify(requestDTO.getPassword().toCharArray(), userAccount.getPassword());
		
		if (!result.verified) {
			return new LoginResponseDTO(false, "Wrong password", "");
		}
	
		String token = RandomStringUtils.random(80, true, true);
		
		userAccount.setToken(token);
	
		IUserAccountRepo.save(userAccount);
		
		return new LoginResponseDTO(true, "", token);
	
	}
}
