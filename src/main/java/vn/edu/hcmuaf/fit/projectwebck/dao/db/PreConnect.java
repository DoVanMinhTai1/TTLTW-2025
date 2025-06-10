package vn.edu.hcmuaf.fit.projectwebck.dao.db;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PreConnect {

    public static Connection getConnection() throws SQLException {
        try {
            // Ưu tiên đọc từ biến môi trường của Render
            String databaseUrl = System.getenv("DATABASE_URL");

            if (databaseUrl != null && !databaseUrl.isEmpty()) {
                // ----- CHẠY TRÊN RENDER -----
                URI dbUri = new URI(databaseUrl);

                String username = dbUri.getUserInfo().split(":")[0];
                String password = dbUri.getUserInfo().split(":")[1];
                String jdbcUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

                // Đăng ký driver của PostgreSQL
                Class.forName("org.postgresql.Driver");

                return DriverManager.getConnection(jdbcUrl, username, password);

            } else {
                // ----- CHẠY LOCAL (fallback) -----
                // Bạn cần cập nhật DBProperties để trỏ tới PostgreSQL local nếu muốn test
                String localUrl = "jdbc:postgresql://" + DBProperties.host() + ":" + DBProperties.port() + "/" + DBProperties.dbname();
                String localUser = DBProperties.username();
                String localPassword = DBProperties.password();

                Class.forName("org.postgresql.Driver");

                return DriverManager.getConnection(localUrl, localUser, localPassword);
            }
        } catch (URISyntaxException | ClassNotFoundException e) {
            System.err.println("Database connection configuration error: " + e.getMessage());
            throw new SQLException("Configuration error", e);
        }
    }

    // Main method để test
}