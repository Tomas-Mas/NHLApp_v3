package com.tom.nhl.entity.view;

import java.util.Date;
import java.util.Set;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Immutable
@Table(name = "MAIN_PAGE_GAME_BASIC_DATA")
public class GameBasicData {

	private int id;
	private Date gameDate;
	private String gameType;
	private String gameStatus;
	private int homeId;
	private String homeTeam;
	private String homeAbr;
	private int homeScore;
	private int awayId;
	private String awayTeam;
	private String awayAbr;
	private int awayScore;
	private Integer endPeriod;
	private String endPeriodType;
	
	private Set<GoalsPerPeriod> goalsPerPeriod;
	
	@Id
	@Column(name = "g_id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "game_date")
	public Date getGameDate() {
		return gameDate;
	}
	public void setGameDate(Date gameDate) {
		this.gameDate = gameDate;
	}
	
	@Column(name = "game_type")
	public String getGameType() {
		return gameType;
	}
	public void setGameType(String gameType) {
		this.gameType = gameType;
	}
	
	@Column(name = "game_status")
	public String getGameStatus() {
		return gameStatus;
	}
	public void setGameStatus(String gameStatus) {
		this.gameStatus = gameStatus;
	}
	
	@Column(name = "home_id")
	public int getHomeId() {
		return homeId;
	}
	public void setHomeId(int homeId) {
		this.homeId = homeId;
	}
	
	@Column(name = "home_team")
	public String getHomeTeam() {
		return homeTeam;
	}
	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}
	
	@Column(name = "home_abr")
	public String getHomeAbr() {
		return homeAbr;
	}
	public void setHomeAbr(String homeAbr) {
		this.homeAbr = homeAbr;
	}
	
	@Column(name = "home_score")
	public int getHomeScore() {
		return homeScore;
	}
	public void setHomeScore(int homeScore) {
		this.homeScore = homeScore;
	}
	
	@Column(name = "away_id")
	public int getAwayId() {
		return awayId;
	}
	public void setAwayId(int awayId) {
		this.awayId = awayId;
	}
	
	@Column(name = "away_team")
	public String getAwayTeam() {
		return awayTeam;
	}
	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
	}
	
	@Column(name = "away_abr")
	public String getAwayAbr() {
		return awayAbr;
	}
	public void setAwayAbr(String awayAbr) {
		this.awayAbr = awayAbr;
	}
	
	@Column(name = "away_score")
	public int getAwayScore() {
		return awayScore;
	}
	public void setAwayScore(int awayScore) {
		this.awayScore = awayScore;
	}
	
	@Column(name = "period_num")
	public int getEndPeriod() {
		return endPeriod;
	}
	public void setEndPeriod(int endPeriod) {
		this.endPeriod = endPeriod;
	}
	
	@Column(name = "period_type")
	public String getEndPeriodType() {
		return endPeriodType;
	}
	public void setEndPeriodType(String endPeriodType) {
		this.endPeriodType = endPeriodType;
	}
	
	@OneToMany(mappedBy = "game", fetch = FetchType.LAZY)
	public Set<GoalsPerPeriod> getGoalsPerPeriod() {
		return goalsPerPeriod;
	}
	public void setGoalsPerPeriod(Set<GoalsPerPeriod> goalsPerPeriod) {
		this.goalsPerPeriod = goalsPerPeriod;
	}
}
