package com.excelr.pharma.service;

import com.excelr.pharma.exceptions.PharmaBusinessException;
import com.excelr.pharma.exceptions.PharmaException;
import com.excelr.pharma.vo.BatchInfo;

/**
 * @author admin
 *
 */
public interface IPharmaService {
	/**
	 * @param batchInfo
	 * @return boolean
	 * @throws PharmaBusinessException
	 * @throws PharmaException
	 */
	public abstract boolean addBatch(BatchInfo batchInfo) throws PharmaBusinessException, PharmaException;

	/**
	 * @param batchInfo
	 * @return boolean
	 * @throws PharmaBusinessException
	 * @throws PharmaException
	 */
	public abstract boolean updateBatch(BatchInfo batchInfo) throws PharmaBusinessException, PharmaException;

}
