package de.tomahawk.model;

public class LanguageCount implements Comparable<LanguageCount> {
	
	private String login;
	private String language;
	private int repoCount;
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
	public int getRepoCount() {
		return repoCount;
	}

	public void setRepoCount(int repoCount) {
		this.repoCount = repoCount;
	}
	
	public int compareTo(LanguageCount lp) {
		if (this.language.equals("Unknown")) return 1;
		if (lp.getLanguage().equals("Unknown")) return -1;
		
		return lp.getRepoCount() - this.repoCount;
	}
	
}
