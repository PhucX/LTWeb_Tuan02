package vn.iotstar.services;

import vn.iotstar.models.UserModel;

public interface UserService {

	UserModel login(String username, String password);
	boolean register(String username, String password, String email, String fullname, String phone);
	boolean checkExistEmail(String email);
	boolean checkExistUsername(String username);
	boolean checkExistPhone(String phone);
	void insert(UserModel u);
}
