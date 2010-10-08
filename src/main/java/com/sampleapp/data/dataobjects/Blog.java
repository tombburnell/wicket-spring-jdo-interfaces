package com.sampleapp.data.dataobjects;

import java.lang.Boolean;
import java.util.ArrayList;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;


@PersistenceCapable
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
public class Blog extends DomainObject {
	private static final long serialVersionUID = 3959377496669050427L;


	private String title;

	// Store blog items in 'thread'
	// this is a list of items that implement 'BlogItem' interface
	//@Persistent(types=" com.sampleapp.data.dataobjects")
	@Persistent(types=Comment.class)
	//. Comment,com.sampleapp.data.dataobjects.Event")
	@Join
	private ArrayList<BlogItem> thread = new ArrayList<BlogItem>();


	public Blog() {
	}

	@Override
	public String toString() {
		return String.format("Blog [id=%s, title=%s]", id, getTitle());
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	 public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the thread
	 */ 
	 public ArrayList<BlogItem> getThread() {
		return thread;
	}

	/**
	 * @param thread the thread to set
	 */ public void setThread(ArrayList<BlogItem> thread) {
		this.thread = thread;
	}

}
