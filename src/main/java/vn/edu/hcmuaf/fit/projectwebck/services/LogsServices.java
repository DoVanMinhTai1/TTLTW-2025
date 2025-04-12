package vn.edu.hcmuaf.fit.projectwebck.services;

import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.fit.projectwebck.dao.LogsDao;
import vn.edu.hcmuaf.fit.projectwebck.dao.db.JDBIConect;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Log;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class LogsServices {
    LogsDao logsDao = new LogsDao();

    public void alert(String label,Integer userID, String location, String beforeData, String afterData) {
      Log log = new Log("(Alert) "+label, userID,LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), location, beforeData, afterData);
      logsDao.insertLog(log);
    }

    public void infor(String label,Integer userID, String location, String beforeData, String afterData) {
        Log log = new Log("(Infor) "+label, userID, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), location, beforeData, afterData);
        logsDao.insertLog(log);
    }

    public void warning(String label,Integer userID, String location, String beforeData, String afterData) {
        Log log = new Log("(Warning) "+label, userID, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), location, beforeData, afterData);
        logsDao.insertLog(log);
    }

    public void danger(String label,Integer userID, String location, String beforeData, String afterData) {
        Log log = new Log("(Danger) "+label, userID, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), location, beforeData, afterData);
        logsDao.insertLog(log);
    }
    public List<Log> getAllLogs() {
      return logsDao.getAllLogs();
    }
    public Log getByLogId(int logId) {
       return logsDao.getByLogId(logId);
    }
}
