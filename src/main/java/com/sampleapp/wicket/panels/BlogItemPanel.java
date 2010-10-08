/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sampleapp.wicket.panels;

import com.sampleapp.data.dataobjects.BlogItem;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.panel.Panel;

/**
 *
 * @author tom
 */
public class BlogItemPanel extends Panel {

	public BlogItemPanel(String id, final ListItem<BlogItem> item) {
		super(id);

		add(new AjaxFallbackLink("deleteLink") {

			private static final long serialVersionUID = 1L;

			public void onClick(AjaxRequestTarget target) {
				onClickDelete(item,target);
			}
		});

	}
//
	public void onClickDelete (ListItem<BlogItem> item, AjaxRequestTarget target) {
		System.out.println("Does nothing");
	}
}
