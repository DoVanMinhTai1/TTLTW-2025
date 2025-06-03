package vn.edu.hcmuaf.fit.projectwebck.dao;

import vn.edu.hcmuaf.fit.projectwebck.dao.db.PreConnect;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.ReturnRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static vn.edu.hcmuaf.fit.projectwebck.dao.db.PreConnect.getConnection;

public class ReturnRequestDAO {
    PreConnect conn = new PreConnect();

    public ReturnRequestDAO(PreConnect conn) {
        this.conn = conn;
    }

    public ReturnRequestDAO() {
    }

    public static void insertRequest(int orderId, int userId, String reason, List<String> imagePaths) {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO return_requests (order_id, user_id, reason) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, orderId);
            stmt.setInt(2, userId);
            stmt.setString(3, reason);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            int returnRequestId = 0;
            if (rs.next()) {
                returnRequestId = rs.getInt(1);
            }

            String insertImage = "INSERT INTO return_images (return_request_id, image_path) VALUES (?, ?)";
            PreparedStatement imgStmt = conn.prepareStatement(insertImage);
            for (String path : imagePaths) {
                imgStmt.setInt(1, returnRequestId);
                imgStmt.setString(2, path);
                imgStmt.addBatch();
            }
            imgStmt.executeBatch();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi lưu yêu cầu đổi/trả hàng.");
        }
    }

    public List<ReturnRequest> getAllReturn() {
        List<ReturnRequest> returnRequests = new ArrayList<>();

        try (Connection conn = getConnection()) {
            String sql = "SELECT rr.id, rr.order_id, rr.user_id, rr.reason, rr.admin_response, rr.status " +
                    "FROM return_requests rr ORDER BY rr.id DESC";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int orderId = rs.getInt("order_id");
                int userId = rs.getInt("user_id");
                String reason = rs.getString("reason");
                String adminResponse = rs.getString("admin_response");
                String status = rs.getString("status");

                // Lấy ảnh liên quan đến request
                List<String> imagePaths = new ArrayList<>();
                String imgSql = "SELECT image_path FROM return_images WHERE return_request_id = ?";
                PreparedStatement imgStmt = conn.prepareStatement(imgSql);
                imgStmt.setInt(1, id);
                ResultSet imgRs = imgStmt.executeQuery();
                while (imgRs.next()) {
                    imagePaths.add(imgRs.getString("image_path"));
                }

                ReturnRequest request = new ReturnRequest(id, orderId, userId, reason, imagePaths, adminResponse, status);

                returnRequests.add(request);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi truy xuất yêu cầu đổi/trả hàng.");
        }

        return returnRequests;
    }


    public void updateAdminResponse(int returnId, String response, String status) {
        try (Connection conn = getConnection()) {
            String sql = "UPDATE return_requests SET admin_response = ?, status = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, response);
            stmt.setString(2, status);
            stmt.setInt(3, returnId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi cập nhật đơn đổi trả");
        }
    }

}
