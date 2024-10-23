package com.tom.nhl.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "Conference_Division")
public class ConferenceDivision {

	private ConferenceDivisionPK conferenceDivisionPk;

	@Id
	public ConferenceDivisionPK getConferenceDivisionPk() {
		return conferenceDivisionPk;
	}
	public void setConferenceDivisionPk(ConferenceDivisionPK conferenceDivisionPk) {
		this.conferenceDivisionPk = conferenceDivisionPk;
	}
}
