<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="content">
	<c:forEach var="product" items="${requestScope.products}">
		<div class="product">
			<a href="#"><img src="${product.image}" width="252" height="224"></a>
			<div class="product_name">
				<a href="#">${product.name}</a>
			</div>
			<div class="product_price"> 
				${product.price} грн.
				<button class="buy_button" onclick="location.href='http://ukrteaco.com/ru/tea.html'">Купити</button>
			</div>
		</div>
	</c:forEach>
</div>