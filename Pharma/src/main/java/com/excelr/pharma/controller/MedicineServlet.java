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
import com.excelr.pharma.vo.MedicineInfo;

/**
 * Servlet implementation class AddMedicineServlet
 */
@WebServlet("/MedicineServlet")
public class MedicineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		try {
			PharmaDaoImpl dao = new PharmaDaoImpl();
			Set<MedicineInfo> values = dao.allMedicineInfo();
			System.out.println(values);
			req.setAttribute("values", values);
			dispatcher = req.getRequestDispatcher("viewMedicine.jsp");
			dispatcher.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String medicineCode = req.getParameter("medicineCode");
		String medicineName = req.getParameter("medicineName");

		// System.out.println(medicineCode + " " + medicineName);

		MedicineInfo medicine = new MedicineInfo();
		medicine.setMedicineCode(medicineCode);
		medicine.setMedicineName(medicineName);
		HttpSession session = req.getSession();
		PharmaDaoImpl dao = new PharmaDaoImpl();
		try {
			boolean valFlag = dao.validateMedicineCode(medicineCode);
			boolean medFlag = dao.checkMedicineCode(medicineCode);
			if (valFlag) {
				if (!medFlag) {
					boolean addFlag = dao.addMedicine(medicine);
					if (addFlag) {
						session.setAttribute("succMsg", "Medicine Code: " + medicineCode + "  added Successfully");
						resp.sendRedirect("addmedicine.jsp");
					} else {
						session.setAttribute("failedMsg", "Something Error On Server");
						resp.sendRedirect("addmedicine.jsp");
					}
				} else {
					session.setAttribute("failedMsg", "Medicine Code: " + medicineCode + "  is already Exists");
					resp.sendRedirect("addmedicine.jsp");
				}
			} else {
				session.setAttribute("failedMsg",
						"Medicine Code: " + medicineCode + " is in invalid Format i.e MC_XXX");
				resp.sendRedirect("addmedicine.jsp");
			}

		} catch (PharmaException e) {
			e.printStackTrace();
		} catch (PharmaBusinessException e) {
			e.printStackTrace();
		}

	}

}
