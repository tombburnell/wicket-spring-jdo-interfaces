package com.sampleapp.data.dao.jdo;




import com.sampleapp.data.dao.interfaces.BlogDao;
import com.sampleapp.data.dataobjects.Blog;

/**
 * @author Richard Wilkinson - richard.wilkinson@jweekend.com
 *
 */
public class BlogDaoJDO extends BaseDaoJDO<Blog> implements BlogDao {

	public BlogDaoJDO() {
		super(Blog.class);
	}

	public Blog copy(Blog blog) {
		Blog newBlog = new Blog();
		newBlog.setTitle(blog.getTitle());
		return newBlog;

	}

}
