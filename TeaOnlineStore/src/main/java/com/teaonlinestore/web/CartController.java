package com.teaonlinestore.web;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.teaonlinestore.web.component.ShoppingCart;

public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(30*24*60*60);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		if(action == null) {
			action = "";
		}
		Long productId = null;
		JSONObject json = null;
		switch(action) {
			case "add":
				productId = Long.valueOf(request.getParameter("productId"));
				int numberOfProductsAfterAdding = addProductToShoppingCart(session, productId);
				json = new JSONObject();
				json.put("productsNumberInCart", numberOfProductsAfterAdding);
				response.getWriter().write(json.toJSONString());
				break;
			case "remove":
				productId = Long.valueOf(request.getParameter("productId"));
				int numberOfProductsAfterRemoving = removeProductFromShoppingCart(session, productId);
				json = new JSONObject();
				json.put("productsNumberInCart", numberOfProductsAfterRemoving);
				response.getWriter().write(json.toJSONString());
				break;
			case "getAll":
				ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
				if(shoppingCart != null) {
					JSONArray jsonArray = shoppingCart.getShoppingCartAsJSONArray();
					response.getWriter().write(jsonArray.toJSONString());
				} else {
					json = new JSONObject();
					json.put("isEmpty", "true");
					response.getWriter().write(json.toJSONString());
				}
				break;
			default:
				setQuantityForProductsInShoppingCart(session, request.getReader());
				json = new JSONObject();
				json.put("url", "order.jsp");
				response.getWriter().write(json.toJSONString());
				break;
		}
	}
	
	private int addProductToShoppingCart(HttpSession session, Long productId) {
		ShoppingCart shoppingCart = null;
		synchronized (session.getId().intern()) {
			shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
			if (shoppingCart == null) {
				shoppingCart = new ShoppingCart();
			}
			shoppingCart.addNewProduct(productId);
			session.setAttribute("shoppingCart", shoppingCart);
		}
		return shoppingCart.getSize();
	}
	
	private int removeProductFromShoppingCart(HttpSession session, Long productId) {
		ShoppingCart shoppingCart = null;
		synchronized (session.getId().intern()) {
			shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
			if (shoppingCart == null) {
				shoppingCart = new ShoppingCart();
			}
			shoppingCart.removeProduct(productId);
			session.setAttribute("shoppingCart", shoppingCart);
		}
		return shoppingCart.getSize();
	}
	
	private void setQuantityForProductsInShoppingCart(HttpSession session, BufferedReader reader) throws IOException{
		ShoppingCart shoppingCart = null;
		synchronized (session.getId().intern()) {
			shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
			if (shoppingCart == null) {
				shoppingCart = new ShoppingCart();
			}
			JSONArray jsonArray = parseJsonRequest(reader);
			for(Object obj : jsonArray) {
				JSONObject json = (JSONObject) obj;
				Long productId = Long.valueOf((String) json.get("productId"));
				Integer quantity = Integer.valueOf((String) json.get("quantity"));
				shoppingCart.addProductsQuantity(productId, quantity);
			}
			session.setAttribute("shoppingCart", shoppingCart);
		}
	}
	
	private JSONArray parseJsonRequest(BufferedReader reader) throws IOException {
		JSONParser parser = new JSONParser();
		Object obj;
		JSONArray jsonArray = null;
		try {
			obj = parser.parse(reader);
			jsonArray = (JSONArray) obj;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return jsonArray;
	}
}
