package triageapplication.activities;
import triageapplication.patientandrecord.*;
import triageapplication.user.*;

import java.util.ArrayList;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/** An Activity to display lists of Patients. */
public class ListPatientsActivity extends Activity {

	/** This Activity's previous Intent. */
	private Intent intentPrevious;

	/** This Activity's PatientManager. */
	private PatientManager patientManager;

	/** This Activity's Nurse. */
	private User user;

	/** This Activity's array of Patient. */
	private ArrayList<Patient> patients;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Removes title bar.
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_list_patients);

		//Sets this activity's screen orientation to portrait.
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		//Gets previous intent and pulls out the PatientManager and Nurse.
		intentPrevious = getIntent();
		patientManager = (PatientManager) intentPrevious.getSerializableExtra
				("patients");
		// Gets the type-of-list request from the previous Activity.
		int num = (Integer) intentPrevious.getSerializableExtra("type");

		// Checks the type of user from the intent, and casts it correctly.
		if(intentPrevious.getSerializableExtra("user") instanceof 
				triageapplication.user.Physician){
			user = (Physician) intentPrevious.getSerializableExtra("user");
		} else if(intentPrevious.getSerializableExtra("user") instanceof 
				triageapplication.user.Nurse){
			user = (Nurse)intentPrevious.getSerializableExtra("user");
		}

		//Gets the array of patients that are currently being treated.
		// If num == 0, this displays the all list. if num == 1, this 
		// displays the urgency list.
		if(num == 0){
			patients = patientManager.getCurrentPatients();
		} else if(num == 1){
			patients = patientManager.getUrgencyList();
		}

		// Initiates layout.
		ScrollView scrollView= new ScrollView(this);
		LinearLayout mainLayout = new LinearLayout(this);
		mainLayout.setOrientation(LinearLayout.VERTICAL);  

		// The scale of this Activity's display.
		final float SCALE = getBaseContext().getResources().
				getDisplayMetrics().density;

		// Dynamically adds buttons based on the information in the patients 
		// ArrayList<Patient>. Creates the onClick listener with getPatient 
		// as it's onClick method. This also creates a new ScrollView to adapt 
		// to a large amount of current patients.
		for (Patient patient : patients){

			//Creates a new linear layout for the soon to be created button.
			LinearLayout linearLayout = new LinearLayout(this);
			linearLayout.setOrientation(LinearLayout.HORIZONTAL);
			linearLayout.setTag(patient);

			// Creates the new text view.
			TextView textView = new TextView(this);           
			linearLayout.addView(textView);

			// This creates the new button.
			// Dimensions are based off of display.
			Button patientButton = new Button(this);

			int width = (int)(1000 * SCALE); 
			int height = (int)(100 * SCALE); 
			patientButton.setWidth(width);
			patientButton.setHeight(height);
			patientButton.setTag(patient);

			// This sets the text of the new Button, health card number 
			// for all list. urgency for urgency list.
			if(num == 0){
				patientButton.setText(patient.display()); }
			else if (num == 1){
				patientButton.setText(patient.displayUrgency());
			}

			// Adds the view to the layout.
			linearLayout.addView(patientButton);                    
			mainLayout.addView(linearLayout);

			// Assigns the onClick listener to the Button.
			patientButton.setOnClickListener(listener);
		}
		scrollView.addView(mainLayout);
		setContentView(scrollView);
	}

	// The OnClickListener for each dynamically added button.
	OnClickListener listener = new OnClickListener() {

		/**
		 * Reads data from the UI, and calls this Activity's goToPatient 
		 * method.
		 * @param view A user interface component.
		 */
		public void onClick(View v) {
			goToPatient(v);
		}
	};

	/**
	 * Creates a Patient from the View, and passes the patient,
	 * nurse and patientManager to the next Activity, PatientActivity.
	 * @param view A user interface component.
	 */
	public void goToPatient(View v) {
		Intent intent = new Intent(this,PatientActivity.class);
		intent.putExtra("patients", patientManager);

		// Gets the patient from the view as each button has a different view.
		Patient patient = (Patient) v.getTag();

		// Puts patient and nurse into the intent to pass it to the next 
		// Activity.
		intent.putExtra("patient", patient);
		intent.putExtra("user",user);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is 
		// present.
		getMenuInflater().inflate(R.menu.list_patients, menu);
		return true;
	}
}
