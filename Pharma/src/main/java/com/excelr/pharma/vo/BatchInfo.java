package com.excelr.pharma.vo;

import java.io.Serializable;

/**
 * @author admin -- Used to Maintain The BatchInfo State
 */
public class BatchInfo implements Serializable, Comparable<BatchInfo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String batchCode;
	private String medicineCode;
	private int weight;
	private double price;
	private double shippingCharge;
	private String careLevel;
	private String medicineTypeCode;
	private String refrigiration;

	/**
	 * @return the refrigiration
	 */
	public String getRefrigiration() {
		return refrigiration;
	}

	/**
	 * @param refrigiration the refrigiration to set
	 */
	public void setRefrigiration(String refrigiration) {
		this.refrigiration = refrigiration;
	}

	@Override
	public String toString() {
		return "BatchInfo [batchCode=" + batchCode + ", medicineCode=" + medicineCode + ", weight=" + weight
				+ ", price=" + price + ", shippingCharge=" + shippingCharge + ", careLevel=" + careLevel
				+ ", medicineTypeCode=" + medicineTypeCode + "]";
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
	 * @return the batchCode
	 */
	public String getBatchCode() {
		return batchCode;
	}

	/**
	 * @param batchCode the batchCode to set
	 */
	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	/**
	 * @return the medicineCode
	 */
	public String getMedicineCode() {
		return medicineCode;
	}

	/**
	 * @param medicineCode the medicineCode to set
	 */
	public void setMedicineCode(String medicineCode) {
		this.medicineCode = medicineCode;
	}

	/**
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
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

	/**
	 * @return the careLevel
	 */
	public String getCareLevel() {
		return careLevel;
	}

	/**
	 * @param careLevel the careLevel to set
	 */
	public void setCareLevel(String careLevel) {
		this.careLevel = careLevel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((batchCode == null) ? 0 : batchCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof BatchInfo)) {
			return false;
		}
		BatchInfo other = (BatchInfo) obj;
		if (batchCode == null) {
			if (other.batchCode != null) {
				return false;
			}
		} else if (!batchCode.equals(other.batchCode)) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(BatchInfo o) {
		return new String(this.batchCode).compareTo(new String(o.batchCode));
	}
}
