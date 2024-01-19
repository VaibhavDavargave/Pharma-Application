package com.excelr.pharma.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.excelr.pharma.constants.SQLConstants;
import com.excelr.pharma.exceptions.PharmaException;

/**
 * 
 */
public final class DBConnectionUtility {
	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(DBConnectionUtility.class);

	private DBConnectionUtility() {

	}

	/**
	 * @return Connection
	 * @throws PharmaException
	 */
	public static Connection getConnection() throws PharmaException {
		Connection connection = null;
		try {
			Class.forName(SQLConstants.DRIVER_NAME);
			connection = DriverManager.getConnection(SQLConstants.DRIVER_URL, SQLConstants.USER_ID,
					SQLConstants.USER_PASSWORD);
			LOG.info("Connection Success..Start the Operations..!!!");
		} catch (SQLException e) {
			throw new PharmaException(e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

}
