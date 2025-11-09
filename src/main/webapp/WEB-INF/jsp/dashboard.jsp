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
	    <div class="card">
		    <div class="card-label">総売上</div>
		    <div class="card-value">¥<c:out value="${totalSales}" /></div>
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
  const totalSales = Number('${totalSales}');

  const ctx = document.getElementById('dashboardChart').getContext('2d');
  new Chart(ctx, {
    type: 'bar',
    data: {
      labels: ['商品数', '在庫数', '発注件数', '総売上'],
      datasets: [
        {
          label: '数量',
          data: [totalProducts, totalStock, totalOrders, 0], 
          backgroundColor: ['#4caf50', '#2196f3', '#ff9800', 'transparent'],
          yAxisID: 'y-left'
        },
        {
          label: '総売上',
          data: [0, 0, 0, totalSales], 
          backgroundColor: '#AF504C',
          yAxisID: 'y-right'
        }
      ]
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      scales: {
        'y-left': {
          type: 'linear',
          position: 'left',
          beginAtZero: true,
          title: { display: true, text: '数量' }
        },
        'y-right': {
          type: 'linear',
          position: 'right',
          beginAtZero: true,
          title: { display: true, text: '売上（円）' },
          grid: { drawOnChartArea: false } 
        }
      }
    }
  });
})();
</script>
</body>
</html>