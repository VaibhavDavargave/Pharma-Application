package com.excelr.pharma.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.excelr.pharma.constants.SQLConstants;
import com.excelr.pharma.db.DBConnectionUtility;
import com.excelr.pharma.exceptions.PharmaBusinessException;
import com.excelr.pharma.exceptions.PharmaException;
import com.excelr.pharma.vo.BatchInfo;
import com.excelr.pharma.vo.MedicineInfo;
import com.excelr.pharma.vo.MedicineTypeInfo;
import com.excelr.pharma.vo.ShippingMasterInfo;

/**
 * @author Prudvi Raj
 *
 */
public class PharmaDaoImpl implements IPharmaDAO {
	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(PharmaDaoImpl.class);

	@Override
	public boolean addBatch(BatchInfo batchInfo) throws PharmaException {
		final String METHOD_NAME = "addBatch";
		LOG.info("Method Invoked:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + batchInfo);
		boolean addBatchFlag = false;
		try (Connection connection = DBConnectionUtility.getConnection();
				PreparedStatement pstStatement = connection.prepareStatement(SQLConstants.ADD_BATCH_QRY)) {
			pstStatement.setString(1, batchInfo.getBatchCode());
			pstStatement.setString(2, batchInfo.getMedicineCode());
			pstStatement.setInt(3, batchInfo.getWeight());
			pstStatement.setDouble(4, batchInfo.getPrice());
			pstStatement.setString(5, batchInfo.getMedicineTypeCode());
			pstStatement.setDouble(6, batchInfo.getShippingCharge());
			pstStatement.setString(7, batchInfo.getCareLevel());
			int updateCount = pstStatement.executeUpdate();
			if (updateCount > 0) {
				addBatchFlag = true;
			}
		} catch (SQLException e) {
			throw new PharmaException(e);
		} catch (PharmaException e) {
			throw new PharmaException(e);
		}
		LOG.info("Method Exit:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + addBatchFlag);
		return addBatchFlag;
	}

	@Override
	public boolean updateBatch(BatchInfo batchInfo) throws PharmaException {
		final String METHOD_NAME = "updateBatch";
		LOG.info("Method Invoked:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + batchInfo);
		boolean upBatchFlag = false;
		final String UPD_BATCH_QRY = "UPDATE pharma.batch_info SET WEIGHT=?,PRICE=?,MEDICINE_TYPE_CODE=?,SHIPPING_CHARGE=?,CARE_LEVEL=?  WHERE BATCH_CODE=?";
		try (Connection connection = DBConnectionUtility.getConnection();
				PreparedStatement pstStatement = connection.prepareStatement(UPD_BATCH_QRY)) {
			pstStatement.setInt(1, batchInfo.getWeight());
			pstStatement.setDouble(2, batchInfo.getPrice());
			pstStatement.setString(3, batchInfo.getMedicineTypeCode());
			pstStatement.setDouble(4, batchInfo.getShippingCharge());
			pstStatement.setString(5, batchInfo.getCareLevel());
			pstStatement.setString(6, batchInfo.getBatchCode());
			int updateCount = pstStatement.executeUpdate();
			if (updateCount > 0) {
				upBatchFlag = true;
			}
		} catch (SQLException e) {
			throw new PharmaException(e);
		} catch (PharmaException e) {
			throw new PharmaException(e);
		}
		LOG.info("Method Exit:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + upBatchFlag);
		return upBatchFlag;

	}

	@Override
	public boolean checkMedicineCode(String medicineCode) throws PharmaException {
		final String METHOD_NAME = "checkMedicineCode";
		LOG.info("Method Invoked:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + medicineCode);
		boolean medicineCodeFlag = false;
		try (Connection connection = DBConnectionUtility.getConnection();
				PreparedStatement pstStatement = connection.prepareStatement(SQLConstants.CHCK_MDC_QRY)) {
			pstStatement.setString(1, medicineCode);
			ResultSet resultSet = pstStatement.executeQuery();
			while (resultSet.next()) {
				medicineCodeFlag = true;
			}
		} catch (SQLException e) {
			throw new PharmaException(e);
		} catch (PharmaException e) {
			throw new PharmaException(e);
		}
		LOG.info("Method Exit:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + medicineCodeFlag);
		return medicineCodeFlag;
	}

	@Override
	public double getShippingCharge(String medicineTypeCode, String weightRange) throws PharmaException {
		final String METHOD_NAME = "getShippingCharge";
		LOG.info("Method Invoked:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + medicineTypeCode + ":"
				+ weightRange);
		double shippingCharge = 0;
		try (Connection connection = DBConnectionUtility.getConnection();
				PreparedStatement pstStatement = connection.prepareStatement(SQLConstants.SHIPPING_CHARGE_QRY)) {
			pstStatement.setString(1, medicineTypeCode);
			pstStatement.setString(2, weightRange);
			ResultSet resultSet = pstStatement.executeQuery();
			if (resultSet.next()) {
				shippingCharge = resultSet.getDouble(1);
				System.out.println(shippingCharge);
			}
		} catch (SQLException e) {
			throw new PharmaException(e);
		} catch (PharmaException e) {
			throw new PharmaException(e);
		}
		LOG.info("Method Exit:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + shippingCharge);
		return shippingCharge;
	}

	@Override
	public boolean checkBatchCode(String batchCode) throws PharmaException {
		final String METHOD_NAME = "checkBatchCode";
		LOG.info("Method Invoked:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + batchCode);
		boolean batchCodeFlag = false;
		try (Connection connection = DBConnectionUtility.getConnection();
				PreparedStatement pstStatement = connection.prepareStatement(SQLConstants.CHCK_BTC_CODE)) {
			pstStatement.setString(1, batchCode);
			ResultSet resultSet = pstStatement.executeQuery();
			while (resultSet.next()) {
				batchCodeFlag = true;
			}
		} catch (SQLException e) {
			throw new PharmaException(e);
		} catch (PharmaException e) {
			throw new PharmaException(e);
		}
		LOG.info("Method Response:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + batchCodeFlag);
		return batchCodeFlag;
	}

	@Override
	public BatchInfo findBatchByBatchCode(String batchCode) throws PharmaException {
		final String METHOD_NAME = "findBatchByBatchCode";
		LOG.info("Method Invoked:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + batchCode);
		BatchInfo batchValues = null;
		System.out.println("Method Invoked: " + METHOD_NAME + ":" + batchCode);
		try (Connection connection = DBConnectionUtility.getConnection();
				PreparedStatement pstStatement = connection.prepareStatement(SQLConstants.FND_BTC_BY_BTC_CODE)) {
			pstStatement.setString(1, batchCode);
			ResultSet resultSet = pstStatement.executeQuery();
			while (resultSet.next()) {
				batchValues = new BatchInfo();
				batchValues.setBatchCode(resultSet.getString(1));
				batchValues.setMedicineCode(resultSet.getString(2));
				batchValues.setWeight(resultSet.getInt(3));
				batchValues.setPrice(resultSet.getDouble(4));
				batchValues.setMedicineTypeCode(resultSet.getString(5));
				batchValues.setShippingCharge(resultSet.getDouble(6));
				batchValues.setCareLevel(resultSet.getString(7));
			}

		} catch (SQLException e) {
			throw new PharmaException(e);
		}
		LOG.info("Method Exit:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + batchValues);
		return batchValues;
	}

	@Override
	public boolean removeBatchByBacthCode(String batchCode) throws PharmaException {
		final String METHOD_NAME = "removeBatchByBacthCode";
		boolean delFlag = false;
		LOG.info("Method Invoked: " + METHOD_NAME + ":" + batchCode);
		try (Connection connection = DBConnectionUtility.getConnection();
				PreparedStatement pstStatement = connection.prepareStatement(SQLConstants.REM_BTC_BY_BTC_CODE)) {
			pstStatement.setString(1, batchCode);
			int rowsAffected = pstStatement.executeUpdate();
			if (rowsAffected > 0) {
				delFlag = true;
			} else {
				throw new PharmaException("Batch Code Not Found in the database..");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		LOG.info("Method Exit:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + delFlag);
		return delFlag;
	}

	@Override
	public Set<BatchInfo> allBatchesInfo() throws PharmaException {
		final String METHOD_NAME = "allBatchesInfo";
		LOG.info("Method Invoked:" + this.getClass().getName() + ":" + METHOD_NAME);
		Set<BatchInfo> batchSet = null;
		try (Connection connection = DBConnectionUtility.getConnection();
				PreparedStatement pstStatement = connection.prepareStatement(SQLConstants.ALL_BATCHES)) {
			ResultSet resultSet = pstStatement.executeQuery();
			batchSet = new TreeSet<BatchInfo>();
			while (resultSet.next()) {
				BatchInfo batchValues = new BatchInfo();
				batchValues.setBatchCode(resultSet.getString(1));
				batchValues.setMedicineCode(resultSet.getString(2));
				batchValues.setWeight(resultSet.getInt(3));
				batchValues.setPrice(resultSet.getDouble(4));
				batchValues.setMedicineTypeCode(resultSet.getString(5));
				batchValues.setShippingCharge(resultSet.getDouble(6));
				batchValues.setCareLevel(resultSet.getString(7));
				batchSet.add(batchValues);
			}

		} catch (SQLException e) {
			throw new PharmaException(e);
		} catch (PharmaException e) {
			throw new PharmaException(e);
		}
		LOG.info("Method Exit:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + batchSet);
		return batchSet;
	}

	/**
	 * @param batchCode
	 * @return boolean
	 * @throws PharmaBusinessException
	 */
	@Override
	public boolean validateBatchCode(String batchCode) throws PharmaBusinessException {
		final String METHOD_NAME = "validateBatchCode";
		LOG.info("Method Invoked:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + batchCode);
		boolean valFlag = false;
		String pattern = "BTC-\\d{4}";
		Pattern regex = Pattern.compile(pattern);
		Matcher matcher = regex.matcher(batchCode);
		valFlag = matcher.matches();
		LOG.info("Method Exit:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + valFlag);
		return valFlag;
	}

	@Override
	public boolean addMedicine(MedicineInfo medicineInfo) throws PharmaException {
		final String METHOD_NAME = "addMedicine";
		LOG.info("Method Invoked:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + medicineInfo);
		boolean addMedFlag = false;
		try (Connection connection = DBConnectionUtility.getConnection();
				PreparedStatement pstStatement = connection.prepareStatement(SQLConstants.ADD_MED)) {
			pstStatement.setString(1, medicineInfo.getMedicineCode());
			pstStatement.setString(2, medicineInfo.getMedicineName());
			int rowsAff = pstStatement.executeUpdate();
			if (rowsAff > 0) {
				addMedFlag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		LOG.info("Method Exit:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + addMedFlag);
		return addMedFlag;
	}

	@Override
	public boolean validateMedicineCode(String medicineCode) throws PharmaBusinessException {
		final String METHOD_NAME = "validateMedicineCode";
		LOG.info("Method Invoked:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + medicineCode);
		boolean valFlag = false;
		String pattern = "MC_\\d{3}";
		Pattern regex = Pattern.compile(pattern);
		Matcher matcher = regex.matcher(medicineCode);
		valFlag = matcher.matches();
		LOG.info("Method Exit:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + valFlag);
		return valFlag;
	}

	@Override
	public Set<MedicineInfo> allMedicineInfo() throws PharmaException {
		final String METHOD_NAME = "allMedicineInfo";
		LOG.info("Method Invoked:" + this.getClass().getName() + ":" + METHOD_NAME);
		Set<MedicineInfo> medSet = null;
		try (Connection connection = DBConnectionUtility.getConnection();
				PreparedStatement pstStatement = connection.prepareStatement(SQLConstants.ALL_MED_INFO)) {
			ResultSet resultSet = pstStatement.executeQuery();
			medSet = new TreeSet<MedicineInfo>();
			while (resultSet.next()) {
				MedicineInfo medicine = new MedicineInfo();
				medicine.setMedicineCode(resultSet.getString(1));
				medicine.setMedicineName(resultSet.getString(2));
				medSet.add(medicine);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		LOG.info("Method Exit:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + medSet);
		return medSet;
	}

	@Override
	public MedicineInfo getSingleMedicineByMedCode(String medicineCode) throws PharmaBusinessException {
		final String METHOD_NAME = "getSingleMedicineByMedCode";
		LOG.info("Method Invoked:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + medicineCode);
		MedicineInfo medicine = null;
		try (Connection connection = DBConnectionUtility.getConnection();
				PreparedStatement pstStatement = connection.prepareStatement(SQLConstants.GET_SING_MED_BY_MED_CODE)) {
			pstStatement.setString(1, medicineCode);
			ResultSet resultSet = pstStatement.executeQuery();
			while (resultSet.next()) {
				medicine = new MedicineInfo();
				medicine.setMedicineCode(resultSet.getString(1));
				medicine.setMedicineName(resultSet.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (PharmaException e1) {
			e1.printStackTrace();
		}
		LOG.info("Method Exit:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + medicine);
		return medicine;
	}

	@Override
	public boolean updateMedicine(MedicineInfo medicineInfo) throws PharmaException {
		final String METHOD_NAME = "updateMedicine";
		LOG.info("Method Invoked:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + medicineInfo);
		boolean updFlag = false;
		try (Connection connection = DBConnectionUtility.getConnection();
				PreparedStatement pstStatement = connection.prepareStatement(SQLConstants.UPD_MED_CODE)) {
			pstStatement.setString(1, medicineInfo.getMedicineName());
			pstStatement.setString(2, medicineInfo.getMedicineCode());
			int rowsAff = pstStatement.executeUpdate();
			if (rowsAff > 0) {
				updFlag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		LOG.info("Method Exit:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + updFlag);
		return updFlag;
	}

	@Override
	public boolean removeMedicineByMedicineCode(String medicineCode) throws PharmaException {
		final String METHOD_NAME = "removeMedicineByMedicineCode";
		LOG.info("Method Invoked:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + medicineCode);
		boolean delFlag = false;
		try (Connection connection = DBConnectionUtility.getConnection();
				PreparedStatement pstStatement = connection.prepareStatement(SQLConstants.REM_MED_CODE)) {
			pstStatement.setString(1, medicineCode);
			int rowsAff = pstStatement.executeUpdate();
			if (rowsAff > 0) {
				delFlag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		LOG.info("Method Exit:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + delFlag);
		return delFlag;
	}

	@Override
	public int getBatchCount() throws PharmaBusinessException {
		final String METHOD_NAME = "getBatchCount";
		int rows = 0;
		LOG.info("Method Invoked:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + rows);
		try (Connection connection = DBConnectionUtility.getConnection();
				PreparedStatement pstStatement = connection.prepareStatement(SQLConstants.BATCH_CODE_COUNT_CHECK_QRY)) {
			ResultSet resultSet = pstStatement.executeQuery();
			if (resultSet.next()) {
				rows = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (PharmaException e1) {
			e1.printStackTrace();
		}
		LOG.info("Method Exit:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + rows);
		return rows;
	}

	@Override
	public int getMedicineCount() throws PharmaBusinessException {
		final String METHOD_NAME = "getMedicineCount";
		int rows = 0;
		LOG.info("Method Invoked:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + rows);
		try (Connection connection = DBConnectionUtility.getConnection();
				PreparedStatement pstStatement = connection
						.prepareStatement(SQLConstants.MEDICINE_CODE_COUNT_CHECK_QRY)) {
			ResultSet resultSet = pstStatement.executeQuery();
			if (resultSet.next()) {
				rows = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (PharmaException e1) {
			e1.printStackTrace();
		}
		LOG.info("Method Exit:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + rows);
		return rows;
	}

	@Override
	public Set<MedicineTypeInfo> getAllMedicineTypes() throws PharmaBusinessException {
		final String METHOD_NAME = "getAllMedicineTypes";
		Set<MedicineTypeInfo> typeSet = null;
		LOG.info("Method Invoked:" + this.getClass().getName() + ":" + METHOD_NAME);
		try (Connection connection = DBConnectionUtility.getConnection();
				PreparedStatement pstStatement = connection.prepareStatement(SQLConstants.MED_TYPE_DISP_ALL_QRY)) {
			ResultSet resultSet = pstStatement.executeQuery();
			typeSet = new TreeSet<MedicineTypeInfo>();
			while (resultSet.next()) {
				MedicineTypeInfo typeInfo = new MedicineTypeInfo();
				typeInfo.setMedicineTypeCode(resultSet.getString(1));
				typeInfo.setMedicineTypeName(resultSet.getString(2));
				typeSet.add(typeInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (PharmaException e) {
			e.printStackTrace();
		}
		LOG.info("Method Exit:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + typeSet);
		return typeSet;
	}

	@Override
	public int getMedicineTypeCount() throws PharmaBusinessException {
		final String METHOD_NAME = "getMedicineTypeCount";
		int rows = 0;
		LOG.info("Method Invoked:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + rows);
		try (Connection connection = DBConnectionUtility.getConnection();
				PreparedStatement pstStatement = connection
						.prepareStatement(SQLConstants.MEDICINE_TYPE_CODE_COUNT_CHECK_QRY)) {
			ResultSet resultSet = pstStatement.executeQuery();
			if (resultSet.next()) {
				rows = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (PharmaException e1) {
			e1.printStackTrace();
		}
		LOG.info("Method Exit:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + rows);
		return rows;
	}

	@Override
	public int getShippingMasterCount() throws PharmaBusinessException {
		final String METHOD_NAME = "getShippingMasterCount";
		int rows = 0;
		LOG.info("Method Invoked:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + rows);
		try (Connection connection = DBConnectionUtility.getConnection();
				PreparedStatement pstStatement = connection
						.prepareStatement(SQLConstants.SHIPPING_MASTER_COUNT_CHECK_QRY)) {
			ResultSet resultSet = pstStatement.executeQuery();
			if (resultSet.next()) {
				rows = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (PharmaException e1) {
			e1.printStackTrace();
		}
		LOG.info("Method Exit:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + rows);
		return rows;
	}

	@Override
	public List<ShippingMasterInfo> getAllShippingMaster() throws PharmaBusinessException {
		final String METHOD_NAME = "getAllShippingMaster";
		List<ShippingMasterInfo> shippingSet = null;
		LOG.info("Method Invoked:" + this.getClass().getName() + ":" + METHOD_NAME);
		try (Connection connection = DBConnectionUtility.getConnection();
				PreparedStatement pstStatement = connection.prepareStatement(SQLConstants.SHIP_MASTER_DISP_ALL_QRY)) {
			ResultSet resultSet = pstStatement.executeQuery();
			shippingSet = new ArrayList<ShippingMasterInfo>();
			while (resultSet.next()) {
				ShippingMasterInfo shippingInfo = new ShippingMasterInfo();
				shippingInfo.setMedicineTypeCode(resultSet.getString(1));
				shippingInfo.setWeightRange(resultSet.getString(2));
				shippingInfo.setShippingCharge(resultSet.getDouble(3));
				shippingSet.add(shippingInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (PharmaException e) {
			e.printStackTrace();
		}
		LOG.info("Method Exit:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + shippingSet);
		return shippingSet;
	}

	@Override
	public List<BatchInfo> getBatchBySearch(String ch) throws PharmaBusinessException {
		final String METHOD_NAME = "getBatchBySearch";
		List<BatchInfo> searchList = null;
		BatchInfo batchValues = null;
		LOG.info("Method Invoked:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + ch);
		try (Connection connection = DBConnectionUtility.getConnection();
				PreparedStatement pstStatement = connection.prepareStatement(SQLConstants.SEARCH_BATCH_CODE)) {
			pstStatement.setString(1, "%" + ch + "%");
			pstStatement.setString(2, "%" + ch + "%");
			pstStatement.setString(3, "%" + ch + "%");
			pstStatement.setString(4, "%" + ch + "%");
			pstStatement.setString(5, "%" + ch + "%");
			pstStatement.setString(6, "%" + ch + "%");
			pstStatement.setString(7, "%" + ch + "%");
			ResultSet resultSet = pstStatement.executeQuery();
			searchList = new ArrayList<BatchInfo>();
			while (resultSet.next()) {
				batchValues = new BatchInfo();
				batchValues.setBatchCode(resultSet.getString(1));
				batchValues.setMedicineCode(resultSet.getString(2));
				batchValues.setWeight(resultSet.getInt(3));
				batchValues.setPrice(resultSet.getDouble(4));
				batchValues.setMedicineTypeCode(resultSet.getString(5));
				batchValues.setShippingCharge(resultSet.getDouble(6));
				batchValues.setCareLevel(resultSet.getString(7));
				searchList.add(batchValues);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (PharmaException e) {
			e.printStackTrace();
		}

		LOG.info("Method Exit:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + searchList);
		return searchList;
	}

	@Override
	public List<MedicineInfo> getMedicineBySearch(String ch) throws PharmaBusinessException {
		final String METHOD_NAME = "getMedicineBySearch";
		List<MedicineInfo> medList = null;
		MedicineInfo medInfo = null;
		LOG.info("Method Invoked:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + ch);
		try (Connection connection = DBConnectionUtility.getConnection();
				PreparedStatement pstStatement = connection.prepareStatement(SQLConstants.SEARCH_MED_CODE)) {
			pstStatement.setString(1, "%" + ch + "%");
			pstStatement.setString(2, "%" + ch + "%");
			ResultSet resultSet = pstStatement.executeQuery();
			medList = new ArrayList<MedicineInfo>();
			while (resultSet.next()) {
				medInfo = new MedicineInfo();
				medInfo.setMedicineCode(resultSet.getString(1));
				medInfo.setMedicineName(resultSet.getString(2));
				medList.add(medInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (PharmaException e) {
			e.printStackTrace();
		}
		LOG.info("Method Exit:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + medList);
		return medList;
	}

	@Override
	public List<MedicineTypeInfo> getMedicineTypeBySearch(String ch) throws PharmaBusinessException {
		final String METHOD_NAME = "getMedicineTypeBySearch";
		List<MedicineTypeInfo> medTypeList = null;
		MedicineTypeInfo medTypeInfo = null;
		LOG.info("Method Invoked:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + ch);
		try (Connection connection = DBConnectionUtility.getConnection();
				PreparedStatement pstStatement = connection.prepareStatement(SQLConstants.SEARCH_MED_TYPE)) {
			pstStatement.setString(1, "%" + ch + "%");
			pstStatement.setString(2, "%" + ch + "%");
			ResultSet resultSet = pstStatement.executeQuery();
			medTypeList = new ArrayList<MedicineTypeInfo>();
			while (resultSet.next()) {
				medTypeInfo = new MedicineTypeInfo();
				medTypeInfo.setMedicineTypeCode(resultSet.getString(1));
				medTypeInfo.setMedicineTypeName(resultSet.getString(2));
				medTypeList.add(medTypeInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (PharmaException e) {
			e.printStackTrace();
		}
		LOG.info("Method Exit:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + medTypeList);
		return medTypeList;

	}

	@Override
	public List<ShippingMasterInfo> getShippSearch(String ch) throws PharmaBusinessException {
		final String METHOD_NAME = "getShippSearch";
		List<ShippingMasterInfo> shippList = null;
		ShippingMasterInfo shippInfo = null;
		LOG.info("Method Invoked:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + ch);
		try (Connection connection = DBConnectionUtility.getConnection();
				PreparedStatement pstStatement = connection.prepareStatement(SQLConstants.SEARCH_SHIPP_DET)) {
			pstStatement.setString(1, "%" + ch + "%");
			pstStatement.setString(2, "%" + ch + "%");
			pstStatement.setString(3, "%" + ch + "%");
			ResultSet resultSet = pstStatement.executeQuery();
			shippList = new ArrayList<ShippingMasterInfo>();
			while (resultSet.next()) {
				shippInfo = new ShippingMasterInfo();
				shippInfo.setMedicineTypeCode(resultSet.getString(1));
				shippInfo.setWeightRange(resultSet.getString(2));
				shippInfo.setShippingCharge(resultSet.getDouble(3));
				shippList.add(shippInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (PharmaException e) {
			e.printStackTrace();
		}
		LOG.info("Method Exit:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + shippList);
		return shippList;

	}

	@Override
	public boolean checkValidMedicineTypeCode(String medTypeCode, String medTypeName) throws PharmaException {
		boolean checkMedTypFlag = false;
		final String METHOD_NAME = "checkValidMedicineTypeCode";
		LOG.info("Method Invoked:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + checkMedTypFlag);
		try (Connection connection = DBConnectionUtility.getConnection();
				PreparedStatement pstStatement = connection.prepareStatement(SQLConstants.CHECK_MED_TYPE)) {
			pstStatement.setString(1, medTypeCode);
			pstStatement.setString(2, medTypeName);
			ResultSet resultSet = pstStatement.executeQuery();
			while (resultSet.next()) {
				checkMedTypFlag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (PharmaException e) {
			e.printStackTrace();
		}

		LOG.info("Method Exit:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + checkMedTypFlag);
		return checkMedTypFlag;
	}

	@Override
	public boolean addMedicineType(MedicineTypeInfo medTypeInfo) throws PharmaBusinessException {
		final String METHOD_NAME = "addMedicineType";
		boolean addMedTypeFlag = false;
		LOG.info("Method Invoked:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + addMedTypeFlag);
		try (Connection connection = DBConnectionUtility.getConnection();
				PreparedStatement pstStatement = connection.prepareStatement(SQLConstants.ADD_MED_TYPE)) {
			pstStatement.setString(1, medTypeInfo.getMedicineTypeCode());
			pstStatement.setString(2, medTypeInfo.getMedicineTypeName());
			int rowsAfff = pstStatement.executeUpdate();
			if (rowsAfff > 0) {
				addMedTypeFlag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (PharmaException e) {
			e.printStackTrace();
		}

		LOG.info("Method Exit:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + addMedTypeFlag);

		return addMedTypeFlag;
	}

	@Override
	public MedicineTypeInfo findMedType(String medTypeCode) throws PharmaException {
		final String METHOD_NAME = "findMedType";
		MedicineTypeInfo medTypeInfo = null;
		LOG.info("Method Invoked:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + medTypeCode);
		try (Connection connection = DBConnectionUtility.getConnection();
				PreparedStatement pstStatement = connection.prepareStatement(SQLConstants.FIND_MED_TYPE)) {
			pstStatement.setString(1, medTypeCode);
			ResultSet resultSet = pstStatement.executeQuery();
			while (resultSet.next()) {
				medTypeInfo = new MedicineTypeInfo();
				medTypeInfo.setMedicineTypeCode(resultSet.getString(1));
				medTypeInfo.setMedicineTypeName(resultSet.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		LOG.info("Method Exit:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + medTypeInfo);
		return medTypeInfo;
	}

	@Override
	public boolean updMedType(MedicineTypeInfo medTypeInfo) throws PharmaBusinessException {
		final String METHOD_NAME = "updMedType";
		boolean updMedTypeFlag = false;
		LOG.info("Method Invoked:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + updMedTypeFlag);
		try (Connection connection = DBConnectionUtility.getConnection();
				PreparedStatement pstStatement = connection.prepareStatement(SQLConstants.UPD_MED_TYPE)) {
			pstStatement.setString(1, medTypeInfo.getMedicineTypeName());
			pstStatement.setString(2, medTypeInfo.getMedicineTypeCode());
			int rowsAfff = pstStatement.executeUpdate();
			if (rowsAfff > 0) {
				updMedTypeFlag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (PharmaException e) {
			e.printStackTrace();
		}

		LOG.info("Method Exit:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + updMedTypeFlag);
		return updMedTypeFlag;
	}

	@Override
	public boolean delMedType(String medTypeCode) throws PharmaException {
		final String METHOD_NAME = "delMedType";
		boolean delMedTypeFlag = false;
		LOG.info("Method Invoked:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + medTypeCode);
		try (Connection connection = DBConnectionUtility.getConnection();
				PreparedStatement pstStatement = connection.prepareStatement(SQLConstants.DEL_MED_TYPE)) {
			pstStatement.setString(1, medTypeCode);
			int rowsAff = pstStatement.executeUpdate();
			if (rowsAff > 0) {
				delMedTypeFlag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		LOG.info("Method Exit:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + delMedTypeFlag);
		return delMedTypeFlag;
	}

	@Override
	public boolean checkAlreadyshippingCharge(String medTypeCode, String weightRange) throws PharmaException {
		final String METHOD_NAME = "checkAlreadyshippingCharge";
		boolean checkShipFlag = false;
		LOG.info("Method Invoked:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + medTypeCode + ":"
				+ weightRange);

		try (Connection connection = DBConnectionUtility.getConnection();
				PreparedStatement pstStatement = connection.prepareStatement(SQLConstants.CHECK_SHIPPING_MASTER)) {
			pstStatement.setString(1, medTypeCode);
			pstStatement.setString(2, weightRange);
			ResultSet resultSet = pstStatement.executeQuery();
			while (resultSet.next()) {
				checkShipFlag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		LOG.info("Method Exit:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + checkShipFlag);
		return checkShipFlag;
	}

	@Override
	public boolean addShippingMaster(ShippingMasterInfo masterInfo) throws PharmaException {
		final String METHOD_NAME = "addShippingMaster";
		boolean addShipFlag = false;
		LOG.info("Method Invoked:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + masterInfo);
		try (Connection connection = DBConnectionUtility.getConnection();
				PreparedStatement pstStatement = connection.prepareStatement(SQLConstants.ADD_SHIPPING_MASTER)) {
			pstStatement.setString(1, masterInfo.getMedicineTypeCode());
			pstStatement.setString(2, masterInfo.getWeightRange());
			pstStatement.setDouble(3, masterInfo.getShippingCharge());
			int rowsAff = pstStatement.executeUpdate();
			if (rowsAff > 0) {
				addShipFlag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		LOG.info("Method Exit:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + addShipFlag);
		return addShipFlag;
	}

	@Override
	public ShippingMasterInfo findShippingMaster(String medTypeCode, String weightRange) throws PharmaException {
		final String METHOD_NAME = "findShippingMaster";
		ShippingMasterInfo masterInfo = null;
		LOG.info("Method Invoked:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + medTypeCode + ":"
				+ weightRange);
		try (Connection connection = DBConnectionUtility.getConnection();
				PreparedStatement pstStatement = connection.prepareStatement(SQLConstants.FIND_SHIPPING_MASTER)) {
			pstStatement.setString(1, medTypeCode);
			pstStatement.setString(2, weightRange);
			ResultSet resultSet = pstStatement.executeQuery();
			while (resultSet.next()) {
				masterInfo = new ShippingMasterInfo();
				masterInfo.setMedicineTypeCode(resultSet.getString(1));
				masterInfo.setWeightRange(resultSet.getString(2));
				masterInfo.setShippingCharge(resultSet.getDouble(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		LOG.info("Method Exit:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + masterInfo);
		return masterInfo;
	}

	@Override
	public boolean updShippingMaster(ShippingMasterInfo masterInfo) throws PharmaException {
		final String METHOD_NAME = "updShippingMaster";
		boolean updShipFlag = false;
		LOG.info("Method Invoked:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + masterInfo);

		try (Connection connection = DBConnectionUtility.getConnection();
				PreparedStatement pstStatement = connection.prepareStatement(SQLConstants.UPD_SHIPPING_MASTER)) {
			pstStatement.setDouble(1, masterInfo.getShippingCharge());
			pstStatement.setString(2, masterInfo.getMedicineTypeCode());
			pstStatement.setString(3, masterInfo.getWeightRange());
			int rowsAff = pstStatement.executeUpdate();
			if (rowsAff > 0) {
				updShipFlag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		LOG.info("Method Exit:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + updShipFlag);
		return updShipFlag;
	}

	@Override
	public boolean delShippingMaster(String medTypeCode, String weightRange) throws PharmaException {
		final String METHOD_NAME = "delShippingMaster";
		boolean delShipFlag = false;
		LOG.info("Method Invoked:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + medTypeCode + ":"
				+ weightRange);
		try (Connection connection = DBConnectionUtility.getConnection();
				PreparedStatement pstStatement = connection.prepareStatement(SQLConstants.DEL_SHIPPING_MASTER)) {
			pstStatement.setString(1, medTypeCode);
			pstStatement.setString(2, weightRange);
			int rowsAff = pstStatement.executeUpdate();
			if (rowsAff > 0) {
				delShipFlag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		LOG.info("Method Exit:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + delShipFlag);
		return delShipFlag;
	}

}
