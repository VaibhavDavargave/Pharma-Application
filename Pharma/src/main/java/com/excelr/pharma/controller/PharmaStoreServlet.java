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
import com.excelr.pharma.exceptions.PharmaBusinessException;

/**
 * Servlet implementation class PharmaStoreServlet
 */
@WebServlet("/PharmaStoreServlet")
public class PharmaStoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PharmaDaoImpl pharma = new PharmaDaoImpl();
		RequestDispatcher dispatcher = null;

		try {
			int batchRows = pharma.getBatchCount();
			int medRows = pharma.getMedicineCount();
			int medTypeRows = pharma.getMedicineTypeCount();
			int shipRows = pharma.getShippingMasterCount();
			req.setAttribute("batchRows", batchRows);
			req.setAttribute("medRows", medRows);
			req.setAttribute("medTypeRows", medTypeRows);
			req.setAttribute("shipRows", shipRows);
			dispatcher = req.getRequestDispatcher("pharmastore.jsp");
			dispatcher.forward(req, resp);
		} catch (PharmaBusinessException e) {
			e.printStackTrace();
		}
	}

}
