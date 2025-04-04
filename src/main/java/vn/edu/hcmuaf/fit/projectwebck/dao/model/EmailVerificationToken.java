package vn.edu.hcmuaf.fit.projectwebck.dao.model;

import java.time.LocalDateTime;

public class EmailVerificationToken {
    private int id;
    private String token;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private boolean isUsed;
    public EmailVerificationToken(String token, String email) {
        this.token = token;
        this.email = email;
    }




}
