package com.sampleapp.data.dataobjects;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
public class Comment extends DomainObject implements BlogItem {

	private static final long serialVersionUID = 2959377496669050427L;

	private String title;

	public Comment() {}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return String.format("Comment [id=%s,  title=%s]", id, title);
	}

}