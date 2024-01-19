package com.excelr.pharma.logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Servlet implementation class LoggerInitializer
 */
public class LoggerInitializer extends HttpServlet {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(LoggerInitializer.class);

	/**
	 * ServletConfig - servlet config object.
	 *
	 * @param config the config
	 * @throws ServletException the servlet exception
	 */
	@Override
	public void init(final ServletConfig config) throws ServletException {

		final String log4jFile = "C:\\Users\\user\\eclipse-workspace\\PharmaAppp Web Application\\src\\main\\webapp\\WEB-INF\\log4j.properties";

		PropertyConfigurator.configure(log4jFile);
		LOG.info("Application Initialized");
	}

}
