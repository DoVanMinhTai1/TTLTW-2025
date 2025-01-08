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
    public void insertAddress(Address address) {
        Jdbi jdbi = JDBIConect.get();
        jdbi.useHandle(handle -> handle.createUpdate("INSERT INTO address (userId,name, address, phone, origin, company) " +
                        "VALUES (:userId, :name, :address, :phone, :origin, :company)")
                .bind("userId", address.getUserId())
                .bind("name", address.getName())
                .bind("address", address.getAddress())
                .bind("phone", address.getPhone())
                .bind("origin", address.getOrigin())
                .bind("company", address.getCompany())
                .execute());
    }
    public void removeAddress(int addressId) {
        Jdbi jdbi = JDBIConect.get();
        jdbi.useHandle(handle -> handle.createUpdate("DELETE FROM address WHERE id = :addressId")
                .bind("addressId", addressId)
                .execute());
    }
    public void updateAddress(Address address) {
        Jdbi jdbi = JDBIConect.get();
        jdbi.useHandle(handle -> {
            handle.createUpdate("UPDATE address SET name = :name, address = :address, phone = :phone, " +
                            "origin = :origin, company = :company " +
                            "WHERE id = :id")
                    .bind("name", address.getName())
                    .bind("address", address.getAddress())
                    .bind("phone", address.getPhone())
                    .bind("origin", address.getOrigin())
                    .bind("company", address.getCompany())
                    .bind("id", address.getId()) // Điều kiện cập nhật
                    .execute();
        });
    }

}
