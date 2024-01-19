package com.excelr.pharma.vo;

import java.io.Serializable;
import java.util.Objects;

public class MedicineInfo implements Serializable, Comparable<MedicineInfo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String medicineCode;
	private String medicineName;

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
	 * @return the medicineName
	 */
	public String getMedicineName() {
		return medicineName;
	}

	/**
	 * @param medicineName the medicineName to set
	 */
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	@Override
	public String toString() {
		return "MedicineInfo [medicineCode=" + medicineCode + ", medicineName=" + medicineName + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(medicineCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MedicineInfo other = (MedicineInfo) obj;
		return Objects.equals(medicineCode, other.medicineCode);
	}

	@Override
	public int compareTo(MedicineInfo o) {
		return this.medicineCode.compareTo(o.medicineCode);
	}

}
