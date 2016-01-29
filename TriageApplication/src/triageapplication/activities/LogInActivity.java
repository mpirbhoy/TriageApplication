package triageapplication.activities;

import java.io.IOException;

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

/** An Activity to log into the Application. */
public class LogInActivity extends Activity {

	/** This LogInActivity's file of Patient information to load. */
	protected static final String FILENAME_P = "records6.txt";

	/** This LogInActivity's file of usernames and passwords for Nurses. */
	protected static final String FILENAME_N = "passwords.txt";

	/** This LogInActivity's PatientManager. */
	private PatientManager patientManager;

	/** This LogInActivity's UserManager. */
	private UserManager userManager;

	/** This LogInActivity's User. */
	private User user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Removes title bar.
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_log_in);

		// Sets the screen orientation to portrait.
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		try {
			//Creates the Patient Manager using the file FILENAME_P.
			patientManager = new
					PatientManager(this.getApplicationContext().getFilesDir(),
							FILENAME_P);   
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidUserInputException invalidInput) {
			invalidInput.printStackTrace();
		}

		try {
			//Creates the Nurse Manager.
			userManager = new
					UserManager(this.getApplicationContext().getFilesDir(),
							"passwords.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}      
	}

	/**
	 * Verifies the username and password of this LogInActivity's User and  
	 * passes this LogInActivity's User and UserManager to HomeActivity 
	 * via the intent.
	 * @param view A component of the User Interface.
	 */
	public void goToHome(View view) {
		Intent intent = new Intent(this, HomeActivity.class);

		//Gets the username entered by the user. 
		EditText usernameText = (EditText) findViewById(R.id.username_field);
		String username = usernameText.getText().toString();

		//Gets the password entered by the user.
		EditText passwordText = (EditText) findViewById(R.id.password_field);
		String password = passwordText.getText().toString();

		//Verifys whether the username and password are correct
		if (userManager.getUsernames().contains(username)
				&& userManager.getNameToPassword(username).matches(password)
				&& !username.matches("") && !password.matches("")) {

			user = userManager.getUser(username);
			intent.putExtra("user", user );
			intent.putExtra("patients", patientManager);
			startActivity(intent);

		} else {
			// Displays incorrect username/password message.
			TextView message = (TextView) findViewById(R.id.incorrect);
			message.setText(getString(R.string.incorrect_user));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.log_in, menu);
		return true;
	}
}
