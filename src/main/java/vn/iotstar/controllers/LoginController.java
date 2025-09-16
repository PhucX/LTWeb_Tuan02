package vn.iotstar.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.models.UserModel;
import vn.iotstar.services.UserService;
import vn.iotstar.services.impl.UserServiceImpl;
import vn.iotstar.utils.Constant;
import vn.iotstar.utils.RoleRedirectUtil;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute(Constant.SESSION_ACCOUNT) != null) {
            UserModel user = (UserModel) session.getAttribute(Constant.SESSION_ACCOUNT);
            String redirectUrl = RoleRedirectUtil.getRedirectUrl(user, req.getContextPath());
            resp.sendRedirect(redirectUrl);
            return;
        }

        // Check remember cookie
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (Constant.COOKIE_REMEMBER.equals(c.getName()) && c.getValue() != null && !c.getValue().isEmpty()) {
                    UserModel rememberedUser = userService.findByUserName(c.getValue());
                    if (rememberedUser != null) {
                        HttpSession newSession = req.getSession(true);
                        newSession.setAttribute(Constant.SESSION_ACCOUNT, rememberedUser);
                        String redirectUrl = RoleRedirectUtil.getRedirectUrl(rememberedUser, req.getContextPath());
                        resp.sendRedirect(redirectUrl);
                        return;
                    }
                }
            }
        }

        req.getRequestDispatcher(Constant.Path.LOGIN).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String remember = req.getParameter("rememberMe");

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            req.setAttribute("alert", "Tài khoản hoặc mật khẩu không được rỗng");
            req.getRequestDispatcher(Constant.Path.LOGIN).forward(req, resp);
            return;
        }

        UserModel user = userService.login(username, password);
        if (user != null) {
            HttpSession session = req.getSession(true);
            session.setAttribute(Constant.SESSION_ACCOUNT, user);

            if (remember != null && ("true".equalsIgnoreCase(remember) || "on".equalsIgnoreCase(remember))) {
                Cookie ck = new Cookie(Constant.COOKIE_REMEMBER, username);
                ck.setMaxAge(30 * 60);
                ck.setPath(req.getContextPath().isEmpty() ? "/" : req.getContextPath());
                resp.addCookie(ck);
            }

            String redirectUrl = RoleRedirectUtil.getRedirectUrl(user, req.getContextPath());
            resp.sendRedirect(redirectUrl);
        } else {
            req.setAttribute("alert", "Tài khoản hoặc mật khẩu không đúng");
            req.getRequestDispatcher(Constant.Path.LOGIN).forward(req, resp);
        }
    }
}


