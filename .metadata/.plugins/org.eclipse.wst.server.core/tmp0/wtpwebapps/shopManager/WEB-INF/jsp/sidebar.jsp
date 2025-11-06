<%@ page contentType="text/html;charset=UTF-8" %>
<link rel ="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<nav class="app-sidebar">
    <ul>
        <li><a href="${pageContext.request.contextPath}/dashboard">ダッシュボード</a></li>
        <li><a href="${pageContext.request.contextPath}/page?name=productList">商品管理</a></li>
        <li><a href="${pageContext.request.contextPath}/page?name=orderList">発注管理</a></li>
        <li><a href="${pageContext.request.contextPath}/page?name=customerList">顧客管理</a></li>
    </ul>
</nav>

