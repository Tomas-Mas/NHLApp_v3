package com.tom.nhl.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "Conference_teams")
public class ConferenceTeam {

	private ConferencePK conferencePk;

	@Id
	public ConferencePK getConferencePk() {
		return conferencePk;
	}
	public void setConferencePk(ConferencePK conferencePk) {
		this.conferencePk = conferencePk;
	}
}
