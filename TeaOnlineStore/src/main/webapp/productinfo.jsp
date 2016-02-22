<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/header.css" type="text/css" rel="stylesheet">
<link href="css/cart.css" type="text/css" rel="stylesheet">
<link href="css/wrapper.css" type="text/css" rel="stylesheet">
<link href="css/productinfo.css" type="text/css" rel="stylesheet">
<link href="css/jquery-ui.css" type="text/css" rel="stylesheet">
<link href="css/footer.css" type="text/css" rel="stylesheet">
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="js/jquery-ui.js" type="text/javascript"></script>
<script src="js/jquery.ui.dialog-clickoutside.js" type="text/javascript"></script>
<script src="js/header.js" type="text/javascript"></script>

<title>${product.name}</title>
</head>
<body>
	<div id="wrapper">
		<jsp:include page="WEB-INF/header.jsp" />
		<div id="productinfo">
			<div class="clear"></div>
			<div id="left-block">
				<img src="ImageLoader?image=${product.image}" width="525" height="525">
			</div>
			<div id="right-block">
				<div id="product-name">
					${product.name}
				</div>
				<div class="product-price">
					<div id="price">${product.price} грн.</div>
					<div id="buttons">
						<button class="product_button cart_button" onclick="addProductToCart('${product.productId}', 'productinfo');">В кошик</button>
						<button class="product_button buy_button" onclick="location.href='http://ukrteaco.com/ru/tea.html'">Купити</button>
					</div>
				</div>
				<div class="title">ОПИС</div>
				<div id="description" class="attribute-desc">
					${product.description}
				</div>
				<c:forEach var="attribute" items="${requestScope.attributeValues}">
					<div class="attr-name">${attribute.key.displayName}</div>
					<div class="attribute-desc">${attribute.value}</div>
					<div class="clear"></div>
				</c:forEach>	
			</div>
			<div class="clear"></div>
		</div>
		<div class="clear"></div>
		<%@include file="WEB-INF/footer.jsp"%>
	</div>
</body>
</html>