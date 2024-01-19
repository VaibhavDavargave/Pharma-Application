package com.excelr.pharma.service;

import com.excelr.pharma.constants.ErrorConstants;
import com.excelr.pharma.dao.IPharmaDAO;
import com.excelr.pharma.dao.PharmaDaoImpl;
import com.excelr.pharma.exceptions.PharmaBusinessException;
import com.excelr.pharma.exceptions.PharmaException;
import com.excelr.pharma.vo.BatchInfo;

/**
 * @author admin
 *
 */
public class IPharmaServiceImpl implements IPharmaService {
	private IPharmaDAO pharmaDAO = null;

	/**
	 * 
	 */
	public IPharmaServiceImpl() {
		pharmaDAO = new PharmaDaoImpl();
	}

	@Override
	public boolean addBatch(BatchInfo batchInfo) throws PharmaBusinessException, PharmaException {
		final String METHOD_NAME = "addBatch";
		System.out.println("Method Invoked:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + batchInfo);
		boolean addBatchFlag = false;

		if (pharmaDAO.checkBatchCode(batchInfo.getBatchCode())) {
			throw new PharmaBusinessException(ErrorConstants.ERRORCODE_511);
		}

		if (!pharmaDAO.checkMedicineCode(batchInfo.getMedicineCode())) {
			throw new PharmaBusinessException(ErrorConstants.ERRORCODE_510);
		}

		if (!pharmaDAO.validateBatchCode(batchInfo.getBatchCode())) {
			throw new PharmaException(ErrorConstants.ERRORCODE_509);
		}

		int weight = batchInfo.getWeight();
		if (weight < 100) {
			throw new PharmaBusinessException(ErrorConstants.ERRORCODE_512);
		}

		String weightCode = null;
		if (weight >= 100 && weight < 500) {
			weightCode = "W1";
		} else if (weight >= 500) {
			weightCode = "W2";
		} else {
			weightCode = "W3";
		}
		double shippingCharge = pharmaDAO.getShippingCharge(batchInfo.getMedicineTypeCode(), weightCode);
		System.out.println("Service:" + shippingCharge);
		String careLevelMsg = "Normal";
		if (batchInfo.getRefrigiration().equalsIgnoreCase("Yes")) {
			shippingCharge = shippingCharge + (0.05 * shippingCharge);
			careLevelMsg = "Extremely High";
			batchInfo.setCareLevel(careLevelMsg);
			batchInfo.setShippingCharge(shippingCharge);
		} else {
			shippingCharge = shippingCharge * 1;
			careLevelMsg = "Extremely Low";
			batchInfo.setCareLevel(careLevelMsg);
			batchInfo.setShippingCharge(shippingCharge);
		}

		addBatchFlag = pharmaDAO.addBatch(batchInfo);
		System.out.println("Method Exit:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + addBatchFlag);
		return addBatchFlag;
	}

	@Override
	public boolean updateBatch(BatchInfo batchInfo) throws PharmaBusinessException, PharmaException {
		final String METHOD_NAME = "updateBatch";
		System.out.println("Method Invoked:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + batchInfo);
		boolean updBatchFlag = false;
		int weight = batchInfo.getWeight();
		System.out.println(weight);
		if (weight < 100) {
			throw new PharmaBusinessException(ErrorConstants.ERRORCODE_512);
		}

		String weightCode = null;
		if (weight >= 100 && weight < 500) {
			weightCode = "W1";
		} else if (weight >= 500) {
			weightCode = "W2";
		} else {
			weightCode = "W3";
		}
		System.out.println(weightCode);
		double shippingCharge = pharmaDAO.getShippingCharge(batchInfo.getMedicineTypeCode(), weightCode);
		System.out.println("Service:" + shippingCharge);
		String careLevelMsg = "Normal";
		if (batchInfo.getRefrigiration().equalsIgnoreCase("Yes")) {
			shippingCharge = shippingCharge + (0.05 * shippingCharge);
			careLevelMsg = "Extremely High";
			batchInfo.setCareLevel(careLevelMsg);
			batchInfo.setShippingCharge(shippingCharge);
		} else {
			shippingCharge = shippingCharge * 1;
			careLevelMsg = "Extremely Low";
			batchInfo.setCareLevel(careLevelMsg);
			batchInfo.setShippingCharge(shippingCharge);
		}
		System.out.println(careLevelMsg);
		System.out.println(shippingCharge);
		updBatchFlag = pharmaDAO.updateBatch(batchInfo);
		System.out.println("Method Exit:" + this.getClass().getName() + ":" + METHOD_NAME + ":" + updBatchFlag);
		return updBatchFlag;
	}

}
