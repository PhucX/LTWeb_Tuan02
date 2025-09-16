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
import vn.iotstar.utils.RoleRedirectUtil;

@WebServlet("/manager/home")
public class ManagerHomeController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        
        // Kiểm tra xem user đã đăng nhập chưa
        if (session == null || session.getAttribute(Constant.SESSION_ACCOUNT) == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        
        UserModel user = (UserModel) session.getAttribute(Constant.SESSION_ACCOUNT);
        
        // Kiểm tra quyền manager
        if (!RoleRedirectUtil.isManager(user)) {
            // Nếu không phải manager, redirect về trang phù hợp với role
            String redirectUrl = RoleRedirectUtil.getRedirectUrl(user, req.getContextPath());
            resp.sendRedirect(redirectUrl);
            return;
        }
        
        // Forward tới trang manager home
        req.getRequestDispatcher("/manager/home.jsp").forward(req, resp);
    }
}
