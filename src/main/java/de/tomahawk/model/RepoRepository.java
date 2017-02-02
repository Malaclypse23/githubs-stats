package de.tomahawk.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface RepoRepository extends CrudRepository<Repo, Long> {

	List<Repo> findByDeveloper(String developer);
	
	List<Repo> findByDeveloperAndLanguage(String developer, String language);
	
	/*
	@Query("select r.developer, r.language, r.name from Repo r where r.developer = :developer and r.language = :language")
	public List<Repo> findByDeveloperAndLanguage(@Param("developer") String developer,
												 @Param("language") String language);
	*/

}
