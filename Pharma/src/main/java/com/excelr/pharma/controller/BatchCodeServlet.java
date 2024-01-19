package com.excelr.pharma.controller;

import java.io.IOException;
import java.util.Set;



import com.excelr.pharma.dao.PharmaDaoImpl;
import com.excelr.pharma.exceptions.PharmaBusinessException;
import com.excelr.pharma.exceptions.PharmaException;
import com.excelr.pharma.service.IPharmaServiceImpl;
import com.excelr.pharma.vo.BatchInfo;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class AddBatchCodeServlet
 */
@WebServlet("/BatchCodeServlet")
public class BatchCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		try {
			PharmaDaoImpl dao = new PharmaDaoImpl();
			Set<BatchInfo> values = dao.allBatchesInfo();
			System.out.println(values);
			req.setAttribute("values", values);
			dispatcher = req.getRequestDispatcher("viewBatch.jsp");
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
		String batchCode = req.getParameter("batchCode");
		String medicineCode = req.getParameter("medicineCode");
		int weight = Integer.parseInt(req.getParameter("weight"));
		double price = Double.parseDouble(req.getParameter("price"));
		String medicineTypeCode = req.getParameter("medicineTypeCode");
		String isrefrigiration = req.getParameter("refrigeration");

		req.setAttribute("batchCodeValue", batchCode);
		req.setAttribute("MedCodeValue", medicineCode);
		req.setAttribute("weightValue", weight);
		req.setAttribute("priceValue", price);
		req.setAttribute("medTypeCodeValue", medicineTypeCode);
		req.setAttribute("RefValue", isrefrigiration);

		// System.out.println(batchCode + " " + medicineCode + " " + weight + " " +
		// price + " " + medicineTypeCode + " "+ refrigiration);

		BatchInfo batchVO = new BatchInfo();
		batchVO.setBatchCode(batchCode);
		batchVO.setMedicineCode(medicineCode);
		batchVO.setWeight(weight);
		batchVO.setPrice(price);
		batchVO.setMedicineTypeCode(medicineTypeCode);
		batchVO.setRefrigiration(isrefrigiration);
		HttpSession session = req.getSession();
		IPharmaServiceImpl service = new IPharmaServiceImpl();
		PharmaDaoImpl dao = new PharmaDaoImpl();
		try {
			boolean batchFlag = dao.checkBatchCode(batchCode);
			boolean valFlag = dao.validateBatchCode(batchCode);
			boolean medFlag = dao.checkMedicineCode(medicineCode);
			RequestDispatcher dispatcher = null;
			if (valFlag) {
				if (medFlag) {
					if (!batchFlag) {
						boolean addFlag = service.addBatch(batchVO);
						if (addFlag) {
							session.setAttribute("succMsg", "Batch Number: " + batchCode + " Added Successfully");
							resp.sendRedirect("addbatch.jsp");
						} else {
							session.setAttribute("failedMsg", "Something Error On Server");
							dispatcher = req.getRequestDispatcher("addbatch.jsp");
							dispatcher.forward(req, resp);
						}
					} else {
						session.setAttribute("failedMsg", "Batch Code: " + batchCode + "  already Exists");
						dispatcher = req.getRequestDispatcher("addbatch.jsp");
						dispatcher.forward(req, resp);
					}
				} else {
					session.setAttribute("failedMsg", "Medicine Code: " + medicineCode + "  Doesnt Exists");
					dispatcher = req.getRequestDispatcher("addbatch.jsp");
					dispatcher.forward(req, resp);
				}
			} else {
				session.setAttribute("failedMsg", "Batch Code: " + batchCode + "  is in invalid Format i.e BTC-XXXX");
				dispatcher = req.getRequestDispatcher("addbatch.jsp");
				dispatcher.forward(req, resp);
			}
		} catch (PharmaException e) {
			e.printStackTrace();
		} catch (PharmaBusinessException e) {
			e.printStackTrace();
		}

	}

}
