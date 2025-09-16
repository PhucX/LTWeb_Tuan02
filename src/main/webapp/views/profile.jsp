<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cập nhật hồ sơ</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body class="container py-4">
    <h3 class="mb-3">Cập nhật hồ sơ</h3>
    <c:if test="${not empty message}">
        <div class="alert alert-info">${message}</div>
    </c:if>
    <form method="post" action="${pageContext.request.contextPath}/profile" enctype="multipart/form-data" class="needs-validation" novalidate>
        <div class="mb-3">
            <label class="form-label">Họ tên</label>
            <input type="text" name="fullname" class="form-control" value="${user.fullname}" required>
            <div class="invalid-feedback">Vui lòng nhập họ tên.</div>
        </div>
        <div class="mb-3">
            <label class="form-label">Số điện thoại</label>
            <input type="text" name="phone" class="form-control" value="${user.phone}" required>
            <div class="invalid-feedback">Vui lòng nhập số điện thoại.</div>
        </div>
        <div class="mb-3">
            <label class="form-label">Ảnh đại diện hiện tại</label><br>
            <c:set var="now" value="<%= new java.util.Date() %>" />
            <c:if test="${not empty user.avatar}">
                <img src="${pageContext.request.contextPath}/uploads/${user.avatar}?v=${now.time}" alt="avatar" style="height:80px;">
            </c:if>
            <c:if test="${empty user.avatar}">
                <span class="text-muted">Chưa có ảnh</span>
            </c:if>
        </div>
        <div class="mb-3">
            <label class="form-label">Tải ảnh mới (tùy chọn)</label>
            <input type="file" name="image" accept="image/*" class="form-control">
        </div>
        <button class="btn btn-primary" type="submit">Cập nhật</button>
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
