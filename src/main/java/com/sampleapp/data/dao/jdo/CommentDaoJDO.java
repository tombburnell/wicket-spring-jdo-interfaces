package com.sampleapp.data.dao.jdo;




import com.sampleapp.data.dao.interfaces.CommentDao;
import com.sampleapp.data.dataobjects.Comment;

/**
 * @author Richard Wilkinson - richard.wilkinson@jweekend.com
 *
 */
public class CommentDaoJDO extends BaseDaoJDO<Comment> implements CommentDao {

	public CommentDaoJDO() {
		super(Comment.class);
	}

	public Comment copy(Comment blog) {
		Comment newComment = new Comment();
		newComment.setTitle(blog.getTitle());
		return newComment;

	}

}
