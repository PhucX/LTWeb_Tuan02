package vn.iotstar.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import vn.iotstar.services.UserService;
import vn.iotstar.services.impl.UserServiceImpl;

public class ForgotPasswordController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService = new UserServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("reset".equals(action)) {
            // Hiển thị form đặt lại mật khẩu
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("resetEmail");
            
            if (email != null) {
                request.setAttribute("email", email);
                request.getRequestDispatcher("/views/reset-password.jsp").forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/forgot-password");
            }
        } else {
            // Hiển thị form quên mật khẩu
            request.getRequestDispatcher("/views/forgot-password.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String action = request.getParameter("action");
        
        if ("check-email".equals(action)) {
            handleCheckEmail(request, response);
        } else if ("reset-password".equals(action)) {
            handleResetPassword(request, response);
        }
    }
    
    private void handleCheckEmail(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String email = request.getParameter("email");
        
        if (email == null || email.trim().isEmpty()) {
            request.setAttribute("error", "Vui lòng nhập email");
            request.getRequestDispatcher("/views/forgot-password.jsp").forward(request, response);
            return;
        }
        
        // Kiểm tra email có tồn tại trong hệ thống không
        if (userService.forgotPassword(email)) {
            // Email tồn tại, chuyển đến form đặt lại mật khẩu
            HttpSession session = request.getSession();
            session.setAttribute("resetEmail", email);
            response.sendRedirect(request.getContextPath() + "/forgot-password?action=reset");
        } else {
            // Email không tồn tại
            request.setAttribute("error", "Email không tồn tại trong hệ thống");
            request.getRequestDispatcher("/views/forgot-password.jsp").forward(request, response);
        }
    }
    
    private void handleResetPassword(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("resetEmail");
        
        if (email == null) {
            response.sendRedirect(request.getContextPath() + "/forgot-password");
            return;
        }
        
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        
        if (newPassword == null || newPassword.trim().isEmpty()) {
            request.setAttribute("error", "Vui lòng nhập mật khẩu mới");
            request.setAttribute("email", email);
            request.getRequestDispatcher("/views/reset-password.jsp").forward(request, response);
            return;
        }
        
        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("error", "Mật khẩu xác nhận không khớp");
            request.setAttribute("email", email);
            request.getRequestDispatcher("/views/reset-password.jsp").forward(request, response);
            return;
        }
        
        if (newPassword.length() < 6) {
            request.setAttribute("error", "Mật khẩu phải có ít nhất 6 ký tự");
            request.setAttribute("email", email);
            request.getRequestDispatcher("/views/reset-password.jsp").forward(request, response);
            return;
        }
        
        // Cập nhật mật khẩu mới
        if (userService.resetPassword(email, newPassword)) {
            // Xóa session
            session.removeAttribute("resetEmail");
            request.setAttribute("success", "Đặt lại mật khẩu thành công! Vui lòng đăng nhập với mật khẩu mới.");
            request.getRequestDispatcher("/views/login.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Có lỗi xảy ra khi đặt lại mật khẩu");
            request.setAttribute("email", email);
            request.getRequestDispatcher("/views/reset-password.jsp").forward(request, response);
        }
    }
}
