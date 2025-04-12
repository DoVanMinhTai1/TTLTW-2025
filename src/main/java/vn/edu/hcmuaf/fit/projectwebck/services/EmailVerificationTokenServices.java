package vn.edu.hcmuaf.fit.projectwebck.services;



import vn.edu.hcmuaf.fit.projectwebck.dao.EmailVerificationTokenDao;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.EmailVerificationToken;

import java.util.List;

public class EmailVerificationTokenServices {
    EmailVerificationTokenDao emailVerificationTokenDao = new EmailVerificationTokenDao();

    public void insertToken(EmailVerificationToken token){emailVerificationTokenDao.insertToken(token);}
    public EmailVerificationToken findToken(String token){return emailVerificationTokenDao.findToken(token);}
    public void markAsUsed(String email, String token) {emailVerificationTokenDao.markAsUsed(email,token);}
    public boolean verifyToken(String email, String token) {return emailVerificationTokenDao.verifyToken(email, token);}
}
