package com.sampleapp.wicket.pages;

import com.sampleapp.data.dataobjects.BlogItem;
import java.util.List;

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

import com.sampleapp.data.dao.interfaces.BlogDao;
import com.sampleapp.data.dao.interfaces.CommentDao;
import com.sampleapp.data.dao.interfaces.EventDao;
import com.sampleapp.data.dataobjects.Blog;
import com.sampleapp.data.dataobjects.Comment;
import com.sampleapp.data.dataobjects.Event;
import com.sampleapp.wicket.panels.CommentPanel;
import com.sampleapp.wicket.panels.EventPanel;
import org.apache.wicket.model.LoadableDetachableModel;

/**
 * @author Richard Wilkinson - richard.wilkinson@jweekend.com
 *
 */
public class BlogPage extends BasePage {

	@SpringBean
	private BlogDao blogDao;
	@SpringBean
	private CommentDao commentDao;
	@SpringBean
	private EventDao eventDao;

	final WebMarkupContainer wmc = new WebMarkupContainer("listContainer");

	public BlogPage(final Blog blog) {
		System.out.println("Got " + blog);

		add(new Label("blogTitle", blog.getTitle()));

		//add(new FeedbackPanel("feedbackpanel"));



		LoadableDetachableModel<List<BlogItem>> threadModel = new LoadableDetachableModel<List<BlogItem>>() {

			@Override
			protected List<BlogItem> load() {
				return (List<BlogItem>) blog.getThread();
			}
		};

		wmc.add(new ListView<BlogItem>("list", threadModel) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final ListItem<BlogItem> item) {
				BlogItem threadItem = item.getModelObject();

				System.out.println("Populate Item" + threadItem);
				//TODO use visitor

				if (threadItem instanceof Comment) {
					item.add(new CommentPanel("threadItemPanel", item) {

						@Override
						public void onClickDelete(ListItem<BlogItem> item, AjaxRequestTarget target) {
							deleteBlogItem(blog, item, target);
						}
					});

				} else if (threadItem instanceof Event) {
					item.add(new EventPanel("threadItemPanel", item) {
						@Override
						public void onClickDelete(ListItem<BlogItem> item, AjaxRequestTarget target) {
							deleteBlogItem(blog, item, target);
						}
					});
				}


			}
		});
		wmc.setOutputMarkupId(true);
		add(wmc);



		//TODO put in panel..
		// Add form..
		Form<Comment> commentForm = new Form<Comment>("commentForm", new CompoundPropertyModel<Comment>(new Comment()));
		commentForm.add(new TextField<String>("title").setRequired(true));

		commentForm.add(new AjaxSubmitLink("submit") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				Comment comment = (Comment) form.getModelObject();

//				Comment newComment = new Comment();
//				newComment.setTitle(newComment.getTitle());

				Comment newComment = commentDao.copy(comment);

				blog.getThread().add(newComment);

				blogDao.save(blog);

				infoPrint("Added newComment" + newComment);

				target.addComponent(wmc);
				target.addComponent(fp);
			}
		});

		add(commentForm);



		// Add form..
		Form<Event> eventForm = new Form<Event>("eventForm", new CompoundPropertyModel<Event>(new Event()));
		eventForm.add(new TextField<String>("title").setRequired(true));
		eventForm.add(new TextField<String>("location").setRequired(true));

		eventForm.add(new AjaxSubmitLink("submit") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				Event event = (Event) form.getModelObject();

				event = eventDao.copy(event);

				System.out.println("SAVED: " + event);

				blog.getThread().add(event);

				blogDao.save(blog);

				infoPrint("Added event: " + event);

				target.addComponent(wmc);
				target.addComponent(fp);
			}
		});

		add(eventForm);
	}


	public void deleteBlogItem(Blog blog, ListItem<BlogItem> item, AjaxRequestTarget target) {

		BlogItem threadItem = item.getModelObject();
		blog.getThread().remove(threadItem);
		blogDao.save(blog);

		info("Removed "+threadItem+" from blog");

		target.addComponent(wmc);
		target.addComponent(fp);
	}
}
