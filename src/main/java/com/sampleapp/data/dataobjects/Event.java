package com.sampleapp.data.dataobjects;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
/**
 * @author Richard Wilkinson - richard.wilkinson@jweekend.com
 *
 */
@PersistenceCapable
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
public class Event extends DomainObject implements BlogItem {

	private static final long serialVersionUID = 2959377496669050427L;

	private String title;
	private String location;

	public Event() {}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return String.format("Event [id=%s, location=%s, title=%s]", id, location, title);
	}

}