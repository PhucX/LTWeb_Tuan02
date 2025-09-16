package vn.iotstar.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import vn.iotstar.configs.DBConnection;
import vn.iotstar.models.UserModel;

@WebServlet("/profile")
@MultipartConfig
public class ProfileController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        UserModel user = (UserModel) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        // Lấy lại thông tin user từ DB (nếu cần)
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT fullname, phone, image FROM users WHERE id = ?");
            ps.setInt(1, user.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user.setFullName(rs.getString("fullname"));
                user.setPhone(rs.getString("phone"));
                user.setImage(rs.getString("image"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        req.setAttribute("user", user);
        req.getRequestDispatcher("/views/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession(false);
        UserModel user = (UserModel) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        String fullname = req.getParameter("fullname");
        String phone = req.getParameter("phone");
        Part imagePart = req.getPart("image");
        String imageFileName = user.getImage();
        if (imagePart != null && imagePart.getSize() > 0) {
            String uploadDir = req.getServletContext().getRealPath("/uploads");
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();
            String submittedFileName = imagePart.getSubmittedFileName();
            File file = new File(uploadDir, submittedFileName);
            try (InputStream in = imagePart.getInputStream()) {
                Files.copy(in, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                imageFileName = submittedFileName;
            }
        }
        boolean success = false;
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("UPDATE users SET fullname=?, phone=?, image=? WHERE id=?");
            ps.setString(1, fullname);
            ps.setString(2, phone);
            ps.setString(3, imageFileName);
            ps.setInt(4, user.getId());
            int rows = ps.executeUpdate();
            if (rows > 0) success = true;
            // Cập nhật lại user trong session
            user.setFullName(fullname);
            user.setPhone(phone);
            user.setImage(imageFileName);
            session.setAttribute("user", user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        req.setAttribute("user", user);
        req.setAttribute("message", success ? "Cập nhật thành công!" : "Cập nhật thất bại!");
        req.getRequestDispatcher("/views/profile.jsp").forward(req, resp);
    }
}
