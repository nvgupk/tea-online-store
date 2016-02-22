<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/personal.css" type="text/css" rel="stylesheet">
<link href="css/wrapper.css" type="text/css" rel="stylesheet">
<link href="css/tooltipster.css" type="text/css" rel="stylesheet">
<link href="css/tooltipster-shadow.css" type="text/css" rel="stylesheet"/>
<link href="css/jquery-ui.css" type="text/css" rel="stylesheet">
<link href="css/cart.css" type="text/css" rel="stylesheet">
<link href="css/header.css" type="text/css" rel="stylesheet">
<link href="css/footer.css" type="text/css" rel="stylesheet">
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="js/jquery-ui.js" type="text/javascript"></script>
<script src="js/jquery.tooltipster.min.js" type="text/javascript" ></script>
<script src="js/jquery.form.min.js" type="text/javascript"></script>
<script src="js/personal.js" type="text/javascript"></script>
<script src="js/header.js" type="text/javascript"></script>
<script src="js/jquery.ui.dialog-clickoutside.js" type="text/javascript"></script>
<title>Мій кабінет</title>
</head>
<body>
	<div id="wrapper">
		<jsp:include page="WEB-INF/header.jsp" />
		<div id="personal">
			<div id="personal-menu-block">
				<div class="my-office-label">Мій кабінет</div>
				<ul id="personal-menu">
					<li id="personal-data-item" class="active-menu-item">Особисті дані</li>
					<li id="personal-order-item">Замовлення</li>
				</ul>
			</div>
			<div id="personal-data" class="non-active-tab">
				<h3>Редагувати особисті дані</h3>
				<div id="ajax-status-message">
				</div>
				<form action="Personal" method="POST" name="personal-form">
					<div class="data-name">
						Ім'я
					</div>
					<div class="data-value">
						<input class="datainput" type="text" name="name" maxlength="50" value="${sessionScope.currentSessionUser.firstName}" required>
					</div>
					<div class="data-name">
						Фамілія
					</div>
					<div class="data-value">
						<input class="datainput" type="text" name="surname" maxlength="50" value="${sessionScope.currentSessionUser.lastName}" required>
					</div>
					<div class="data-name">
						Місто
					</div>
					<div class="data-value">
						<input class="datainput" type="text" name="city" value="${sessionScope.currentSessionUser.address.city}" maxlength="50">
					</div>
					<div class="data-name">
						Вулиця/Номер будинку:
					</div>
					<div class="data-value">
						<input class="datainput" type="text" name="street" value="${sessionScope.currentSessionUser.address.street}" maxlength="50">
					</div>
					<div class="data-name">
						Телефон:
					</div>
					<div class="data-value">
						<input class="datainput" type="text" name="phonenumber" value="${sessionScope.currentSessionUser.phoneNumber}" required>
						<img class="tooltip img-status" id="phonenumber_status_img" src="Images/successful.png" width="18" height="18">
					</div>
					<div class="data-name">
						E-mail:
					</div>
					<div class="data-value">
						<input class="datainput" type="text" name="email" value="${sessionScope.currentSessionUser.email}" required>
						<img class="tooltip img-status" id="email_status_img" src="Images/successful.png" width="18" height="18">
					</div>
					<div class="clear"></div>
					<h4>Змінити пароль</h4>
					<div class="data-name">
						Старий пароль:
					</div>
					<div class="data-value">
						<input class="datainput" type="password" name="old-password" maxlength="32">
						<img class="tooltip img-status" id="old_password_status_img" src="Images/successful.png" width="18" height="18">
					</div>
					<div class="data-name">
						Новий пароль:
					</div>
					<div class="data-value">
						<input class="datainput" type="password" name="password" maxlength="32">
						<img class="tooltip img-status" id="password_status_img" src="Images/successful.png" width="18" height="18">
					</div>
					<div class="data-name">
						Підтвердити пароль:
					</div>
					<div class="data-value">
						<input class="datainput" type="password" name="confPassword" maxlength="32">
						<img class="tooltip img-status" id="confPassword_status_img" src="Images/successful.png" width="18" height="18">
					</div>
					<div class="clear"></div>
					<div id="save-change">
						<input type="submit" name="save-change-button" value="Зберегти">
					</div>
				</form>
			</div>
			<div id="personal-order">
				<h3>Попередні замовлення</h3>
				<c:forEach var="purchase" items="${requestScope.purchases}">
					<div class="personal-order-info">
						<div class="arrow-down"></div>
						<div class="order-id">№ ${purchase.purchaseId}</div>
						<div class="order-date">${purchase.purchaseDate}</div>
						<div class="order-img-list">
							<c:forEach var="purchaseProduct" items="${purchase.purchaseProducts}" begin="0" end="4">
								<img class="order-img" src="ImageLoader?image=${purchaseProduct.product.image}" width="24" height="24">
							</c:forEach>
						</div>
						<div class="order-quantity">Всього товару: ${purchase.totalQuantity}</div>
						<div class="order-price">Ціною: ${purchase.totalPrice} грн.</div>
						<div class="order-status">
							<c:choose>
								<c:when test="${empty purchase.status}">
									Обробляється
								</c:when>
								<c:otherwise>
									${purchase.status.name}
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div class="perosnal-order-detailed-info-list">
						<c:forEach var="purchaseProduct" items="${purchase.purchaseProducts}">
							<div class="perosnal-order-detailed-info">
								<div class="made-order-product">
									<img class="made-order-product-img" src="ImageLoader?image=${purchaseProduct.product.image}" width="67" height="64">
									<div class="made-order-product-name">${purchaseProduct.product.name}</div>
									<div class="made-order-product-price">
										<span>Ціна за одиницю:</span> ${purchaseProduct.price} грн.
									</div>
									<div class="made-order-product-number">
										<span>Кількість:</span> ${purchaseProduct.quantity}
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</c:forEach>
			</div>
		</div>
		<div class="clear"></div>
		<%@include file="WEB-INF/footer.jsp"%>
	</div>
</body>
</html>