package triageapplication.patientandrecord;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Scanner;

/** A PatientManager class. */
public class PatientManager implements Serializable, Saveable {

	/** The serialization ID for this Patient Manager. */
	private static final long serialVersionUID = -3648279409724895106L;

	/** This PatientManager's patientArray. */
	private ArrayList<Patient> patientArray;

	/** This PatientManager's patients. */ 
	private HashMap<String, Patient> patients;

	/** This PatientManager's list of current patients. */
	private ArrayList<Patient> currentPatients;

	/** This PatientManager's medicalRecords. */
	private HashMap<String, MedicalRecord> medicalRecords;

	/** This PatientManager's list of Patients not yet seen by a doctor.*/
	private ArrayList<Patient> patientsNotSeenByDoctor;

	/** This PatientManager's list of Patients ordered by urgency */
	private ArrayList<Patient> urgencyList;

	/** 
	 * Creates a new PatientManager with a File dir and a filename filename.
	 * @param file The directory containing the file of written Patient 
	 * information.
	 * @param fileName The name of the file containing written Patient 
	 * information.
	 * @throws IOException
	 * @throws InvalidUserInputException 
	 */
	public PatientManager(File dir, String filename) throws IOException, 
	InvalidUserInputException {
		this.patients = new HashMap<String, Patient>();
		this.patientArray = new ArrayList<Patient>();
		this.currentPatients = new ArrayList<Patient>();
		this.medicalRecords = new HashMap<String, MedicalRecord>();
		this.patientsNotSeenByDoctor = new ArrayList<Patient>();

		File file = new File(dir, filename);
		if (file.exists()) {
			this.populate(file.getPath());
		} else {
			file.createNewFile();
		}
	}

	/**
	 * Adds a Patient object to this PatientManager's patientArray and 
	 * patients.
	 * @param patient The Patient to be added to this PatientManager's 
	 * patientArray and patients.
	 */
	public void add(Patient patient) {
		this.patients.put(patient.getHealthCardNumber(),patient);
		this.patientArray.add(patient);
		this.medicalRecords.put(patient.getHealthCardNumber(), 
				patient.getMedicalRecord());
		if(patient.getCurrent()){
			this.currentPatients.add(patient);
			if(!patient.getSeenByPhysician())
			{
				this.patientsNotSeenByDoctor.add(patient);
			}
		}
	}

	/**
	 * Returns this PatientManager's HashMap of Patients, patients.
	 * @return This PatientManager's HashMap of Patients, patients.
	 */
	public HashMap<String, Patient> getPatients() {
		HashMap<String,Patient>patientsClone = new HashMap<String, 
				Patient>(this.patients);
		return patientsClone; 
	}

	/**
	 * Returns a Patient from this PatientManager's patients with the health 
	 * card number healthCardNumber.
	 * @param healthCardNumber The patient's health card number.
	 * @return A Patient from this PatientManager's patients.
	 */
	public Patient getPatient(String healthCardNumber) {
		return patients.get(healthCardNumber);
	}

	/**
	 * Returns MedicalRecords for Patients stored in this PatientManager.
	 * @return MedicalRecords for Patients stored in this PatientManager.
	 */
	public HashMap<String, MedicalRecord> getMedicalRecords() {
		HashMap<String, MedicalRecord> medRecords = new HashMap<String, 
				MedicalRecord>(this.medicalRecords);
		return medRecords;
	}

	/**
	 * Returns this PatientManager's list of current patients, currentPatients.
	 * @return This PatientManager's list of current patients, currentPatients.
	 */
	public ArrayList<Patient> getCurrentPatients() {
		ArrayList<Patient> curPatients = new ArrayList<Patient>
		(this.currentPatients);
		return curPatients;
	}

	/**
	 * Returns a list of Patients that have not been seen by a doctor, ordered
	 * by descending urgency.
	 * @return  An ArrayList of Patients that have not been seen by a 
	 * doctor, ordered by descending urgency.
	 */
	public ArrayList<Patient> getUrgencyList() {
		this.urgencyList = new ArrayList<Patient>(this.patientsNotSeenByDoctor);
		for(Patient patient : this.patientsNotSeenByDoctor){
			if(patient.getSeenByPhysician())
			{
				this.urgencyList.remove(patient);
			}
		}

		Patient temp;
		if (this.urgencyList.size()>1) {
			for (int x=0; x<this.urgencyList.size(); x++) {
				for (int i=0; i < this.urgencyList.size()-i; i++) {
					if (this.urgencyList.get(i + 1).getUrgency() > 
					urgencyList.get(i).getUrgency()) {
						temp = this.urgencyList.get(i);
						this.urgencyList.set(i,this.urgencyList.get(i+1) );
						this.urgencyList.set(i+1, temp);
					}
				}
			}
		}
		return this.urgencyList;
	}

	/**
	 * Returns this PatientManager's list of Patients.
	 * @return This PatientManager's list of Patients.
	 */
	public ArrayList<Patient> getArrayOfPatients() {
		ArrayList<Patient> patientsClone = new ArrayList<Patient>
		(this.patientArray);
		return patientsClone;
	}

	/**
	 * Adds a Patient's VitalSigns.
	 * @param string The Patient's health card number.
	 * @param vitals The VitalSigns to be added to the Patient. 
	 */
	public void addPatientVitals(String healthCardNumber, VitalSigns vitals) {
		this.patients.get(healthCardNumber).addVitals(vitals);
	}

	/**
	 * Adds a Patient's VitalSigns.
	 * @param healthCardNumber The Patient's health card number.
	 * @param prescription The Patient's Prescription prescription.
	 */
	public void addPatientPrescription(String healthCardNumber,
			Prescription prescription) {
		this.patients.get(healthCardNumber).addPrescription(prescription);
	}

	/**
	 * Saves this PatientMananger's Patients' data onto a file.
	 * @param output This PatientMananger's Patients' information.
	 */
	public void saveToFile(FileOutputStream outputStream) {
		try {
			for (Patient n : this.patientArray) {
				outputStream.write((n.toString() + "\n").getBytes());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reads from a file located at file path filePath and loads saved data 
	 * to this PatientManager.
	 * @param filePath The file path of the file to be read.
	 * @throws FileNotFoundException 
	 */
	private void populate(String filePath) throws FileNotFoundException, 
	InvalidUserInputException {

		Scanner scanner = new Scanner(new FileInputStream(filePath));
		String[] reader;
		Patient patient = null;
		Record record = null;

		while(scanner.hasNextLine()) {
			reader = scanner.nextLine().split(",");

			// This checks if the line being read is for a patient,
			// and loads an individual patient based on that.
			if(reader[0].equals("patient")){
				String name = reader[1];

				// Gets the birth date of the patient.
				Calendar dob = new GregorianCalendar();
				String[] dobSplit = reader[2].split("/");
				Integer day = Integer.parseInt(dobSplit[2]);
				Integer month = Integer.parseInt(dobSplit[1]);
				Integer year = Integer.parseInt(dobSplit[0]);
				dob.set(year,month,day);

				// Gets the health card number, arrival time, current, 
				// seenByDoctor, and time seen by doctor.
				String hcn = reader[3];
				long arrivalTime = Long.parseLong(reader[4]);
				boolean current = (reader[5].equals("true"));
				boolean seenByDoctor = (reader[6].equals("true"));
				long timeSeen = Long.parseLong(reader[7]);

				// Constructs a new Patient with the data from the file and adds 
				// it to this PatientManager
				patient = new Patient(name, dob, hcn, arrivalTime, 
						this.getMedicalRecords(), current, seenByDoctor, 
						timeSeen);
				this.add(patient);
			}
			// Reads the Record section of the .txt file.
			if(reader[0].equals("record")) {
				long dateOfRecord = Long.parseLong(reader[1]);
				record = new Record(patient.getHealthCardNumber(), 
						dateOfRecord);

				// Adds to the most recently created patient.
				patient.addNewRecord(record);
			} 
			// Reads the VitalSigns section of the .txt file.
			if(reader[0].equals("vitals")) {

				// All of the VitalSigns information.
				Double systolic = Double.parseDouble(reader[1]);
				Double diastolic = Double.parseDouble(reader[2]);
				Double temperature = Double.parseDouble(reader[3]);
				Double heartRate = Double.parseDouble(reader[4]);
				Long timeStamp = Long.parseLong(reader[5]);

				VitalSigns vitals = new VitalSigns(systolic, diastolic, 
						temperature, heartRate, timeStamp);
				this.addPatientVitals(patient.getHealthCardNumber(), vitals);
			}
			if(reader[0].equals("prescription")){

				// Adds all Prescription information, if any.
				String name = reader[1];
				String instructions = reader[2];
				Long timeStamp = Long.parseLong(reader[3]);
				Prescription prescription = new Prescription(name, instructions, 
						timeStamp);
				this.addPatientPrescription(patient.getHealthCardNumber(), 
						prescription);
			}
		}
		scanner.close();
	}
}