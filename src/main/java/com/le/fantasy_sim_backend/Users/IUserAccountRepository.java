package com.le.fantasy_sim_backend.Users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserAccountRepository extends JpaRepository<UserAccount, Long>{
	
	Optional<UserAccount> findByUsername(String username);
	Optional<UserAccount> findByEmailAdress(String emailAdress);
	Optional<UserAccount> findByToken(String token);
}
