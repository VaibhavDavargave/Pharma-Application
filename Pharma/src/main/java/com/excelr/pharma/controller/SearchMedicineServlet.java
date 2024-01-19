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
import com.excelr.pharma.vo.MedicineInfo;

/**
 * Servlet implementation class SearchMedicineServlet
 */
@WebServlet("/SearchMedicineServlet")
public class SearchMedicineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		String medicine = request.getParameter("searchMedicine");
		try {
			PharmaDaoImpl dao = new PharmaDaoImpl();
			List<MedicineInfo> medValues = dao.getMedicineBySearch(medicine);
			if (medValues.isEmpty()) {
				request.setAttribute("noDataFound", true);
				dispatcher = request.getRequestDispatcher("viewMedicine.jsp");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("medValues", medValues);
				dispatcher = request.getRequestDispatcher("viewMedicine.jsp");
				dispatcher.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
