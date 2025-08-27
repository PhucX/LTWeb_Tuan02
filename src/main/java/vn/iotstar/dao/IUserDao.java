package vn.iotstar.dao;

import java.util.List;
import vn.iotstar.models.UserModel;

public interface IUserDao {
	
	List<UserModel> findAll();
	UserModel findByUserName(String username);
	void insert(UserModel u);
	boolean checkExistEmail(String email);
	boolean checkExistUsername(String username);
	boolean checkExistPhone(String phone);
}
