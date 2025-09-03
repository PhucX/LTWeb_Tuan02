<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm danh mục</title>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
...
<c:if test="${not empty error}">
	<div class="alert alert-danger" role="alert">${error}</div>
</c:if>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body class="container py-4">
	<h3 class="mb-3">Thêm danh mục</h3>
	<form method="post"
		action="${pageContext.request.contextPath}/admin/category/add"
		enctype="multipart/form-data" class="needs-validation" novalidate>
		<div class="mb-3">
			<label class="form-label">Mã danh mục (nếu DB không tự tăng)</label>
			<input type="number" name="cateid" class="form-control">
		</div>
		<div class="mb-3">
			<label class="form-label">Tên danh mục</label> <input type="text"
				name="catename" class="form-control" required>
			<div class="invalid-feedback">Vui lòng nhập tên.</div>
		</div>
		<div class="mb-3">
			<label class="form-label">Ảnh icon</label> <input type="file"
				name="icon" accept="image/*" class="form-control">
		</div>
		<div class="d-flex gap-2">
			<button class="btn btn-primary" type="submit">Lưu</button>
			<a class="btn btn-secondary"
				href="${pageContext.request.contextPath}/admin/categories">Hủy</a>
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
