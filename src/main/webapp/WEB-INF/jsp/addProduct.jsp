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
 	<div class="form-container">
		<h2>新規商品登録</h2>
		 <form action="${pageContext.request.contextPath}/addProduct" method="post">
		     <div class="form-group">
			     <label for="name">商品名</label>
	       		 <input type="text" id="name" name="name" required>
       		 </div>
       		 
		   	 <div class="form-group">
			     <label for="stock">在庫数</label>
			     <input type="number" id="stock" name="stock" min="0" required>
      		 </div>

	         <div class="form-group">
			     <label for="price">価格</label>
			     <input type="number" id="price" name="price" min="0" required>
	         </div>
	         
		     <div class="form-actions">
        	 	<input type="submit" value="登録">
      		 </div>
		</form>
		<a href="${pageContext.request.contextPath}/productList">商品一覧に戻る</a>
	</div>	
</main>
</body>
</html>