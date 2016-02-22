<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="css/infopage.css" type="text/css" rel="stylesheet">
	<link href="css/wrapper.css" type="text/css" rel="stylesheet">
  	<script language="javascript" type="text/javascript">
  		window.setTimeout('window.location="welcome.jsp";', 10000);
  	</script>
<title>Повідомлення</title>
</head>
<body>
	<div id="wrapper">
		<div id="info">
			${requestScope.message}<br>
			<a href="welcome.jsp">На головну</a>
		</div>
	</div>
</body>
</html>