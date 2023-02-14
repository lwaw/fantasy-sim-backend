package com.le.fantasy_sim_backend.Roles;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface IRoleRepository extends JpaRepository<Role, Long>{
	
	Optional<Role> findByName(String name);
}
