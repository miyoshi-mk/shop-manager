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
		      <h3>月別売上推移</h3>
		      <canvas id="monthlySalesChart"></canvas>
		    </div>
		  </div>
	</div>
</main>
	
<script>
(function(){
  const totalProducts = Number('${totalProducts}');
  const totalStock = Number('${totalStock}');
  const totalOrders = Number('${totalOrders}');
  const totalSales = Number('${totalSales}');
//商品別売上データ
  const productNames = ${productLabelsJson};
  const productSales = ${productSalesJson};
  const monthlyLabels = ${monthLabelsJson};
  const monthlyValues = ${monthValuesJson};

//棒グラフ（全体指標）
   new Chart(document.getElementById('overallChart').getContext('2d'), {
    type: 'bar',
    data: {
      labels: ['商品数','在庫数','発注件数','総売上(円)'],
      datasets:[{
        label:'数量/金額',
        data:[totalProducts,totalStock,totalOrders,totalSales],
        backgroundColor:['#2563eb','#3b82f6','#60a5fa','#93c5fd']
      }]
    },
    options:{ responsive:true, plugins:{ legend:{display:false} }, scales:{ y:{ beginAtZero:true, title:{ display:true, text:'数量・金額' } } } }
  });


  // 円グラフ（商品別売上）
  new Chart(document.getElementById('productSalesChart').getContext('2d'), {
    type:'doughnut',
    data:{ labels:productNames, datasets:[{ data:productSales, backgroundColor:['#2563eb','#1e3a8a','#475569','#64748b','#94a3b8'] }] },
    options:{ 
        responsive:true, 
        plugins:{ 
            legend:{ 
                position:'right', 
                labels: { 
                    font: { 
                        size: 7 
                    }, 
                    color: '#333', 
                    boxWidth: 14 
                } 
            },
            title: { display: false }
       }
    }
 });


  // --- 折れ線グラフ（月別売上推移） ---
  new Chart(document.getElementById('monthlySalesChart').getContext('2d'), {
	    type:'line',
	    data:{ labels:monthlyLabels, datasets:[{ label:'売上推移', data:monthlyValues, borderColor:'#2563eb', tension:0.3, fill:false }] },
	    options:{ responsive:true, plugins:{ legend:{ display:false } }, scales:{ y:{ beginAtZero:true, title:{ display:true, text:'売上(円)' } } } }
	  });
	})();
</script>
</body>
</html>