package enviroment;

import javax.swing.JOptionPane;

public class InfoMsgs {

	public static String BUILD_SUCCESS = "BUILD_SUCCESS";
	
	public static void handleMsg(String commandToPerform, int jOptionPaneMessageType, String popupTitle, String popupMsg, String infLogDescription) {
		JOptionPane.showMessageDialog(null, popupMsg, popupTitle, jOptionPaneMessageType);
		ErrorMsgs.writeToErrorLog(commandToPerform + infLogDescription);
	}
}
