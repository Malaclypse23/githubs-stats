package de.tomahawk.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="Developer")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Developer implements Serializable {

	private static final long serialVersionUID = 6043457972375680630L;

	@Id
	@Column(name="developer_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column
	private String login;
	
	@JsonProperty("repos_url")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name="repos_url")
	private String reposUrl;

	@Column(name="repo_count")
	@JsonIgnore
	private int repoCount;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getReposUrl() {
		return reposUrl;
	}

	public void setReposUrl(String reposUrl) {
		this.reposUrl = reposUrl;
	}
	
	public int getRepoCount() {
		return repoCount;
	}

	public void setRepoCount(int repoCount) {
		this.repoCount = repoCount;
	}
	
}
