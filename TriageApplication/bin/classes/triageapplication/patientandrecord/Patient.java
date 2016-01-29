package triageapplication.patientandrecord;

import android.annotation.SuppressLint;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;


/** A Patient class. */
@SuppressLint("SimpleDateFormat")
public class Patient implements Serializable {

	/** A unique ID for serialization. */
	private static final long serialVersionUID = -2544206858057379340L;

	/** This Patient's name. */
	private String name;

	/** This Patient's date of birth. */
	private java.util.Calendar dateOfBirth;

	/** This Patient's health card number. */
	private String healthCardNumber;

	/** This Patient's current record. */
	private Record currentRecord;

	/** This Patient's arrival time. */
	private Long arrivalTime;

	/** This Patient's current status at the hospital/ER. */
	private Boolean current;

	/** This Patient's medical record. */
	private MedicalRecord medicalRecord;

	/** A boolean value representing whether or not this Patient has been seen by a Physician*/
	private Boolean seenByPhysician;

	/** The time this Patient was seen by a Physician, if at all */
	private long timeSeenByPhysician;

	/**
	 * Constructs a Patient named name and with date of birth dateOfBirth,
	 * health card number healthCardNumber, a boolean value representing 
	 * their presence at the ER current, an arrival time arrivalTime, 
	 * a hashmap of records records to pull a MedicalRecord from, a boolean 
	 * value representing whether or not this Patient has been seen by a 
	 * Physician seenByPhysician, and a time that this Patient has been 
	 * seen by a Physician timeSeenByPhysician.
	 * @param name The name of this Patient.
	 * @param dateOfBirth The date of birth of this Patient.
	 * @param healthCardNumber The health card number of this Patient.
	 * @param arrivalTime The arrival time of this Patient.
	 * @param records A hash map with health card number for keys and
	 * MedicalRecord objects for values.
	 * @param current A boolean value representing whether a Patient is 
	 * present in the ER or not.
	 * @param seenByPhysician A boolean value representing whether or not 
	 * the Patient has been seen by a Physician.
	 * @param timeSeenByPhysician The time this Patient has been seen by a 
	 * Physician, if at all.
	 * @throws InvalidUserInputException
	 */
	public Patient(String name, Calendar dateOfBirth,
			String healthCardNumber, long arrivalTime, HashMap<String,
			MedicalRecord> records, boolean current, boolean seenByPhysician, 
			long timeSeenByPhysician)
					throws InvalidUserInputException {
		// Checks for unwanted characters and empty strings.
		// Unwanted characters are newlines and commas, due to the writing
		// to file procedure.
		if (!name.contains("\n")&&
				!name.contains(",")&&
				!name.matches("") &&
				!healthCardNumber.matches("")) {
			this.name = name;
			this.dateOfBirth = dateOfBirth;
			this.healthCardNumber= healthCardNumber;
			this.current = current;
			this.medicalRecord = new MedicalRecord(healthCardNumber);
			this.seenByPhysician = seenByPhysician;
			this.timeSeenByPhysician = 0;

			if(records.get(healthCardNumber) == null) {
				this.medicalRecord = new MedicalRecord(healthCardNumber);
			} else {
				this.medicalRecord = records.get(healthCardNumber);
			}

			// This sets the arrival time of the patient if they have just 
			// arrived.
			if(arrivalTime == 0){
				Calendar rightNow = Calendar.getInstance();
				this.arrivalTime = rightNow.getTimeInMillis();
			} else {
				// Otherwise, it sets it to the time given in the constructor.
				this.arrivalTime = arrivalTime;
			}

			this.timeSeenByPhysician = timeSeenByPhysician;

		} else {
			throw new InvalidUserInputException();
		}
	}

	/**
	 * Returns the name of this Patient.
	 * @return The name of this Patient.
	 */
	public String getName() {
		String name = new String(this.name);
		return name;
	}

	/**
	 * Returns the date of birth of this Patient.
	 * @return The date of birth of this Patient.
	 */
	public Calendar getBirthDate() {
		Calendar dob = (Calendar) this.dateOfBirth.clone();
		return dob;
	}

	/**
	 * Returns the health card number of this Patient.
	 * @return The health card number of this Patient.
	 */
	public String getHealthCardNumber() {
		String hcn = new String(this.healthCardNumber);
		return hcn;
	}

	/**
	 * Returns the most recent Record of this Patient.
	 * @return The most recent Record of this patient.
	 */
	public Record getMostRecentRecord() {
		return (Record) this.medicalRecord.getMostRecentRecord().clone();
	}

	/**
	 * Returns the MedicalRecord of this Patient.
	 * @return The MedicalRecord of this Patient.
	 */
	public MedicalRecord getMedicalRecord() {
		return (MedicalRecord) this.medicalRecord.clone();
	}

	/**
	 * Returns the current record of this Patient.
	 * @return The current record of this Patient.
	 */
	public Record getCurrentRecord() {
		return (Record) this.currentRecord.clone();
	}

	/**
	 * Returns whether this Patient is currently signed in.
	 * @return Whether this Patient is currently signed in.
	 */
	public boolean getCurrent(){
		return this.current;
	}

	/**
	 * Returns the urgency of this Patient.
	 * @return The urgency of this Patient.
	 */
	public int getUrgency() {
		// Gets the date and time from right now, determines this Patient's 
		// age, and if the Patient is 2 years old or younger, assigns 1 
		// urgency point to this Patient.
		Calendar today = Calendar.getInstance(); 
		int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR); 
		if (today.get(Calendar.MONTH) < dateOfBirth.get(Calendar.MONTH)) {
			age--; 
		} else if (today.get(Calendar.MONTH) == dateOfBirth.get(
				Calendar.MONTH)
				&& today.get(Calendar.DAY_OF_MONTH) < dateOfBirth.get(
						Calendar.DAY_OF_MONTH)) {
			age--; 
		}
		int urgency = 0;
		if(age < 2){
			urgency += 1;
		}

		// Gets the urgency from the most recent vitals record, and if there 
		// is no record, it doesn't add any urgency points.
		if(!this.currentRecord.getVitalSignsRecords().isEmpty()) {
			ArrayList<VitalSigns> vitals = this.currentRecord.
					getVitalSignsRecords();
			urgency += vitals.get(vitals.size() - 1).getUrgency();
		}
		return urgency;
	}


	/**
	 * Returns true iff this Patient has been seen by a Physician.
	 * @return true iff this Patient has been seen by a Physician.
	 */
	public boolean getSeenByPhysician() {
		return this.seenByPhysician;
	}

	/**
	 * Returns the time this patient has been seen by a Physician, 
	 * and 0 if the patient has not been seen.
	 * @return The time this patient has been seen by a Physician, 
	 * and 0 if the patient has not been seen.
	 */
	public long getTimeSeenByPhysician() {
		return this.timeSeenByPhysician;
	}

	/**
	 * Returns the arrival time of this Patient.
	 * @return The arrival time of this Patient.
	 */
	public long getArrivalTime() {
		return this.arrivalTime;
	}

	/**
	 * Sets this patients seenByPhysician to true, and sets the time stamp of 
	 * this Patient's seenByPhysician.
	 */
	public void setSeenByPhysician() {

		// Sets time to the time the method is called.
		Calendar rightNow = Calendar.getInstance();
		this.timeSeenByPhysician = rightNow.getTimeInMillis();

		this.seenByPhysician = true;
	}

	/**
	 * Adds a new Record to this Patient's MedicalRecord.
	 * @param record A new Record of this Patient.
	 */
	public void addNewRecord(Record record) {
		this.medicalRecord.addRecord(record);
		this.currentRecord = record;
	}

	/**
	 * Adds a new VitalSigns to this Patient's current Record.
	 * @param vitals A new VitalSigns of this Patient.
	 */
	public void addVitals(VitalSigns vitals) {
		this.currentRecord.addNewVitalSignsRecord(vitals); 
	}

	/**
	 * Adds a new Prescription to this Patient's current Record.
	 * @param prescription A new Prescription of this Patient.
	 */
	public void addPrescription(Prescription prescription) {
		this.currentRecord.addNewPrescriptionRecord(prescription); 
	}

	/**
	 * Returns a String representation of this Patient's vital signs to 
	 * display.
	 * @return A String representation of this Patient's vital signs.
	 */
	public String displayVitals() {
		String vitalsDisplay = "";
		ArrayList<VitalSigns> vitalsArray = this.currentRecord.
				getVitalSignsRecords();

		for(VitalSigns vitals : vitalsArray) {
			vitalsDisplay = vitalsDisplay + vitals.display();
		}
		return vitalsDisplay;
	}

	/**
	 * Returns a String representation of this Patient's prescriptions to 
	 * display.
	 * @return A String representation of this Patient's prescriptions.
	 */
	public String displayPrescriptions() {
		String prescriptionsDisplay = "";
		ArrayList<Prescription> prescriptionsArray = this.currentRecord.
				getPrescriptionRecords();
		for(Prescription prescription : prescriptionsArray) {
			prescriptionsDisplay = prescriptionsDisplay + 
					prescription.display();
		}
		return prescriptionsDisplay;
	}

	/**
	 * Returns the string display of this Patient for the GUI.
	 * @return The string display of this Patient for the GUI.
	 */
	public String display() {
		return this.name + "\n" + "HCN: " + this.getHealthCardNumber();
	}

	/**
	 * Returns the string representation of this Patient's Records for the 
	 * GUI.
	 * @return The string representation of this Patient's Records for the 
	 * GUI.
	 */
	public String displayRecords() {
		String recordString = "";
		for(Record record : this.medicalRecord.getPastRecords()){
			recordString = recordString + record.getDisplay();
		}
		return recordString;
	}

	/**
	 * Returns a String representation of this Patient's arrival time.
	 * @return A String representation of this Patient's arrival time.
	 */
	public String getArrivalTimeDisplay() {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		return formatter.format(new Date(this.arrivalTime));
	}

	/**
	 * Returns a String representation of this Patient's date of birth.
	 * @return A String representation of this Patient's date of birth.
	 */
	public String getDateOfBirthDisplay() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		long dateLong = this.dateOfBirth.getTimeInMillis();
		return formatter.format(new Date(dateLong));
	}

	/**
	 * Returns a String representation of this Patient's timeSEenByDoctor.
	 * @return A String representation of this Patient's timeSEenByDoctor.
	 */
	public String getTimeSeenByPhysicianDisplay() {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		return formatter.format(new Date(this.timeSeenByPhysician));
	}

	public CharSequence displayUrgency() {
		return this.name + "\n" + "Urgency: " + this.getUrgency();
	}

	@Override
	public String toString() {
		// This converts the Calendar of date of birth to a readable string.
		int year = this.dateOfBirth.get(Calendar.YEAR);
		int month = this.dateOfBirth.get(Calendar.MONTH);
		int day = this.dateOfBirth.get(Calendar.DAY_OF_MONTH);
		String dobString = year + "/" + month + "/" + day;

		return  "patient," + this.name + ","  + dobString + ","
		+ this.healthCardNumber+ "," + this.arrivalTime+ ","
		+ this.current+ "," + this.seenByPhysician + "," + 
		this.timeSeenByPhysician + "\n" 
		+ this.medicalRecord.toString();
	}
}