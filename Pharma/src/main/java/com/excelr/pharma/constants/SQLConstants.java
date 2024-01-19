package com.excelr.pharma.constants;

/**
 * @author Prudvi Raj
 *
 */
public final class SQLConstants {
	private SQLConstants() {

	}

	/**
	 * MySQL Driver Name
	 */
	public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

	/**
	 * MySQL URL
	 */
	public static final String DRIVER_URL = "jdbc:mysql://127.0.0.1:3306/?user=root";

	/**
	 * MySQL User Id
	 */
	public static final String USER_ID = "root";

	/**
	 * MySQL User Password
	 */
	public static final String USER_PASSWORD = "root";

	/**
	 * 1.Add Batch Query
	 */
	public static final String ADD_BATCH_QRY = "INSERT INTO pharma.batch_info VALUES(?,?,?,?,?,?,?)";
	/**
	 * 2.Update Batch Query
	 */
	public static final String UPD_BATCH_QRY = "UPDATE pharma.batch_info SET MEDICINE_CODE=?,WEIGHT=?,PRICE=?,MEDICINE_TYPE_CODE=?,SHIPPING_CHARGE=?,CARE_LEVEL=?  WHERE BATCH_CODE=?";
	/**
	 * 3.Check Medicine Query
	 */
	public static final String CHCK_MDC_QRY = "SELECT * FROM pharma.medicine_master WHERE medicine_code=?";
	/**
	 * 4.Query Used for returning shipping_charge value
	 */
	public static final String SHIPPING_CHARGE_QRY = "SELECT shipping_charge FROM pharma.shipping_master WHERE medicine_type_code=? AND weight_range=?";
	/**
	 * 5.Check Batch Code
	 */
	public static final String CHCK_BTC_CODE = "SELECT * FROM pharma.batch_info WHERE batch_code=?";
	/**
	 * 6.Find Batch By Batch Code
	 */
	public static final String FND_BTC_BY_BTC_CODE = "SELECT * FROM pharma.batch_info WHERE batch_code=?";
	/**
	 * 7.Remove Batch By Batch Code
	 */
	public static final String REM_BTC_BY_BTC_CODE = "DELETE FROM pharma.batch_info WHERE batch_code=?";
	/**
	 * 8.Find all Batches Info
	 */
	public static final String ALL_BATCHES = "SELECT * FROM pharma.batch_info";
	/**
	 * 9.Add Medicine
	 */
	public static final String ADD_MED = "INSERT INTO pharma.medicine_master VALUES(?,?)";
	/**
	 * 10.Fing all Medicine Info
	 */
	public static final String ALL_MED_INFO = "SELECT * FROM pharma.medicine_master ORDER by MEDICINE_CODE";
	/**
	 * 11.Get Single Medicine Info By Medicine Code
	 */
	public static final String GET_SING_MED_BY_MED_CODE = "SELECT * FROM pharma.medicine_master WHERE MEDICINE_CODE=?";
	/**
	 * 12.Update Medicine By Medicine Code
	 */
	public static final String UPD_MED_CODE = "UPDATE pharma.medicine_master SET MEDICINE_NAME=? WHERE MEDICINE_CODE=?";

	/**
	 * 13.Remove Medicine Info By Medicine Code
	 */
	public static final String REM_MED_CODE = "DELETE FROM pharma.medicine_master WHERE MEDICINE_CODE=?";

	/**
	 * 14.Total Batch Code rows Count
	 */
	public static final String BATCH_CODE_COUNT_CHECK_QRY = "SELECT COUNT(*) FROM pharma.batch_info";

	/**
	 * 15.Total Medicine Code rows Count
	 */

	public static final String MEDICINE_CODE_COUNT_CHECK_QRY = "SELECT COUNT(*) FROM pharma.medicine_master";
	/**
	 * 16.Total Medicine Types Code rows Count
	 */
	public static final String MEDICINE_TYPE_CODE_COUNT_CHECK_QRY = "SELECT COUNT(*) FROM pharma.medicine_type_master";

	/**
	 * 17.Total Shipping Master rows Count
	 */
	public static final String SHIPPING_MASTER_COUNT_CHECK_QRY = "SELECT COUNT(*) FROM pharma.shipping_master";

	/**
	 * 18.All Medicine Types
	 */
	public static final String MED_TYPE_DISP_ALL_QRY = "SELECT * FROM pharma.medicine_type_master";
	/**
	 * 19.All Shipping Mastery
	 */
	public static final String SHIP_MASTER_DISP_ALL_QRY = "SELECT * FROM pharma.shipping_master ORDER by MEDICINE_TYPE_CODE";

	/**
	 * 20.Check Medicine Type Code and Type Name
	 */
	public static final String CHECK_MED_TYPE = "SELECT * FROM pharma.medicine_type_master WHERE MEDICINE_TYPE_CODE=? AND MEDICINE_TYPE_NAME=?";

	/**
	 * 21.Get Medicine Type Code and Type Name
	 */
	public static final String FIND_MED_TYPE = "SELECT * FROM pharma.medicine_type_master WHERE MEDICINE_TYPE_CODE=?";

	/**
	 * 22.Add Medicine Type Code and Type Name
	 */
	public static final String ADD_MED_TYPE = "INSERT INTO pharma.medicine_type_master VALUES(?,?)";

	/**
	 * 23.Update Medicine Type Code and Type Name
	 */
	public static final String UPD_MED_TYPE = "UPDATE pharma.medicine_type_master SET MEDICINE_TYPE_NAME=? WHERE MEDICINE_TYPE_CODE=?";

	/**
	 * 24.Delete Medicine Type Code and Type Name
	 */
	public static final String DEL_MED_TYPE = "DELETE FROM pharma.medicine_type_master WHERE MEDICINE_TYPE_CODE=?";
	/**
	 * 25.Find medicine type code and weight range
	 */
	public static final String FIND_SHIPPING_MASTER = "SELECT * FROM pharma.shipping_master WHERE MEDICINE_TYPE_CODE=? AND WEIGHT_RANGE=?";
	/**
	 * 26. Add Shipping Master
	 */
	public static final String ADD_SHIPPING_MASTER = "INSERT INTO pharma.shipping_master VALUES(?,?,?)";
	/**
	 * 27.Update Shipping Master
	 */
	public static final String UPD_SHIPPING_MASTER = "UPDATE pharma.shipping_master SET SHIPPING_CHARGE=? WHERE MEDICINE_TYPE_CODE=? AND WEIGHT_RANGE=?";
	/**
	 * 28. Del Shipping Master
	 */
	public static final String DEL_SHIPPING_MASTER = "DELETE FROM pharma.shipping_master WHERE MEDICINE_TYPE_CODE=? AND WEIGHT_RANGE=?";
	/**
	 * 28. Check Shipping Master
	 */
	public static final String CHECK_SHIPPING_MASTER = "SELECT * FROM pharma.shipping_master WHERE MEDICINE_TYPE_CODE=? AND WEIGHT_RANGE=?";
	/**
	 * 
	 */
	public static final String SEARCH_BATCH_CODE = "SELECT * FROM pharma.batch_info WHERE BATCH_CODE like ? OR MEDICINE_CODE like ? OR WEIGHT like ? OR PRICE like ? OR MEDICINE_TYPE_CODE like ? OR SHIPPING_CHARGE like ? OR CARE_LEVEL like ?";
	/**
	 * 
	 */
	public static final String SEARCH_MED_CODE = "SELECT * FROM pharma.medicine_master WHERE MEDICINE_CODE like ? OR MEDICINE_NAME like ?";
	/**
	 * 
	 */
	public static final String SEARCH_MED_TYPE = "SELECT * FROM pharma.medicine_type_master WHERE MEDICINE_TYPE_CODE like ? OR MEDICINE_TYPE_NAME like ?";
	/**
	 * 
	 */
	public static final String SEARCH_SHIPP_DET = "SELECT * FROM pharma.shipping_master WHERE MEDICINE_TYPE_CODE like ? OR WEIGHT_RANGE like ? OR SHIPPING_CHARGE like ?";
}
