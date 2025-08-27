<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Register</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body>
	<jsp:include page="topbar.jsp" />
	<div class="container mt-4" style="max-width: 640px;">
		<h3 class="mb-3">Đăng ký tài khoản</h3>
		<c:if test="${not empty alert}">
			<div class="alert alert-danger" role="alert">${alert}</div>
		</c:if>
		<form method="post" action="${pageContext.request.contextPath}/register">
			<div class="row g-3">
				<div class="col-md-6">
					<label for="username" class="form-label">Username</label>
					<input type="text" class="form-control" id="username" name="username" required>
				</div>
				<div class="col-md-6">
					<label for="password" class="form-label">Password</label>
					<input type="password" class="form-control" id="password" name="password" required>
				</div>
				<div class="col-md-6">
					<label for="email" class="form-label">Email</label>
					<input type="email" class="form-control" id="email" name="email" required>
				</div>
				<div class="col-md-6">
					<label for="fullname" class="form-label">Full name</label>
					<input type="text" class="form-control" id="fullname" name="fullname" required>
				</div>
				<div class="col-md-6">
					<label for="phone" class="form-label">Phone</label>
					<input type="text" class="form-control" id="phone" name="phone" required>
				</div>
			</div>
			<button type="submit" class="btn btn-success mt-3 w-100">Đăng ký</button>
			<div class="text-center mt-3">
				<span>Đã có tài khoản? </span>
				<a href="${pageContext.request.contextPath}/login">Đăng nhập</a>
			</div>
		</form>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

