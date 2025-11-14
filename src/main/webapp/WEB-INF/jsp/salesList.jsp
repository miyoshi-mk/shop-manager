<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.Sales, model.dao.ProductDAO, model.dao.CustomerDAO, model.Product, model.Customer" %>
<%
    List<Sales> salesList = (List<Sales>) request.getAttribute("salesList");
    ProductDAO pdao = new ProductDAO();
    CustomerDAO cdao = new CustomerDAO();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>売上一覧</title>
<link rel="stylesheet"  href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="header.jsp" />
<jsp:include page="sidebar.jsp" />

<main class="main-content">
	<h2>売上一覧</h2>

	<div class="table-actions">
	    <a href="${pageContext.request.contextPath}/sales-insert" class="btn add-btn">＋ 新規登録</a>
	</div>

	<%
	    if (request.getAttribute("error") != null) {
	%>
	    <p class="error"><%= request.getAttribute("error") %></p>
	<%
	    }
	%>

	<table class="sales-table">
	    <tr>
	        <th>ID</th>
	        <th>商品名</th>
	        <th>顧客名</th>
	        <th>数量</th>
	        <th>日時</th>
	        <th>備考</th>
	    </tr>

	<%
	    if (salesList != null && !salesList.isEmpty()) {
	        for (Sales s : salesList) {
	            Product p = pdao.selectById(s.getProductId());
	            Customer c = cdao.selectAll().stream()
	                    .filter(x -> x.getId() == s.getCustomerId())
	                    .findFirst()
	                    .orElse(null);
	%>
	    <tr>
	        <td><%= s.getSaleId() %></td>
	        <td><%= (p != null ? p.getProductName() : "不明") %></td>
	        <td><%= (c != null ? c.getName() : "不明") %></td>
	        <td><%= s.getQuantity() %></td>
	        <td>
			<%
			    Object dt = s.getSaleDate();
			    if (dt != null) {
			        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			        if (dt instanceof java.sql.Timestamp) {
			            out.print(sdf.format((java.sql.Timestamp) dt));
			        } else {
			            out.print(dt.toString());
			        }
			    }
			%>
	        <td><%= (s.getRemarks() != null ? s.getRemarks() : "") %></td>
	    </tr>
	<%
	        }
	    } else {
	%>
	    <tr><td colspan="6" style="text-align:center;">売上データがありません</td></tr>
	<%
	    }
	%>
	</table>
</main>
</body>
</html>