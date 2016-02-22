<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="content">
	<c:forEach var="page_number" begin="0" end="${requestScope.pageCount - 1}">
		<div id = "page_${page_number + 1}" 
			<c:choose>
				<c:when test="${page_number eq 0}">
					class="active"
				</c:when>
				<c:otherwise>
					class="inactive"
				</c:otherwise>
			</c:choose>
			<c:if test="${page_number ne 0}">
				class="inactive"
			</c:if>
		>
		<c:forEach var="product" items="${requestScope.products}" begin="${page_number*requestScope.productCount }" end="${(page_number + 1)*requestScope.productCount - 1}">
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
	</c:forEach>
</div>