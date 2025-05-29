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

    // Lấy một bình luận theo ID
    public  Comment getCommentById(int commentId) {
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM comments WHERE id = :id")
                .bind("id", commentId)
                .mapToBean(Comment.class)
                .findOne()
                .orElse(null));
    }

    // Thêm bình luận mới
    public  void insertComment(Comment comment) {
        jdbi.useHandle(handle -> handle.createUpdate("INSERT INTO comments(productId, userId, userName, content, createdAt) VALUES(:productId, :userId, :userName, :content, NOW())")
                .bindBean(comment)
                .execute());
    }

    // Xóa bình luận theo ID
    public  void deleteComment(int commentId) {
        jdbi.useHandle(handle -> handle.createUpdate("DELETE FROM comments WHERE id = :id")
                .bind("id", commentId)
                .execute());
    }

    // Cập nhật nội dung bình luận, trả về true nếu thành công, false nếu thất bại
    public boolean updateComment(int commentId, String newContent) {
        int rowsAffected = jdbi.withHandle(handle ->
                handle.createUpdate("UPDATE comments SET content = :content WHERE id = :id")
                        .bind("id", commentId)
                        .bind("content", newContent)
                        .execute()
        );
        return rowsAffected > 0;
    }

}
