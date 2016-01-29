package triageapplication.user;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import triageapplication.patientandrecord.Saveable;

/** An abstract User class.*/
public abstract class User implements Serializable {

	/** A unique ID for serialization. */
	private static final long serialVersionUID = -2544206858057379335L;

	/** This User's username. */
	private String username;

	/** This User's password. */
	private String password;

	/**
	 * Constructs a User with a username username and a password pasword. 
	 * @param username The username of this User.
	 * @param password The password of this User.
	 */
	public User (String username, String password) {
		this.username = username;
		this.password = password;
	}

	/**
	 * Returns this User's username.
	 * @return The username of this User.
	 */
	public String getUsername () {
		//This returns a clone of this User's username.
		String usernameClone = new String(this.username);
		return usernameClone;
	}

	/**
	 * Returns this User's password.
	 * @return The password of this User.
	 */
	public String getPassword () {
		//This returns a clone of this User's password
		String passwordClone = new String(this.password);
		return passwordClone;
	}

	/**
	 * Saves all collected data to the Manager manager.
	 * @param manager The Manager to write the data to file.
	 * @param outputstream The OutputStream given to the manager.
	 * @throws IOException
	 */
	public void save(Saveable manager, OutputStream outputStream) {
		manager.saveToFile((FileOutputStream) outputStream);
	}
}