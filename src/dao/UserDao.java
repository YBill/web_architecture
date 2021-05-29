package dao;

public interface UserDao {

    int register(String userName, String password);

    int login(String userName, String password);

    boolean isRegister(String userName);

}
