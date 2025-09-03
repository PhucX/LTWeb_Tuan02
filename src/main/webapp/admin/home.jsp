<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Admin Home</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body>
	<jsp:include page="/views/topbar.jsp" />
	<div class="container mt-4">
		<h1>Admin Home</h1>
		<div class="mt-4">
			<h4>Danh mục</h4>
			<p>Quản lý danh mục sản phẩm: thêm mới, chỉnh sửa, xóa.</p>
			<div class="d-flex gap-2">
				<a class="btn btn-primary" href="${pageContext.request.contextPath}/admin/categories">Xem danh sách danh mục</a>
			</div>
		</div>
	</div>
</body>
</html>

