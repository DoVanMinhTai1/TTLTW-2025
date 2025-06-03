package vn.edu.hcmuaf.fit.projectwebck.dao.model;

import java.util.List;

public class ReturnRequest {
    private int id;
    private int orderId;
    private int userId;
    private String reason;
    private List<String> imagePaths;
    private String adminResponse;
    private String status;

    public ReturnRequest(int id, int orderId, int userId, String reason, List<String> imagePaths, String adminResponse, String status) {
        this.id = id;
        this.orderId = orderId;
        this.userId = userId;
        this.reason = reason;
        this.imagePaths = imagePaths;
        this.adminResponse = adminResponse;
        this.status = status;
    }

    public ReturnRequest() {
    }

    @Override
    public String toString() {
        return "ReturnRequest{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", userId=" + userId +
                ", reason='" + reason + '\'' +
                ", imagePaths=" + imagePaths +
                ", adminResponse='" + adminResponse + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<String> getImagePaths() {
        return imagePaths;
    }

    public void setImagePaths(List<String> imagePaths) {
        this.imagePaths = imagePaths;
    }

    public String getAdminResponse() {
        return adminResponse;
    }

    public void setAdminResponse(String adminResponse) {
        this.adminResponse = adminResponse;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
