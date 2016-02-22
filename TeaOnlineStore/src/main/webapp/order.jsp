<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/header.css" type="text/css" rel="stylesheet">
<link href="css/cart.css" type="text/css" rel="stylesheet">
<link href="css/order.css" type="text/css" rel="stylesheet">
<link href="css/wrapper.css" type="text/css" rel="stylesheet">
<link href="css/jquery-ui.css" type="text/css" rel="stylesheet">
<link href="css/tooltipster-shadow.css" type="text/css" rel="stylesheet" />
<link href="css/tooltipster.css" type="text/css" rel="stylesheet">
<link href="css/footer.css" type="text/css" rel="stylesheet">
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="js/jquery-ui.js" type="text/javascript"></script>
<script src="js/jquery.ui.dialog-clickoutside.js" type="text/javascript"></script>
<script src="js/jquery.tooltipster.min.js" type="text/javascript"></script>
<script src="js/header.js" type="text/javascript"></script>
<script src="js/order.js" type="text/javascript"></script>
<title>Замовлення</title>
</head>
<body>
	<div id="wrapper">
		<jsp:include page="WEB-INF/header.jsp" />
		<div id="order">
			<h2>Оформлення замовлення</h2>
			<form action="Order" method="POST" name="order">
				<div id="l">
					<p>Адреса замовника</p>
					<p>
						Ім'я<span class="required_star">*</span>:<br> 
						<input class="datainput" type="text" name="name" maxlength="50" required value="${sessionScope.currentSessionUser.firstName}">
					</p>
					<p>
						Фамілія<span class="required_star">*</span>:<br>
						<input class="datainput" type="text" name="surname" maxlength="50" required
							value="${sessionScope.currentSessionUser.lastName}">
					</p>
					<p>
						E-mail<span class="required_star">*</span>:<br> 
						<input class="datainput" type="text" name="email" required
							value="${sessionScope.currentSessionUser.email}">
						<img class="tooltip" id="email_status_img" src="Images/successful.png"
							width="18" height="18">
					</p>
					<p>
						Місто<span class="required_star">*</span>:<br> 
						<input class="datainput" type="text" name="city" maxlength="50" required
							value="${sessionScope.currentSessionUser.address.city}">
					</p>
					<p>
						Вулиця/Номер будинку:<br> <input class="datainput" type="text" name="street" maxlength="50"
						value="${sessionScope.currentSessionUser.address.street}">
					</p>
					<p>
						Телефон<span class="required_star">*</span>:<br> 
						<input class="datainput" type="text" name="phonenumber" required
							value="${sessionScope.currentSessionUser.phoneNumber}">
						<img class="tooltip" id="phonenumber_status_img" src="Images/successful.png" width="18" height="18">
					</p>
				</div>
				<div id="r">
					<p>Спосіб оплати</p>
					<c:forEach var="payment" items="${requestScope.payments}" varStatus="loop">
						<div>
							<input type="radio" name="payment" value="${payment.paymentId}"
								<c:if test="${loop.index eq 0}">checked</c:if>>
							${payment.name}
						</div>
					</c:forEach>
					<p>Спосіб доставки</p>
					<c:forEach var="delivery" items="${requestScope.deliveries}" varStatus="loop">
						<div>
							<input type="radio" name="delivery" value="${delivery.deliveryId}"
							<c:if test="${loop.index eq 0}">checked class="prev_deliv_val"</c:if>>
							${delivery.name}
							<c:if test="${!(empty delivery.price)}">
								(<span> ${delivery.price}</span> грн.)
							</c:if>
						</div>
					</c:forEach>
					<p>Додаткова Інформація</p>
					<textarea id="add_info" class="datainput" name="additional_info" rows="4" maxlength="1000" value="">
  					</textarea>
  					<div class="clear"></div>
				</div>
				<div class="clear"></div>
				<input id="submit_order" type="submit">
			</form>
			<div class="clear"></div>
			<div id="order_product_list">
				<div id="order_labels">
					<div class="img_label">Продукт</div>
					<div class="name_label">Назва</div>
					<div class="price_label">Ціна за одиницю</div>
					<div class="number_label">Кількість</div>
					<div class="price_label">Загальна ціна</div>
				</div>
				<c:forEach var="cartProduct" items="${sessionScope.shoppingCart.products}">
					<div class="order_product">
						<img class="order_product_img" src="ImageLoader?image=${cartProduct.value.product.image}" width="128" height="128">
						<div class="order_product_name">${cartProduct.value.product.name}</div>
						<div class="order_product_price order_product_unit_price">
							<span>${cartProduct.value.product.price}</span> грн.
						</div>
						<div class="order_product_number">${cartProduct.value.quantity}</div>
						<div class="order_product_price order_product_total_price">
							<span>${cartProduct.value.totalPrice}</span> грн.
						</div>
					</div>
				</c:forEach>
				<div class="order_footer_cart">
					<div id="order_total">
						Всього: <span>${sessionScope.shoppingCart.totalPrice}</span> грн.
						<button id="confirm_order">Підтвердити замовлення</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="clear"></div>
	<%@include file="WEB-INF/footer.jsp"%>
</body>
</html>