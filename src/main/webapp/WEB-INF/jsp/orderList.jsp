<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Order" %>
<%@ page import="model.dao.OrderDAO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>発注管理</title>
<link rel="stylesheet"  href="${pageContext.request.contextPath}/css/style.css">
<script>
function editOrder(id) {
    window.location.href = "${pageContext.request.contextPath}/editOrder?id=" + id;
}
function deleteOrder(id) {
    if(confirm("本当に削除しますか？")) {
        window.location.href = "${pageContext.request.contextPath}/deleteOrder?id=" + id;
    }
}
</script>
</head>
<body>
<jsp:include page="header.jsp" />
<jsp:include page="sidebar.jsp" />

<main class="main-content">
	<h2>発注管理</h2>
	
	<table border="1" cellpadding="5" cellspacing="0">
	    <tr>
	        <th>注文ID</th>
	        <th>商品ID</th>
	        <th>数量</th>
	        <th>ステータス</th>
	        <th>注文日</th>
	        <th>操作</th>
	    </tr>
	<%
	    OrderDAO dao = new OrderDAO();
	    List<Order> orders = dao.selectAll();
	    for(Order o : orders){
	        String rowClass = "";
	        switch(o.getStatus()){
	            case "未発注":
	                rowClass = "status-unordered";
	                break;
	            case "発注済":
	                rowClass = "status-ordered";
	                break;
	            case "入荷済":
	                rowClass = "status-received";
	                break;
	            default:
	                rowClass = "";
	        }
	%>
	    <tr class="<%=rowClass%>">
	        <td><%=o.getOrderId()%></td>
	        <td><%=o.getProductId()%></td>
	        <td><%=o.getQuantity()%></td>
	        <td><%=o.getStatus()%></td>
	        <td><%=o.getOrderDate()%></td>
	        <td>
	            <a href="${pageContext.request.contextPath}/updateOrderStatus?id=<%= o.getOrderId() %>&status=発注済">発注済</a> |
                <a href="${pageContext.request.contextPath}/updateOrderStatus?id=<%= o.getOrderId() %>&status=入荷済">入荷済</a>
	        </td>
	    </tr>
	<% } %>
	</table>
	
	<a href="${pageContext.request.contextPath}/addOrder">新規発注</a>
</main>
</body>
</html>