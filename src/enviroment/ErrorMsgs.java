package enviroment;

import javax.swing.JOptionPane;

public class ErrorMsgs {
	
	public final static String FAILD_TO_CREATE_CONNECTION_WITH_MACHINE = "Faild to create connection with machine";
	public final static String USERNAME_OR_PASSWORD_INCORRECT = "Username or Password incorrect";
	public final static String COMMAND_IS_MISSING = "The command is missing";
	public final static String COMMANDS_FILE_PARSING_ERROR_PH = "Error while parsing the place holders file";
	public final static String COMMANDS_FILE_PARSING_ERROR_CO = "Error while parsing the Commands file";
	public final static String USER_PROPERTIES_FILE_ERROR = "Error while parsing the User properties file\n using default values";
	public final static String VERSIONS_BB_FILE_ERROR = "Error while parsing the Versions and BB file\n using default values";
	public final static String FAILD_TO_OPEN_FILE = "Faild to open file";
	public final static String FILE_IS_EMPTY = "File is empty";
	public final static String COMMAND_IS_MISSING_DESCRIPTION = " : command is missing from the commands xml file";
	
	
	
	public final static String TITLE_CONNECTION_REFUSED = "Connection refused";
	public final static String TITLE_WRONG_DETAILS = "Wrong Detaild"; 
	public final static String TITLE_FAILD_TO_LOAD_FILE = "Faild to load from file"; 

	
	/**
	 * Handle an error event
	 * @param commandToPerform - the command that failed
	 * @param jOptionPaneMessageType - the type of the Pop-up message
	 * @param popupTitle - the title of the popup 
	 * @param popupError - the description of the error 
	 * @param errorLogDescription - the description to the log
	 */
	public static void handleException(String commandToPerform, int jOptionPaneMessageType, String popupTitle, String popupError, String errorLogDescription) {
		JOptionPane.showMessageDialog(null, popupError, popupTitle, jOptionPaneMessageType);
		EnviromentHolder.writeToErrorLog(commandToPerform + errorLogDescription);
	}
}
