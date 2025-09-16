<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sản phẩm</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css">
</head>
<body>
    <div class="container mt-4">
        <div class="row">
            <div class="col-12">
                <h1 class="mb-4"><i class="bi bi-list"></i> Danh mục sản phẩm</h1>
                <p class="text-muted mb-4">Khám phá các danh mục sản phẩm điện thoại đa dạng</p>
            </div>
        </div>
        
        <div class="row">
            <div class="col-12">
                <div class="card">
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-hover align-middle">
                                <thead class="table-dark">
                                    <tr>
                                        <th style="width: 80px;">ID</th>
                                        <th>Tên danh mục</th>
                                        <th style="width: 120px;">Icon</th>
                                        <th style="width: 150px;">Thao tác</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:choose>
                                        <c:when test="${not empty _cats}">
                                            <c:forEach var="c" items="${_cats}">
                                                <tr>
                                                    <td>
                                                        <span class="badge bg-primary">${c.cateid}</span>
                                                    </td>
                                                    <td>
                                                        <h6 class="mb-0">${c.catename}</h6>
                                                        <small class="text-muted">Danh mục sản phẩm</small>
                                                    </td>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${not empty c.icon}">
                                                                <img src="${pageContext.request.contextPath}/download-image?fname=${c.icon}" 
                                                                     alt="icon" class="img-thumbnail" style="width: 60px; height: 60px; object-fit: cover;">
                                                            </c:when>
                                                            <c:otherwise>
                                                                <div class="bg-light d-flex align-items-center justify-content-center" 
                                                                     style="width: 60px; height: 60px; border-radius: 8px;">
                                                                    <i class="bi bi-image text-muted"></i>
                                                                </div>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td>
                                                        <div class="btn-group" role="group">
                                                            <button class="btn btn-outline-primary btn-sm" 
                                                                    onclick="viewProducts('${c.cateid}', '${c.catename}')">
                                                                <i class="bi bi-eye"></i> Xem
                                                            </button>
                                                            <button class="btn btn-outline-success btn-sm" 
                                                                    onclick="addToCart('${c.cateid}')">
                                                                <i class="bi bi-cart-plus"></i> Mua
                                                            </button>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <tr>
                                                <td colspan="4" class="text-center py-5">
                                                    <i class="bi bi-inbox display-1 text-muted mb-3"></i>
                                                    <h5 class="text-muted">Chưa có danh mục nào</h5>
                                                    <p class="text-muted">Vui lòng thêm danh mục sản phẩm</p>
                                                </td>
                                            </tr>
                                        </c:otherwise>
                                    </c:choose>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- Statistics -->
        <div class="row mt-4">
            <div class="col-md-3">
                <div class="card bg-primary text-white">
                    <div class="card-body text-center">
                        <i class="bi bi-grid-3x3-gap display-4 mb-2"></i>
                        <h4>${fn:length(_cats)}</h4>
                        <p class="mb-0">Danh mục</p>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card bg-success text-white">
                    <div class="card-body text-center">
                        <i class="bi bi-phone display-4 mb-2"></i>
                        <h4>50+</h4>
                        <p class="mb-0">Sản phẩm</p>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card bg-warning text-white">
                    <div class="card-body text-center">
                        <i class="bi bi-star display-4 mb-2"></i>
                        <h4>4.8</h4>
                        <p class="mb-0">Đánh giá</p>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card bg-info text-white">
                    <div class="card-body text-center">
                        <i class="bi bi-people display-4 mb-2"></i>
                        <h4>1000+</h4>
                        <p class="mb-0">Khách hàng</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        function viewProducts(categoryId, categoryName) {
            alert('Xem sản phẩm trong danh mục: ' + categoryName + ' (ID: ' + categoryId + ')');
            // Có thể redirect đến trang chi tiết sản phẩm
            // window.location.href = '${pageContext.request.contextPath}/products/' + categoryId;
        }
        
        function addToCart(categoryId) {
            alert('Thêm sản phẩm từ danh mục ID: ' + categoryId + ' vào giỏ hàng');
            // Có thể thêm logic thêm vào giỏ hàng
        }
    </script>
</body>
</html>
