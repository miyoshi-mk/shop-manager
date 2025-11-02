<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>顧客管理ページ</title>
<link rel="stylesheet"  href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="header.jsp" />
<jsp:include page="sidebar.jsp" />

<main class="main-content">
	<h2>顧客一覧</h2>
	<table border="1" cellpadding="5" cellspacing="0">
        <thead>
            <tr>
                <th>ID</th>
                <th>名前</th>
                <th>Email</th>
                <th>電話番号</th>
                <th>住所</th>
                <th>登録日時</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="c" items="${customers}">
                <tr>
                    <td>${c.id}</td>
                    <td>${c.name}</td>
                    <td>${c.email}</td>
                    <td>${c.phone}</td>
                    <td>${c.address}</td>
                    <td>${c.registeredAt}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</main>
</body>
</html>