package vn.iotstar.configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String DEFAULT_SERVER = "localhost";
    private static final String DEFAULT_DB = "vidu1"; // Đổi theo tên DB của bạn
    private static final String DEFAULT_PORT = "1433";
    private static final String DEFAULT_INSTANCE = ""; // Ví dụ: MSSQLSERVER hoặc SQLEXPRESS
    private static final String DEFAULT_USER = "sa";
    private static final String DEFAULT_PASSWORD = "123456";

    public static Connection getConnection() throws SQLException {
        String serverName = getEnvOrDefault("DB_SERVER", DEFAULT_SERVER);
        String dbName = getEnvOrDefault("DB_NAME", DEFAULT_DB);
        String portNumber = getEnvOrDefault("DB_PORT", DEFAULT_PORT);
        String instance = getEnvOrDefault("DB_INSTANCE", DEFAULT_INSTANCE);
        String userID = getEnvOrDefault("DB_USER", DEFAULT_USER);
        String password = getEnvOrDefault("DB_PASSWORD", DEFAULT_PASSWORD);

        String url;
        if (instance == null || instance.trim().isEmpty()) {
            url = "jdbc:sqlserver://" + serverName + ":" + portNumber
                + ";databaseName=" + dbName
                + ";encrypt=true;trustServerCertificate=true";
        } else {
            url = "jdbc:sqlserver://" + serverName + "\\" + instance
                + ";databaseName=" + dbName
                + ";encrypt=true;trustServerCertificate=true";
        }

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("SQL Server JDBC Driver not found.", e);
        }

        return DriverManager.getConnection(url, userID, password);
    }

    private static String getEnvOrDefault(String key, String defaultVal) {
        String v = System.getenv(key);
        return (v == null || v.isEmpty()) ? defaultVal : v;
    }
}
