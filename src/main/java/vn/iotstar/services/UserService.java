package vn.iotstar.services;

import vn.iotstar.models.UserModel;

public interface UserService {

	UserModel login(String username, String password);
	UserModel findByUserName(String username);
	UserModel findByEmail(String email);
	boolean register(String username, String password, String email, String fullname, String phone);
	boolean forgotPassword(String email);
	boolean resetPassword(String email, String newPassword);
	boolean checkExistEmail(String email);
	boolean checkExistUsername(String username);
	boolean checkExistPhone(String phone);
	void insert(UserModel u);
}
