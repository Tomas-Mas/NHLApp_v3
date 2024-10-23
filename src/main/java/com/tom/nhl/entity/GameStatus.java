package com.tom.nhl.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "Game_Status")
public class GameStatus {

	private int code;
	private String name;
	private List<Game> games = new ArrayList<Game>();
	
	@Id
	@Column(name = "code")
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
	@Column(name = "name", length = 15)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(mappedBy = "gameStatus", fetch = FetchType.LAZY)
	public List<Game> getGames() {
		return games;
	}
	public void setGames(List<Game> games) {
		this.games = games;
	}
}
