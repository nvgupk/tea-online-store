<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<link href="css/header.css" type="text/css" rel="stylesheet">
</head>
<div id="header">
	<img src="Images/logo.png" width="218" height="75">
	<div id="top_header">
		<form action="#" method="get" name="search">
			<input type="text" name="searchQuery"> <input type="submit"
				name="searchButton" value="Пошук">
		</form>
	</div>
	<div id="mid_header">
		<c:if test="${sessionScope.user eq null}">
			<div>
				Ласкаво просимо (<a href="login.jsp">Увійти</a>)
			</div>
			<div>
				<a href="registration.html">Зареєструватися</a>
			</div>
		</c:if>
		<c:if test="${sessionScope.user ne null}">
			<div>Ласкаво просимо, ${sessionScope.user}</div>
			<div>
				<a href="personal.jsp">Ваш кабінет</a>
			</div>
		</c:if>
	</div>
	<div id="bot_header">
		<ul id="menu">
			<c:forEach var="category" items="${requestScope.categories}">
				<li><a href="#">${category.name}</a>
					<ul>
						<li><a href="#">Зелений чай</a></li>
						<li><a href="#">Чорний чай</a></li>
						<li><a href="#">Білий чай</a></li>
					</ul>
				</li>
			</c:forEach>
			<li><a href="#">Про компанію</a>
				<ul>
					<li><a href="#">Історія компанії</a></li>
					<li><a href="#">Новини</a></li>
					<li><a href="#">Оплата і доставка</a></li>
				</ul></li>
		</ul>
	</div>
</div>