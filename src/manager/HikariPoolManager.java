package manager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public enum HikariPoolManager {

    INSTANCE;

    public static void main(String[] args) {
        Connection connection = HikariPoolManager.INSTANCE.getConnection();
        System.out.println("connection:" + connection);

    }

    private final Logger logger = LoggerFactory.getLogger(HikariPoolManager.class);

    private final HikariDataSource mDataSource;

    private HikariPoolManager() {
        logger.info("start connect db by HikariCP");
        HikariConfig config = new HikariConfig("/hikaricp.properties");
        /*HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/db_user");
        config.setUsername("root");
        config.setPassword("123456");*/
        mDataSource = new HikariDataSource(config);
    }

    public Connection getConnection() {
        try {
            return mDataSource.getConnection();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            close();
        }
        return null;
    }

    public boolean close() {
        if (mDataSource != null)
            mDataSource.close();
        return true;
    }

}
