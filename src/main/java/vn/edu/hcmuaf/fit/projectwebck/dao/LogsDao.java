package vn.edu.hcmuaf.fit.projectwebck.dao;

import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.fit.projectwebck.dao.db.JDBIConect;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Log;

import java.util.List;

public class LogsDao {
    public List<Log> getAllLogs() {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM logs")
                .mapToBean(Log.class)
                .list());
    }

    public Log getByLogId(int logId) {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM logs WHERE logId = :logId")
                .bind("logId", logId)
                .mapToBean(Log.class)
                .findOne()
                .orElse(null));
    }
    public void insertLog(Log log) {
        Jdbi jdbi = JDBIConect.get();
        jdbi.useHandle(handle -> handle.createUpdate("INSERT INTO logs (label, userId, time, location, beforeData, afterData) " +
                        "VALUES (:label, :userId, :time, :location, :beforeData, :afterData)")
                .bind("label", log.getLabel())
                .bind("userId", log.getUserId())
                .bind("time", log.getTime()) // LocalDateTime phải được ánh xạ đúng định dạng
                .bind("location", log.getLocation())
                .bind("beforeData", log.getBeforeData())
                .bind("afterData", log.getAfterData())
                .execute());
    }



}
