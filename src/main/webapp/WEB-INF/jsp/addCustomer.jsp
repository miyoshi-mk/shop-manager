<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>顧客登録</title>
</head>
<body>
<jsp:include page="header.jsp" />
<jsp:include page="sidebar.jsp" />

<main class="main-content">
	<h2>顧客登録</h2>
	<div class="form-container">
		<form action="${pageContext.request.contextPath}/add-customer" method="post">
		    <div class="form-group">
			    <label>名前：</label><br>
			    <input type="text" name="name" required><br>
		    </div>
		    <div class="form-group">
			    <label>Email：</label><br>
			    <input type="email" name="email"><br>
		    </div>
		    <div class="form-group">
			    <label>電話番号：</label><br>
			    <input type="text" name="phone"><br>
		    </div>
		    <div class="form-group">
			    <label>住所：</label><br>
			    <textarea name="address" rows="3" cols="30"></textarea><br>
		    </div>
		     <div class="form-actions">
		    	<button type="submit">登録</button>
		    </div>
		</form>
		<p><a href="${pageContext.request.contextPath}/page?name=customerList">顧客一覧に戻る</a></p>
	</div>
</main>
<p style="color:red;">${error}</p>
<a href="${pageContext.request.contextPath}/customerList">顧客一覧へ戻る</a>
</body>
</html>