<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Product" %>
<%@ page import="model.dao.ProductDAO" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品管理</title>
<link rel="stylesheet"  href="${pageContext.request.contextPath}/css/style.css">
<script>
function editProduct(id) {
    window.location.href = "${pageContext.request.contextPath}/editProduct?id=" + id;
}

function deleteProduct(id) {
    if(confirm("本当に削除しますか？")) {
        window.location.href = "${pageContext.request.contextPath}/deleteProduct?id=" + id;
    }
}
</script>
</head>
<body>
<jsp:include page="header.jsp" />
<jsp:include page="sidebar.jsp" />

<main class="main-content">
	<h2>商品一覧</h2>
	
	<!-- 新規登録ボタン -->
    <div class="table-actions">
        <a href="${pageContext.request.contextPath}/addProduct" class="btn add-btn">＋ 新規登録</a>
    </div>
    
	 <table class="product-table">
	    <tr>
	        <th>ID</th>
	        <th>商品名</th>
	        <th>在庫数</th>
	        <th>価格</th>
	        <th>操作</th>
	    </tr>
		<%
		    ProductDAO dao = new ProductDAO();
		    List<Product> products = dao.selectAll();
		    for(Product p : products){
		        String rowClass = p.getStock() <= 5 ? "low-stock" : "";
		%>
	    <tr class="<%=rowClass%>">
	        <td><%=p.getProductId()%></td>
	        <td><%=p.getProductName()%></td>
	        <td><%=p.getStock()%></td>
	        <td><%=p.getPrice()%></td>
	        <td>
	            <a href="${pageContext.request.contextPath}/editProduct?id=<%=p.getProductId()%>">編集</a>
	            <a href="${pageContext.request.contextPath}/deleteProduct?id=<%=p.getProductId()%>"
	               class="link-delete"
	               onclick="return confirm('本当に削除しますか？');">削除</a>
	        </td>
	    </tr>
	<% } %>
	</table>
</main>
</body>
</html>