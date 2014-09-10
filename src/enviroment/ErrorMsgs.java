package enviroment;

import javax.swing.JOptionPane;

public class ErrorMsgs {
	
	public final static String FAILD_TO_CREATE_CONNECTION_WITH_MACHINE = "Faild to create connection with machine";
	public final static String USERNAME_OR_PASSWORD_INCORRECT = "Username or Password incorrect";
	public final static String COMMAND_IS_MISSING = "The command is missing";
	public final static String COMMANDS_FILE_PARSING_ERROR_PH = "Error while parsing the place holders file";
	public final static String COMMANDS_FILE_PARSING_ERROR_CO = "Error while parsing the Commands file";
	public final static String USER_PROPERTIES_FILE_ERROR = "Error while parsing the User properties file\n using default values";
	public final static String LOCATIONS_FILE = "Error while parsing the LOCATIONS file\n please check the path";
	public final static String VERSIONS_BB_FILE_ERROR = "Error while parsing the Versions and BB file\n using default values";
	public final static String FAILD_TO_OPEN_FILE = "Faild to open file";
	public final static String FILE_IS_EMPTY = "File is empty";
	public final static String FAILD_TO_CREATE_FILE = "Faild to create file";
	public final static String COMMAND_IS_MISSING_DESCRIPTION = " : command is missing from the commands xml file";
	public final static String ACTIVITY_IS_RUNNING = "This activity is already running. Please stop the current activity and then try again.";
	public final static String ACTIVITY_IS_NOT_RUNNING = "There is no activity that is currently running.";
	public final static String ACTIVITY_IS_RUNNING_DESCRIPTION = "One of the activitys from the selected tab is already running. In order to start new activity the current activity must be terminated.";
	public final static String ACTIVITY_IS_NOT_RUNNING_DESCRIPTION = "There is no activity which belongs to the selected tab that is currently running.";
	public final static String BUILD_FAILD = "Build failed";

	public final static String TITLE_CONNECTION_REFUSED = "Connection refused";
	public final static String TITLE_WRONG_DETAILS = "Wrong Detaild"; 
	public final static String TITLE_FAILD_TO_LOAD_FILE = "Faild to load from file"; 
	public final static String INSTANCE_IS_ALREADY_RUNNING = "Instance is already runing"; 
	public final static String NO_INSTANCE_IS_RUNNING = "No instance is runing"; 
	public final static String TITLE_FAILD_TO_CREATE_FILE = "Faild to create file"; 

	
	/**
	 * Handle an error event
	 * @param ActionToPerform - the command that failed
	 * @param jOptionPaneMessageType - the type of the Pop-up message
	 * @param popupTitle - the title of the popup 
	 * @param popupError - the description of the error 
	 * @param e - the exception
	 */
	public static void handleException(String ActionToPerform, int jOptionPaneMessageType, String popupTitle, String popupError, Exception e) {
		JOptionPane.showMessageDialog(null, popupError, popupTitle, jOptionPaneMessageType);
		writeToErrorLog(ActionToPerform + (EnviromentHolder.DEBUG_MODE ? e.getStackTrace().toString() : e.getMessage()));
	}
	
	/**
	 * Handle an error event
	 * @param ActionToPerform - the command that failed
	 * @param jOptionPaneMessageType - the type of the Pop-up message
	 * @param popupTitle - the title of the popup 
	 * @param popupError - the description of the error 
	 * @param errorLogDescription - the description to the log
	 */
	public static void handleException(String ActionToPerform, int jOptionPaneMessageType, String popupTitle, String popupError, String errorLogDescription) {
		JOptionPane.showMessageDialog(null, popupError, popupTitle, jOptionPaneMessageType);
		writeToErrorLog(ActionToPerform + errorLogDescription);
	}


	public static void writeToErrorLog(String error) {
		EnviromentHolder.errorLogs.add(Constants.LOG_SEPARATOR + error);
	}
}
