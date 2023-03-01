package com.le.fantasy_sim_backend.Race;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IRaceRepository extends JpaRepository<Race, Long>{
	Optional<Race> findByName(String name);
}
