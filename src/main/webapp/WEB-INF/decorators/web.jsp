<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title><sitemesh:write property="title"/></title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
</head>
<body class="d-flex flex-column vh-100">
  <!-- Header -->
  <header>
    <jsp:include page="/common/web/header.jsp" />
  </header>
  <!-- Layout chính chia 2 cột -->
  <div class="d-flex flex-grow-1">
    <!-- Sidebar -->
    <aside style="min-width:220px;max-width:260px;">
      <jsp:include page="/common/web/left.jsp" />
    </aside>
    <!-- Nội dung body -->
    <main class="flex-fill p-4">
      <sitemesh:write property="body"/>
    </main>
  </div>
  <!-- Footer -->
  <footer class="mt-auto">
    <jsp:include page="/common/web/footer.jsp" />
  </footer>
</body>
</html>
