package vn.iotstar.services;

import java.io.InputStream;
import java.util.List;

import vn.iotstar.models.Category;

public interface CategoryService {
	void insert(Category category);
	void edit(Category category);
	void delete(int cateid);
	Category get(int cateid);
	Category get(String catename);
	List<Category> getAll();
	List<Category> search(String keyword);
	
	/**
	 * Update category icon: delete old icon file if exists, store new file, and update DB.
	 * @param cateid category id to update
	 * @param newIconContent input stream of new image content (will be consumed and closed by caller)
	 * @param newIconFileName original file name of uploaded image
	 * @param uploadDirRealPath absolute path to the directory to store icons (e.g., context.getRealPath("/uploads/icons"))
	 * @return true if updated successfully; false otherwise
	 */
	boolean updateIcon(int cateid, InputStream newIconContent, String newIconFileName, String uploadDirRealPath);
}
