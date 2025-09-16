<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Manager Home</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="container mt-4">
		<h1 class="mb-3">Danh mục</h1>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<%@ taglib prefix="c" uri="jakarta.tags.core" %>
		<%
			vn.iotstar.services.CategoryService _svc = new vn.iotstar.services.impl.CategoryServiceImpl();
			java.util.List<vn.iotstar.models.Category> _cats = _svc.getAll();
			request.setAttribute("_cats", _cats);
		%>
		<table class="table table-bordered align-middle">
			<thead class="table-light">
				<tr>
					<th style="width: 80px;">ID</th>
					<th>Tên danh mục</th>
					<th style="width: 120px;">Icon</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="c" items="${_cats}">
					<tr>
						<td>${c.cateid}</td>
						<td>${c.catename}</td>
						<td>
							<c:if test="${not empty c.icon}">
								<img src="${pageContext.request.contextPath}/download-image?fname=${c.icon}" alt="icon" style="height:48px;">
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>

