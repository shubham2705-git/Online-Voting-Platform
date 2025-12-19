package in.sr.voteasy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.sr.voteasy.entities.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long>{
	List<Candidate> findAllByOrderByVoteCountDesc();
}
