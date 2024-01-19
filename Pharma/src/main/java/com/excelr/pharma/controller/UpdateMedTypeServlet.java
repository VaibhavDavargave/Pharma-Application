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
import com.excelr.pharma.vo.MedicineTypeInfo;

/**
 * Servlet implementation class UpdateMedTypeServlet
 */
@WebServlet("/UpdateMedTypeServlet")
public class UpdateMedTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String medTypeCode = request.getParameter("medTypeCode");
		RequestDispatcher dispatcher = null;
		PharmaDaoImpl getMedType = new PharmaDaoImpl();
		try {
			MedicineTypeInfo medTypeInfo = getMedType.findMedType(medTypeCode);
			request.setAttribute("medTypeInfo", medTypeInfo);
			dispatcher = request.getRequestDispatcher("editMedType.jsp");
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String medTypeCode = req.getParameter("medicineTypeCode");
		String medTypeName = req.getParameter("medicineTypeName");
		// System.out.println(medTypeCode + "" + medTypeName);

		MedicineTypeInfo medTypeInfo = new MedicineTypeInfo();
		medTypeInfo.setMedicineTypeCode(medTypeCode);
		medTypeInfo.setMedicineTypeName(medTypeName);
		HttpSession session = req.getSession();
		try {
			PharmaDaoImpl dao = new PharmaDaoImpl();
			boolean updMedTypeFlag = dao.updMedType(medTypeInfo);
			if (updMedTypeFlag) {
				session.setAttribute("succMsg", "Medicine Type: " + medTypeName + "  Edited Successfully");
				resp.sendRedirect("MedicineTypeServlet");
			} else {
				session.setAttribute("failedMsg", "Something Error On Server");
				resp.sendRedirect("MedicineTypeServlet");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
