package in.sr.voteasy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sr.voteasy.entities.Candidate;
import in.sr.voteasy.exceptions.ResourceNotFoundException;
import in.sr.voteasy.repositories.CandidateRepository;

@Service
public class CandidateService {
	private CandidateRepository candidateRepository;

	@Autowired
	public CandidateService(CandidateRepository candidateRepository) {
		this.candidateRepository = candidateRepository;
	}
	
	public Candidate addCandidate(Candidate candidate) {
		return candidateRepository.save(candidate);
	}
	
	public List<Candidate>getAllcandidates(){
		return candidateRepository.findAll();
	}
	
	public Candidate getCandidateById(Long id) {
		Candidate candidate = candidateRepository.findById(id).orElse(null); 
		if(candidate==null) {
			throw new ResourceNotFoundException("Candidate with id:"+id+" not found");
		}
		return candidate;
	}
	
	public Candidate updateCandidate(Long id,Candidate updatedCandidate) {
		Candidate candidate = getCandidateById(id);
		if(updatedCandidate.getName()!=null) {
			candidate.setName(updatedCandidate.getName());	
		}
		if(updatedCandidate.getParty()!=null) {
			candidate.setParty(updatedCandidate.getParty());	
		}
		return candidateRepository.save(candidate);
	}
	
	public void deleteCandidate(Long id) {
		
	}
	
	

}
