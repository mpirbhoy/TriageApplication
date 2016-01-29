package triageapplication.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


/**A UserManager class. */
public class UserManager implements Serializable {

	/** Generated serial ID for this UserManager.*/
	private static final long serialVersionUID = 7194942683915757678L;

	/** This UserManager's userNamesToUser. */
	private HashMap<String, User> userNamesToUser;

	/** This UserManager's userNamesToPasswords. */
	private HashMap<String, String> userNamesToPasswords;

	/** This UserManager's usernameArray. */
	private ArrayList<String> usernameArray; 

	/**
	 * Constructs this UserManager object with the file path file
	 * and the file name fileName.
	 * @param file The file path for the file that this UserManager to 
	 * load and save to.
	 * @param fileName The name of file this UserManager to loads and saves 
	 * to.
	 * @throws IOException
	 */
	public UserManager(File dir, String filename) throws IOException {
		this.userNamesToUser = new HashMap<String, User>();
		this.usernameArray = new ArrayList<String>();
		this.userNamesToPasswords = new HashMap<String, String>();

		File file = new File(dir, filename);

		// This loads data from the specified File,
		if (file.exists()) {
			this.populate(file.getPath());
		} else {
			file.createNewFile();
		}
	}

	/**
	 * Returns this UserManager's userNamesToPasswords value, password,
	 * of the corresponding key, username.
	 * @param username The username of the Nurse being retrieved.
	 * @return The password value of the key username in this UserManager's 
	 * userNamesToPasswords.
	 */
	public String getNameToPassword(String username) {
		String password = new String(userNamesToPasswords.get(username));
		return password;
	}

	/** 
	 * Returns this UserManager's ArrayList of User usernames.
	 * @return This UserManager's usernameArray ArrayList.
	 * */
	public ArrayList<String> getUsernames() {
		ArrayList<String> usernameClone = new ArrayList<String>
		(this.usernameArray);
		return usernameClone;
	}

	/** 
	 * Returns a User with username username from this UserManager's 
	 * userNamesToUser.
	 * @param username The username of the Nurse retrieved by this 
	 * UserManager.
	 * @return The Nurse with username username in this UserManager's 
	 * userNamesToNurses.
	 * */
	public User getUser(String username) {

		// Can return either a Nurse or Physician.
		return this.userNamesToUser.get(username);
	}

	/**
	 * Populates this UserManager's userNamesToNurses, 
	 * usernameArray and userNamesToPasswords from the filepath parameter.
	 * @param filepath The filepath of the file where Nurse data is stored 
	 * for this UserManager.
	 * @throws FileNotFoundException 
	 */
	private void populate(String filePath) throws FileNotFoundException {
		//Loads saved data.
		Scanner scanner = new Scanner(new FileInputStream(filePath));
		String[] users;
		while (scanner.hasNextLine()) {

			//Parses files in "nurse,username,password" fashion.
			users = scanner.nextLine().split(",");
			if(users[0].equals("nurse")){

				Nurse nurse = new Nurse(users[1], users[2]);
				this.userNamesToUser.put(users[1], nurse);
				this.userNamesToPasswords.put(users[1], users[2]);
				this.usernameArray.add(users[1]);} 

			// Creates a new Physician, if the username and password information belong to a Physician.
			else if(users[0].equals("physician"))
			{
				Physician physician = new Physician(users[1], users[2]);
				this.userNamesToUser.put(users[1], physician);
				this.userNamesToPasswords.put(users[1], users[2]);
				this.usernameArray.add(users[1]);}
		}
	}

}
