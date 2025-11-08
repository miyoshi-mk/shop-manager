<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="loginpage">
<div class="login-container">
    <h2>ログイン</h2>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <label>ユーザー名：</label><br>
        <input type="text" name="userName" required><br>
        <label>パスワード：</label><br>
        <input type="password" name="password" required><br>
        <input type="submit" value="ログイン">
        
        <a href="${pageContext.request.contextPath}/user-insert">新規ユーザー登録</a>
    </form>
    <p class="message">${message}</p>
    <p class="error">${error}</p>
</div>
</body>
</html>