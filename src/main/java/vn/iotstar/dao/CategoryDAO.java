package vn.iotstar.dao;

import java.util.List;
import vn.iotstar.models.Category;

public interface CategoryDAO {
	void insert(Category category);
	void edit(Category category);
	void delete(int cateid);
	Category get(int cateid);
	Category get(String catename);
	List<Category> getAll();
	List<Category> search(String keyword);
}
