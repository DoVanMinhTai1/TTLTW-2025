package vn.edu.hcmuaf.fit.projectwebck.services;

import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.fit.projectwebck.dao.CommentDao;
import vn.edu.hcmuaf.fit.projectwebck.dao.db.JDBIConect;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Comment;

import java.util.List;

public class CommentServices {
    CommentDao commentDao = new CommentDao();
    // Lấy tất cả bình luận của một sản phẩm
    public  List<Comment> getCommentsByProductId(String productId) {
        return commentDao.getCommentsByProductId(productId);
    }


    // Thêm bình luận mới
    public void insertComment(Comment comment) {
        commentDao.insertComment(comment);
    }


}
