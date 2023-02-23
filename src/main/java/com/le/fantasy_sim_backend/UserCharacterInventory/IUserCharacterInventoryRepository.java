package com.le.fantasy_sim_backend.UserCharacterInventory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.le.fantasy_sim_backend.UserCharacter.UserCharacter;

public interface IUserCharacterInventoryRepository extends JpaRepository<UserCharacterInventory, Long>{

	Optional<UserCharacterInventory> findByUserCharacter(UserCharacter userCharacter);
}
