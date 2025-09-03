package vn.iotstar.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.iotstar.models.Category;
import vn.iotstar.services.CategoryService;
import vn.iotstar.services.impl.CategoryServiceImpl;


public class CategoryDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final CategoryService categoryService = new CategoryServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idParam = req.getParameter("id");
		if (idParam != null) {
			int id = Integer.parseInt(idParam);
			categoryService.delete(id);
		}
		resp.sendRedirect(req.getContextPath() + "/admin/categories");
	}
}
