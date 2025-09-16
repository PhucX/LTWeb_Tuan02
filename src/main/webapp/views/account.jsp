<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thông tin tài khoản</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css">
</head>
<body>
    <div class="container mt-4">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">
                        <h4 class="mb-0"><i class="bi bi-person-circle"></i> Thông tin tài khoản</h4>
                    </div>
                    <div class="card-body">
                        <c:if test="${not empty user}">
                            <div class="row mb-3">
                                <div class="col-sm-3">
                                    <strong>Tên đăng nhập:</strong>
                                </div>
                                <div class="col-sm-9">
                                    <span class="text-muted">${user.userName}</span>
                                </div>
                            </div>
                            
                            <div class="row mb-3">
                                <div class="col-sm-3">
                                    <strong>Họ tên:</strong>
                                </div>
                                <div class="col-sm-9">
                                    <span class="text-muted">${user.fullName}</span>
                                </div>
                            </div>
                            
                            <div class="row mb-3">
                                <div class="col-sm-3">
                                    <strong>Email:</strong>
                                </div>
                                <div class="col-sm-9">
                                    <span class="text-muted">${user.email}</span>
                                </div>
                            </div>
                            
                            <div class="row mb-3">
                                <div class="col-sm-3">
                                    <strong>Vai trò:</strong>
                                </div>
                                <div class="col-sm-9">
                                    <c:choose>
                                        <c:when test="${user.roleid == 1}">
                                            <span class="badge bg-primary">Admin</span>
                                        </c:when>
                                        <c:when test="${user.roleid == 2}">
                                            <span class="badge bg-success">Manager</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge bg-secondary">User</span>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            
                            <div class="row mb-3">
                                <div class="col-sm-3">
                                    <strong>Trạng thái:</strong>
                                </div>
                                <div class="col-sm-9">
                                    <span class="badge bg-success">Hoạt động</span>
                                </div>
                            </div>
                            
                            <div class="row mb-3">
                                <div class="col-sm-3">
                                    <strong>Ngày tạo:</strong>
                                </div>
                                <div class="col-sm-9">
                                    <span class="text-muted">${user.createdDate}</span>
                                </div>
                            </div>
                            
                            <hr>
                            
                            <div class="d-flex gap-2">
                                <a href="${pageContext.request.contextPath}/profile" class="btn btn-primary">
                                    <i class="bi bi-pencil-square"></i> Chỉnh sửa tài khoản
                                </a>
                                <a href="${pageContext.request.contextPath}/home" class="btn btn-secondary">
                                    <i class="bi bi-house"></i> Về trang chủ
                                </a>
                                <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger">
                                    <i class="bi bi-box-arrow-right"></i> Đăng xuất
                                </a>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
