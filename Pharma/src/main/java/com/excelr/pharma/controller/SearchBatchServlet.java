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
import com.excelr.pharma.vo.BatchInfo;

/**
 * Servlet implementation class SearchBatchServlet
 */
@WebServlet("/SearchBatchServlet")
public class SearchBatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		String batch = request.getParameter("searchBatch");
		try {
			PharmaDaoImpl dao = new PharmaDaoImpl();
			List<BatchInfo> batchSearch = dao.getBatchBySearch(batch);
			if (batchSearch.isEmpty()) {
				request.setAttribute("noDataFound", true);
				dispatcher = request.getRequestDispatcher("viewBatch.jsp");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("batchSearch", batchSearch);
				dispatcher = request.getRequestDispatcher("viewBatch.jsp");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
