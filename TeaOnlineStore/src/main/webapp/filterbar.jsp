<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div id="filterbar">
	<form action="#" method="POST" name="filter">
			<div class="attribute">
				<div class="arrow-down"></div>
				attr1
			</div>
			<div class="attribute_value_list">
				<div>
					<label><input type="checkbox" name="Зелений">val1</label>
				</div>
				<div>
					<label><input type="checkbox" name="Чорний">val2</label>
				</div>
				<div>
					<label><input type="checkbox" name="Білий">val3</label>
				</div>
			</div>
		<div class="button">
			<input type="submit" name="showWithFilter" value="Показати">
			<input type="reset" name="resetFilter" value="Скинути">
		</div>
	</form>
</div>