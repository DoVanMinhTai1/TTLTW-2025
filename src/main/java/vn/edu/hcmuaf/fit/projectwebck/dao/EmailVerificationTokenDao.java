package vn.edu.hcmuaf.fit.projectwebck.dao;

import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.fit.projectwebck.dao.db.JDBIConect;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.EmailVerificationToken;

public class EmailVerificationTokenDao {
    public void insertToken(EmailVerificationToken token) {
        Jdbi jdbi = JDBIConect.get();
        jdbi.useTransaction(handle ->
                handle.createUpdate("INSERT INTO emailverificationtokens (email, token, createdAt, expiresAt, isUsed) VALUES (:email, :token, NOW(), DATE_ADD(NOW(), INTERVAL 1 HOUR), false)")
                        .bind("email", token.getEmail())
                        .bind("token", token.getToken())
                        .execute()
        );
    }

    public boolean verifyToken(String email, String token) {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT COUNT(*) FROM emailverificationtokens WHERE email = :email AND token = :token AND isUsed = false")
                        .bind("email", email)
                        .bind("token", token)
                        .mapTo(Long.class)
                        .one() > 0
        );
    }

    public EmailVerificationToken findToken(String token) {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM emailverificationtokens WHERE token = :token AND isUsed = false AND expiresAt > NOW()")
                        .bind("token", token)
                        .mapTo(EmailVerificationToken.class)
                        .findOne()
                        .orElse(null)
        );
    }

    public void markAsUsed(String email, String token) {
        Jdbi jdbi = JDBIConect.get();
        jdbi.useTransaction(handle ->
                handle.createUpdate("UPDATE emailverificationtokens SET isUsed = true WHERE token = :token")
                        .bind("email", email)
                        .bind("token", token)
                        .execute()
        );
    }
}
