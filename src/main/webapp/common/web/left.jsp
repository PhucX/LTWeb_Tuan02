<%-- Web Left Navigation --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<div class="bg-light border-end h-100 p-3">
	<h5 class="mb-3">Danh mục</h5>
	<style>
		.nav-link.active {
			background-color: #0d6efd !important;
			color: white !important;
			border-radius: 5px;
		}
		.nav-link.active:hover {
			background-color: #0b5ed7 !important;
			color: white !important;
		}
	</style>
	<ul class="nav flex-column">
		<li class="nav-item mb-2">
			<c:choose>
				<c:when test="${not empty sessionScope.account}">
					<c:choose>
						<c:when test="${sessionScope.account.roleid == 1}">
							<a class="nav-link ${fn:contains(pageContext.request.requestURI, '/admin/home') ? 'active' : ''}" href="${pageContext.request.contextPath}/admin/home"><i class="bi bi-house"></i> Trang chủ Admin</a>
						</c:when>
						<c:when test="${sessionScope.account.roleid == 2}">
							<a class="nav-link ${fn:contains(pageContext.request.requestURI, '/manager/home') ? 'active' : ''}" href="${pageContext.request.contextPath}/manager/home"><i class="bi bi-house"></i> Trang chủ Manager</a>
						</c:when>
						<c:otherwise>
							<a class="nav-link ${fn:contains(pageContext.request.requestURI, '/home') ? 'active' : ''}" href="${pageContext.request.contextPath}/home"><i class="bi bi-house"></i> Trang chủ</a>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<a class="nav-link ${fn:contains(pageContext.request.requestURI, '/home') ? 'active' : ''}" href="${pageContext.request.contextPath}/home"><i class="bi bi-house"></i> Trang chủ</a>
				</c:otherwise>
			</c:choose>
		</li>
		<li class="nav-item mb-2">
			<a class="nav-link ${fn:contains(pageContext.request.requestURI, '/products') ? 'active' : ''}" href="${pageContext.request.contextPath}/products"><i class="bi bi-list"></i> Sản phẩm</a>
		</li>
		<c:if test="${not empty sessionScope.account}">
			<c:if test="${sessionScope.account.roleid == 1}">
				<li class="nav-item mb-2">
					<a class="nav-link ${fn:contains(pageContext.request.requestURI, '/admin/categories') ? 'active' : ''}" href="${pageContext.request.contextPath}/admin/categories"><i class="bi bi-tags"></i> Quản lý danh mục</a>
				</li>
			</c:if>
			<c:if test="${sessionScope.account.roleid == 2}">
				<li class="nav-item mb-2">
					<a class="nav-link ${fn:contains(pageContext.request.requestURI, '/manager/products') ? 'active' : ''}" href="#"><i class="bi bi-box"></i> Quản lý sản phẩm</a>
				</li>
			</c:if>
		</c:if>
		<li class="nav-item mb-2">
			<c:choose>
				<c:when test="${not empty sessionScope.account}">
					<a class="nav-link ${fn:contains(pageContext.request.requestURI, '/account') ? 'active' : ''}" href="${pageContext.request.contextPath}/account"><i class="bi bi-person"></i> Tài khoản</a>
				</c:when>
				<c:otherwise>
					<a class="nav-link ${fn:contains(pageContext.request.requestURI, '/login') ? 'active' : ''}" href="${pageContext.request.contextPath}/login"><i class="bi bi-person"></i> Đăng nhập</a>
				</c:otherwise>
			</c:choose>
		</li>
	</ul>
</div>
