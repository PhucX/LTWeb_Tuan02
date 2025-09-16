<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Trang chủ</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css">
</head>
<body>
	<div class="container mt-4">
		<!-- Hero Section -->
		<div class="row mb-5">
			<div class="col-12">
				<div class="bg-primary text-white p-5 rounded text-center">
					<h1 class="display-4 fw-bold mb-3">📱 Điện Thoại Thông Minh</h1>
					<p class="lead mb-4">Khám phá bộ sưu tập điện thoại mới nhất với công nghệ tiên tiến</p>
					<button class="btn btn-light btn-lg">Mua ngay</button>
				</div>
			</div>
		</div>

		<!-- Featured Products -->
		<div class="row mb-5">
			<div class="col-12">
				<h2 class="text-center mb-4">🔥 Sản phẩm nổi bật</h2>
			</div>
		</div>

		<div class="row g-4 mb-5">
			<!-- iPhone 15 Pro -->
			<div class="col-md-4">
				<div class="card h-100 shadow-sm">
					<div class="card-img-top bg-light d-flex align-items-center justify-content-center" style="height: 250px;">
						<i class="bi bi-phone display-1 text-primary"></i>
					</div>
					<div class="card-body d-flex flex-column">
						<h5 class="card-title">iPhone 15 Pro</h5>
						<p class="card-text text-muted">Chip A17 Pro mạnh mẽ, camera 48MP chuyên nghiệp</p>
						<div class="mt-auto">
							<div class="d-flex justify-content-between align-items-center">
								<span class="h4 text-danger mb-0">29.990.000₫</span>
								<button class="btn btn-outline-primary">Chi tiết</button>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- Samsung Galaxy S24 Ultra -->
			<div class="col-md-4">
				<div class="card h-100 shadow-sm">
					<div class="card-img-top bg-light d-flex align-items-center justify-content-center" style="height: 250px;">
						<i class="bi bi-phone display-1 text-success"></i>
					</div>
					<div class="card-body d-flex flex-column">
						<h5 class="card-title">Samsung Galaxy S24 Ultra</h5>
						<p class="card-text text-muted">S Pen tích hợp, camera 200MP siêu nét</p>
						<div class="mt-auto">
							<div class="d-flex justify-content-between align-items-center">
								<span class="h4 text-danger mb-0">26.990.000₫</span>
								<button class="btn btn-outline-primary">Chi tiết</button>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- Xiaomi 14 Pro -->
			<div class="col-md-4">
				<div class="card h-100 shadow-sm">
					<div class="card-img-top bg-light d-flex align-items-center justify-content-center" style="height: 250px;">
						<i class="bi bi-phone display-1 text-warning"></i>
					</div>
					<div class="card-body d-flex flex-column">
						<h5 class="card-title">Xiaomi 14 Pro</h5>
						<p class="card-text text-muted">Snapdragon 8 Gen 3, camera Leica chuyên nghiệp</p>
						<div class="mt-auto">
							<div class="d-flex justify-content-between align-items-center">
								<span class="h4 text-danger mb-0">18.990.000₫</span>
								<button class="btn btn-outline-primary">Chi tiết</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- Features Section -->
		<div class="row mb-5">
			<div class="col-12">
				<h2 class="text-center mb-4">✨ Tại sao chọn chúng tôi?</h2>
			</div>
		</div>

		<div class="row g-4">
			<div class="col-md-3 text-center">
				<div class="p-4">
					<i class="bi bi-shield-check display-4 text-success mb-3"></i>
					<h5>Bảo hành chính hãng</h5>
					<p class="text-muted">12 tháng bảo hành toàn quốc</p>
				</div>
			</div>
			<div class="col-md-3 text-center">
				<div class="p-4">
					<i class="bi bi-truck display-4 text-primary mb-3"></i>
					<h5>Giao hàng nhanh</h5>
					<p class="text-muted">Miễn phí giao hàng trong 24h</p>
				</div>
			</div>
			<div class="col-md-3 text-center">
				<div class="p-4">
					<i class="bi bi-credit-card display-4 text-info mb-3"></i>
					<h5>Thanh toán linh hoạt</h5>
					<p class="text-muted">Trả góp 0% lãi suất</p>
				</div>
			</div>
			<div class="col-md-3 text-center">
				<div class="p-4">
					<i class="bi bi-headset display-4 text-warning mb-3"></i>
					<h5>Hỗ trợ 24/7</h5>
					<p class="text-muted">Tư vấn miễn phí mọi lúc</p>
				</div>
			</div>
		</div>

		<!-- Call to Action -->
		<div class="row mt-5">
			<div class="col-12">
				<div class="bg-dark text-white p-5 rounded text-center">
					<h3 class="mb-3">🎉 Ưu đãi đặc biệt</h3>
					<p class="lead mb-4">Giảm giá lên đến 30% cho tất cả sản phẩm điện thoại</p>
					<button class="btn btn-warning btn-lg">Xem ngay</button>
				</div>
			</div>
		</div>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
