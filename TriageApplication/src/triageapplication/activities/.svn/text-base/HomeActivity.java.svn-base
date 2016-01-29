package triageapplication.activities;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import triageapplication.patientandrecord.*;
import triageapplication.user.*;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
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


/** An Activity that displays initial options to the user. */
public class HomeActivity extends Activity {

	/** This HomeHomeActivity's Patient Manager. */
	private PatientManager patientManager;

	/** This HomeHomeActivity's User. */
	private User user;

	/** A value representing the type of this HomeActivity's user.*/
	private Boolean isNurse;

	/** The width of this HomeHomeActivity's Buttons. */
	private int width;

	/** The height of this HomeHomeActivity's Buttons. */
	private int height;

	/** The LinearLayout of this HomeActivity for all dynamically added Views
	 *  to be added.*/
	private LinearLayout mainLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Removes title bar.
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		// Sets the screen orientation to portrait.
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		//Get's the Patient Manager and Nurse from the previous activity
		Intent intent = getIntent();
		patientManager = (PatientManager) intent.getSerializableExtra(
				"patients");

		// Checks and sets the User to it's respective type, and changes the 
		// isNurse switch accordingly.
		if(intent.getSerializableExtra("user") instanceof 
				triageapplication.user.Physician){
			isNurse = false;
			user = (Physician)intent.getSerializableExtra("user");
		} else if(intent.getSerializableExtra("user") instanceof 
				triageapplication.user.Nurse){
			isNurse = true;
			user = (Nurse)intent.getSerializableExtra("user");
		}

		// Gets an adaptive scale from hardware device, and sets the 
		// height and width of the buttons accordingly.
		float scale = getBaseContext().getResources().
				getDisplayMetrics().density;
		width = (int)(1000 * scale);
		height = (int)(100 * scale);

		// Sets up the dynamic layout for all views to reside.
		ScrollView scrollView= new ScrollView(this);
		mainLayout = new LinearLayout(this);
		mainLayout.setOrientation(LinearLayout.VERTICAL); 

		// Creates and adds the "Welcome" text to the mainLayout.
		LinearLayout layoutName = new LinearLayout(this);
		TextView textView = new TextView(this);
		textView.setHeight(height+50);
		textView.setTextSize(getResources().getDimension(R.dimen.textsize_big));
		textView.setText("\n" + getString(R.string.welcome_show) + user.getUsername());
		layoutName.addView(textView);
		layoutName.setGravity(Gravity.CENTER);
		mainLayout.addView(layoutName);

		// Adds this button only if the user is a Nurse.
		if(isNurse){
			createButton(getString(R.string.sign_in_patient), signInPatientListener);
		}

		// Button created for all!
		createButton(getString(R.string.list_all), listAllPatientsListener);

		// Adds this button only if the user is a Nurse.
		if(isNurse) {
			createButton(getString(R.string.list_urgency), 
					listPatientsByUrgencyListener);
		}

		// Both Buttons created for all!
		createButton(getString(R.string.look_up_record_show), lookUpRecordsListener);
		createButton(getString(R.string.save), saveListener);

		scrollView.addView(mainLayout);
		setContentView(scrollView);
	}

	/**
	 * Creates and adds a new Button to this HomeActivity's layout, and sets the 
	 * text to text, and OnClickListener to listener.
	 * @param text This Button's text.
	 * @param listener This Button's OnClickListener.
	 */
	private void createButton(String text, OnClickListener listener) {
		LinearLayout layout = new LinearLayout(this);
		Button button = new Button(this);
		button.setText(text); 

		// Takes the determined width and height from above, centers the button,
		// and adds the corresponding onClickListener. Then it adds the button.
		button.setWidth(width);
		button.setHeight(height);
		button.setOnClickListener(listener);
		layout.addView(button);
		layout.setGravity(Gravity.CENTER);
		mainLayout.addView(layout);
	}

	// OnClickListener to sign the patient in.
	OnClickListener signInPatientListener = new OnClickListener() {

		/**
		 * Reads data from the UI, and calls this HomeActivity's 
		 * goToSignInPatient method.
		 * @param view A user interface component.
		 */
		public void onClick(View v) {
			goToSignInPatient(v);
		}
	};	

	// OnClickListener to list all patients.
	OnClickListener listAllPatientsListener = new OnClickListener() {

		/**
		 * Reads data from the UI, and calls this HomeActivity's 
		 * goToListPatientsAct method.
		 * @param view A user interface component.
		 */	
		public void onClick(View v) {
			goToListPatientsAct(v);
		}
	};

	// OnClickListener to list all patients by urgency, whom have not yet
	// been seen by the  Physician.
	OnClickListener listPatientsByUrgencyListener = new OnClickListener() {

		/**
		 * Reads data from the UI, and calls this HomeActivity's goToUrgencyList
		 * method.
		 * @param view A user interface component.
		 */
		public void onClick(View v) {
			goToUrgencyList(v);
		}
	};

	// OnClickListener to look up Patient records with the HCN.
	OnClickListener lookUpRecordsListener = new OnClickListener() {

		/**
		 * Reads data from the UI, and calls this HomeActivity's 
		 * goToLookUpRecordAct method.
		 * @param view A user interface component.
		 */
		public void onClick(View v) {
			goToLookUpRecordAct(v);
		}
	};

	// OnClickListener to save!
	OnClickListener saveListener = new OnClickListener() {

		/**
		 * Saves all collected data by calling this HomeActivity's saveAllData method.
		 * @param view A user interface component.
		 */
		public void onClick(View v) {
			try {
				saveAllData(v);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	};

	/**
	 * Passes along this HomeActivity's Patient Manager and User
	 * via intent to NewPatientActivity 
	 * @param view A component of the User Interface.
	 */
	protected void goToSignInPatient(View v) {

		//Passing on the Patient Manager and Nurse to NewPatientActivity
		Intent intent = new Intent(this, NewPatientActivity.class);
		intent.putExtra("patients", patientManager);
		intent.putExtra("user", user);
		startActivity(intent);
	}

	/**
	 * Passes along this HomeActivity's PatientManager and User via intent to 
	 * ListPatientsActivity, along with the list preference type. 
	 * @param view A component of the User Interface.
	 */
	public void goToListPatientsAct(View view) {

		//Passing on the Patient Manager and User to ListPatientsActivity
		Intent intent = new Intent(this, ListPatientsActivity.class);
		intent.putExtra("patients", patientManager);
		intent.putExtra("user", user);

		// "0" indicates that the user is requesting to see the all patients 
		// list.
		intent.putExtra("type", 0);
		startActivity(intent);
	}

	/**
	 * Passes along this HomeActivity's PatientManager and User via intent to 
	 * ListPatientsActivity, along with the list preference type. 
	 * @param view A component of the User Interface.
	 */
	protected void goToUrgencyList(View v) {
		// Passing on the Patient Manager and Nurse to NewPatientActivity.
		Intent intent = new Intent(this, ListPatientsActivity.class);
		intent.putExtra("patients", patientManager);

		// "1" indicates that the user is requesting to see the urgency list.
		intent.putExtra("type", 1);
		intent.putExtra("user", user);
		startActivity(intent);
	}

	/**
	 * Saves all the data using this HomeActivity's PatientManager 
	 * patientManager.
	 * @param view A component of the User Interface.
	 */
	public void saveAllData(View view) throws IOException{
		FileOutputStream outputStream;
		try {
			//Creates the OutputStream object using the filename in FILENAME_P
			//variable in LogInActivity
			outputStream = openFileOutput(LogInActivity.FILENAME_P, 
					Context.MODE_PRIVATE);

			//Saves the data from the Patient Manager to the file FILENAME_P
			user.save(patientManager, outputStream);


		} catch (FileNotFoundException e) {
			e.printStackTrace(); 
		}
	}

	/**
	 * Passes along the Patient Manager and User via intent to 
	 * LookUpRecordActivity.
	 * @param view A component of the User Interface.
	 */
	public void goToLookUpRecordAct(View view) {
		Intent intent = new Intent(this, LookUpRecordActivity.class);
		intent.putExtra("user", user);
		intent.putExtra("patients", patientManager);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is 
		// present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}
}