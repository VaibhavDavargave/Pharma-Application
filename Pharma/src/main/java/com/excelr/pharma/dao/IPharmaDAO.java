package com.excelr.pharma.dao;

import java.util.List;
import java.util.Set;

import com.excelr.pharma.exceptions.PharmaBusinessException;
import com.excelr.pharma.exceptions.PharmaException;
import com.excelr.pharma.vo.BatchInfo;
import com.excelr.pharma.vo.MedicineInfo;
import com.excelr.pharma.vo.MedicineTypeInfo;
import com.excelr.pharma.vo.ShippingMasterInfo;

/**
 * @author admin
 *
 */
public interface IPharmaDAO {
	/**
	 * @param medicineTypeCode
	 * @param weightRange
	 * @return double
	 * @throws PharmaException
	 */
	public abstract double getShippingCharge(String medicineTypeCode, String weightRange) throws PharmaException;

	/**
	 * @param batchCode
	 * @return boolean
	 * @throws PharmaException
	 */
	public abstract boolean checkBatchCode(String batchCode) throws PharmaException;

	/**
	 * @param medicineCode
	 * @return boolean
	 * @throws PharmaException
	 */
	public abstract boolean checkMedicineCode(String medicineCode) throws PharmaException;

	/**
	 * @param batchInfo
	 * @return boolean
	 * @throws PharmaException
	 */
	public abstract boolean addBatch(BatchInfo batchInfo) throws PharmaException;

	/**
	 * @param batchInfo
	 * @return boolean
	 * @throws PharmaException
	 */
	public abstract boolean updateBatch(BatchInfo batchInfo) throws PharmaException;

	/**
	 * @param addMedicine
	 * @return boolean
	 * @throws PharmaException
	 */
	public abstract boolean addMedicine(MedicineInfo medicineInfo) throws PharmaException;

	/**
	 * @param updateMedicine
	 * @return boolean
	 * @throws PharmaException
	 */
	public abstract boolean updateMedicine(MedicineInfo medicineInfo) throws PharmaException;

	/**
	 * @param batchCode
	 * @return BatchInfo
	 * @throws PharmaException
	 */
	public abstract BatchInfo findBatchByBatchCode(String batchCode) throws PharmaException;

	/**
	 * @param batchCode
	 * @throws PharmaException
	 */
	public abstract boolean removeBatchByBacthCode(String batchCode) throws PharmaException;

	/**
	 * @param medicineCode
	 * @throws PharmaException
	 */
	public abstract boolean removeMedicineByMedicineCode(String medicineCode) throws PharmaException;

	/**
	 * @return Set<BatchInfo>
	 * @throws PharmaException
	 */
	public abstract Set<BatchInfo> allBatchesInfo() throws PharmaException;

	/**
	 * @return Set<BatchInfo>
	 * @throws PharmaException
	 */
	public abstract Set<MedicineInfo> allMedicineInfo() throws PharmaException;

	/**
	 * @param validateBatchCode
	 * @return boolean
	 * @throws PharmaBusinessException
	 */
	public abstract boolean validateBatchCode(String batchCode) throws PharmaBusinessException;

	/**
	 * @param validateMedicineCode
	 * @return boolean
	 * @throws PharmaBusinessException
	 */
	public abstract boolean validateMedicineCode(String medicineCode) throws PharmaBusinessException;

	/**
	 * @param getSingleMedicineByMedCode
	 * @return boolean
	 * @throws PharmaBusinessException
	 */
	public abstract MedicineInfo getSingleMedicineByMedCode(String medicineCode) throws PharmaBusinessException;

	/**
	 * @param getBatchCount
	 * @return int
	 * @throws PharmaBusinessException
	 */
	public abstract int getBatchCount() throws PharmaBusinessException;

	/**
	 * @param getMedicineCount
	 * @return int
	 * @throws PharmaBusinessException
	 */
	public abstract int getMedicineCount() throws PharmaBusinessException;

	/**
	 * @param getMedicineTpeCount
	 * @return int
	 * @throws PharmaBusinessException
	 */
	public abstract int getMedicineTypeCount() throws PharmaBusinessException;

	/**
	 * @param getShippingMasterCount
	 * @return int
	 * @throws PharmaBusinessException
	 */
	public abstract int getShippingMasterCount() throws PharmaBusinessException;

	/**
	 * @param getAllMedicineTypes
	 * @return Set<MedicineTypeInfo>
	 * @throws PharmaBusinessException
	 */
	public abstract Set<MedicineTypeInfo> getAllMedicineTypes() throws PharmaBusinessException;

	/**
	 * @param getAllShippingMaster
	 * @return List<ShippingMasterInfo>
	 * @throws PharmaBusinessException
	 */
	public abstract List<ShippingMasterInfo> getAllShippingMaster() throws PharmaBusinessException;

	/**
	 * @param getBatchBySearch
	 * @return List<BatchInfo>
	 * @throws PharmaBusinessException
	 */
	public abstract List<BatchInfo> getBatchBySearch(String ch) throws PharmaBusinessException;

	/**
	 * @param getMedicineBySearch
	 * @return List<MedicineInfo>
	 * @throws PharmaBusinessException
	 */
	public abstract List<MedicineInfo> getMedicineBySearch(String ch) throws PharmaBusinessException;

	/**
	 * @param getMedicineTypeBySearch
	 * @return List<MedicineTypeInfo>
	 * @throws PharmaBusinessException
	 */
	public abstract List<MedicineTypeInfo> getMedicineTypeBySearch(String ch) throws PharmaBusinessException;

	/**
	 * @param getShippSearch
	 * @return List<ShippingMasterInfo>
	 * @throws PharmaBusinessException
	 */
	public abstract List<ShippingMasterInfo> getShippSearch(String ch) throws PharmaBusinessException;

	/**
	 * @param checkValidMedicineTypeCode
	 * @return boolean
	 * @throws PharmaBusinessException
	 */
	public abstract boolean checkValidMedicineTypeCode(String medTypeCode, String medTypeName) throws PharmaException;

	/**
	 * @param addMedicineType
	 * @return boolean
	 * @throws PharmaBusinessException
	 */
	public abstract boolean addMedicineType(MedicineTypeInfo medTypeInfo) throws PharmaBusinessException;

	/**
	 * @param findMedType
	 * @return MedicineTypeInfo
	 * @throws PharmaException
	 */
	public abstract MedicineTypeInfo findMedType(String medTypeCode) throws PharmaException;

	/**
	 * @param updMedType
	 * @return MedicineTypeInfo
	 * @throws PharmaBusinessException
	 */
	public abstract boolean updMedType(MedicineTypeInfo medTypeInfo) throws PharmaBusinessException;

	/**
	 * @param delMedType
	 * @return boolean
	 * @throws PharmaException
	 */

	public abstract boolean delMedType(String medTypeCode) throws PharmaException;

	/**
	 * @param checkAlreadyshippingCharge
	 * @return boolean
	 * @throws PharmaException
	 */
	public abstract boolean checkAlreadyshippingCharge(String medTypeCode, String weightRange) throws PharmaException;

	/**
	 * @param addShippingMaster
	 * @return boolean
	 * @throws PharmaException
	 */
	public abstract boolean addShippingMaster(ShippingMasterInfo masterInfo) throws PharmaException;

	/**
	 * @param findShippingMaster
	 * @return ShippingMasterInfo
	 * @throws PharmaException
	 */
	public abstract ShippingMasterInfo findShippingMaster(String medTypeCode, String weightRange)
			throws PharmaException;

	/**
	 * @param updShippingMaster
	 * @return boolean
	 * @throws PharmaException
	 */
	public abstract boolean updShippingMaster(ShippingMasterInfo masterInfo) throws PharmaException;

	/**
	 * @param delShippingMaster
	 * @return boolean
	 * @throws PharmaException
	 */
	public abstract boolean delShippingMaster(String medTypeCode, String weightRange) throws PharmaException;
}
