<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Product" %>
<%@ page import="model.dao.ProductDAO" %>

<%
    int id = Integer.parseInt(request.getParameter("id"));
    ProductDAO dao = new ProductDAO();
    Product p = dao.selectById(id);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品編集</title>
<link rel="stylesheet" type="text/css" href="css/dashboard.css">
</head>
<body>
<h1>商品編集</h1>

<form action="editProduct" method="post">
    <input type="hidden" name="id" value="<%= p.getId() %>">
    <p>商品名: <input type="text" name="name" value="<%= p.getProductName() %>" required></p>
    <p>在庫数: <input type="number" name="stock" value="<%= p.getStock() %>" min="0" required></p>
    <p>価格: <input type="number" name="price" value="<%= p.getPrice() %>" min="0" required></p>
    <input type="submit" value="更新">
</form>

<a href="products.jsp">商品一覧に戻る</a>
</body>
</html>