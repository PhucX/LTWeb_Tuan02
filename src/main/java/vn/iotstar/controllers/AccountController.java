package vn.iotstar.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.models.UserModel;
import vn.iotstar.utils.Constant;

@WebServlet("/account")
public class AccountController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute(Constant.SESSION_ACCOUNT) == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        // Lấy thông tin user từ session
        UserModel user = (UserModel) session.getAttribute(Constant.SESSION_ACCOUNT);
        // Đảm bảo key 'user' luôn tồn tại trong session (cho các controller khác dùng)
        session.setAttribute("user", user);
        req.setAttribute("user", user);
        req.getRequestDispatcher("/views/account.jsp").forward(req, resp);
    }
}
