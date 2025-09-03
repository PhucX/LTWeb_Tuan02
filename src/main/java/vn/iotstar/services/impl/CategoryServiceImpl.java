package vn.iotstar.services.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import vn.iotstar.dao.CategoryDAO;
import vn.iotstar.dao.impl.CategoryDaoImpl;
import vn.iotstar.models.Category;
import vn.iotstar.services.CategoryService;

public class CategoryServiceImpl implements CategoryService {
	private final CategoryDAO categoryDAO = new CategoryDaoImpl();
	
	@Override
	public void insert(Category category) {
		categoryDAO.insert(category);
	}
	
	@Override
	public void edit(Category category) {
		categoryDAO.edit(category);
	}
	
	@Override
	public void delete(int cateid) {
		Category existing = categoryDAO.get(cateid);
		if (existing != null && existing.getIcon() != null) {
			deleteFileIfExists(existing.getIcon());
		}
		categoryDAO.delete(cateid);
	}
	
	@Override
	public Category get(int cateid) {
		return categoryDAO.get(cateid);
	}
	
	@Override
	public Category get(String catename) {
		return categoryDAO.get(catename);
	}
	
	@Override
	public List<Category> getAll() {
		return categoryDAO.getAll();
	}
	
	@Override
	public List<Category> search(String keyword) {
		return categoryDAO.search(keyword);
	}
	
	@Override
	public boolean updateIcon(int cateid, InputStream newIconContent, String newIconFileName, String uploadDirRealPath) {
		Category cat = categoryDAO.get(cateid);
		if (cat == null) return false;
		
		// Ensure upload dir exists
		if (uploadDirRealPath == null || uploadDirRealPath.trim().isEmpty()) return false;
		Path uploadDir = Paths.get(uploadDirRealPath);
		try {
			Files.createDirectories(uploadDir);
		} catch (IOException e) {
			return false;
		}
		
		// Generate unique file name to avoid collisions
		String safeName = (newIconFileName == null || newIconFileName.isEmpty()) ? "icon" : newIconFileName;
		String ext = "";
		int dot = safeName.lastIndexOf('.');
		if (dot >= 0) {
			ext = safeName.substring(dot);
			safeName = safeName.substring(0, dot);
		}
		String uniqueFileName = safeName + "_" + UUID.randomUUID().toString().replace("-", "") + ext;
		Path destPath = uploadDir.resolve(uniqueFileName);
		
		// Save new file
		try (InputStream in = newIconContent; FileOutputStream out = new FileOutputStream(destPath.toFile())) {
			byte[] buffer = new byte[8192];
			int bytesRead;
			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
			}
		} catch (IOException e) {
			return false;
		}
		
		// Delete old file if exists (old icon stores absolute or relative path?)
		if (cat.getIcon() != null && !cat.getIcon().isEmpty()) {
			deleteFileIfExists(cat.getIcon());
		}
		
		// Update DB with relative path from upload dir name (store file name only)
		cat.setIcon(uniqueFileName);
		categoryDAO.edit(cat);
		return true;
	}
	
	private void deleteFileIfExists(String filePath) {
		if (filePath == null || filePath.isEmpty()) return;
		try {
			Path p = Paths.get(filePath);
			if (!p.isAbsolute()) {
				// If only a file name is stored, try relative to common uploads directories
				String[] guesses = {"uploads/icons", "uploads", "images/icons", "images"};
				for (String g : guesses) {
					Path candidate = Paths.get(g).resolve(filePath);
					File f = candidate.toFile();
					if (f.exists()) {
						Files.delete(candidate);
						return;
					}
				}
			} else {
				Files.deleteIfExists(p);
			}
		} catch (IOException ignored) {
		}
	}
}
