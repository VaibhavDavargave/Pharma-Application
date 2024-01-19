package com.excelr.pharma.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.excelr.pharma.dao.PharmaDaoImpl;
import com.excelr.pharma.exceptions.PharmaException;
import com.excelr.pharma.vo.ShippingMasterInfo;

/**
 * Servlet implementation class ShippingChargeServlet
 */
@WebServlet("/ShippingChargeServlet")
public class ShippingChargeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		try {
			PharmaDaoImpl dao = new PharmaDaoImpl();
			List<ShippingMasterInfo> values = dao.getAllShippingMaster();
			System.out.println(values);
			request.setAttribute("values", values);
			dispatcher = request.getRequestDispatcher("viewShippingMaster.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String medTypeCode = request.getParameter("medicineTypeCode");
		String weightRange = request.getParameter("weightRange");
		double shippingCharge = Double.parseDouble(request.getParameter("shippingCharge"));
		System.out.println(medTypeCode + " " + weightRange + " " + shippingCharge);
		ShippingMasterInfo masterInfo = new ShippingMasterInfo();
		masterInfo.setMedicineTypeCode(medTypeCode);
		masterInfo.setWeightRange(weightRange);
		masterInfo.setShippingCharge(shippingCharge);
		HttpSession session = request.getSession();
		PharmaDaoImpl dao = new PharmaDaoImpl();
		try {
			boolean checkShippFlag = dao.checkAlreadyshippingCharge(medTypeCode, weightRange);

			if (!checkShippFlag) {
				boolean addShipFlag = dao.addShippingMaster(masterInfo);
				if (addShipFlag) {
					session.setAttribute("succMsg",
							"Shipping Master: " + medTypeCode + " and " + weightRange + " Added Successfully");
					response.sendRedirect("addShipping.jsp");
				} else {
					session.setAttribute("failedMsg", "Something Error on Server..s");
					response.sendRedirect("addShipping.jsp");
				}
			} else {
				session.setAttribute("failedMsg",
						"Shipping Master: " + medTypeCode + " and " + weightRange + "  is already exists");
				response.sendRedirect("addShipping.jsp");
			}
		} catch (PharmaException e) {
			e.printStackTrace();
		}
	}

}
