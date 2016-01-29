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

/** An Activity to display options concerning a specific Patient to the 
 * user. */
public class PatientActivity extends Activity {

	/** This PatientActivity's Patient Manager. */
	private PatientManager patientManager;

	/** This PatientActivity's Nurse. */
	private User user;

	/** A value representing the type of this PatientActivity's user.*/
	private Boolean isNurse;

	/** A value representing the type of this PatientActivity's user.*/
	private boolean isPhysician;

	/** This PatientActivity's Patient. */
	private Patient patient;

	/** This PatientActivity's width for Buttons. */
	private int width;

	/** This PatientActivity's height for Buttons. */
	private int height;

	/** This PatientActivity's main layout. */
	private LinearLayout mainLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Removes title bar.
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		// Sets the screen orientation to portrait.
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		//Get's the Patient Manager and User from the previous activity
		Intent intent = getIntent();
		patientManager = (PatientManager) intent.getSerializableExtra(
				"patients");
		patient = (Patient) intent.getSerializableExtra(
				"patient");

		// Sets the standard width and height of the Buttons relative to the 
		// display.
		float scale = getBaseContext().getResources().
				getDisplayMetrics().density;
		width = (int)(1000 * scale); 
		height = (int)(100 * scale);

		// Casts user appropriately based on the user passed through the intent, 
		// and sets corresponding switches correctly.
		if(intent.getSerializableExtra("user") instanceof 
				triageapplication.user.Physician){
			isPhysician = true;
			isNurse = false;
			user = (Physician)intent.getSerializableExtra("user");
		} else if(intent.getSerializableExtra("user") instanceof 
				triageapplication.user.Nurse){
			isNurse = true;
			isPhysician = false;
			user = (Nurse)intent.getSerializableExtra("user");
		}

		// Initiates this PatientActivity's layout.
		ScrollView scrollView= new ScrollView(this);
		mainLayout = new LinearLayout(this);
		mainLayout.setOrientation(LinearLayout.VERTICAL);  

		//Creates the Text View for the Patient's name
		createText("\n" + patient.getName(), height+20, 
				getResources().getDimension(R.dimen.textsize_big));

		//Creates the prompt to scroll down.
		createText(getString(R.string.scroll_down), height/2, 
				getResources().getDimension(R.dimen.textsize_small));

		//Creates the Text View for the Patient's HCN.
		createText(patient.getHealthCardNumber(), height/2, 
				getResources().getDimension(R.dimen.textsize_medium));

		//Creates the Text View for the Patient's DOB.
		createText(getString(R.string.dob_show) + patient.getDateOfBirthDisplay(), 
				height/2, getResources().getDimension(R.dimen.textsize_small));

		//Creates the Text View for the Patient's Arrival Time at the ER.
		createText(getString(R.string.arrival_time_show) + patient.
				getArrivalTimeDisplay(), 
				height/2, getResources().getDimension(R.dimen.textsize_small));


		// Creates the Text View for the Patient's time seen by doctor. 
		// If a patient has not yet been seen by a doctor, then it displays 
		// the 'not seen' message.
		String timeSeenText = "";
		if(patient.getSeenByPhysician() == false){
			timeSeenText = getString(R.string.not_seen);
		} else {
			timeSeenText = getString(R.string.time_seen) + 
					patient.getTimeSeenByPhysicianDisplay();
		}
		createText(timeSeenText , height/2, 
				getResources().getDimension(R.dimen.textsize_small));

		// Creates the add/show prescription Buttons if user is Physician.
		if(isPhysician){		
			createButton(getString(R.string.add_prescription), 
					addPrescriptionListener);
			createButton(getString(R.string.show_prescriptions), 
					getPrescriptionsListener);
		} else if(isNurse) {

			// Creates record vitals if user is Nurse.
			createButton(getString(R.string.record_vitals), addVitalsListener);
		}

		// Creates these Buttons for all users.
		createButton(getString(R.string.show_vitals), showVitalsListener);
		createButton(getString(R.string.show_patient_records), 
				showRecordListener);

		// If the Patient has not yet been seen by a Physician, create a Button
		// for the Nurse to send this Patient to the Physician.
		if(isNurse && !patient.getSeenByPhysician()){
			createButton(getString(R.string.send_patient), 
					sendPatientToPhysicianListener);
		} 

		scrollView.addView(mainLayout);
		setContentView(scrollView);
	}

	/**
	 * Creates and adds a new TextView to the main layout, and sets the 
	 * text to textToBeSet,the height to height and the dimensions to dimens.
	 * @param text This TextView's text.
	 * @param height This TextView's height.
	 * @param dimens This TextView's dimensions.
	 */
	private void createText(String textToBeSet, int height, float dimens) {

		// Creates a new TextView for the layout, centers it, and 
		// sets the given dimensions.
		LinearLayout layout = new LinearLayout(this);
		TextView text = new TextView(this);
		text.setHeight(height);
		text.setTextSize(dimens);
		text.setText(textToBeSet);
		layout.addView(text);
		layout.setGravity(Gravity.CENTER);
		mainLayout.addView(layout);
	}

	/**
	 * Creates and adds a new Button to the layout, and sets the text to text,
	 * and OnClickListener to listener.
	 * @param text This Button's text.
	 * @param listener This Button's OnClickListener.
	 */
	private void createButton(String text, OnClickListener listener) {

		// Creates this Button, sets the width and height accordingly, 
		// the listener. Centers it, and adds it to the layout.
		LinearLayout layout = new LinearLayout(this);
		Button button = new Button(this);
		button.setText(text); 
		button.setWidth(width);
		button.setHeight(height);
		button.setOnClickListener(listener);
		layout.addView(button);
		layout.setGravity(Gravity.CENTER);
		mainLayout.addView(layout);
	}

	// The onClickListener to go to the Activity to add vitals.
	OnClickListener addVitalsListener = new OnClickListener() {

		/**
		 * Reads data from the UI, and calls this PatientActivity's 
		 * addVitalsRecord method.
		 * @param view A user interface component.
		 */
		public void onClick(View v) {
			addVitalsRecord(v);
		}
	};

	// The onClickListener to go to the Activity to show vitals.
	OnClickListener showVitalsListener = new OnClickListener() {

		/**
		 * Reads data from the UI, and calls this PatientActivity's 
		 * getVitalsRecord method.
		 * @param view A user interface component.
		 */
		public void onClick(View v) {
			getVitalsRecord(v);
		}
	};

	// The onClickListener to go to the Activity to show records.
	OnClickListener showRecordListener = new OnClickListener() {

		/**
		 * Reads data from the UI, and calls this PatientActivity's 
		 * getPatientRecords method.
		 * @param view A user interface component.
		 */
		public void onClick(View v) {
			getPatientRecords(v);
		}
	};

	// The onClickListener to go to the Activity to add prescriptions.
	OnClickListener addPrescriptionListener = new OnClickListener() {

		/**
		 * Reads data from the UI, and calls this PatientActivity's 
		 * addPrescriptionRecord method.
		 * @param view A user interface component.
		 */
		public void onClick(View v) {
			addPrescriptionRecord(v);
		}
	};

	// The onClickListener to go to the Activity to show prescriptions.
	OnClickListener getPrescriptionsListener = new OnClickListener() {

		/**
		 * Reads data from the UI, and calls this PatientActivity's 
		 * getPrescriptionsRecord method.
		 * @param view A user interface component.
		 */
		public void onClick(View v) {
			getPrescriptionsRecord(v);
		}
	};

	// The onClickListener to send a patient to a Physician (virtually).
	OnClickListener sendPatientToPhysicianListener = new OnClickListener() {

		/**
		 * Reads data from the UI, and calls this PatientActivity's 
		 * sendPatientToPhysician method.
		 * @param view A user interface component.
		 */
		public void onClick(View v) {
			sendPatientToPhysician(v);
		}
	}; 

	/**
	 * Passes along this PatientActivity's PatientManager, Patient and User via
	 * intent to AddVitalsActivity.
	 * @param view A component of the User Interface.
	 */
	public void addVitalsRecord(View view) {
		Intent intent = new Intent(this, AddVitalsActivity.class);

		// Puts this PatientActivity's PatientMAnager, Patient and Nurse in the 
		// intent and start the AddVitalsActivity from the Intent.
		intent.putExtra("patients", patientManager);
		intent.putExtra("patient", patient);
		intent.putExtra("user",user);
		startActivity(intent);
	}

	/**
	 * Passes along this PatientActivity's PatientManager, Patient and User 
	 * via intent to AddPrescriptionActivity. 
	 * @param view A component of the User Interface.
	 */
	public void addPrescriptionRecord(View view) {
		Intent intent = new Intent(this, AddPrescriptionActivity.class);

		// Put this PatientActivity's PatientMAnager, Patient and User in the 
		// intent and start the AddVitalsActivity from the Intent.
		intent.putExtra("patients", patientManager);
		intent.putExtra("patient", patient);
		intent.putExtra("user",user);
		startActivity(intent);
	}

	/**
	 * Passes along this PatientActivity's PatientManager, Patient and User 
	 * via intent to ShowVitalsActivity.
	 * @param view A component of the User Interface.
	 */
	public void getVitalsRecord(View view) {
		Intent intent = new Intent(this, ShowActivity.class);

		// Put this PatientActivity's PatientMAnager, Patient and User in the 
		// intent and starts next Activity.
		intent.putExtra("patients", patientManager);
		intent.putExtra("patient", patient);
		intent.putExtra("user",user);
		intent.putExtra("switch", 0);
		startActivity(intent);
	}

	/**
	 * Passes along this PatientActivity's PatientManager, Patient and 
	 * User via intent to GetVitalsActivity.
	 * @param view A component of the User Interface.
	 */
	public void getPrescriptionsRecord(View view) {
		Intent intent = new Intent(this, ShowActivity.class);

		// Puts this PatientActivity's PatientMAnager, Patient and Physician 
		// in the  intent and starts next Activity.
		intent.putExtra("patients", patientManager);
		intent.putExtra("patient", patient);
		intent.putExtra("user",user);
		intent.putExtra("switch", 1);
		startActivity(intent);
	}

	/**
	 * Passes along this PatientActivity's PatientManager, Patient and User 
	 * via intent to ShowPatientRecordsActivity.
	 * @param view A component of the User Interface.
	 */
	public void getPatientRecords(View view) {
		Intent intent = new Intent(this, ShowActivity.class);

		// Puts this PatientActivity's PatientMAnager, Patient and User in the 
		// intent and starts next Activity.
		intent.putExtra("patients", patientManager);
		intent.putExtra("patient", patient);
		intent.putExtra("user",user);
		intent.putExtra("switch", 2);
		startActivity(intent);
	}

	/**
	 * Passes along this PatientActivity's PatientManager, Patient and 
	 * User via intent to HomeActivity.
	 * @param view A component of the User Interface.
	 */
	public void sendPatientToPhysician(View view) {
		Intent intent = new Intent(this, HomeActivity.class);

		// Virtually sends patient to Physician. Neither Nurse nor Physician 
		// do this directly, for extensibility purposes.
		patientManager.getPatient(patient.getHealthCardNumber()).
		setSeenByPhysician();

		intent.putExtra("patients", patientManager);
		intent.putExtra("patient", patient);
		intent.putExtra("user",user);
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