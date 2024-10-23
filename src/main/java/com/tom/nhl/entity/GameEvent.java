package com.tom.nhl.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "GAME_EVENTS")
public class GameEvent {

	private int id;
	private Game game;
	private int jsonId;
	private Event event;
	private int periodNumber;
	private String periodType;
	private String periodTime;
	private int coordX;
	private int coordY;
	private List<EventPlayer> players;
	
	@Id
	@SequenceGenerator(name = "gameEventIdGenerator", sequenceName = "SEQ_GAME_EVENTS_ID", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gameEventIdGenerator")
	@Column(name = "ge_id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "game_id")
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	
	@Column(name = "game_event_id", unique = true)
	public int getJsonId() {
		return jsonId;
	}
	public void setJsonId(int jsonId) {
		this.jsonId = jsonId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_id")
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	
	@Column(name = "period_number")
	public int getPeriodNumber() {
		return periodNumber;
	}
	public void setPeriodNumber(int periodNumber) {
		this.periodNumber = periodNumber;
	}
	
	@Column(name = "period_type", length = 15)
	public String getPeriodType() {
		return periodType;
	}
	public void setPeriodType(String periodType) {
		this.periodType = periodType;
	}
	
	@Column(name = "period_time", length = 10)
	public String getPeriodTime() {
		return periodTime;
	}
	public void setPeriodTime(String periodTime) {
		this.periodTime = periodTime;
	}
	
	@Column(name = "coord_x")
	public int getCoordX() {
		return coordX;
	}
	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}
	
	@Column(name = "coord_y")
	public int getCoordY() {
		return coordY;
	}
	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}
	
	@OneToMany(mappedBy = "id.event", fetch = FetchType.LAZY)
	public List<EventPlayer> getPlayers() {
		return players;
	}
	public void setPlayers(List<EventPlayer> players) {
		this.players = players;
	}
}
