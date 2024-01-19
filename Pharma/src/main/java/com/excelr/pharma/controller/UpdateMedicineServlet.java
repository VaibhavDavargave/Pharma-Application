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
import com.excelr.pharma.exceptions.PharmaException;
import com.excelr.pharma.vo.MedicineInfo;

/**
 * Servlet implementation class EditMedicineServlet
 */
@WebServlet("/UpdateMedicineServlet")
public class UpdateMedicineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String medCode = req.getParameter("medCode");
		RequestDispatcher dispatcher = null;
		PharmaDaoImpl getMed = new PharmaDaoImpl();
		try {
			MedicineInfo medicine = getMed.getSingleMedicineByMedCode(medCode);
			req.setAttribute("medicine", medicine);
			dispatcher = req.getRequestDispatcher("editMedicine.jsp");
			dispatcher.forward(req, resp);
		} catch (PharmaBusinessException e) {
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
		String medicineCode = request.getParameter("medicineCode");
		String medicineName = request.getParameter("medicineName");

		MedicineInfo medicine = new MedicineInfo();
		medicine.setMedicineCode(medicineCode);
		medicine.setMedicineName(medicineName);
		HttpSession session = request.getSession();
		PharmaDaoImpl updateImpl = new PharmaDaoImpl();
		try {
			boolean updFlag = updateImpl.updateMedicine(medicine);
			if (updFlag) {
				session.setAttribute("succMsg", "The Medicine: " + medicineName + " Edited Successfully");
				response.sendRedirect("MedicineServlet");
			} else {
				session.setAttribute("failedMsg", "Something Error On Server");
				response.sendRedirect("MedicineServlet");
			}
		} catch (PharmaException e) {
			e.printStackTrace();
		}

	}

}
