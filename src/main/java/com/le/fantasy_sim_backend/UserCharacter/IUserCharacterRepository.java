package com.le.fantasy_sim_backend.UserCharacter;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface IUserCharacterRepository extends JpaRepository<UserCharacter, Long>{

	Optional<UserCharacter> findById(Long id);
	Optional<UserCharacter> findByName(String name);
}
