TriageApplication
=================
*Note: This was the README file for the project that was given to the grader.*

Welcome to the TriageApplication, version 2.0! This is an application built for all of your required ER needs.

Instructions for use:

1. Locate the file in group_0735/PIII/TriageApplication. in group_0735/PIII, there is also a file named passwords.txt - you'll need this to sign in.

2. Load the Android project in Eclipse. The project has been tested on the Nexus 5 phone, but should be capable of running on any android platform.

3. Once the app is running, push the given passwords.txt file to the app, by
   issuing the command " adb push passwords.txt /data/data/packagename/files/passwords.txt ".
   The app has been tested with pushing the files with DDMS, but this has worked as well.

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
NURSE PATH:

4. Once in the app "Log in" screen, type "nurseAdmin" for the username, and "password" for the password.

5. You're on the home page! You have five options, Add a new patient, list all current patients, list patients not seen by the doctor in decreasing order of urgency, look up a patients record by health card number, or save all data. Let's go with sign in a new patient, first.


6. Add a new patient by typing in the patient's name, health card number, and use the spinners to find the patients
   date of birth. The app will not work with dates that do not exist, such as September 31st.

7. Once you click the sign in patient button, you will be directed to a success screen. Go home from here.

8. At this point you can save all collected data. Please feel free to do so!

9. Now, go to the list of current patients. Your new patient should be on that list! The list can hold as many patients as
   you require, with the dynamically added buttons. Click the patient to go further!

10. Now you are on the patients profile page. From here, you can either record vital signs, display all recorded vital signs, display the patient's entire record, or even send the patient to the Physician (when the time is ready, of course!)

11. Record the vital signs to your hearts desire! There is an upper limit of about 300.0 per field, but your patients
    shouldn't be needing anything higher than that. Note, this changes the patient's Urgency based on certain boundaries.

12. You're now on the home screen. Navigate back to the patient profile you were just in- and get the vital signs!

13. You can add as many vital signs as you want, and a scroll view allows you to see them all.

14. Now, go to the List of Patients By Urgency. This displays the Patient's that have not been seen by a Physician, ordered by their current Urgency!

15. Heading back to the Home screen, we can now go and look up a patient with their health card number. Remember any patients health card number from before, and this will display their entire record!

16. Let's save the data one more time from the home screen.

17. Shut down the app, re-open it, and all of your information will be there! Imagine the possibilities.

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

PHYSICIAN PATH:

4. Once in the app "Log in" screen, type "physicianAdmin" for the username, and "password" for the password.

5. You're on the home page! You have three options, list all current patients, look up a patients record by health card number, or save all data. Let's go with sign in a new patient, first.

6. (From the NURSE PATH, a Patient should be saved in the application) Now, go to the list of current patients. From here, choose the patient you want to go to!

7. Now you are on the patients profile page. From here, you can either record a prescription, display all prescriptions, display all recorded vital signs, or display the patient's entire record.

8. Record any Prescription you wish! Just enter the Prescription name, and instructions, and the Application will store the Prescription for you!

7. Back on the Patients page, we can either display all Prescriptions, display all vitals, or display all patient information. Feel free to explore these three options, they are much like the Nurse's options from the NURSE PATH.

8. Heading back to the Home page, we can see that we can also look up a patient's record with the health card number. Let's save first, and head on over to search up a Patient.

9. Now that we've exhausted every litre of funcionality that we need, feel free to explore the Application more, if need be!

Enhancements for bonus:
	- Jazzed up user interface for all your asthetically appealing needs, featuring the dynamically added layouts, spinners, Holo Dark, and much more!
	

Credit given to:

Jennifer Campbell for her idea of the PatientManager (derived from the 'RecordManager.java class').
Many concepts have been derived from her lectures. SaveToFile, populate and the constructor were based on her RecordManager.java.


http://android-holo-colors.com/ for the used Android Themes.
	
Group Members:
	
    - Samuel Ko
    
    - Mohammad Mujtaba Pirbhoy
    
    - Md Rafiur Rashid Tajbir Rashid
    
    - Ian William Stewart- Binks

