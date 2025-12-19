package in.sr.voteasy.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.sr.voteasy.entities.ElectionResult;

public interface ElectionResultRepository extends JpaRepository<ElectionResult,Long> {
	Optional<ElectionResult> findByElectionName(String ElectionName);
}
