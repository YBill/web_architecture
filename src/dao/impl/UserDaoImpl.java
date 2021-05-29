package dao.impl;

import dao.BaseDao;
import dao.UserDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao {

    @Override
    public int register(String userName, String password) {
        final String sql = "INSERT INTO db_user.table_user(name, password) VALUES (?, ?)";
        List<Object> params = new ArrayList<>(2);
        params.add(userName);
        params.add(password);
        try {
            int result = executeUpdate(sql, params);
            if (result == 1) {
                return 0;
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return throwable.getErrorCode();
        } finally {
            close();
        }
        return -1;
    }

    @Override
    public int login(String userName, String password) {
        final String sql = "SELECT COUNT(*) FROM db_user.table_user WHERE name = ? AND password = ?";
        List<Object> params = new ArrayList<>(2);
        params.add(userName);
        params.add(password);
        try {
            ResultSet rs = executeQuery(sql, params);
            if (rs.next() && rs.getInt(1) > 0) {
                return 0;
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return throwable.getErrorCode();
        } finally {
            close();
        }
        return -1;
    }

    @Override
    public boolean isRegister(String userName) {
        final String sql = "SELECT COUNT(*) FROM db_user.table_user WHERE name = ?";
        List<Object> params = new ArrayList<>(1);
        params.add(userName);
        try {
            ResultSet rs = executeQuery(sql, params);
            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            close();
        }
        return false;
    }
}
