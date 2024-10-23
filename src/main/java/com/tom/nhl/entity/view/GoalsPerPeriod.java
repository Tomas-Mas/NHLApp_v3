package com.tom.nhl.entity.view;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Immutable
@Table(name = "GOALS_PER_PERIOD")
public class GoalsPerPeriod {

	private String id;
	private GameBasicData game;
	private String team;
	private int period;
	private int goals;
	
	@Id
	@Column(name = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "game_id")
	public GameBasicData getGame() {
		return game;
	}
	public void setGame(GameBasicData game) {
		this.game = game;
	}
	
	@Basic
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	
	@Basic
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	
	@Basic
	public int getGoals() {
		return goals;
	}
	public void setGoals(int goals) {
		this.goals = goals;
	}
}
