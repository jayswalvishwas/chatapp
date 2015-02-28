package com.javapapers.java.gcm;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jboss.weld.util.collections.ArraySet;

@WebServlet("/GCMNotification")
public class GCMNotification extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Put your Google API Server Key here
	private static final String GOOGLE_SERVER_KEY = "AIzaSyBiVt9qDiILNtwYZ1PebxIVw2F6Xz5-FBk";

	// Put your Google Project number here
	final String GOOGLE_USERNAME = "938692234412" + "@gcm.googleapis.com";

	public GCMNotification() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			String userMessage = request.getParameter("message");
			//Set<String> regIdSet = RegIdManager.readFromFile();
			Set<String> regIdSet =new ArraySet<>();
                                                 regIdSet.add("APA91bHh1MQRzAa0tR5zK1CncyhouaASpmSZMvkE1CDqgJtpuhFpkADlbdkNbKXXuNkGF8m2Fld_orstHTcwNKrj_ntB3dvhU8TxGGBTsvg7oyKPmd17_sJzUQTNrlZqBOYrZEuwFHCCBeWJDpCTIkxltmK4LpaXGg");
                                                 regIdSet.add("APA91bHjnIZhbrcuzKBhhNh1WS0Wiim5zE2ss8u06Of3C5aOHcZ7t0iIIBl38HUT0-5jVPqCXEYzHkHodgk7BwWslcta8hT4QTOMRUwUQCvGih4dFtLVqZCOJY0pJlTkCqxWU2gQrMrUwaI3kzgTmCzdD_4yNYWFvw");
			String toDeviceRegId = (String) (regIdSet.toArray())[0];
			SmackCcsClient.sendMessage(GOOGLE_USERNAME, GOOGLE_SERVER_KEY,
					toDeviceRegId,userMessage);
			request.setAttribute("pushStatus", "Message Sent.");
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("pushStatus", e.toString());
		}
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
