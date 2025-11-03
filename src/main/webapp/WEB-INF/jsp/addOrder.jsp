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
	<div class="form-container">
		<h2>新規発注登録</h2>
	 	<form action="${pageContext.request.contextPath}/addOrder" method="post">
		    <div class="form-group">
        		<label for="productId">商品</label>
        		<select name="productId" id="productId" required>
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
		    </div>
		    
		    <div class="form-group">
		        <label for="quantity">数量</label>
		        <input type="number" id="quantity" name="quantity" min="1" required>
      		</div>
      		
		     <div class="form-group">
		        <label for="status">ステータス</label>
		        <select name="status" id="status" required>
			        <option value="未発注">未発注</option>
			        <option value="発注済">発注済</option>
			        <option value="入荷済">入荷済</option>
       			</select>
      		 </div>
		    <div class="form-actions">
		        <a class="link-back" href="${pageContext.request.contextPath}/page?name=orderList">キャンセル</a>
		        <input type="submit" value="登録">
     		 </div>
		</form>
	
		<a href="${pageContext.request.contextPath}/page?name=orderList">発注一覧に戻る</a></p>
	</div>
</main>
</body>
</html>