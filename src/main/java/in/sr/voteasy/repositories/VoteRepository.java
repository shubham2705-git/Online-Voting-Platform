package in.sr.voteasy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import in.sr.voteasy.entities.Vote;

public interface VoteRepository extends JpaRepository<Vote,Long> {

}
