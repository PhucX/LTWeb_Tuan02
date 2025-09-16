<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Danh sách danh mục</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body class="container py-4">
	<div class="d-flex justify-content-between align-items-center mb-3">
		<h3>Danh sách danh mục</h3>
		<a class="btn btn-primary" href="${pageContext.request.contextPath}/admin/category/add">Thêm danh mục</a>
	</div>
	<form class="mb-3" method="get" action="${pageContext.request.contextPath}/admin/categories">
		<div class="input-group">
			<input type="text" class="form-control" placeholder="Tìm kiếm" name="q" value="${param.q}">
			<button class="btn btn-outline-secondary" type="submit">Tìm</button>
		</div>
	</form>
	<table class="table table-bordered table-hover align-middle">
		<thead class="table-light">
			<tr>
				<th style="width: 80px;">ID</th>
				<th>Tên</th>
				<th style="width: 120px;">Icon</th>
				<th style="width: 180px;">Thao tác</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="c" items="${categories}">
				<tr>
					<td>${c.cateid}</td>
					<td>${c.catename}</td>
					<td>
						<c:if test="${not empty c.icon}">
							<img src="${pageContext.request.contextPath}/download-image?fname=${c.icon}" alt="icon">
						</c:if>
					</td>
					<td>
						<a class="btn btn-sm btn-warning" href="${pageContext.request.contextPath}/admin/category/edit?id=${c.cateid}">Sửa</a>
						<form action="${pageContext.request.contextPath}/admin/category/delete" method="post" style="display:inline" onsubmit="return confirm('Xóa danh mục này?');">
							<input type="hidden" name="id" value="${c.cateid}">
							<button class="btn btn-sm btn-danger" type="submit">Xóa</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
