package com.excelr.pharma.controller;

import java.io.IOException;

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
 * Servlet implementation class UpdateShippingServlet
 */
@WebServlet("/UpdateShippingServlet")
public class UpdateShippingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String medTypeCode = request.getParameter("medTypeCode");
		String weightRange = request.getParameter("weightRange");
		RequestDispatcher dispatcher = null;
		PharmaDaoImpl getShipType = new PharmaDaoImpl();
		try {
			ShippingMasterInfo shipTypeInfo = getShipType.findShippingMaster(medTypeCode, weightRange);
			request.setAttribute("shipTypeInfo", shipTypeInfo);
			dispatcher = request.getRequestDispatcher("editShipping.jsp");
			dispatcher.forward(request, response);
		} catch (PharmaException e) {
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
		try {
			PharmaDaoImpl dao = new PharmaDaoImpl();
			boolean updShipFlag = dao.updShippingMaster(masterInfo);
			if (updShipFlag) {
				session.setAttribute("succMsg",
						"Shipping Master: " + medTypeCode + " and " + weightRange + "  Edited Successfully");
				response.sendRedirect("ShippingChargeServlet");
			} else {
				session.setAttribute("failedMsg", "Something Error On Server");
				response.sendRedirect("ShippingChargeServlet");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
