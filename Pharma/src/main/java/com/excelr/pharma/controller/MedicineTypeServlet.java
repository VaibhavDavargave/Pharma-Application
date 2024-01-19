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
import com.excelr.pharma.exceptions.PharmaBusinessException;
import com.excelr.pharma.exceptions.PharmaException;
import com.excelr.pharma.vo.MedicineTypeInfo;

/**
 * Servlet implementation class MedicineTypeServlet
 */
@WebServlet("/MedicineTypeServlet")
public class MedicineTypeServlet extends HttpServlet {
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
			dispatcher = request.getRequestDispatcher("viewMedicineType.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String medTypeCode = req.getParameter("medicineTypeCode");
		String medTypeName = req.getParameter("medicineTypeName");
		// System.out.println(medTypeCode + "" + medTypeName);

		MedicineTypeInfo medTypeInfo = new MedicineTypeInfo();
		medTypeInfo.setMedicineTypeCode(medTypeCode);
		medTypeInfo.setMedicineTypeName(medTypeName);
		HttpSession session = req.getSession();
		PharmaDaoImpl dao = new PharmaDaoImpl();
		try {
			boolean checkMedTypFlag = dao.checkValidMedicineTypeCode(medTypeCode, medTypeName);

			if (!checkMedTypFlag) {
				boolean addMedTypeFlag = dao.addMedicineType(medTypeInfo);
				if (addMedTypeFlag) {
					session.setAttribute("succMsg",
							"Medicine Type: " + medTypeCode + " and " + medTypeName + " Added Successfully");
					resp.sendRedirect("addMedType.jsp");
				} else {
					session.setAttribute("failedMsg", "Something Error on Server..s");
					resp.sendRedirect("addMedType.jsp");
				}
			} else {
				session.setAttribute("failedMsg",
						"Medicine Type: " + medTypeCode + " and " + medTypeName + "  is already exists");
				resp.sendRedirect("addMedType.jsp");
			}
		} catch (PharmaException e) {
			e.printStackTrace();
		} catch (PharmaBusinessException e) {
			e.printStackTrace();
		}
	}

}
