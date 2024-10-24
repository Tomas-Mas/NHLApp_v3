package com.tom.nhl.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "Rosters")
public class Roster {

	private int id;
	private Game game;
	private Team team;
	private Player player;
	private Position position;
	private String timeOnIce;
	private Integer plusMinus;
	
	@Id
	@SequenceGenerator(name = "rosterIdGenerator", sequenceName = "SEQ_ROSTERS_ID", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rosterIdGenerator")
	@Column(name = "r_id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "g_id")
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "t_id")
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "p_id")
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pos_id")
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	
	@Column(name = "time_on_ice")
	public String getTimeOnIce() {
		return timeOnIce;
	}
	public void setTimeOnIce(String timeOnIce) {
		this.timeOnIce = timeOnIce;
	}
	
	@Column(name = "plus_minus")
	public Integer getPlusMinus() {
		return plusMinus;
	}
	public void setPlusMinus(Integer plusMinus) {
		this.plusMinus = plusMinus;
	}
}
