package vn.edu.hcmuaf.fit.projectwebck.dao.model;

import java.time.LocalDateTime;

public class EmailVerificationToken {
    private int id;
    private String token;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private boolean isUsed;
    public EmailVerificationToken(String email, String token) {
        this.email = email;
        this.token = token;
    }
    public EmailVerificationToken() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }
    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }
    public boolean isUsed() {
        return isUsed;
    }
    public void setUsed(boolean used) {
        isUsed = used;
    }
}
