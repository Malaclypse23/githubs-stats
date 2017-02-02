package de.tomahawk.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.tomahawk.model.LanguageCount;
import de.tomahawk.model.Repo;
import de.tomahawk.model.RepoRepository;

@Service
public class RepoService {
	
	private static final String UNKNOWN = "Unknown";
	
	@Autowired
	RepoRepository repoRepository;
	
	public List<Repo> getAllRepos() {
		return (List<Repo>) repoRepository.findAll();
	}
	
	public List<Repo> findByDeveloper(String developer) {
		return (List<Repo>) repoRepository.findByDeveloper(developer);
	}
	
	public List<Repo> findByDeveloperAndLanguage(String developer, String language) {
		return (List<Repo>) repoRepository.findByDeveloperAndLanguage(developer, language);
	}
	
	public void saveRepo(Repo repo) {
		repoRepository.save(repo);
	}
	
	public List<LanguageCount> findMostPopularLanguages(String developer) {
		Map<String, Integer> allLanguages = new HashMap<String, Integer>();
		for (Repo r : findByDeveloper(developer)) {
			String l = r.getLanguage();
			if (l != null) {
				if (allLanguages.get(l) != null) {
					allLanguages.put(l, allLanguages.get(l) + 1);
				} else {
					allLanguages.put(l, 1);
				}
			} else {
				allLanguages.put(UNKNOWN, allLanguages.get(UNKNOWN) == null ? 1 : allLanguages.get(UNKNOWN) + 1);
			}
		}
		return getRepoCountList(allLanguages);
	}

	private List<LanguageCount> getRepoCountList(Map<String, Integer> allLanguages) {
		List<LanguageCount> languageProjectsUser = new ArrayList<LanguageCount>();
		for (String language : allLanguages.keySet()) {
			LanguageCount lc = new LanguageCount();
			lc.setLanguage(language);
			lc.setRepoCount(allLanguages.get(language));
			languageProjectsUser.add(lc);
		}
		Collections.sort(languageProjectsUser);
		return languageProjectsUser;
	}

	public Set<String> getDistinctLanguages() {
		Set<String> result = new HashSet<String>();
		for (Repo r : getAllRepos()) {
			if (r.getLanguage() != null) result.add(r.getLanguage());
		}
		return result;
	}

}
