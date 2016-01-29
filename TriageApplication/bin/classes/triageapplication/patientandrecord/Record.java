package triageapplication.patientandrecord;

import android.annotation.SuppressLint;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

/** A Record class.*/
@SuppressLint("SimpleDateFormat")
public class Record implements Serializable {

	/** A unique ID for serialization. */
	private static final long serialVersionUID = -2544206858057379345L;

	/** This Records health card number. */
	private String healthCardNumber;

	/** This Records vitalSignsRecords.  */
	private ArrayList<VitalSigns> vitalSignsRecords;

	/** This Records' time stamp. */
	private long dateOfRecord;

	/** This Records' list of Prescriptions, prescriptionRecords. */
	private ArrayList<Prescription> prescriptionRecords;

	/**
	 * Constructs a Record with health card number healthCardNumber and a time 
	 * stamp dateOfRecord.
	 * @param healthCardNumber The health card number of this Record.
	 * @param dateOfRecord The time stamp of this Record.
	 */
	public Record(String healthCardNumber, long dateOfRecord) {
		this.healthCardNumber = healthCardNumber;
		this.vitalSignsRecords = new ArrayList<VitalSigns>();
		this.prescriptionRecords = new ArrayList<Prescription>();
		if(dateOfRecord == 0){

			Calendar rightNow = Calendar.getInstance();

			this.dateOfRecord = rightNow.getTimeInMillis();
		} else {

			// If this is an old VitalSigns, the timeStamp is retrieved
			// from the parameter.
			this.dateOfRecord = dateOfRecord;
		}
	}

	/**
	 * Returns the health card number of this Record.
	 * @return The health card number of this Record.
	 */
	public String getHealthCardNumber () {
		return this.healthCardNumber;
	}

	/**
	 * Returns the VitalSigns ArrayList vitalSignsRecords of this Record.
	 * @return The VitalSigns ArrayList vitalSignsRecords of this Record.
	 */
	public ArrayList<VitalSigns> getVitalSignsRecords () {
		ArrayList<VitalSigns> vitalSignsCopy = new ArrayList<VitalSigns>
		(vitalSignsRecords);
		return vitalSignsCopy;
	}

	/**
	 * Returns the Prescription ArrayList prescriptionRecords of this Record.
	 * @return The Prescription ArrayList prescriptionRecords of this Record.
	 */
	public ArrayList<Prescription> getPrescriptionRecords() {
		ArrayList<Prescription> prescriptionsCopy = 
				new ArrayList<Prescription>
		(prescriptionRecords);
		return prescriptionsCopy;
	}

	/**
	 * Adds new VitalSigns to this Record.
	 * @param vitals The VitalSigns of a Patient that this Record belongs to.
	 */
	public void addNewVitalSignsRecord(VitalSigns vitals) {
		this.vitalSignsRecords.add(vitals);
	}

	/**
	 * Adds new Prescription to this Record.
	 * @param prescription The Prescription of a Patient that this Record 
	 * belongs to.
	 */
	public void addNewPrescriptionRecord(Prescription prescription) {
		this.prescriptionRecords.add(prescription);
	}

	/**
	 * Returns the time stamp dateOfRecord of this Record.
	 * @return The time stamp dateOfRecord of this Record.
	 */
	public long getDateOfRecord() {
		return this.dateOfRecord;
	}

	/**
	 * Returns a string representation to display this Record.
	 * @return A string representation to display this Record.
	 */
	public String getDisplay() {
		//Formats dates based on the time stamp.
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

		// Creates date representations based on formats.
		String dateString = formatter.format(new Date(dateOfRecord));
		String timeString = timeFormat.format(new Date(dateOfRecord));

		String vitalString = "";
		for(VitalSigns vitals : this.vitalSignsRecords) {
			vitalString = vitalString + vitals.display();
		}

		String prescriptionString = "";
		for(Prescription prescription : this.prescriptionRecords) {
			prescriptionString = prescriptionString + prescription.display();
		}

		return "+++++++++++++++++++++ \n Record \n" +
		"+++++++++++++++++++++ \n" +
		"Date: " + dateString + "\n" +
		"Time: " + timeString + "\n" +
		vitalString +
		prescriptionString;
	}

	@Override
	public String toString() {
		String recordString = "record" + "," + this.dateOfRecord;

		for(VitalSigns vitals : this.vitalSignsRecords) {
			recordString = recordString + "\n" + vitals.toString();
		}
		for(Prescription prescription : this.prescriptionRecords){
			recordString = recordString + "\n" + prescription.toString();
		}
		return recordString;
	}

	@Override
	public Object clone(){ 
		try{ 
			return super.clone(); 
		} catch(Exception e){
			return null;
		}
	}
}