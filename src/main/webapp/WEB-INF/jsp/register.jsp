<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="loginpage">
<div class="login-container">
    <h2>新規ユーザー登録</h2>
    <form action="${pageContext.request.contextPath}/user-insert" method="post">
        <label>ユーザー名：</label><br>
        <input type="text" name="userName" required><br>

        <label>パスワード：</label><br>
        <input type="password" name="password" required><br>

        <input type="submit" value="登録">
    </form>

    <p class="error">${error}</p>
    <p><a href="${pageContext.request.contextPath}/login">ログインページへ戻る</a></p>
</div>
</body>
</html>