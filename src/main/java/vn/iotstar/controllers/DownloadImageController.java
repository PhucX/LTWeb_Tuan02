package vn.iotstar.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.utils.Constant;

import jakarta.servlet.annotation.WebServlet;

@WebServlet("/download-image")
public class DownloadImageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fname = req.getParameter("fname");
		if (fname == null || fname.isBlank()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing fname");
			return;
		}
		
		// Prevent path traversal
		if (fname.contains("..") || fname.contains("/") || fname.contains("\\")) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid file name");
			return;
		}
		
	       // Lấy đường dẫn thực tế trong dự án
	       String uploadDir = getServletContext().getRealPath(Constant.UPLOAD_ROOT_DIR);
	       Path filePath = Paths.get(uploadDir, fname);
	       if (!Files.exists(filePath) || Files.isDirectory(filePath)) {
		       resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		       return;
	       }
	       String mime = Files.probeContentType(filePath);
	       if (mime == null) {
		       mime = "application/octet-stream";
	       }
	       resp.setContentType(mime);
	       resp.setHeader("Cache-Control", "public, max-age=86400");
	       resp.setContentLengthLong(Files.size(filePath));
	       try (FileInputStream in = new FileInputStream(filePath.toFile()); OutputStream out = resp.getOutputStream()) {
		       byte[] buffer = new byte[8192];
		       int len;
		       while ((len = in.read(buffer)) != -1) {
			       out.write(buffer, 0, len);
		       }
	       }
	}
}
