package de.tomahawk;

import java.net.URL;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.tomahawk.model.Developer;
import de.tomahawk.model.Repo;
import de.tomahawk.service.DeveloperService;
import de.tomahawk.service.RepoService;

@SpringBootApplication
public class GithubStatsApplication {
	
	public static final String COMPANY = "codecentric";
	public static final String DEVELOPERS_URL = "https://api.github.com/orgs/" + COMPANY + "/members?per_page=100";
	public static final int LIMIT = 15; // limit API requests
	
	private static final Logger log = LoggerFactory.getLogger(GithubStatsApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(GithubStatsApplication.class, args);
	}
	
	@Autowired
	RepoService repoService;
	
	@Autowired
	DeveloperService developerService;
	
	@Bean
	public CommandLineRunner importDevelopers() {
		return (args) -> {
			ObjectMapper mapper = new ObjectMapper();
			List<Developer> developers = mapper.readValue(new URL(DEVELOPERS_URL), new TypeReference<List<Developer>>(){});
			
			int i = 1;

			for (Developer developer : developers) {
				if (i > LIMIT) break;
				final String USER_REPOS_URL = developer.getReposUrl() + "?per_page=100";
				
				int reposSize = 0;
				if (developer.getReposUrl() != null) {
					List<Repo> repos = mapper.readValue(new URL(USER_REPOS_URL),  new TypeReference<List<Repo>>(){});
					
					if (repos != null) {
						for (Repo r : repos) {
							r.setDeveloper(developer.getLogin());
							repoService.saveRepo(r);
						}
						reposSize = repos.size();
					}
				}
				developer.setRepoCount(reposSize);
				log.info(developer.getLogin() + " saved with " + reposSize + " repositories");
				developerService.saveDeveloper(developer);
				i++;
			}
		};
	}
}
