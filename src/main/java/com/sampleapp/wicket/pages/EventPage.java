package com.sampleapp.wicket.pages;

import java.util.List;

import org.apache.wicket.PageParameters;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.sampleapp.data.dao.interfaces.EventDao;
import com.sampleapp.data.dataobjects.Event;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.model.LoadableDetachableModel;
/**
 * @author Richard Wilkinson - richard.wilkinson@jweekend.com
 *
 */
public class EventPage extends BasePage {
	
	@SpringBean
	private EventDao eventDao;
	
	public EventPage(final PageParameters pp)
	{
		Form<Event> eventForm = new Form<Event>("eventForm", new CompoundPropertyModel<Event>(new Event()));
		eventForm.add(new TextField<String>("title").setRequired(true));
		eventForm.add(new TextField<String>("location").setRequired(true));


		
		final WebMarkupContainer wmc = new WebMarkupContainer("listContainer");




		LoadableDetachableModel<List<Event>> blogModel = new LoadableDetachableModel<List<Event>>() {

			@Override
			protected List<Event> load() {
				return (List<Event>) eventDao.findAll();
			}
		};

		wmc.add(new ListView<Event>("list", blogModel) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final ListItem<Event> item) {
				Event event = item.getModelObject();
				item.add(new Label("eventName", event.getTitle()));
				item.add(new Label("eventLocation", event.getLocation()));

				System.out.println("Populate Item" + event);


				item.add(new AjaxFallbackLink("delete"){
					private static final long serialVersionUID = 1L;

					public void onClick(AjaxRequestTarget target ) {
						Event event = item.getModelObject();
						eventDao.delete(event);
						info("Deleted event");
						target.addComponent(wmc);
					}

				});

			}
			
		});
		wmc.setOutputMarkupId(true);
		add(wmc);
		
		eventForm.add(new AjaxSubmitLink("submit"){
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				Event event = (Event) form.getModelObject();
				Event newEvent = new Event();
				newEvent.setLocation(event.getLocation());
				newEvent.setTitle(event.getTitle());
				eventDao.save(newEvent);

				System.out.println("SAVED "+newEvent);
				target.addComponent(wmc);
				info("Added event");
			}
		});
		
		
		add(eventForm);
		
	}
}
