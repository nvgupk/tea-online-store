<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="js/jquery-ui.js" type="text/javascript"></script>
<script src="js/jquery.ui.dialog-clickoutside.js" type="text/javascript"></script>
<script src="js/header.js" type="text/javascript"></script>
<link href="css/header.css" type="text/css" rel="stylesheet">
<link href="css/footer.css" type="text/css" rel="stylesheet">
<link href="css/wrapper.css" type="text/css" rel="stylesheet">
<link href="css/content.css" type="text/css" rel="stylesheet">
<link href="css/jquery-ui.css" type="text/css" rel="stylesheet">
<link href="css/cart.css" type="text/css" rel="stylesheet">
<link href="css/searchresult.css" type="text/css" rel="stylesheet">
<title>Результати пошуку</title>
</head>
<body>
	<div id="wrapper">
		<jsp:include page="WEB-INF/header.jsp" />
		<c:choose>
			<c:when test="${fn:length(products) gt 0}">
				<div id="search-succ">Результати пошуку</div>
			</c:when>
			<c:otherwise>
				<div id="search-empt">Пошук не дав результатів</div>
			</c:otherwise>
		</c:choose>
		<div id="content">
			<c:forEach var="product" items="${requestScope.products}">
				<div id="content_product_${product.productId}" class="product">
					<a href="productinfo.jsp?category_id=${requestScope.category.categoryId}&product_id=${product.productId}"><img src="ImageLoader?image=${product.image}" width="252" height="224"></a>
					<div class="product_name">
						<a href="productinfo.jsp?category_id=${requestScope.category.categoryId}&product_id=${product.productId}">${product.name}</a>
					</div>
					<div class="product_price">
						${product.price} грн.
						<div id="buttons">
							<button class="product_button buy_button" onclick="addProductToCart('${product.productId}', 'content_product_${product.productId}', 'true');">Купити</button>
							<button class="product_button cart_button" onclick="addProductToCart('${product.productId}', 'content_product_${product.productId}');">В кошик</button>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="clear"></div>
		<%@include file="WEB-INF/footer.jsp"%>
	</div>
</body>
</html>