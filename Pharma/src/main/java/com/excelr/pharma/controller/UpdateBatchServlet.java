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
import com.excelr.pharma.service.IPharmaServiceImpl;
import com.excelr.pharma.vo.BatchInfo;
import com.excelr.pharma.vo.MedicineTypeInfo;

/**
 * Servlet implementation class EditBatchServlet
 */
@WebServlet("/UpdateBatchServlet")
public class UpdateBatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String batchcode = req.getParameter("btc");
		RequestDispatcher dispatcher = null;
		PharmaDaoImpl getBatch = new PharmaDaoImpl();
		try {
			BatchInfo batch = getBatch.findBatchByBatchCode(batchcode);
			Set<MedicineTypeInfo> values = getBatch.getAllMedicineTypes();
			req.setAttribute("batch", batch);
			req.setAttribute("values", values);
			dispatcher = req.getRequestDispatcher("editbatch.jsp");
			dispatcher.forward(req, resp);
		} catch (PharmaException e) {
			e.printStackTrace();
		} catch (PharmaBusinessException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String batchCode = request.getParameter("batchCode");
		String medicineCode = request.getParameter("medicineCode");
		int weight = Integer.parseInt(request.getParameter("weight"));
		double price = Double.parseDouble(request.getParameter("price"));
		String medicineTypeCode = request.getParameter("medicineTypeCode");
		String isrefrigiration = request.getParameter("refrigeration");

		System.out.println(batchCode + " " + medicineCode + " " + weight + " " + price + " " + medicineTypeCode + " "
				+ isrefrigiration);

		BatchInfo batchVO = new BatchInfo();
		batchVO.setBatchCode(batchCode);
		batchVO.setMedicineCode(medicineCode);
		batchVO.setWeight(weight);
		batchVO.setPrice(price);
		batchVO.setMedicineTypeCode(medicineTypeCode);
		batchVO.setRefrigiration(isrefrigiration);
		HttpSession session = request.getSession();
		try {
			IPharmaServiceImpl service = new IPharmaServiceImpl();
			boolean addFlag = service.updateBatch(batchVO);
			if (addFlag) {
				session.setAttribute("succMsg", "Batch Code: " + batchCode + "  Edited Successfully");
				response.sendRedirect("BatchCodeServlet");
			} else {
				session.setAttribute("failedMsg", "Something Error On Server");
				response.sendRedirect("BatchCodeServlet");
			}
		} catch (PharmaException e) {
			e.printStackTrace();
		} catch (PharmaBusinessException e) {
			e.printStackTrace();
		}

	}
}