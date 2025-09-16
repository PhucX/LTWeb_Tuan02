package vn.iotstar.utils;

import vn.iotstar.models.UserModel;

public class RoleRedirectUtil {
    
    // Role constants
    public static final int ROLE_ADMIN = 1;
    public static final int ROLE_MANAGER = 2;
    public static final int ROLE_USER = 3;
    
    /**
     * Lấy URL redirect dựa trên role của user
     * @param user UserModel object
     * @param contextPath Context path của ứng dụng
     * @return URL để redirect
     */
    public static String getRedirectUrl(UserModel user, String contextPath) {
        if (user == null) {
            return contextPath + "/login";
        }
        
        switch (user.getRoleid()) {
            case ROLE_ADMIN:
                return contextPath + "/admin/home";
            case ROLE_MANAGER:
                return contextPath + "/manager/home";
            case ROLE_USER:
            default:
                return contextPath + "/home";
        }
    }
    
    /**
     * Kiểm tra xem user có quyền truy cập trang admin không
     * @param user UserModel object
     * @return true nếu có quyền admin
     */
    public static boolean isAdmin(UserModel user) {
        return user != null && user.getRoleid() == ROLE_ADMIN;
    }
    
    /**
     * Kiểm tra xem user có quyền truy cập trang manager không
     * @param user UserModel object
     * @return true nếu có quyền manager trở lên
     */
    public static boolean isManager(UserModel user) {
        return user != null && (user.getRoleid() == ROLE_MANAGER || user.getRoleid() == ROLE_ADMIN);
    }
}
