package de.tomahawk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.tomahawk.model.Developer;
import de.tomahawk.model.DeveloperRepository;

@Service
public class DeveloperService {

	@Autowired
	DeveloperRepository developerRepository;
	
	public List<Developer> getAllDevelopers() {
		return (List<Developer>) developerRepository.findAll();
	}
	
	public void saveDeveloper(Developer developer) {
		developerRepository.save(developer);
	}
	
}
