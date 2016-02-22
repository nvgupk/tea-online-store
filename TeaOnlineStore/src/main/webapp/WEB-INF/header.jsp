<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div id="header">
	<a href="welcome.jsp"><img id="logo" src="Images/logo.png" width="218" height="75"></a>
	<div id="top_header">
		<form action="Search" method="get" name="search">
			<input type="text" name="searchQuery"> 
			<input type="submit" name="searchButton" value="Пошук">
			<div id="search-result">
				<ul id="search-result-list">
				</ul>
			</div>
		</form>
	</div>
	<div id="mid_header">
		<c:if test="${sessionScope.currentSessionUser eq null}">
			<div class="mid_header_menu">
				Ласкаво просимо (<a href="login.jsp">Увійти</a>)
			</div>
			<div class="mid_header_menu">
				<a href="registration.jsp">Реєстрація</a>
			</div>
		</c:if>
		<c:if test="${sessionScope.currentSessionUser ne null}">
			<div class="mid_header_menu">Ласкаво просимо, <span id="currentUser">${sessionScope.currentSessionUser.firstName}</span> (<a href="logout">Вийти</a>)</div>
			<div class="mid_header_menu">
				<a href="personal.jsp">Ваш кабінет</a>
			</div>
		</c:if>
		<div id="shopping_cart">
			<div id="cart_count">
				<c:choose>
					<c:when test="${empty sessionScope.shoppingCart}">
						0
					</c:when>
					<c:otherwise>
						${sessionScope.shoppingCart.size}
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
	<div id="bot_header">
		<ul id="menu">
			<c:forEach var="entry" items="${requestScope.categoryKinds}">
				<li><a href="product.jsp?category_id=${entry.key.categoryId}">${entry.key.name}</a>
					<ul>
						<c:forEach var="kind" items="${entry.value}">
							<li><a
								href="product.jsp?category_id=${entry.key.categoryId}&kind=${kind}">${kind}</a></li>
						</c:forEach>
					</ul></li>
			</c:forEach>
			<li><a href="#">Про компанію</a>
				<ul>
					<li><a href="#">Історія компанії</a></li>
					<li><a href="#">Новини</a></li>
					<li><a href="#">Оплата і доставка</a></li>
				</ul></li>
		</ul>
	</div>
	<div id="cart_product_list">
		<div id="cart_labels">
			<div class="img_label">Продукт</div>
			<div class="name_label">Назва</div>
			<div class="price_label">Ціна за одиницю</div>
			<div class="number_label">Кількість</div>
			<div class="price_label">Загальна ціна</div>
			<div class="delete_label">Видалити</div>
		</div>
		<div class="footer_cart">
    		<div id="total">
      			Всього: <span>0</span> грн.
      			<button id="make_purchase">Оформити замовлення</button>
   	 		</div>
  		</div>
	</div>
</div>