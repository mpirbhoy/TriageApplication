package triageapplication.user;

import triageapplication.patientandrecord.*;

/** A Physician class.*/
public class Physician extends User {

	/** This Physicians's serialVersionUID. */
	private static final long serialVersionUID = -3721606512163191418L;

	public Physician(String username, String password) {
		super(username, password);
	}

	/**
	 * Adds a Prescription prescription for Patient patient in
	 * PatientManager patientManager.
	 * @param patient The patient this Physician adds the Prescription 
	 * prescription to.
	 * @param prescription The Prescription this Physician adds to Patient 
	 * patient.
	 * @param patientManager The PatientManager that stores the Patient 
	 * patient.
	 */
	public void addPatientPrescription(Patient patient, 
			Prescription prescription, PatientManager patientManager) {
		patient.addPrescription(prescription);
		patientManager.addPatientPrescription(patient.getHealthCardNumber(), 
				prescription);
	}

	@Override
	/** 
	 * Returns a clone of this Physician.
	 * @return A clone of this Physician.
	 */
	public Object clone(){ 
		try{  
			return super.clone(); 
		} catch(Exception e){ 
			return null; 
		}
	}
} 