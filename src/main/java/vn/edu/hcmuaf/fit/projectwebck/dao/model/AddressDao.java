package vn.edu.hcmuaf.fit.projectwebck.dao.model;

import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.fit.projectwebck.dao.db.JDBIConect;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressDao {
    static Map<Integer, Address> data = new HashMap<>();

    public List<Address> getAllById(int userId) {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle -> handle.createQuery("select * from address where userId = :userId")
                .bind("userId", userId)
                .mapToBean(Address.class)
                .list());
    }
    public Address getByIdOrigin(int userId) {
        Jdbi jdbi = JDBIConect.get();
        return jdbi.withHandle(handle -> handle.createQuery("select * from address where userId = :userId AND origin = :origin")
                .bind("userId", userId)
                .bind("origin", 1)
                .mapToBean(Address.class)
                .findOne() // Tìm một đối tượng duy nhất
                .orElse(null)); // Trả về null nếu không tìm thấy
    }

}
