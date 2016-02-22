<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="css/registration.css" type="text/css" rel="stylesheet">
<link href="css/tooltipster.css" type="text/css" rel="stylesheet">
<link href="css/header.css" type="text/css" rel="stylesheet">
<link href="css/cart.css" type="text/css" rel="stylesheet">
<link href="css/tooltipster-shadow.css" type="text/css" rel="stylesheet"/>
<link href="css/wrapper.css" type="text/css" rel="stylesheet">
<link href="css/jquery-ui.css" type="text/css" rel="stylesheet">
<link href="css/footer.css" type="text/css" rel="stylesheet">
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="js/jquery-ui.js" type="text/javascript"></script>
<script src="js/jquery.ui.dialog-clickoutside.js" type="text/javascript"></script>
<script src="js/header.js" type="text/javascript"></script>
<script src="js/jquery.tooltipster.min.js" type="text/javascript" ></script>
<script src="js/registration.js" type="text/javascript"></script>
<title>Реєстрація</title>
</head>
<body>
	<div id="wrapper">
		<jsp:include page="WEB-INF/header.jsp" />
		<div id="registration">
			<h2>Зареєструватися</h2>
			<p id="advan">
				Після реєстрації ви зможете отримувати повідомлення про статус
				вашого замовлення,<br> переглядати попередні покупки.
			</p>
			<form action="Registration" method="POST" name="registration">
				<div id="l">
					<p>
						Ім'я<span class="required_star">*</span>:<br> 
						<input class="datainput" type="text" name="name" maxlength="50" required>
					</p>
					<p>
						Фамілія<span class="required_star">*</span>:<br> 
						<input class="datainput" type="text" name="surname" maxlength="50" required>
					</p>
					<p>
						Місто:<br> <input class="datainput" type="text" name="city" maxlength="50">
					</p>
					<p>
						Вулиця/Номер будинку:<br> 
						<input class="datainput" type="text" name="street" maxlength="50">
					</p>
				</div>

				<div id="r">
					<p>
						Телефон<span class="required_star">*</span>:<br> 
						<input class="datainput" type="text" name="phonenumber" required>
						<img class="tooltip" id="phonenumber_status_img" src="Images/successful.png" width="18" height="18">
					</p>
					<p>
						E-mail<span class="required_star">*</span>:<br> 
						<input class="datainput" type="text" name="email" required> 
						<img class="tooltip" id="email_status_img" src="Images/successful.png" width="18" height="18">
					</p>
					<p>
						Пароль<span class="required_star">*</span>:<br> 
						<input class="datainput" type="password" name="password" maxlength="32" required> 
						<img class="tooltip" id="password_status_img" src="Images/successful.png" width="18" height="18">
					</p>
					<p>
						Підтвердити пароль<span class="required_star">*</span>:<br> 
						<input class="datainput" type="password" name="confPassword" maxlength="32" required> 
						<img class="tooltip" id="confPassword_status_img" src="Images/successful.png" width="18" height="18">
					</p>
				</div>

				<div class="clear"></div>
				<div id="confReg">
					<input class="conf" type="submit" name="createAccount"
						value="Створити акаунт">
				</div>
			</form>
		</div>
		<div class="clear"></div>
		<%@include file="WEB-INF/footer.jsp"%>
	</div>
</body>
</html>