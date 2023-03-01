package com.le.fantasy_sim_backend.Nation;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.le.fantasy_sim_backend.Race.Race;

public interface INationRepository extends JpaRepository<Nation, Long>{

	Optional<Nation> findByRace(Race race);
}
