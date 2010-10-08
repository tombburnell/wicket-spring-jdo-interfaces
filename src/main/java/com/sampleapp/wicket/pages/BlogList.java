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

import com.sampleapp.data.dao.interfaces.BlogDao;
import com.sampleapp.data.dataobjects.Blog;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.LoadableDetachableModel;
/**
 * @author Richard Wilkinson - richard.wilkinson@jweekend.com
 *
 */
public class BlogList extends BasePage {
	
	@SpringBean
	private BlogDao blogDao;
	
	public BlogList(final PageParameters pp)
	{
		Form<Blog> blogForm = new Form<Blog>("blogForm", new CompoundPropertyModel<Blog>(new Blog()));
		blogForm.add(new TextField<String>("title").setRequired(true));
		
		final WebMarkupContainer wmc = new WebMarkupContainer("listContainer");

		LoadableDetachableModel<List<Blog>> blogModel = new LoadableDetachableModel<List<Blog>>() {

			@Override
			protected List<Blog> load() {
				return (List<Blog>) blogDao.findAll();
			}
		};

		wmc.add(new ListView<Blog>("list", blogModel) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final ListItem<Blog> item) {
				Blog blog = item.getModelObject();
				item.add(new Label("blogName", blog.getTitle()));

				item.add(new AjaxFallbackLink("delete"){
					private static final long serialVersionUID = 1L;

					public void onClick(AjaxRequestTarget target ) {
						Blog blog = item.getModelObject();
						blogDao.delete(blog);
						info("Deleted blog");
						target.addComponent(wmc);
					}

				});
				item.add(new Link("show"){
					private static final long serialVersionUID = 1L;

					public void onClick() {
						Blog blog = item.getModelObject();
						setResponsePage(new BlogPage(blog));
					}

				});

			}
			
		});
		wmc.setOutputMarkupId(true);
		add(wmc);
		
		blogForm.add(new AjaxSubmitLink("submit"){
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				Blog blog = (Blog) form.getModelObject();
				Blog newBlog = new Blog();
				newBlog.setTitle(blog.getTitle());
				blogDao.save(newBlog);

				System.out.println("SAVED BLOG: "+newBlog+" ID="+newBlog.getId());
				target.addComponent(wmc);
				info("Added blog");
			}
		});
		
		
		add(blogForm);
		
	}
}
