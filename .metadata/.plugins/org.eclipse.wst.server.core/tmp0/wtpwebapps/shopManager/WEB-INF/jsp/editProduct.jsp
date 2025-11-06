<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Product" %>
<%@ page import="model.dao.ProductDAO" %>

<%
    Product p = (Product)request.getAttribute("product");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品編集</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="header.jsp" />
<jsp:include page="sidebar.jsp" />

<main class="main-content">
	<h2>商品編集</h2>
	
	<form action="${pageContext.request.contextPath}/editProduct" method="post">
	    <input type="hidden" name="id" value="<%=p.getProductId()%>">
		     
	    <div class="form-group">
		    <label>商品名：</label>
	        <input type="text" name="name" value="<%=p.getProductName()%>" required><br>
		</div>
		
		<div class="form-group">
	        <label>在庫数：</label>
	        <input type="number" name="stock" value="<%=p.getStock()%>" required><br>
		</div>
		
		<div class="form-group">
	        <label>価格：</label>
	        <input type="number" name="price" value="<%=p.getPrice()%>" required><br>
		</div>
		
		<div class="form-actions">
        	<button type="submit">更新</button>
        	<a href="${pageContext.request.contextPath}/productList">商品一覧へ戻る</a>
        </div>
	</form>
</main>
</body>
</html>