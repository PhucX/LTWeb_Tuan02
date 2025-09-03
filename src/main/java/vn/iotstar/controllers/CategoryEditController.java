package vn.iotstar.controllers;

import java.io.IOException;
import java.io.InputStream;

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


@MultipartConfig
public class CategoryEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final CategoryService categoryService = new CategoryServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idParam = req.getParameter("id");
		if (idParam == null) {
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
			return;
		}
		int id = Integer.parseInt(idParam);
		Category c = categoryService.get(id);
		if (c == null) {
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
			return;
		}
		req.setAttribute("category", c);
		req.getRequestDispatcher("/admin/edit-category.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(req.getParameter("cateid"));
		String catename = req.getParameter("catename");
		
		Category c = categoryService.get(id);
		if (c == null) {
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
			return;
		}
		c.setCatename(catename);
		
		// handle icon upload
		Part filePart = req.getPart("icon");
		if (filePart != null && filePart.getSize() > 0) {
			String uploadDir = req.getServletContext().getRealPath("/uploads/icons");
			try (InputStream in = filePart.getInputStream()) {
				categoryService.updateIcon(id, in, filePart.getSubmittedFileName(), uploadDir);
			}
		}
		
		categoryService.edit(c);
		resp.sendRedirect(req.getContextPath() + "/admin/categories");
	}
}
