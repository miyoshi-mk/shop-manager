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

  	<div class="dashboard">
		  <!-- 全体指標 棒グラフ -->
		  <div class="overall-chart">
		    <h3>全体指標</h3>
		    <canvas id="overallChart"></canvas>
		  </div>

		  <!-- 商品別＆月別チャート -->
		  <div class="charts">
		    <div class="chart-container">
		      <h3>商品別売上</h3>
		      <canvas id="productSalesChart"></canvas>
		    </div>

		    <div class="chart-container">
		      <h3>週別売上推移</h3>
		      <canvas id="monthlySalesChart"></canvas>
		    </div>
		  </div>
	</div>
</main>

<script>
(function(){
  const totalProducts = Number('<c:out value="${totalProducts}" />');
  const totalStock = Number('<c:out value="${totalStock}" />');
  const totalOrders = Number('<c:out value="${totalOrders}" />');
  const totalSales = Number('<c:out value="${totalSales}" />');

  console.log("totalSales =", totalSales);    //デバッグ用

  // 商品別売上データ
  const productNames = ${productLabelsJson};
  const productSales = ${productSalesJson};
  const weeklyLabels = ${weekLabelsJson};
  const weeklyValues = ${weekValuesJson};

//棒グラフ（全体指標）
  new Chart(document.getElementById('overallChart').getContext('2d'), {
    type: 'bar',
    data: {
      labels: ['商品数','在庫数','発注件数','総売上(円)'],
      datasets: [
        {
          label: '数量',
          data: [totalProducts, totalStock, totalOrders, null],
          backgroundColor: ['#2563eb','#3b82f6','#60a5fa','#ffffff00'],
          yAxisID: 'y1'
        },
        {
          label: '売上(円)',
          data: [null, null, null, totalSales],
          backgroundColor: ['#93c5fd'],
          yAxisID: 'y2'
        }
      ]
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      plugins: { legend: { display: true } },
      scales: {
        y1: {
          type: 'linear',
          position: 'left',
          beginAtZero: true,
          title: { display: true, text: '数量' }
        },
        y2: {
          type: 'linear',
          position: 'right',
          beginAtZero: true,
          title: { display: true, text: '売上(円)' },
          grid: { drawOnChartArea: false }
        }
      }
    }
  });

//円グラフ（商品別売上）
  new Chart(document.getElementById('productSalesChart').getContext('2d'), {
    type:'doughnut',
    data:{
      labels: productNames,
      datasets:[{
        data: productSales,
        backgroundColor: ['#2563eb','#1e3a8a','#475569','#64748b','#94a3b8','#cbd5e1','#e2e8f0']
      }]
    },
    options:{
      responsive:true,
      maintainAspectRatio: false,
      plugins:{
        legend:{
          position:'right',
          labels: {
            font: { size: 8 },
            color: '#333',
            boxWidth: 12
          }
        }
      }
    }
  });

//折れ線グラフ（週別売上推移） -- ここは weeklyLabels を使う
  new Chart(document.getElementById('monthlySalesChart').getContext('2d'), {
    type:'line',
    data:{
      labels: weeklyLabels,
      datasets:[
        {
          label:'週別売上',
          data: weeklyValues,
          borderColor:'#2563eb',
          tension:0.3,
          fill:false
        }
      ]
    },
    options:{
      responsive:true,
      maintainAspectRatio: false,
      plugins:{ legend:{ display:false } },
      scales:{ y:{ beginAtZero:true, title:{ display:true, text:'売上(円)' } } }
    }
  });
})();
</script>
</body>
</html>