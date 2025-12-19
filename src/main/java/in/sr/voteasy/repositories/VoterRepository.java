package in.sr.voteasy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import in.sr.voteasy.entities.Voter;

public interface VoterRepository extends JpaRepository<Voter, Long> {
	boolean existsByEmail(String email);
	
}
