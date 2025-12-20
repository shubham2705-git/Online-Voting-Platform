package in.sr.voteasy.services;

import java.util.List;

import org.springframework.stereotype.Service;

import in.sr.voteasy.entities.Candidate;
import in.sr.voteasy.entities.Vote;
import in.sr.voteasy.entities.Voter;
import in.sr.voteasy.exceptions.ResourceNotFoundException;
import in.sr.voteasy.exceptions.VoteNotAllowedException;
import in.sr.voteasy.repositories.CandidateRepository;
import in.sr.voteasy.repositories.VoteRepository;
import in.sr.voteasy.repositories.VoterRepository;
import jakarta.transaction.Transactional;

@Service
public class VotingService {
	private VoteRepository voteRepository;
	private CandidateRepository candidateRepository;
	private VoterRepository voterRepository ;
	public VotingService(VoteRepository voteRepository, CandidateRepository candidateRepository,
			VoterRepository voterRepository) {
		
		this.voteRepository = voteRepository;
		this.candidateRepository = candidateRepository;
		this.voterRepository = voterRepository;
	}
	
	@Transactional
	public Vote castVote(Long voterId,Long candidateId) {
		if(!voterRepository.existsById(voterId)) {
			throw new ResourceNotFoundException("Voter not found with ID: "+voterId);
		}
		if(!candidateRepository.existsById(candidateId)) {
			throw new ResourceNotFoundException("Candidate not found with ID: "+candidateId);
		}
		Voter voter = voterRepository.findById(voterId).get();
		if(voter.isHasVoted()) {
			throw new VoteNotAllowedException("Voter ID: "+voterId+" has already casted vote");
		}
		Candidate candidate = candidateRepository.findById(candidateId).get();
		Vote vote = new Vote();
		vote.setVoter(voter);;
		vote.setCandidate(candidate);
		voteRepository.save(vote);
		
		candidate.setVoteCount(candidate.getVoteCount()+1);
		candidateRepository.save(candidate);
		
		voter.setHasVoted(true);
		voterRepository.save(voter);
		
		return vote;
	}
	public List<Vote> getAllVotes(){
		return voteRepository.findAll();
	}


	
	
	

}
