package vn.iotstar.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import vn.iotstar.models.Category;
import vn.iotstar.services.CategoryService;
import vn.iotstar.services.impl.CategoryServiceImpl;

import jakarta.servlet.annotation.WebServlet;

@WebServlet("/admin/category/add")
@MultipartConfig
public class CategoryAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final CategoryService categoryService = new CategoryServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/admin/add-category.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String catename = req.getParameter("catename");
		if (catename != null) catename = catename.trim();
		String icon = null;
		
	       Part filePart = req.getPart("icon");
	       if (filePart != null && filePart.getSize() > 0) {
		       // Lưu vào thư mục dự án để truy cập qua URL
		       String uploadDir = req.getServletContext().getRealPath(vn.iotstar.utils.Constant.UPLOAD_ROOT_DIR);
		       String fileName = filePart.getSubmittedFileName();
		       try (InputStream in = filePart.getInputStream()) {
			       icon = vn.iotstar.controllers._FileUtil.save(in, fileName, uploadDir);
		       }
	       }
		
		if (catename == null || catename.isEmpty()) {
			req.setAttribute("error", "Vui lòng nhập tên danh mục");
			req.getRequestDispatcher("/admin/add-category.jsp").forward(req, resp);
			return;
		}
		
		Category c = new Category();
		c.setCatename(catename);
		c.setIcon(icon);
		String cateidParam = req.getParameter("cateid");
		if (cateidParam != null && !cateidParam.isEmpty()) {
			c.setCateid(Integer.parseInt(cateidParam));
		}
		try {
			categoryService.insert(c);
		} catch (Exception ex) {
			req.setAttribute("error", "Có lỗi khi lưu danh mục: " + ex.getMessage());
			req.getRequestDispatcher("/admin/add-category.jsp").forward(req, resp);
			return;
		}
		resp.sendRedirect(req.getContextPath() + "/admin/categories");
	}
}
