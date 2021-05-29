package biz.impl;

import biz.UserBiz;
import com.alibaba.fastjson.JSONObject;
import dao.UserDao;
import dao.impl.UserDaoImpl;

public class UserBizImpl implements UserBiz {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public String register(String userName, String password) {
        if (userName == null || userName.equals(""))
            return jsonResult(2);
        if (password == null || password.equals(""))
            return jsonResult(3);

        boolean isRegister = userDao.isRegister(userName);
        if (isRegister) {
            return jsonResult(1);
        }

        int code = userDao.register(userName, password);
        return jsonResult(code);
    }

    @Override
    public String login(String userName, String password) {
        int code = userDao.login(userName, password);
        return jsonResult(code);
    }

    private String jsonResult(int code) {
        JSONObject obj = new JSONObject();
        obj.put("code", code);
        return obj.toJSONString();
    }

}
