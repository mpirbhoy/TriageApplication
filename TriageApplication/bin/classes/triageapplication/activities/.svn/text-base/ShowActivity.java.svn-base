package triageapplication.activities;

import triageapplication.patientandrecord.*;
import triageapplication.user.*;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/** An Activity to display Patient's data.*/
public class ShowActivity extends Activity {

	/** This ShowActivity's previous intent. */
	private Intent intentPrevious;

	/** This ShowActivity's PatientManager. */
	private PatientManager patientManager;

	/** This ShowActivity's Patient. */
	private Patient patient;

	/** This ShowActivity's User. */
	private User user;

	/** This ShowActivity's main layout */
	private LinearLayout mainLayout;

	/** An int representing the type of data displayed in this Activity. 
	 * 0 for VitalSigns, 1, for Prescription and 2 for Records*/
	private int typeSwitch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//Removes the title bar.
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		//Set this ShowActivity's orientation to portrait.
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		// Get the Intent and extract the PatientManager and Patient,
		// and set them as the variables for this Activity.
		intentPrevious = getIntent();
		patientManager = (PatientManager) intentPrevious.getSerializableExtra(
				"patients");
		patient = (Patient) intentPrevious.getSerializableExtra("patient");
		typeSwitch = (Integer) intentPrevious.getSerializableExtra("switch");

		// Determine the user's type and cast accordingly.
		if(intentPrevious.getSerializableExtra("user") instanceof 
				triageapplication.user.Physician){
			user = (Physician) intentPrevious.getSerializableExtra("user");
		} else if(intentPrevious.getSerializableExtra("user") instanceof 
				triageapplication.user.Nurse){
			user = (Nurse)intentPrevious.getSerializableExtra("user");
		}

		// This sets up the screen to have the Patient's name and vitals to be
		// displayed, as well as the home button and back button.
		ScrollView scrollView= new ScrollView(this);
		mainLayout = new LinearLayout(this);
		mainLayout.setOrientation(LinearLayout.VERTICAL);  

		//Creates the Text View for the Patient's name.
		createText(patient.getName());

		// The Home button is being created and added.
		LinearLayout layout2 = new LinearLayout(this);
		Button home = new Button(this);
		home.setText(getString(R.string.home_show));  
		home.setOnClickListener(homeListener);
		layout2.addView(home);

		// The Back button is being created and added.
		Button back = new Button(this);
		back.setText(getString(R.string.back_show));
		back.setOnClickListener(backListener);
		layout2.addView(back);

		// Both buttons are added.
		mainLayout.addView(layout2);

		// Based on the switch that is passed through the intent, this 
		// determines what will be displayed.
		// 0 for vitals.
		// 1 for Prescriptions.
		// 2 for Records.
		if(typeSwitch == 0) {
			createText(patient.displayVitals());
		} else if(typeSwitch == 1) {
			createText(patient.displayPrescriptions());
		} else if (typeSwitch == 2){
			createText(patient.displayRecords());
		}

		//Adds everything to the scroll view.
		scrollView.addView(mainLayout);
		setContentView(scrollView);
	}

	/**
	 * Creates and adds a new TextView to the main layout, and sets the text 
	 * to textToBeSet.
	 * @param text This TextView's text.
	 */
	private void createText(String textToBeSet) {

		// Creates the layout for the TextView, adds the TextView to the 
		//layout and adds the layout to the main layout. 
		//The TextView is also centered.
		LinearLayout layout = new LinearLayout(this);
		TextView text = new TextView(this);
		text.setText(textToBeSet);
		layout.addView(text);
		layout.setGravity(Gravity.CENTER);
		mainLayout.addView(layout);
	}

	// The OnClickListener to send the user to HomeActivity.
	OnClickListener homeListener = new OnClickListener() {

		/**
		 * Reads data from the UI, and calls this ShowActivity's goHome
		 * method.
		 * @param view A user interface component.
		 */
		public void onClick(View v) {
			goHome(v);
		}
	};

	// The OnClickListener to send the user to the previous Activity.
	OnClickListener backListener = new OnClickListener() {

		/**
		 * Reads data from the UI, and calls this ShowActivity's goBack
		 * method.
		 * @param view A user interface component.
		 */
		public void onClick(View v) {
			goBack(v);
		}
	};

	/**
	 * Passes along this ShowActivity's PatientManager and User via intent to 
	 * HomeActivity.
	 * @param view A component of the User Interface.
	 */
	public void goHome(View v) {
		Intent intent = new Intent(this, HomeActivity.class);

		// Puts patientManager, patient and user into the intent to pass 
		//it to the next  Activity.
		intent.putExtra("patients", patientManager);
		intent.putExtra("user",user);
		startActivity(intent);
	}

	/**
	 * Passes along this ShowActivity's PatientManager, Patient and User via 
	 * intent to PatientActivity.
	 * @param view A component of the User Interface.
	 */
	public void goBack(View v) {
		Intent intent = new Intent(this, PatientActivity.class);

		// Puts patientManager, patient and user into the intent to pass it 
		// to the next Activity.
		intent.putExtra("patients", patientManager);
		intent.putExtra("patient", patient);
		intent.putExtra("user",user);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is 
		// present.
		getMenuInflater().inflate(R.menu.show_vitals, menu);
		return true;
	}
}