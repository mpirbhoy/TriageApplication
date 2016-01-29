package triageapplication.activities; 

import triageapplication.patientandrecord.*;
import triageapplication.user.*; 
import android.os.Bundle; 
import android.app.Activity; 
import android.content.Intent; 
import android.content.pm.ActivityInfo; 
import android.view.Menu; 
import android.view.View; 
import android.view.Window; 
import android.widget.EditText;

/** An Activity to add a Prescription for a specific Patient. */
public class AddPrescriptionActivity extends Activity { 

	/** This AddPrescriptionActivity's previous Intent. */
	private Intent intentPatient; 

	/** This AddPrescriptionActivity's PatientManager. */
	private PatientManager patientManager; 

	/** This AddPrescriptionActivity's Patient. */
	private Patient patient; 

	/** This AddPrescriptionActivity's Physician. */
	private Physician user; 

	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		// Removes title bar. 
		this.requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.activity_add_prescription);

		// Sets screen orientation lock. 
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); 

		// Gets all objects necessary to complete this AddPrescriptionActivity's 
		// responsibilities.
		intentPatient = getIntent(); 
		patientManager = (PatientManager) intentPatient.getSerializableExtra 
				("patients"); 
		patient = (Patient) intentPatient.getSerializableExtra("patient"); 

		// As this activity is always used by a Physician, we can assume that 
		// the User is a Physician.
		user = (Physician)intentPatient.getSerializableExtra("user"); 
	} 

	/**
	 * Reads data from the UI, creates a Prescription if user input is correct
	 * and has the user add the new vitals. Passes patientManager, user 
	 * and patient to the next activity, PatientActivity.
	 * @param view A user interface component.
	 */
	public void addNewPrescription(View view) throws 
	InvalidUserInputException { 
		Intent intent = new Intent(this, PatientActivity.class); 

		// Gets the Prescription name from the user.
		EditText nameText = (EditText) findViewById(R.id.editText1); 
		String nameString = nameText.getText().toString(); 

		// Gets the Prescription instructions from the user.
		EditText instructionText = (EditText) findViewById(R.id.editText2); 
		String instructionString = instructionText.getText().toString(); 

		if(!nameString.matches("") 
				&& !instructionString.matches("")) { 
			// Adds the Prescriptions for Patient patient if the Prescription 
			// doesn't contain illegal characters, such as new line and 
			// comma (for parsing reasons).
			try {
				Prescription prescription = new Prescription(nameString, 
						instructionString, 0); 
				((Physician)user).addPatientPrescription(patient, 
						prescription, patientManager);

				// Passes on objects to PatientActivity.java.
				intent.putExtra("patient", patient); 
				intent.putExtra("user",user); 
				intent.putExtra("patients", patientManager); 
				startActivity(intent);} 
			catch (InvalidUserInputException e) {
				e.printStackTrace();
			} 
		}
	} 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) { 
		// Inflate the menu; this adds items to the action bar if it is present. 
		getMenuInflater().inflate(R.menu.add_prescription, menu); 
		return true; 
	} 
} 