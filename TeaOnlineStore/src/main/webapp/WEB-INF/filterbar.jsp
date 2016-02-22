<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div id="filterbar">
	<form action="product.jsp" method="GET" name="filter_price">
		<input type="hidden" name="lowestPrice" value="${requestScope.productLowestPrice}">
		<input type="hidden" name="highestPrice" value="${requestScope.productHighestPrice}">
		<div class="attribute">
				<div class="arrow-down"></div>
				Ціна
		</div>
		<div class="attribute_value_list">
			<div id = "priceBlock">
         		Від:<input type="text" name="minPrice" value="${requestScope.curProductMinPrice}">
         		До:<input type="text" name="maxPrice" value="${requestScope.curProductMaxPrice}">
        	</div>
			<div id="slider-price"></div>
			<div class="filter_button">
				<input type="button" name="submitPriceRange" value="Показати" onclick="submitForm('category_id','${requestScope.category.categoryId}')">
			</div>
		</div>
	</form>
	<form action="product.jsp" method="GET" name="filter_form">
		<c:forEach var="entryAttr" items="${requestScope.attributeValues}">
			<div class="attribute">
				<div class="arrow-down"></div>
				${entryAttr.key.displayName}
			</div>
			<div class="attribute_value_list">
				<c:forEach var="attrVal" items="${entryAttr.value}">
					<c:set var="isChecked" value="false"/>
					<div>
						<c:forEach var="checkedAttrVal" items="${requestScope[entryAttr.key.attrName]}">
							<c:if test="${attrVal eq checkedAttrVal}">
								<c:set var="isChecked" value="true"/>
							</c:if>
						</c:forEach>
						<c:choose>
							<c:when test="${isChecked}">
								<label><input class="filter_checkbox" type="checkbox" onchange="submitForm('category_id','${requestScope.category.categoryId}');" name="${entryAttr.key.attrName}" value="${attrVal}" checked="checked">${attrVal}</label>
							</c:when>
							<c:otherwise>
								<label><input class="filter_checkbox" type="checkbox" onchange="submitForm('category_id','${requestScope.category.categoryId}');" name="${entryAttr.key.attrName}" value="${attrVal}">${attrVal}</label>
							</c:otherwise>
						</c:choose>
					</div>
				</c:forEach>
			</div>
		</c:forEach>
		<div class="filter_button">
			<input type="button" name="resetFilter" value="Скинути" onclick="clearAndSubmit('category_id','${requestScope.category.categoryId}');">
		</div>
	</form>
</div>