/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sampleapp.wicket.panels;

import com.sampleapp.data.dataobjects.BlogItem;
import com.sampleapp.data.dataobjects.Comment;
import org.apache.wicket.markup.html.basic.*;
import org.apache.wicket.markup.html.list.ListItem;

/**
 *
 * @author tom
 */
public class CommentPanel extends BlogItemPanel {
	
	public CommentPanel(String id, ListItem<BlogItem> item) {
		super(id,item);

		Comment comment = (Comment) item.getModelObject();

		add(new Label("title", comment.getTitle() ));

	}


}
