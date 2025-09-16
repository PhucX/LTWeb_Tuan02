<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Login</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="container mt-4" style="max-width: 480px;">
		<h3 class="mb-3">Đăng nhập</h3>
		<c:if test="${not empty alert}">
			<div class="alert alert-danger" role="alert">${alert}</div>
		</c:if>
		<form method="post" action="${pageContext.request.contextPath}/login">
			<div class="mb-3">
				<label for="username" class="form-label">Username</label>
				<input type="text" class="form-control" id="username" name="username" required>
			</div>
			<div class="mb-3">
				<label for="password" class="form-label">Password</label>
				<input type="password" class="form-control" id="password" name="password" required>
			</div>
			<div class="form-check mb-3">
				<input class="form-check-input" type="checkbox" id="rememberMe" name="rememberMe" value="true">
				<label class="form-check-label" for="rememberMe">Ghi nhớ đăng nhập</label>
			</div>
			<button type="submit" class="btn btn-primary w-100">Login</button>
			<div class="text-center mt-3">
				<span>Chưa có tài khoản? </span>
				<a href="${pageContext.request.contextPath}/register">Đăng ký</a>
				<span> | </span>
				<a href="${pageContext.request.contextPath}/forgot-password">Quên mật khẩu?</a>
			</div>
		</form>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>