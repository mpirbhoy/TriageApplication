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
import android.widget.TextView; 

/** An Activity to look up a Patient's MedicalRecord. */
public class LookUpRecordActivity extends Activity { 

	/** This LookUpRecordsActivity's PatientManager */
	private PatientManager patientManager; 

	/** This LookUpRecordsActivity's User */ 
	private User user; 

	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 

		// Takes away ugly title.
		this.requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.activity_look_up_record); 

		// Locks screen orientation.
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); 

		Intent intent = getIntent(); 
		patientManager = (PatientManager) intent.getSerializableExtra( 
				"patients"); 

		// Casts user accordingly to instance from intent.
		if(intent.getSerializableExtra("user") instanceof 
				triageapplication.user.Physician){ 
			user = (Physician)intent.getSerializableExtra("user"); 
		} else if(intent.getSerializableExtra("user") instanceof 
				triageapplication.user.Nurse){ 
			user = (Nurse)intent.getSerializableExtra("user"); 
		} 

	} 

	/** 
	 * Passes along this LookUpRecordsActivity's Patient Manager, User and Patient 
	 * with a health card number that matches the user input via intent 
	 * to ShowPatientRecordActivity, if the Patient exists.
	 * @param view A component of the User Interface.
	 */
	public void lookUpPatient(View view) { 
		Intent intent = new Intent(this, ShowActivity.class); 

		// Gets the requested health card number from UI.
		EditText hcnText = (EditText) findViewById(R.id.editText1); 
		String hcn = hcnText.getText().toString(); 

		// Gets the patient from the PatientManager, and if the patient 
		// is null. this displays an error message.
		Patient patient = patientManager.getPatient(hcn); 
		if(patient != null){ 
			intent.putExtra("patients", patientManager); 
			intent.putExtra("patient", patient); 
			intent.putExtra("user",user); 
			intent.putExtra("switch", 2);
			startActivity(intent); 
		} else { 
			TextView failure = (TextView) findViewById(R.id.textView2); 
			failure.setText(getString(R.string.invalid_hcn)); 
		} 

	} 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) { 
		// Inflate the menu; this adds items to the action bar if it is present. 
		getMenuInflater().inflate(R.menu.look_up_record,menu); 
		return true; 
	} 

} 
