package com.sampleapp.data.dao.jdo;




import com.sampleapp.data.dao.interfaces.EventDao;
import com.sampleapp.data.dataobjects.Event;

/**
 * @author Richard Wilkinson - richard.wilkinson@jweekend.com
 *
 */
public class EventDaoJDO extends BaseDaoJDO<Event> implements EventDao {

	public EventDaoJDO() {
		super(Event.class);
	}

	public Event copy(Event blog) {
		Event newEvent = new Event();
		newEvent.setTitle(blog.getTitle());
		newEvent.setLocation(blog.getLocation());
		return newEvent;

	}

}
