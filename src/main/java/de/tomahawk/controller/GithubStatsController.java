package de.tomahawk.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.tomahawk.model.Developer;
import de.tomahawk.model.LanguageCount;
import de.tomahawk.service.DeveloperService;
import de.tomahawk.service.RepoService;

@Controller
public class GithubStatsController {

	@Autowired
	DeveloperService developerService;
	
	@Autowired
	RepoService repoService;
	
    @RequestMapping(value="/", method = RequestMethod.GET)
    String index(Model model) {
    	
    	List<Developer> developers = developerService.getAllDevelopers();
    	model.addAttribute("developers", developers);
    	model.addAttribute("languages", repoService.getDistinctLanguages());
    	
    	Map<String, List<LanguageCount>> languagecounts = new HashMap<String, List<LanguageCount>>();
		for (Developer dev : developers) {
			languagecounts.put(dev.getLogin(), repoService.findMostPopularLanguages(dev.getLogin()));
		}
    	model.addAttribute("languagecounts", languagecounts);
    	
    	return "index";
    }
    
    @RequestMapping(value="/", method = RequestMethod.POST)
    String indexPost(@ModelAttribute String language) {
    	return "index";
    }

}