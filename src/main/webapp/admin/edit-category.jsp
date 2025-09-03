<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Chỉnh sửa danh mục</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body class="container py-4">
	<h3 class="mb-3">Chỉnh sửa danh mục</h3>
	<form method="post" action="${pageContext.request.contextPath}/admin/category/edit" enctype="multipart/form-data" class="needs-validation" novalidate>
		<input type="hidden" name="cateid" value="${category.cateid}">
		<div class="mb-3">
			<label class="form-label">Tên danh mục</label>
			<input type="text" name="catename" class="form-control" value="${category.catename}" required>
			<div class="invalid-feedback">Vui lòng nhập tên.</div>
		</div>
		<div class="mb-3">
			<label class="form-label">Icon hiện tại</label>
			<div>
				<c:if test="${not empty category.icon}">
					<img src="${pageContext.request.contextPath}/uploads/icons/${category.icon}" alt="icon" style="height:64px;">
				</c:if>
				<c:if test="${empty category.icon}">
					<span class="text-muted">Chưa có icon</span>
				</c:if>
			</div>
		</div>
		<div class="mb-3">
			<label class="form-label">Tải icon mới (tùy chọn)</label>
			<input type="file" name="icon" accept="image/*" class="form-control">
		</div>
		<div class="d-flex gap-2">
			<button class="btn btn-primary" type="submit">Lưu</button>
			<a class="btn btn-secondary" href="${pageContext.request.contextPath}/admin/categories">Hủy</a>
		</div>
	</form>
	<script>
	(() => {
	  'use strict';
	  const forms = document.querySelectorAll('.needs-validation');
	  Array.from(forms).forEach(form => {
		form.addEventListener('submit', event => {
		  if (!form.checkValidity()) {
			event.preventDefault();
			event.stopPropagation();
		  }
		  form.classList.add('was-validated');
		}, false);
	  });
	})();
	</script>
</body>
</html>
