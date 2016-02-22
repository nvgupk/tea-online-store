package com.teaonlinestore.web.component;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageLoader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/jpeg");
		String image = request.getParameter("image");
		String imagePath = getServletContext().getInitParameter("imagePath") + image;
		File file = new File(imagePath);
		BufferedInputStream bin = new BufferedInputStream(new FileInputStream(file));
		BufferedOutputStream bout = new BufferedOutputStream(response.getOutputStream());
		int num = 0;
		while((num = bin.read()) != -1) {
			bout.write(num);
		}
		bin.close();
		bout.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
