<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品登録</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="header.jsp" />
<jsp:include page="sidebar.jsp" />

<main class="main-content">
	<h2>新規商品登録</h2>
	
	 <form action="${pageContext.request.contextPath}/addProduct" method="post">
	    <p>商品名: <input type="text" name="name" required></p>
	    <p>在庫数: <input type="number" name="stock" min="0" required></p>
	    <p>価格: <input type="number" name="price" min="0" required></p>
	    <input type="submit" value="登録">
	</form>
	
	<a href="${pageContext.request.contextPath}/productList">商品一覧に戻る</a>
</main>
</body>
</html>