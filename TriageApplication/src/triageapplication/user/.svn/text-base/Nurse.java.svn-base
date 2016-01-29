package triageapplication.user;

import java.io.*;
import java.util.Calendar;
import java.util.HashMap;

import triageapplication.patientandrecord.*;

/** A Nurse class. */
public class Nurse extends User implements Serializable, Cloneable {

	/** This Nurse's serialVersionUID. */
	private static final long serialVersionUID = 4523515800765783175L;

	/**
	 * Constructs a new Nurse with username username and password password.
	 * @param username This Nurse's username.
	 * @param password This Nurse's password.
	 */
	public Nurse (String username, String password) {
		super(username, password);
	}

	/**
	 * Creates a new Patient object, assigns them a new Record and adds them
	 * to PatientManager patientManager.
	 * @param patientManager The PatientManager for the Patient to be added
	 * to.
	 * @param name The name of the Patient to be signed in.
	 * @param dateOfBirth The date of birth of the Patient to be signed in.
	 * @param healthCardNumber The health card number of the patient to be
	 * signed in.
	 * @param arrivalTime The current time.
	 * @throws InvalidUserInputException
	 */
	public void signInPatient(PatientManager patientManager, String name,
			Calendar dateOfBirth, String healthCardNumber, long arrivalTime)
					throws InvalidUserInputException {
		HashMap<String, MedicalRecord> records = patientManager.
				getMedicalRecords();
		// Creates the new Patient.
		Patient patient = new Patient(name, dateOfBirth, healthCardNumber,
				arrivalTime, records, true, false, 0);

		// Assigns the Patient a new Record, because they are just being
		// signed in.
		patient.addNewRecord(new Record(patient.getHealthCardNumber(), 0));
		patientManager.add(patient);
	}

	/**
	 * Adds VitalSigns vitals to the patient through the patientManager.
	 * @param vitals The VitalSigns to be added to the patient.
	 * @param patient The Patient that vitals are added to.
	 * @param patientManager The PatientManager that adds the vitals to the
	 * patient.
	 */
	public void addVitalSigns(VitalSigns vitals, Patient patient,
			PatientManager patientManager) {
		patientManager.addPatientVitals(patient.getHealthCardNumber(), vitals);
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
