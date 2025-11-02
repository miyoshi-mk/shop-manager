<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ダッシュボード</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
 <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
<jsp:include page="header.jsp" />
<jsp:include page="sidebar.jsp" />
<main class="main-content">
  <!-- メインコンテンツ -->
	<h2>ショップ管理ダッシュボード</h2>

	<div class="summary">
    	<div class="card">
    		<div class="card-label">商品数</div>
    		<div class="card-value"><c:out value="${totalProducts}" /></div>
    	</div>
	    <div class="card">
	    	<div class="card-label">総在庫数</div>
	    	<div class="card-value"><c:out value="${totalStock}" /></div>
	    </div>
	    <div class="card">
	    	<div class="card-label">発注件数</div>
	    	<div class="card-value"><c:out value="${totalOrders}" /></div>
	    </div>
  	</div>
  	
  	<section class="chart-area">
    	<canvas id="dashboardChart" width="800" height="300"></canvas>
  	</section>
</main>
	
<script>
(function(){
	  const totalProducts = Number('${totalProducts}');
	  const totalStock = Number('${totalStock}');
	  const totalOrders = Number('${totalOrders}');

	  const ctx = document.getElementById('dashboardChart').getContext('2d');
	  new Chart(ctx, {
		  type: 'bar',
		  data: {
			  labels: ['商品数', '在庫数', '発注件数'],
		      datasets: [{
			      label: '集計',
		          data: [totalProducts, totalStock, totalOrders],
		          backgroundColor: ['#4caf50', '#2196f3', '#ff9800']
		      }]
		  },
		  options: { responsive: true, maintainAspectRatio: false }
	  });
})();
</script>
</body>
</html>