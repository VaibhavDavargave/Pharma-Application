package com.excelr.pharma.controller;

import java.io.IOException;
import java.util.Set;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.excelr.pharma.dao.PharmaDaoImpl;
import com.excelr.pharma.vo.MedicineTypeInfo;

/**
 * Servlet implementation class GetMedTypeServlet
 */
@WebServlet("/GetMedTypeServlet")
public class GetMedTypeServlet extends HttpServlet {
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
			Set<MedicineTypeInfo> values = dao.getAllMedicineTypes();
			System.out.println(values);
			request.setAttribute("values", values);
			dispatcher = request.getRequestDispatcher("addbatch.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
