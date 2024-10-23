package com.tom.nhl.entity;

import java.util.Set;

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
@Table(name = "Teams")
public class Team {

	private int id;
	private int jsonId;
	private String name;
	private String abbreviation;
	private String teamName;
	private String shortName;
	private Venue venue;
	private TimeZone timeZone;
	private String location;
	private int firstYear;
	private String active;
	
	private Set<DivisionTeam> divisions;
	private Set<ConferenceTeam> conferences;
	
	@Id
	@SequenceGenerator(name = "teamIdGenerator", sequenceName = "SEQ_TEAMS_ID", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teamIdGenerator")
	@Column(name = "t_id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "t_json_id", unique = true)
	public int getJsonId() {
		return jsonId;
	}
	public void setJsonId(int jsonId) {
		this.jsonId = jsonId;
	}
	
	@Column(name = "name", length = 50)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "abbreviation", length = 5)
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	
	@Column(name = "team_name", length = 15)
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	@Column(name = "short_name", length = 15)
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "venue_id")
	public Venue getVenue() {
		return venue;
	}
	public void setVenue(Venue venue) {
		this.venue = venue;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "time_zone_id")
	public TimeZone getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}
	
	@Column(length = 25)
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	@Column(name = "first_year")
	public int getFirstYear() {
		return firstYear;
	}
	public void setFirstYear(int firstYear) {
		this.firstYear = firstYear;
	}
	
	@Column(length = 5)
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	
	@OneToMany(mappedBy = "divisionPk.team", fetch = FetchType.LAZY)
	public Set<DivisionTeam> getDivisions() {
		return divisions;
	}
	public void setDivisions(Set<DivisionTeam> divisions) {
		this.divisions = divisions;
	}
	
	@OneToMany(mappedBy = "conferencePk.team", fetch = FetchType.LAZY)
	public Set<ConferenceTeam> getConferences() {
		return conferences;
	}
	public void setConferences(Set<ConferenceTeam> conferences) {
		this.conferences = conferences;
	}
}
