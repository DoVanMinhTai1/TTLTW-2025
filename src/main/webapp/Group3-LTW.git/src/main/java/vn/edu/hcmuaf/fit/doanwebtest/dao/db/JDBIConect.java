package vn.edu.hcmuaf.fit.doanwebtest.dao.db;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.fit.doanwebtest.dao.model.Product;

import java.sql.SQLException;
import java.util.List;

public class JDBIConect {
    private static Jdbi jdbi;

    static String url = "jdbc:mysql://" + DBProperties.host() + ":" + DBProperties.port() + "/" + DBProperties.dbname() + "?" + DBProperties.option();

    public static Jdbi get() {
        if (jdbi == null) makeConnect();
        return jdbi;
    }

    private static void makeConnect() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl(url);
        dataSource.setUser(DBProperties.username());
        dataSource.setPassword(DBProperties.password());
        try {
            dataSource.setUseCompression(true);
            dataSource.setAutoReconnect(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        jdbi = Jdbi.create(dataSource);
    }

    public static void main(String[] args) {
        Jdbi jdbi = get();
        List<Product> products = jdbi.withHandle(handle -> handle.createQuery("select * from products")
                .mapToBean(Product.class)
                .list());
        System.out.println(products);
    }
}
