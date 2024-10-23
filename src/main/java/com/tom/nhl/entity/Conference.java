package com.tom.nhl.entity;

import java.util.Set;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Immutable
@Table(name = "Conferences")
public class Conference {

	private int id;
	private int jsonId;
	private String name;
	private Set<ConferenceDivision> divisions;
	
	@Id
	@SequenceGenerator(name = "ConferenceIdGenerator", sequenceName = "SEQ_CONFERENCES_ID", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ConferenceIdGenerator")
	@Column(name = "c_id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "c_json_id")
	public int getJsonId() {
		return jsonId;
	}
	public void setJsonId(int jsonId) {
		this.jsonId = jsonId;
	}
	
	@Column(length = 15)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(mappedBy = "conferenceDivisionPk.conference", fetch = FetchType.LAZY)
	public Set<ConferenceDivision> getDivisions() {
		return divisions;
	}
	public void setDivisions(Set<ConferenceDivision> divisions) {
		this.divisions = divisions;
	}
	
}
