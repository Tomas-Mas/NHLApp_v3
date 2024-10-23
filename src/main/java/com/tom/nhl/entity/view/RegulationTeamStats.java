package com.tom.nhl.entity.view;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Immutable
@Table(name = "Regulation_Team_Stats")
public class RegulationTeamStats {

	private RegulationTeamStatsPK id;
	private String teamName;
	private String teamAbbreviation;
	private String conference;
	private String division;
	private Integer homeGames;
	private Integer homeGoalsFor;
	private Integer homeGoalsAgainst;
	private Integer homePoints;
	private Integer homeRegWins;
	private Integer homeRegLoses;
	private Integer homeOtWins;
	private Integer homeOtLoses;
	private Integer awayGames;
	private Integer awayGoalsFor;
	private Integer awayGoalsAgainst;
	private Integer awayPoints;
	private Integer awayRegWins;
	private Integer awayRegLoses;
	private Integer awayOtWins;
	private Integer awayOtLoses;
	
	@EmbeddedId
	public RegulationTeamStatsPK getId() {
		return id;
	}
	public void setId(RegulationTeamStatsPK id) {
		this.id = id;
	}
	
	@Column(name = "team_name")
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	@Column(name = "team_abr_name")
	public String getTeamAbbreviation() {
		return teamAbbreviation;
	}
	public void setTeamAbbreviation(String teamAbbreviation) {
		this.teamAbbreviation = teamAbbreviation;
	}
	
	@Basic
	public String getConference() {
		return conference;
	}
	public void setConference(String conference) {
		this.conference = conference;
	}
	
	@Basic
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	
	@Column(name = "home_games")
	public Integer getHomeGames() {
		return homeGames;
	}
	public void setHomeGames(Integer homeGames) {
		this.homeGames = homeGames;
	}
	
	@Column(name = "home_goals_for")
	public Integer getHomeGoalsFor() {
		return homeGoalsFor;
	}
	public void setHomeGoalsFor(Integer homeGoalsFor) {
		this.homeGoalsFor = homeGoalsFor;
	}
	
	@Column(name = "home_goals_against")
	public Integer getHomeGoalsAgainst() {
		return homeGoalsAgainst;
	}
	public void setHomeGoalsAgainst(Integer homeGoalsAgainst) {
		this.homeGoalsAgainst = homeGoalsAgainst;
	}
	
	@Column(name = "home_points")
	public Integer getHomePoints() {
		return homePoints;
	}
	public void setHomePoints(Integer homePoints) {
		this.homePoints = homePoints;
	}
	
	@Column(name = "home_reg_wins")
	public Integer getHomeRegWins() {
		return homeRegWins;
	}
	public void setHomeRegWins(Integer homeRegWins) {
		this.homeRegWins = homeRegWins;
	}
	
	@Column(name = "home_reg_loses")
	public Integer getHomeRegLoses() {
		return homeRegLoses;
	}
	public void setHomeRegLoses(Integer homeRegLoses) {
		this.homeRegLoses = homeRegLoses;
	}
	
	@Column(name = "home_ot_wins")
	public Integer getHomeOtWins() {
		return homeOtWins;
	}
	public void setHomeOtWins(Integer homeOtWins) {
		this.homeOtWins = homeOtWins;
	}
	
	@Column(name = "home_ot_loses")
	public Integer getHomeOtLoses() {
		return homeOtLoses;
	}
	public void setHomeOtLoses(Integer homeOtLoses) {
		this.homeOtLoses = homeOtLoses;
	}
	
	@Column(name = "away_games")
	public Integer getAwayGames() {
		return awayGames;
	}
	public void setAwayGames(Integer awayGames) {
		this.awayGames = awayGames;
	}
	
	@Column(name = "away_goals_for")
	public Integer getAwayGoalsFor() {
		return awayGoalsFor;
	}
	public void setAwayGoalsFor(Integer awayGoalsFor) {
		this.awayGoalsFor = awayGoalsFor;
	}
	
	@Column(name = "away_goals_against")
	public Integer getAwayGoalsAgainst() {
		return awayGoalsAgainst;
	}
	public void setAwayGoalsAgainst(Integer awayGoalsAgainst) {
		this.awayGoalsAgainst = awayGoalsAgainst;
	}
	
	@Column(name = "away_points")
	public Integer getAwayPoints() {
		return awayPoints;
	}
	public void setAwayPoints(Integer awayPoints) {
		this.awayPoints = awayPoints;
	}
	
	@Column(name = "away_reg_wins")
	public Integer getAwayRegWins() {
		return awayRegWins;
	}
	public void setAwayRegWins(Integer awayRegWins) {
		this.awayRegWins = awayRegWins;
	}
	
	@Column(name = "away_reg_loses")
	public Integer getAwayRegLoses() {
		return awayRegLoses;
	}
	public void setAwayRegLoses(Integer awayRegLoses) {
		this.awayRegLoses = awayRegLoses;
	}
	
	@Column(name = "away_ot_wins")
	public Integer getAwayOtWins() {
		return awayOtWins;
	}
	public void setAwayOtWins(Integer awayOtWins) {
		this.awayOtWins = awayOtWins;
	}
	
	@Column(name = "away_ot_loses")
	public Integer getAwayOtLoses() {
		return awayOtLoses;
	}
	public void setAwayOtLoses(Integer awayOtLoses) {
		this.awayOtLoses = awayOtLoses;
	}
}
