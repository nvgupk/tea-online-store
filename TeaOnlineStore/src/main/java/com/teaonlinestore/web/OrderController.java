package com.teaonlinestore.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.teaonlinestore.model.Address;
import com.teaonlinestore.model.Customer;
import com.teaonlinestore.model.Delivery;
import com.teaonlinestore.model.Payment;
import com.teaonlinestore.model.Purchase;
import com.teaonlinestore.model.PurchaseProduct;
import com.teaonlinestore.service.DeliveryManager;
import com.teaonlinestore.service.DeliveryManagerInterface;
import com.teaonlinestore.service.PaymentManager;
import com.teaonlinestore.service.PaymentManagerInterface;
import com.teaonlinestore.service.PurchaseManager;
import com.teaonlinestore.service.PurchaseManagerInterface;
import com.teaonlinestore.utils.FileUtil;
import com.teaonlinestore.web.component.OrderParametersValidation;
import com.teaonlinestore.web.component.RequestsParametersValidation;
import com.teaonlinestore.web.component.ShoppingCart;

public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DeliveryManagerInterface deliveryManager = new DeliveryManager();
		List<Delivery> deliveries = deliveryManager.getAllValidDelivery();
		PaymentManagerInterface paymentManager = new PaymentManager();
		List<Payment> payments = paymentManager.getAllValidPayment();
		request.setAttribute("deliveries", deliveries);
		request.setAttribute("payments", payments);
		RequestDispatcher view = request.getRequestDispatcher("order.jsp");
		view.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Properties infoProperties = FileUtil.getProperties("info-message.properties");
		HttpSession session = request.getSession();
		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
		RequestsParametersValidation parametersValidation = new OrderParametersValidation();
		if(!parametersValidation.checkForNull(request) || !parametersValidation.checkForNumber(request)) {
			redirectToInfoPage(request, response, infoProperties.getProperty("incorrect-form-parameters"));
		}
		Customer customer = (Customer) session.getAttribute("currentSessionUser");
		if(customer == null) {
			customer.setEmail(request.getParameter("email"));
			customer.setFirstName(request.getParameter("name"));
			customer.setLastName(request.getParameter("surname"));
			customer.setPhoneNumber(request.getParameter("phonenumber"));
			Address address = new Address();
			address.setCity(request.getParameter("city"));
			address.setStreet(request.getParameter("street"));
			customer.setAddress(address);
		}
		DeliveryManagerInterface deliveryManager = new DeliveryManager();
		PaymentManagerInterface paymentManager = new PaymentManager();
		Long deliveryId = Long.valueOf(request.getParameter("delivery"));
		Long paymentId = Long.valueOf(request.getParameter("payment"));
		Delivery delivery = deliveryManager.getDeliveryById(deliveryId);
		Payment payment = paymentManager.getPaymentById(paymentId);
		String additionalInfo = request.getParameter("additional_info");
		Purchase purchase = new Purchase().setCustomer(customer).
				setDelivery(delivery).setPayment(payment).
				setPurchaseDate(new Date()).setAdditionalInfo(additionalInfo);
		List<PurchaseProduct> purchaseProducts = new ArrayList<PurchaseProduct>();
		for(Long productId : shoppingCart.getProducts().keySet()) {
			PurchaseProduct purchaseProduct = new PurchaseProduct();
			purchaseProduct.setProduct(shoppingCart.getProductById(productId));
			purchaseProduct.setPurchase(purchase);
			purchaseProduct.setQuantity(shoppingCart.getProductsQuentity(productId));
			purchaseProduct.setPrice(shoppingCart.getProductById(productId).getPrice());
			purchaseProducts.add(purchaseProduct);
		}
		purchase.setPurchaseProducts(purchaseProducts);
		PurchaseManagerInterface purchaseManager = new PurchaseManager();
		Boolean result = purchaseManager.makePurchase(purchase);
		session.setAttribute("shoppingCart", null);
		if(result) {
			redirectToInfoPage(request, response, infoProperties.getProperty("purchase-was-successful"));
		} else {
			redirectToInfoPage(request, response, infoProperties.getProperty("purchase-was-unsuccessful"));
		}
	}
	
	private void redirectToInfoPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
		request.setAttribute("message", message);
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/infopage.jsp");
		view.forward(request, response);
	}

}
