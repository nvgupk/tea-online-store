<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div id="filterbar">
	<form action="product.jsp" method="GET" name="filter_form">
		<c:forEach var="entryAttr" items="${requestScope.attributes}">
			<div class="attribute">
				<div class="arrow-down"></div>
				${entryAttr.value}
			</div>
			<div class="attribute_value_list">
				<c:forEach var="attrVal" items="${requestScope.attributeValues[entryAttr.key]}">
					<c:set var="isChecked" value="false"/>
					<div>
						<c:forEach var="checkedAttrVal" items="${requestScope[entryAttr.key]}">
							<c:if test="${attrVal eq checkedAttrVal}">
								<c:set var="isChecked" value="true"/>
							</c:if>
						</c:forEach>
						<c:choose>
							<c:when test="${isChecked}">
								<label><input class="filter_checkbox" type="checkbox" onchange="submitForm('category_id','${requestScope.category.categoryId}');" name="${entryAttr.key}" value="${attrVal}" checked="checked">${attrVal}</label>
							</c:when>
							<c:otherwise>
								<label><input class="filter_checkbox" type="checkbox" onchange="submitForm('category_id','${requestScope.category.categoryId}');" name="${entryAttr.key}" value="${attrVal}">${attrVal}</label>
							</c:otherwise>
						</c:choose>
					</div>
				</c:forEach>
			</div>
		</c:forEach>
		<div class="button">
			<input type="submit" name="showWithFilter" value="Показати">
			<input type="button" name="resetFilter" value="Скинути" onclick="clearAndSubmit('category_id','${requestScope.category.categoryId}')">
		</div>
	</form>
</div>