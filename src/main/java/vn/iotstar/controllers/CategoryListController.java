package vn.iotstar.controllers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.iotstar.models.Category;
import vn.iotstar.services.CategoryService;
import vn.iotstar.services.impl.CategoryServiceImpl;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/admin/categories")

public class CategoryListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final CategoryService categoryService = new CategoryServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String q = req.getParameter("q");
		List<Category> categories = (q == null || q.trim().isEmpty())
				? categoryService.getAll()
				: categoryService.search(q);
		req.setAttribute("categories", categories);
		req.getRequestDispatcher("/admin/list-category.jsp").forward(req, resp);
	}
}
