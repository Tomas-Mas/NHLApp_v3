package com.tom.nhl.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class EventPlayerPK implements Serializable {
	private static final long serialVersionUID = -8486669463720979112L;
	
	private GameEvent event;
	private int rosterId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_id")
	public GameEvent getEvent() {
		return event;
	}
	public void setEvent(GameEvent event) {
		this.event = event;
	}
	
	@Column(name = "roster_id")
	public int getRosterId() {
		return rosterId;
	}
	public void setRosterId(int rosterId) {
		this.rosterId = rosterId;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(event.getId(), rosterId);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(this.getClass() != obj.getClass())
			return false;
		EventPlayerPK pk = (EventPlayerPK) obj;
		return event.getId() == pk.getEvent().getId() && rosterId == pk.getRosterId();
	}
	
}
