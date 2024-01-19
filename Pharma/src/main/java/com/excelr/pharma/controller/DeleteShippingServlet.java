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

/**
 * Servlet implementation class DeleteShippingServlet
 */
@WebServlet("/DeleteShippingServlet")
public class DeleteShippingServlet extends HttpServlet {
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
		PharmaDaoImpl delete = new PharmaDaoImpl();
		HttpSession session = request.getSession();
		try {
			boolean delFlag = delete.delShippingMaster(medTypeCode, weightRange);
			if (delFlag) {
				session.setAttribute("succMsg",
						"Shipping Master: " + medTypeCode + " and " + weightRange + "  deleted Successfully");
				response.sendRedirect("ShippingChargeServlet");
			} else {
				session.setAttribute("failedMsg", "Something Error On Server");
				response.sendRedirect("ShippingChargeServlet");
			}
		} catch (PharmaException e) {
			e.printStackTrace();
		}
	}

}
