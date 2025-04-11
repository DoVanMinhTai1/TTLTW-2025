package vn.edu.hcmuaf.fit.projectwebck.dao.db;

import java.sql.*;

public class PreConnect {
    private static final String URL = "jdbc:mysql://" + DBProperties.host() + ":" + DBProperties.port() + "/" +
            DBProperties.dbname() + "?" + DBProperties.option();
    private static final String USERNAME = DBProperties.username();
    private static final String PASSWORD = DBProperties.password();

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static void main(String[] args) throws SQLException {
        PreConnect p = new PreConnect();
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        PreparedStatement pstmt = conn.prepareStatement("select * from products");
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()) {
            System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
        }
    }
}
