package com.excelr.pharma.vo;

import java.io.Serializable;

public class ShippingMasterInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String medicineTypeCode;
	private String weightRange;
	private double shippingCharge;

	@Override
	public String toString() {
		return "ShippingMasterInfo [medicineTypeCode=" + medicineTypeCode + ", weightRange=" + weightRange
				+ ", shippingCharge=" + shippingCharge + "]";
	}

	/**
	 * @return the medicineTypeCode
	 */
	public String getMedicineTypeCode() {
		return medicineTypeCode;
	}

	/**
	 * @param medicineTypeCode the medicineTypeCode to set
	 */
	public void setMedicineTypeCode(String medicineTypeCode) {
		this.medicineTypeCode = medicineTypeCode;
	}

	/**
	 * @return the weightRange
	 */
	public String getWeightRange() {
		return weightRange;
	}

	/**
	 * @param weightRange the weightRange to set
	 */
	public void setWeightRange(String weightRange) {
		this.weightRange = weightRange;
	}

	/**
	 * @return the shippingCharge
	 */
	public double getShippingCharge() {
		return shippingCharge;
	}

	/**
	 * @param shippingCharge the shippingCharge to set
	 */
	public void setShippingCharge(double shippingCharge) {
		this.shippingCharge = shippingCharge;
	}

}
