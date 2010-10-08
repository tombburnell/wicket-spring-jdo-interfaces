/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sampleapp.wicket.panels;

import com.sampleapp.data.dataobjects.BlogItem;
import com.sampleapp.data.dataobjects.Event;
import org.apache.wicket.markup.html.basic.*;
import org.apache.wicket.markup.html.list.ListItem;

/**
 *
 * @author tom
 */
public class EventPanel extends BlogItemPanel {
	
	public EventPanel(String id, ListItem<BlogItem> item) {
		super(id,item);

		Event event = (Event) item.getModelObject();

		add(new Label("title", event.getTitle() ));
		add(new Label("location", event.getLocation() ));

	}


}
