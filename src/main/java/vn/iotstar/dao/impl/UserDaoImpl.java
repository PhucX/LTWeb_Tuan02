package vn.iotstar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vn.iotstar.configs.DBConnection;
import vn.iotstar.dao.IUserDao;
import vn.iotstar.models.UserModel;

public class UserDaoImpl implements IUserDao {

    private UserModel mapRow(ResultSet rs) throws SQLException {
        UserModel u = new UserModel();
        u.setId(rs.getInt("id"));
        u.setEmail(rs.getString("email"));
        u.setUserName(rs.getString("username"));
        u.setFullName(rs.getString("fullname"));
        u.setPassWord(rs.getString("password"));
        u.setAvatar(rs.getString("avatar"));
        u.setRoleid(rs.getInt("roleid"));
        u.setPhone(rs.getString("phone"));
        u.setCreatedDate(rs.getDate("createddate"));
        return u;
    }

    @Override
    public List<UserModel> findAll() {
        String sql = "SELECT id, email, username, fullname, password, avatar, roleid, phone, createddate FROM [User]";
        List<UserModel> list = new ArrayList<>();
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
    public UserModel findByUserName(String username) {
        String sql = "SELECT id, email, username, fullname, password, avatar, roleid, phone, createddate FROM [User] WHERE username = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
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
    public void insert(UserModel u) {
        String sql = "INSERT INTO [User](email, username, fullname, password, avatar, roleid, phone, createddate) VALUES (?,?,?,?,?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, u.getEmail());
            ps.setString(2, u.getUserName());
            ps.setString(3, u.getFullName());
            ps.setString(4, u.getPassWord());
            ps.setString(5, u.getAvatar());
            ps.setInt(6, u.getRoleid());
            ps.setString(7, u.getPhone());
            ps.setDate(8, u.getCreatedDate());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkExistEmail(String email) {
        String sql = "SELECT 1 FROM [User] WHERE email = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkExistUsername(String username) {
        String sql = "SELECT 1 FROM [User] WHERE username = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkExistPhone(String phone) {
        String sql = "SELECT 1 FROM [User] WHERE phone = ?";
        try (Connection con = new DBConnection().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, phone);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}


