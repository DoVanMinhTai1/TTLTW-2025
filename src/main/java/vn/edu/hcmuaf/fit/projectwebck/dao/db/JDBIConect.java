package vn.edu.hcmuaf.fit.projectwebck.dao.db;

import org.jdbi.v3.core.Jdbi;
import org.postgresql.ds.PGSimpleDataSource; // SỬ DỤNG DATASOURCE CỦA POSTGRESQL

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

public class JDBIConect {
    private static Jdbi jdbi;

    public static Jdbi get() {
        if (jdbi == null) {
            try {
                makeConnect();
            } catch (URISyntaxException | SQLException e) {
                // Log lỗi ra một cách cẩn thận thay vì chỉ throw
                // Trong môi trường sản xuất, bạn nên dùng một thư viện logging như SLF4J
                System.err.println("Failed to connect to the database: " + e.getMessage());
                throw new RuntimeException("Database connection failed", e);
            }
        }
        return jdbi;
    }

    private static void makeConnect() throws URISyntaxException, SQLException {
        // Ưu tiên đọc từ biến môi trường của Render
        String databaseUrl = System.getenv("DATABASE_URL");

        if (databaseUrl != null && !databaseUrl.isEmpty()) {
            // ----- CHẠY TRÊN RENDER -----
            URI dbUri = new URI(databaseUrl);

            String username = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            // Xây dựng lại URL theo chuẩn JDBC cho PostgreSQL
            String jdbcUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

            PGSimpleDataSource dataSource = new PGSimpleDataSource();
            dataSource.setUrl(jdbcUrl);
            dataSource.setUser(username);
            dataSource.setPassword(password);

            jdbi = Jdbi.create(dataSource);

        } else {
            // ----- CHẠY LOCAL (fallback) -----
            // Giữ lại logic cũ để bạn có thể chạy dưới máy tính cá nhân
            PGSimpleDataSource dataSource = new PGSimpleDataSource();
            dataSource.setUrl("jdbc:postgresql://" + DBProperties.host() + ":" + DBProperties.port() + "/" + DBProperties.dbname());
            dataSource.setUser(DBProperties.username());
            dataSource.setPassword(DBProperties.password());

            jdbi = Jdbi.create(dataSource);
        }
    }
    // Main method để test
}