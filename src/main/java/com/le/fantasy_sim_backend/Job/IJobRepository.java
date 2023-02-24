package com.le.fantasy_sim_backend.Job;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.le.fantasy_sim_backend.UserCharacter.UserCharacter;

public interface IJobRepository extends JpaRepository<Job, Long>{
	Optional<Job> findByEmployee(UserCharacter usercharacter);
}
