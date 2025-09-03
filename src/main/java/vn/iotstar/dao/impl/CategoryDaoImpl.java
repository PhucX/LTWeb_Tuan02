package vn.iotstar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vn.iotstar.configs.DBConnection;
import vn.iotstar.dao.CategoryDAO;
import vn.iotstar.models.Category;

public class CategoryDaoImpl implements CategoryDAO {
	
	private Category mapRow(ResultSet rs) throws SQLException {
		Category c = new Category();
		c.setCateid(rs.getInt("cate_id"));
		c.setCatename(rs.getString("cate_name"));
		c.setIcon(rs.getString("icons"));
		return c;
	}
	
	@Override
	public void insert(Category category) {
		boolean hasId = category.getCateid() > 0;
		String sqlWithId = "INSERT INTO [Category]([cate_id], [cate_name], [icons]) VALUES (?, ?, ?)";
		String sqlAuto = "INSERT INTO [Category]([cate_name], [icons]) VALUES (?, ?)";
		try (Connection con = DBConnection.getConnection();
			 PreparedStatement ps = hasId
				 ? con.prepareStatement(sqlWithId)
				 : con.prepareStatement(sqlAuto, Statement.RETURN_GENERATED_KEYS)) {
			int idx = 1;
			if (hasId) {
				ps.setInt(idx++, category.getCateid());
			}
			ps.setString(idx++, category.getCatename());
			ps.setString(idx, category.getIcon());
			ps.executeUpdate();

			if (!hasId) {
				try (ResultSet rs = ps.getGeneratedKeys()) {
					if (rs.next()) {
						int genId = rs.getInt(1);
						category.setCateid(genId);
					}
				}
			}
		} catch (SQLException e) {
			System.err.println("[CategoryDaoImpl.insert] SQL error: " + e.getMessage());
			e.printStackTrace();
			throw new RuntimeException(e); // ném lên để Controller hiển thị
		}
	}
	
	@Override
	public void edit(Category category) {
		String sql = "UPDATE [Category] SET [cate_name] = ?, [icons] = ? WHERE [cate_id] = ?";
		try (Connection con = DBConnection.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, category.getCatename());
			ps.setString(2, category.getIcon());
			ps.setInt(3, category.getCateid());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void delete(int cateid) {
		String sql = "DELETE FROM [Category] WHERE [cate_id] = ?";
		try (Connection con = DBConnection.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, cateid);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Category get(int cateid) {
		String sql = "SELECT [cate_id], [cate_name], [icons] FROM [Category] WHERE [cate_id] = ?";
		try (Connection con = DBConnection.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, cateid);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return mapRow(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Category get(String catename) {
		String sql = "SELECT [cate_id], [cate_name], [icons] FROM [Category] WHERE [cate_name] = ?";
		try (Connection con = DBConnection.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, catename);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return mapRow(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Category> getAll() {
		String sql = "SELECT [cate_id], [cate_name], [icons] FROM [Category]";
		List<Category> list = new ArrayList<>();
		try (Connection con = DBConnection.getConnection();
			 Statement st = con.createStatement();
			 ResultSet rs = st.executeQuery(sql)) {
			while (rs.next()) {
				list.add(mapRow(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public List<Category> search(String keyword) {
		String sql = "SELECT [cate_id], [cate_name], [icons] FROM [Category] WHERE [cate_name] LIKE ? OR [icons] LIKE ?";
		List<Category> list = new ArrayList<>();
		try (Connection con = DBConnection.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql)) {
			String like = "%" + (keyword == null ? "" : keyword.trim()) + "%";
			ps.setString(1, like);
			ps.setString(2, like);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					list.add(mapRow(rs));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
