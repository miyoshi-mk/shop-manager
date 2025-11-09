<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, model.Customer" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

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
	<a href="${pageContext.request.contextPath}/add-customer">新規登録</a>
	<c:if test="${not empty customerList}">
	<table class="customer-table">
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
            <c:forEach var="c" items="${customerList}">
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
    </c:if>
<a href="${pageContext.request.contextPath}/dashboard">ダッシュボードへ戻る</a>
</main>
</body>
</html>