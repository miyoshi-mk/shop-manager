<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.dao.ProductDAO" %>
<%@ page import="model.Product" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>発注登録</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="header.jsp" />
<jsp:include page="sidebar.jsp" />

<main class="main-content">
	<h1>新規発注登録</h1>
	 <form action="${pageContext.request.contextPath}/addOrder" method="post">
	    <p>商品:
	        <select name="productId">
		         <% 
	                List<Product> products = (List<Product>) request.getAttribute("products");
	                if (products != null) {
	                    for (Product p : products) { 
            	%>
	             	<option value="<%= p.getProductId() %>"><%= p.getProductName() %></option>
	            <% 
	                    } 
	                }
	            %>
	        </select>
	    </p>
	    
	    <p>数量: <input type="number" name="quantity" min="1" required></p>
	    <p>ステータス:
	        <select name="status">
	            <option value="未発注">未発注</option>
	            <option value="発注済">発注済</option>
	            <option value="入荷済">入荷済</option>
	        </select>
	    </p>
	    <input type="submit" value="登録">
	</form>
	
	<p><a href="${pageContext.request.contextPath}/page?name=orderList">発注一覧に戻る</a></p>
</main>
</body>
</html>