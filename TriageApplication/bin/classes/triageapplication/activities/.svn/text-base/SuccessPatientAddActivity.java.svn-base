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

/** The Activity to display the successful addition of a Patient and to 
 * navigate the user to the HomeActivity. This Activity is meant for future 
 * modularity. */
public class SuccessPatientAddActivity extends Activity {

	/** This SuccessPatientAddActivity's PatientManager. */
	private PatientManager patientManager;

	/** This SuccessPatientAddActivity's Nurse. */
	private Nurse nurse;

	/** This SuccessPatientAddActivity's previous Intent. */
	private Intent intentPrevious;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// This removes title bar.
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_success_patient_add);

		// This orients this SuccessPatientAddActivity's screen to portrait 
		// only. 
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		// Gets the previous Intent and gets the PatientManager and Nurse from 
		// the previous activity.
		intentPrevious = getIntent();
		patientManager = (PatientManager) intentPrevious.getSerializableExtra
				("patients");
		nurse = (Nurse) intentPrevious.getSerializableExtra("user");
	}

	/**
	 * Passes this SuccessPatientAddActivity's patientManager and nurse to 
	 * HomeActivity.
	 * @param view A user interface component.
	 */
	public void goHome(View view) {
		// Passes patients and nurse to the HomeActivity.
		Intent intent = new Intent(this, HomeActivity.class);
		intent.putExtra("patients", patientManager);
		intent.putExtra("user", nurse);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is 
		// present.
		getMenuInflater().inflate(R.menu.success_patient_add, menu);
		return true;
	}
}