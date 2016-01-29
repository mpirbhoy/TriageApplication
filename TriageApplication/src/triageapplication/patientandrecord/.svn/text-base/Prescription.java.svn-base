package triageapplication.patientandrecord;


import android.annotation.SuppressLint;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/** A Prescription class. */ 
@SuppressLint("SimpleDateFormat")
public class Prescription implements Serializable{

	/** A unique ID for serialization. */
	private static final long serialVersionUID = -717905481277830280L;

	/** This Prescription's timeStamp. */
	private long timeStamp;

	/** This Prescription's name. */
	private String name;

	/** This Prescription's instructions. */
	private String instructions;

	/**
	 * Constructs a new Prescription with the name name, instructions 
	 * instructions and a time stamp timeStamp.
	 * @param name The name of this Prescription.
	 * @param instructions The instructions of this Prescription.
	 * @param timeStamp The time stamp of this Prescription.
	 * @throws InvalidUserInputException 
	 */
	public Prescription(String name, String instructions, long timeStamp) 
			throws InvalidUserInputException {
		//Sets the name and instructions of this Prescription
		if(name.contains(",") ||
				name.contains("\n") ||
				instructions.contains(",") ||
				instructions.contains("\n")){
			throw new InvalidUserInputException();
		} else {
			this.name = name;
			this.instructions = instructions;

			// If this is a new Prescription, a new timeStamp is created.
			if(timeStamp == 0){
				//Gets the current time and date.
				Calendar rightNow = Calendar.getInstance();

				//Sets the timestamp to the millisecond representation 
				// of Calendar.
				this.timeStamp = rightNow.getTimeInMillis();
			} else {
				// If this is an old Prescription, the timeStamp is retrieved 
				// from the parameter.
				this.timeStamp = timeStamp;
			}
		}
	}

	/**
	 * Returns the name of this Prescription.
	 * @return The name of this Prescription.
	 */
	public String getName() {
		String nameCopy = new String(name);
		return nameCopy;
	}

	/**
	 * Returns the instructions of this Prescription.
	 * @return The instructions of this Prescription.
	 */
	public String getInstructions() {
		String instructionsCopy = new String(instructions);
		return instructionsCopy;
	}

	/**
	 * Returns a string representation of this Prescription to be used
	 * to display the name and instructions of this Prescription.
	 * @return A string representation of this Prescription to be used
	 * to display the name and instructions of this Prescription.
	 */
	public String display() {
		//Formats dates based on the time stamp.
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

		// Creates date representations based on formats.
		String dateString = formatter.format(new Date(timeStamp));
		String timeString = timeFormat.format(new Date(timeStamp));

		return "------------------" + "\n" +
		"Prescription:" + "\n" +
		"Date: " + dateString + "\n" +
		"Time: " + timeString + "\n" +
		"Name: " +
		this.name + "\n" +
		"Instructions: " +
		this.instructions + "\n";	
	}

	/**
	 * Returns a String representation of this Prescription.
	 * @return The string representation of this Prescription.
	 */
	@Override
	public String toString() {
		return "prescription"+"," +
				this.name + "," +
				this.instructions + "," +
				this.timeStamp;
	}
}
