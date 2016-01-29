package triageapplication.patientandrecord;

import android.annotation.SuppressLint;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/** A VitalSigns class. */
@SuppressLint("SimpleDateFormat")
public class VitalSigns implements Serializable {

	/** A unique ID for serialization. */
	private static final long serialVersionUID = -2544206858057379330L;

	/** This VitalSigns systolic blood pressure. */
	private double systolic;

	/** This VitalSigns' diastolic blood pressure. */
	private double diastolic;

	/** This VitalSigns' temperature. */
	private double temperature;

	/** This VitalSigns' heart rate. */
	private double heartRate;

	/** This VitalSigns' time stamp. */
	private long timeStamp;

	/** This VitalSigns urgency. */
	private int urgency;

	/**
	 * Constructs a new VitalSigns with systolic systolic, diastolic diastolic,
	 * temperature temperature, heart rate heartRate and a time stamp
	 * timeStamp.
	 * @param systolic The systolic blood pressure of the patient this
	 * VitalSigns belongs to.
	 * @param diastolic The diastolic blood pressure of the patient this
	 * VitalSigns belongs to.
	 * @param temperature The temperature of the patient this VitalSigns
	 * belongs to.
	 * @param heartRate The heart rate of the patient this VitalSigns belongs to.
	 * @param timeStamp The time stamp that this VitalSigns was created at.
	 * @throws InvalidUserInputException
	 */
	public VitalSigns(double systolic, double diastolic,
			double temperature, double heartRate, long timeStamp)
					throws InvalidUserInputException {

		// Checks for unreasonable user input.
		if((systolic > 300) ||
				(systolic < 0) ||
				(diastolic > 300) ||
				(diastolic < 0) ||
				(temperature < 0) ||
				(temperature > 200) ||
				(heartRate < 0) ||
				(heartRate > 350)) {
			throw new InvalidUserInputException();
		} else {
			this.systolic = systolic;
			this.diastolic = diastolic;
			this.temperature = temperature;
			this.heartRate = heartRate;

			// If this is a new VitalSigns, a new timeStamp is created.
			if(timeStamp == 0){

				//Gets the current time and date.
				Calendar rightNow = Calendar.getInstance();

				// Sets the timestamp to the millisecond representation of
				// Calendar.
				this.timeStamp = rightNow.getTimeInMillis();
			} else {

				// If this is an old VitalSigns, the timeStamp is retrieved
				// from the parameter.
				this.timeStamp = timeStamp;
			}

		}
		// Creates the urgency based on the specifications. 
		this.urgency = 0;
		if(this.temperature >= 39.0) {
			this.urgency += 1;
		}
		if(this.diastolic >= 90 || this.systolic >= 140) {
			this.urgency += 1;
		}
		if(this.heartRate >= 100 || this.heartRate <= 50) {
			this.urgency += 1;
		}
	}

	/**
	 * Returns the calculated urgency of this VitalSigns, based on disatolic/systolic blood pressure,
	 * temperature, and heart rate. 
	 * @return An int representation of the the calculated urgency of this VitalSigns, based on disatolic/systolic blood pressure,
	 * temperature, and heart rate. 
	 */  
	public int getUrgency() {
		return this.urgency;
	}

	/**
	 * Returns a string representation of the VitalSigns to be used
	 * to display the VitalSigns.
	 * @return A string representation of the VitalSigns to be used
	 * to display the VitalSigns.
	 */
	public String display() {

		//Formats dates based on the time stamp.
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

		// Creates date representations based on formats.
		String dateString = formatter.format(new Date(timeStamp));
		String timeString = timeFormat.format(new Date(timeStamp));

		return "------------------" + "\n" +
		"Vital Signs:" + "\n" +
		"Date: " + dateString + "\n" +
		"Time: " + timeString + "\n" +
		"Systolic: " +
		this.systolic + "\n" +
		"Diastolic: " +
		this.diastolic + "\n" +
		"Temperature: " +
		this.temperature + "\n" +
		"Heart Rate: " +
		this.heartRate + "\n"; 
	}

	/**
	 * Returns a String representation of this VitalSigns.
	 * @return The string representation of this VitalSigns.
	 */
	@Override
	public String toString() {
		return "vitals"+"," +
				this.systolic + "," +
				this.diastolic + "," +
				this.temperature + "," +
				this.heartRate + "," +
				this.timeStamp;
	}    
}