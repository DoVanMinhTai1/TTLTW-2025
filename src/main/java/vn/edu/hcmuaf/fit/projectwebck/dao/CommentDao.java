package vn.edu.hcmuaf.fit.projectwebck.dao;

import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.fit.projectwebck.dao.db.JDBIConect;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Comment;

import java.util.List;

public class CommentDao {
    Jdbi jdbi = JDBIConect.get();
    // Lấy tất cả bình luận của một sản phẩm
    public  List<Comment> getCommentsByProductId(String productId) {
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM comments WHERE productId = :productId ORDER BY createdAt DESC")
                .bind("productId", productId)
                .mapToBean(Comment.class)
                .list());
    }


    // Thêm bình luận mới
    public  void insertComment(Comment comment) {
        jdbi.useHandle(handle -> handle.createUpdate("INSERT INTO comments(productId, userId, userName, content, createdAt) VALUES(:productId, :userId, :userName, :content, NOW())")
                .bindBean(comment)
                .execute());
    }



}
