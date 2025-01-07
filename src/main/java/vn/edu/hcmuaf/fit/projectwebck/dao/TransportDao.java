package vn.edu.hcmuaf.fit.projectwebck.dao;

import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.fit.projectwebck.dao.db.JDBIConect;
import vn.edu.hcmuaf.fit.projectwebck.dao.model.Transport;

import java.util.List;

public class TransportDao {
    public List<Transport> getAll() {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle -> handle.createQuery("select * from transports")
                .mapToBean(Transport.class)
                .list());
    }
    public Transport getById(int id) {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle ->
                handle.createQuery("select * from transports where id = :id")
                        .bind("id", id)
                        .mapToBean(Transport.class)
                        .findOne()
                        .orElse(null)
        );
    }

}
