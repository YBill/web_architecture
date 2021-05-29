package servlet;

import biz.UserBiz;
import biz.impl.UserBizImpl;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * http://localhost:8080/web_architecture/UserServlet?type=register&name=ywb&password=123
 */

@WebServlet(urlPatterns = "/UserServlet")
public class UserServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Content-Type", "text/html;charset=UTF-8");

        String type = req.getParameter("type");
        String userName = req.getParameter("name");
        String password = req.getParameter("password");

        System.out.println(type);
        System.out.println(userName);
        System.out.println(password);

        // return json to client
        if ("register".equals(type)) {
            UserBiz userBiz = new UserBizImpl();
            String result = userBiz.register(userName, password);
            PrintWriter writer = resp.getWriter();
            writer.write(result);
        } else if ("login".equals(type)) {
            UserBiz userBiz = new UserBizImpl();
            String result = userBiz.login(userName, password);
            PrintWriter writer = resp.getWriter();
            writer.write(result);
        }

        // handler jsp
        if ("注册".equals(type)) {
            UserBiz userBiz = new UserBizImpl();
            String result = userBiz.register(userName, password);
            if (resolveJson(result)) {
                req.setAttribute("result", "注册成功：" + userName);
                req.setAttribute("show", true);
            } else {
                req.setAttribute("result", "注册失败");
                req.setAttribute("show", false);
            }
            req.getRequestDispatcher("result.jsp").forward(req, resp);
        } else if ("登陆".equals(type)) {
            UserBiz userBiz = new UserBizImpl();
            String result = userBiz.login(userName, password);
            if (resolveJson(result)) {
                req.setAttribute("result", "登陆成功：" + userName);
                req.setAttribute("show", false);
            } else {
                req.setAttribute("result", "登陆失败");
                req.setAttribute("show", false);
            }
            req.getRequestDispatcher("result.jsp").forward(req, resp);
        }

    }

    private boolean resolveJson(String json) {
        JSONObject obj = JSONObject.parseObject(json);
        int code = obj.getIntValue("code");
        if (code == 0)
            return true;
        return false;
    }

}
