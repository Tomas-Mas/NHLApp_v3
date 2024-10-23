package com.tom.nhl.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "EventPlayers")
public class EventPlayer {

	private EventPlayerPK id;
	private String role;
	private Roster roster;
	
	@EmbeddedId
	public EventPlayerPK getId() {
		return id;
	}
	public void setId(EventPlayerPK id) {
		this.id = id;
	}
	
	@Column(length = 50)
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roster_id", insertable = false, updatable = false)
	public Roster getRoster() {
		return roster;
	}
	public void setRoster(Roster roster) {
		this.roster = roster;
	}
}
