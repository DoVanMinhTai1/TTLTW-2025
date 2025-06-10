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

    // Trong file vn.edu.hcmuaf.fit.projectwebck.dao.db.JDBIConect.java

    private static void makeConnect() throws URISyntaxException, SQLException {
        // Lấy chuỗi kết nối từ biến môi trường của Render
        String databaseUrl = System.getenv("DATABASE_URL");

        if (databaseUrl == null || databaseUrl.isEmpty()) {
            throw new RuntimeException("FATAL: DATABASE_URL environment variable is not set on Render.");
        }

        // Phân tích chuỗi URI mà Render cung cấp
        URI dbUri = new URI(databaseUrl);

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];

        // === ĐOẠN CODE SỬA LỖI NẰM Ở ĐÂY ===
        int port = dbUri.getPort();
        if (port == -1) {
            port = 5432; // Sử dụng port mặc định của PostgreSQL nếu không tìm thấy
        }
        // ===================================

        // Xây dựng lại URL với port đã được kiểm tra
        String jdbcUrl = "jdbc:postgresql://" + dbUri.getHost() + ":" + port + dbUri.getPath();

        System.out.println("Attempting to connect to: " + jdbcUrl); // Dòng này giúp debug

        // Sử dụng DataSource của PostgreSQL
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl(jdbcUrl);
        dataSource.setUser(username);
        dataSource.setPassword(password);

        // Bật SSL, rất quan trọng trên Render
        dataSource.setSslMode("require");

        jdbi = Jdbi.create(dataSource);
        System.out.println("Database connection initialized successfully!");
    }

}