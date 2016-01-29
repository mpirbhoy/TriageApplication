package triageapplication.activities;

import triageapplication.patientandrecord.*;
import triageapplication.user.*;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

/** An Activity to add vitals for a specific Patient. */
public class AddVitalsActivity extends Activity {

	/** This AddVitalsActivity's previous Intent. */
	private Intent intentPatient;

	/** This AddVitalsActivity's PatientManager. */
	private PatientManager patientManager;

	/** This AddVitalsActivity's Patient. */
	private Patient patient;

	/** This AddVitalsActivity's Nurse. */
	private User user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Removes title bar.
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_add_vitals);

		// Sets screen orientation lock.
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		// Gets the PatientManager, Patient and User from the previous 
		// activity via the intent.
		intentPatient = getIntent();
		patientManager = (PatientManager) intentPatient.getSerializableExtra
				("patients");
		patient = (Patient) intentPatient.getSerializableExtra("patient");

		// This Activity is only ever used by a Nurse, so it is safe to assume
		// the User is a Nurse.
		user = (Nurse) intentPatient.getSerializableExtra("user");

		// Dynamically changes the text of the button to include patients name.
		TextView newPatient = (TextView) findViewById(R.id.button1);
		newPatient.setText("Add " + patient.getName() + "'s Vital Signs");
	}

	/**
	 * Reads data from the UI, creates a VitalSigns if user input is correct
	 * and has the nurse add the new vitals. Passes patientManager, nurse and 
	 * patient to the next activity, HomeActivity.
	 * @param view A user interface component.
	 */
	public void addNewRecord(View v) {

		// Gets the user input for systolic from the EditText field.
		EditText systolicText = (EditText) findViewById(R.id.editText1);
		String systolicString = systolicText.getText().toString();

		// Gets the user input for diastolic from the EditText field.
		EditText diastolicText = (EditText) findViewById(R.id.editText2);
		String diastolicString = diastolicText.getText().toString();

		// Gets the user input for temperature from the EditText field.
		EditText temperatureText = (EditText) findViewById(R.id.editText3);
		String temperatureString = temperatureText.getText().toString();

		// Gets the user input for heart rate from the EditText field.
		EditText heartRateText = (EditText) findViewById(R.id.editText4);
		String heartRateString = heartRateText.getText().toString();

		// Checks if user input is empty.
		if(!systolicString.matches("")
				&& !diastolicString.matches("")
				&& !temperatureString.matches("")
				&& !heartRateString.matches("")){

			// Changes heartRate, temperature, diastolic and systolic each 
			// to a double from a String.
			double heartRate = Double.parseDouble(heartRateString);
			double temperature = Double.parseDouble(temperatureString);
			double diastolic = Double.parseDouble(diastolicString);
			double systolic = Double.parseDouble(systolicString);

			// Creates a new VitalsSigns based on the user input, 
			// putting in the 0 for the time, as that sets time to now.
			try {
				VitalSigns vitals = new VitalSigns(systolic, diastolic, 
						temperature, heartRate, 0);
				//Nurse adds the vitals signs for the Patient.
				((Nurse)user).addVitalSigns(vitals, patient, patientManager);

				//Sets new Intent to be the HomeActivity.
				Intent intent = new Intent(this, HomeActivity.class);

				// Puts this AddVitalsActivity's Patient, Nurse and PatientManager into 
				// the intent.
				intent.putExtra("patient", patient);
				intent.putExtra("user",user);
				intent.putExtra("patients", patientManager);

				//Starts the HomeActivity activity.
				startActivity(intent);
			}
			catch (InvalidUserInputException exception){
				exception.printStackTrace();	
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is 
		// present.
		getMenuInflater().inflate(R.menu.add_vitals, menu);
		return true;
	}
}