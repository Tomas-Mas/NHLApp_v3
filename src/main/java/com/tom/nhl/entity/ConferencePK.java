package com.tom.nhl.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Basic;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class ConferencePK implements Serializable {
	private static final long serialVersionUID = -3298402572668507170L;

	private Conference conference;
	private Team team;
	private int season;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "conference_id")
	public Conference getConference() {
		return conference;
	}
	public void setConference(Conference conference) {
		this.conference = conference;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team_id")
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	
	@Basic
	public int getSeason() {
		return season;
	}
	public void setSeason(int season) {
		this.season = season;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(conference.getJsonId(), team.getJsonId(), season);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(this.getClass() != obj.getClass())
			return false;
		ConferencePK pk = (ConferencePK) obj;
		return conference.getJsonId() == pk.getConference().getJsonId() && team.getJsonId() == pk.getTeam().getJsonId() && season == pk.season;
	}
	
}
