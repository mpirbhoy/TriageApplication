package triageapplication.activities;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.os.Bundle;
import triageapplication.patientandrecord.*;
import triageapplication.user.*;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/** The Activity to sign in a new Patient. */
public class NewPatientActivity extends Activity {

	/** This NewPatientActivity's PatientManager. */
	private PatientManager patientManager;

	/** This NewPatientActivity's Nurse. */
	private Nurse nurse;

	/** This NewPatientActivity's previous Intent. */
	private Intent intentPrevious;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Removes the title bar.
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_new_patient);

		// Sets the screen orientation to portrait.
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		// Gets the previous NewPatientActivity's PatientManager and Nurse via 
		// the intent.
		intentPrevious = getIntent();
		patientManager = (PatientManager) intentPrevious.getSerializableExtra
				("patients");
		nurse = (Nurse) intentPrevious.getSerializableExtra("user");
	}

	/**
	 * Reads data from the UI, creates a Calendar for the date of birth,
	 * has the nurse sign in a new patient, and passes nurse
	 * and patientManager to the next Activity, SuccessPatientAddActivity.
	 * @param view A user interface component.
	 */
	public void addPatient(View view) {
		Intent intent = new Intent(this, SuccessPatientAddActivity.class);

		// Reads the user input for the name of the patient.
		EditText nameText = (EditText) findViewById(R.id.editText1);
		String name = nameText.getText().toString();

		// Reads the user input for the day of the date of birth
		// and creates a string to refer to it.
		Spinner dobDay = (Spinner) findViewById(R.id.spinner3);
		String dayString = dobDay.getSelectedItem().toString();

		// Reads the user input for the month of the date of birth
		// and creates a string to refer to it.
		Spinner dobMonth = (Spinner) findViewById(R.id.spinner2);
		String monthString = dobMonth.getSelectedItem().toString();

		// Reads the user input for the year of the date of birth
		// and creates a string to refer to it.
		Spinner dobYear = (Spinner) findViewById(R.id.spinner1);
		String yearString = dobYear.getSelectedItem().toString();

		// Reads the user input for the health card number of the patient.
		EditText hcnText = (EditText) findViewById(R.id.temperatureEditText);
		String hcn = hcnText.getText().toString();

		// The TextView for when the user inputs incorrect information.
		TextView failure = (TextView) findViewById(R.id.textView4);

		// Creates a new Calendar for the date of birth, parses the date of 
		// birth string into Integers and sets the Calendar date to those 
		// variables. month corrected for Calendar indexing [0 -> 11].
		Calendar dob = new GregorianCalendar();
		Integer day = Integer.parseInt(dayString);
		Integer month = Integer.parseInt(monthString) - 1;
		Integer year = Integer.parseInt(yearString);

		// Checks to make sure no invalid days are entered.
		if ((month.equals(2) && day.equals(30))||
				(month.equals(2) && day.equals(31))||
				(month.equals(4) && day.equals(31))||
				(month.equals(6) && day.equals(31))||
				(month.equals(9) && day.equals(31))||
				(month.equals(11) && day.equals(31))){
			failure.setText("That day does not exist in the calendar.");
		} else {
			dob.set(year,month,day);

			// Checks user input for errors such as newline characters, 
			// empty strings and commas. New line characters and commas are 
			// troublesome for the reading/writing portion of the project.
			try {
				//The nurse signs the patient in with the user input.
				nurse.signInPatient(patientManager, name, dob, hcn, 0);

				//Passes patients and nurse to the next activity via the intent.
				intent.putExtra("patients",patientManager);
				intent.putExtra("user",nurse);
				startActivity(intent);
			} catch (InvalidUserInputException e) {
				failure.setText(getString(R.string.invalid_input));
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is 
		// present.
		getMenuInflater().inflate(R.menu.new_patient, menu);
		return true;
	}
}