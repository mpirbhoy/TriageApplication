package triageapplication.patientandrecord;

import java.io.Serializable;
import java.util.*;

/** A MedicalRecord class. */
public class MedicalRecord implements Serializable {

	/** A unique ID for serialization. */
	private static final long serialVersionUID = -2544206858057379350L;

	/** This MedicalRecords records. */
	private ArrayList<Record> records;

	/** This MedicalRecords health card number. */
	private String healthCardNumber;

	/**
	 * Constructs a new MedicalRecord with a health card number 
	 * healthCardNumber.
	 * @param healthCardNumber The health card number of a Patient.
	 */
	public MedicalRecord (String healthCardNumber) {
		this.healthCardNumber = healthCardNumber;
		records = new ArrayList<Record>();
	}

	/**
	 * Returns the most recent Record of this MedicalRecord.
	 * @return The most recent Record of this MedicalRecord. 
	 */
	public Record getMostRecentRecord() {
		if(!records.isEmpty()) {
			return (Record) this.records.get(this.records.size() - 1).clone();
		} else {
			return null;
		}
	}

	/**
	 * Returns the past Records of this MedicalRecord.
	 * @return The past Records of this MedicalRecord.
	 */
	public ArrayList<Record> getPastRecords() {
		ArrayList<Record> recordsClone = new ArrayList<Record>(this.records);
		return recordsClone;
	}

	/**
	 * Adds a new Record to this MedicalRecord.
	 * @param record A Record to be added to this MedicalRecord.
	 */
	public void addRecord(Record record) {
		this.records.add(record);
	}

	/**
	 * Returns the health card number of this MedicalRecord.
	 * @return The health card number this MedicalRecord.
	 */
	public String getHealthCardNumber() {
		String hcn = new String(this.healthCardNumber);
		return hcn;
	}

	/**
	 * Returns a String representation of this MedicalRecord.
	 * @return A String representation of this MedicalRecord.
	 */
	public String toString() {
		String recordString = "";

		for(Record record : this.records) {
			recordString = recordString + record.toString() + "\n";
		}
		return recordString;
	}

	@Override
	/** 
	 * Returns a clone of this MedicalRecord.
	 * @return A clone of this MedicalRecord.
	 */
	public Object clone(){  
		try {  
			return super.clone();  
		} catch(Exception e){ 
			return null; 
		}
	}
}