<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="js/jquery-ui.js" type="text/javascript"></script>
<script src="js/filterbar.js" type="text/javascript"></script>
<script src="js/product.js" type="text/javascript"></script>
<script src="js/jquery.ui.dialog-clickoutside.js" type="text/javascript"></script>
<script src="js/header.js" type="text/javascript"></script>
<link href="css/filterbar.css" type="text/css" rel="stylesheet">
<link href="css/header.css" type="text/css" rel="stylesheet">
<link href="css/footer.css" type="text/css" rel="stylesheet">
<link href="css/wrapper.css" type="text/css" rel="stylesheet">
<link href="css/content.css" type="text/css" rel="stylesheet">
<link href="css/jquery-ui.css" type="text/css" rel="stylesheet">
<link href="css/sorted.css" type="text/css" rel="stylesheet">
<link href="css/cart.css" type="text/css" rel="stylesheet">
<title>Ввесь чай</title>
</head>
<body onload="selectOrderBy('${requestScope.orderByValue}');">
	<div id="wrapper">
		<jsp:include page="WEB-INF/header.jsp" />
		<div class="sort_wrapper">
			<form action="product.jsp" method="get" name="sortByForm">
				<div id="sortBy_label">Сортувати за:</div>
				<div id="sort">
					<select size="1" name="orderBy" onchange="submitForm('category_id','${requestScope.category.categoryId}');">
						<option value="none">--</option>
						<option value="price">Ціною: Спочатку дешеві</option>
						<option value="pricedesc">Ціною: Спочатку дорогі</option>
						<option value="name">Ім'ям: А - Я</option>
						<option value="namedesc">Ім'ям: Я - А</option>
					</select>
				</div>
			</form>
		</div>
		<jsp:include page="WEB-INF/filterbar.jsp" />
		<jsp:include page="WEB-INF/content.jsp" />
		<div class="page_wrapper">
			<div id="page">
				<ul>
					<li class="selected_page">1</li>
					<c:choose>
						<c:when test="${requestScope.pageCount le 7}">
							<c:forEach var="page_number" begin="1" end="${requestScope.pageCount - 1}">
								<li>${page_number + 1}</li>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<c:forEach var="page_number" begin="1" end="4">
								<li>${page_number + 1}</li>
							</c:forEach>
							<li>...</li>
							<li>${requestScope.pageCount}</li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
		<div class="clear"></div>
		<%@include file="WEB-INF/footer.jsp"%>
	</div>
</body>
</html>