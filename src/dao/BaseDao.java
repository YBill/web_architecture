package dao;

import manager.HikariPoolManager;
import utils.CloseHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BaseDao {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public ResultSet executeQuery(String sql, List<Object> params) throws SQLException {
        connection = HikariPoolManager.INSTANCE.getConnection();
        preparedStatement = connection.prepareStatement(sql);
        addParams(preparedStatement, params);
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public int executeUpdate(String sql, List<Object> params) throws SQLException {
        connection = HikariPoolManager.INSTANCE.getConnection();
        preparedStatement = connection.prepareStatement(sql);
        addParams(preparedStatement, params);
        return preparedStatement.executeUpdate();
    }

    private void addParams(PreparedStatement pstmt, List<Object> params) throws SQLException {
        if (params == null)
            return;

        for (int i = 0; i < params.size(); i++) {
            pstmt.setObject(i + 1, params.get(i));
        }
    }

    public void close() {
        CloseHelper.close(resultSet);
        CloseHelper.close(preparedStatement);
        CloseHelper.close(connection);
    }

}
