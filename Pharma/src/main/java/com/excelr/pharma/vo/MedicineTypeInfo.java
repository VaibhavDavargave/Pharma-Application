package com.excelr.pharma.vo;

import java.io.Serializable;
import java.util.Objects;

public class MedicineTypeInfo implements Serializable, Comparable<MedicineTypeInfo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String medicineTypeCode;
	private String medicineTypeName;

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
	 * @return the medicineTypeName
	 */
	public String getMedicineTypeName() {
		return medicineTypeName;
	}

	/**
	 * @param medicineTypeName the medicineTypeName to set
	 */
	public void setMedicineTypeName(String medicineTypeName) {
		this.medicineTypeName = medicineTypeName;
	}

	@Override
	public String toString() {
		return "MedicineTypeInfo [medicineTypeCode=" + medicineTypeCode + ", medicineTypeName=" + medicineTypeName
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(medicineTypeCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MedicineTypeInfo other = (MedicineTypeInfo) obj;
		return Objects.equals(medicineTypeCode, other.medicineTypeCode);
	}

	@Override
	public int compareTo(MedicineTypeInfo o) {
		return this.medicineTypeCode.compareTo(o.medicineTypeCode);
	}

}
