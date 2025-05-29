package vn.edu.hcmuaf.fit.projectwebck.services;

import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.fit.projectwebck.dao.CommentDao;
import vn.edu.hcmuaf.fit.projectwebck.dao.db.JDBIConect;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Comment;

import java.util.List;

public class CommentServices {
    CommentDao commentDao = new CommentDao();
    public  List<Comment> getCommentsByProductId(String productId) {
        return commentDao.getCommentsByProductId(productId);
    }

    // Lấy một bình luận theo ID
    public Comment getCommentById(int commentId) {
        return commentDao.getCommentById(commentId);
    }

    // Thêm bình luận mới
    public void insertComment(Comment comment) {
        commentDao.insertComment(comment);
    }

    // Xóa bình luận theo ID
    public void deleteComment(int commentId) {
       commentDao.deleteComment(commentId);
    }

    // Cập nhật nội dung bình luận
    public boolean updateComment(int commentId, String newContent) {
        return commentDao.updateComment(commentId, newContent);
    }
}
