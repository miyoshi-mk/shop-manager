<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.dao.ProductDAO, model.dao.CustomerDAO, model.Product, model.Customer" %>
<%
    ProductDAO pdao = new ProductDAO();
    CustomerDAO cdao = new CustomerDAO();
    List<Product> products = pdao.selectAll();
    List<Customer> customers = cdao.selectAll();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>売上登録</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="header.jsp" />
<jsp:include page="sidebar.jsp" />

<main class="main-content">
	<div class="form-container">
		<h2>売上登録フォーム</h2>
		<form action="${pageContext.request.contextPath}/sales-insert" method="post">
			<div class="form-group">
			    <label>商品：</label>
			    <select name="product_id" required>
			        <option value="">選択してください</option>
			        <% for (Product p : products) { %>
			            <option value="<%= p.getProductId() %>"><%= p.getProductName() %>（在庫：<%= p.getStock() %>）</option>
			        <% } %>
			    </select>
			</div>
			<div class="form-group">
			    <label>顧客：</label>
			    <select name="customer_id" required>
			        <option value="">選択してください</option>
			        <% for (Customer c : customers) { %>
			            <option value="<%= c.getId() %>"><%= c.getName() %></option>
			        <% } %>
			    </select>
			</div>
			
			<div class="form-group">
			    <label>数量：</label>
			    <input type="number" name="quantity" min="1" required>
			
			    <label>備考：</label>
			    <input type="text" name="remarks" maxlength="255">
			</div>
			
			<div class="form-actions">
			    <input type="submit"  value="登録">
			</div>
		</form>
		<a href="${pageContext.request.contextPath}/page?name=salesList">売上一覧に戻る</a>
		<% if (request.getAttribute("error") != null) { %>
		    <p style="color:red;"><%= request.getAttribute("error") %></p>
		<%
		}
		%>
	</div>
</main>
</body>
</html>