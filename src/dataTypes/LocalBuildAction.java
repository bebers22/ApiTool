package dataTypes;

import javax.swing.JOptionPane;

import enviroment.Constants;
import enviroment.ErrorMsgs;
import enviroment.InfoMsgs;

public class LocalBuildAction extends Actions{

	private int activityEndMsgCounter = 0;
	private boolean isActivityEnded = false;
	private boolean isBuildSuccess = false;
	private boolean isBuildFaild = false;
	private int numOfSuccessBuilds = 0;
	private boolean isWeblogicUp = false;
	private String resultsMsg = Constants.EMPTY_STRING;

	public LocalBuildAction(LogAreaModel logAreaModel) {
		super(logAreaModel);
	}

	public LocalBuildAction(LogAreaModel logAreaModel, String cmd) {
		super(logAreaModel);
		this.cmd = cmd;
	}
	
	@Override
	public String checkLog(String str) {
		if(isActivityEnded){
			this.stopActivity("End");
			if(isBuildSuccess && !isBuildFaild){
				resetActivity();
				if(cmd.length() > 2) {
					startActivity(cmd);
				} else {
					InfoMsgs.handleMsg(Constants.RUN_LOCAL_BUILD, JOptionPane.INFORMATION_MESSAGE, 
						Constants.BUILD_SUCCESSFUL, resultsMsg, Constants.BUILD_SUCCESSFUL);
				}
				
			}
			
			if(isBuildFaild){
				resetActivity();
				ErrorMsgs.handleException(Constants.RUN_LOCAL_BUILD, JOptionPane.ERROR_MESSAGE, 
						Constants.BUILD_FAILED, resultsMsg, Constants.BUILD_FAILED);
			}
			
			return Constants.EMPTY_STRING;
		}
		
		checkLocalBuildResult(str);
		
		if(str.contains(Constants.END_ACTIVITY_COMMANED)){
			isActivityEnded = true;
		}
		
		if(numOfSuccessBuilds > 0){
			isBuildSuccess = true;
			isActivityEnded = true;
			numOfSuccessBuilds = 0;
			resultsMsg = resultsMsg + Constants.BUILD_SUCCESSFUL + "\n";
		}
		
		if(str.contains(Constants.BUILD_FAILED)){
			isActivityEnded = true;
			isBuildFaild = true;
			resultsMsg = resultsMsg + Constants.BUILD_FAILED + "\n";
			//this.stopActivity("End");
		}
		
		if(str.contains(Constants.WEBLOGIC_FAILD)){
			isActivityEnded = true;
			isWeblogicUp = false;
			resultsMsg = resultsMsg + Constants.WEBLOGIC_FAILD + "\n";
			resetActivity();
			this.stopActivity("End");
		}
		
		if(str.contains(Constants.WEBLOGIC_ALREADY_UP) ){
			isActivityEnded = true;
			isWeblogicUp = true;
			resultsMsg = resultsMsg + Constants.WEBLOGIC_ALREADY_UP + "\n";
			resetActivity();
			this.stopActivity("End");
		}

		if(str.contains(Constants.WEBLOGIC_SUCCESS)){
			isActivityEnded = true;
			isWeblogicUp = true;
			resultsMsg = resultsMsg + Constants.WEBLOGIC_SUCCESS + "\n";
			resetActivity();
			this.stopActivity("End");
		}
		
		if(activityEndMsgCounter > 0){
			isActivityEnded = true;
		}

		return str;
	}

	private void checkLocalBuildResult(String str) {
		if(str.contains(Constants.BUILD_SUCCESSFUL)){
			numOfSuccessBuilds ++;
		}
	}
	
	
	public int getActivityEndMsgCounter() {
		return activityEndMsgCounter;
	}

	public void setActivityEndMsgCounter(int activityEndMsgCounter) {
		this.activityEndMsgCounter = activityEndMsgCounter;
	}

	@Override
	public int getEndMsgCounter() {
		return activityEndMsgCounter;
	}

	@Override
	public void resetActivity() {
		activityEndMsgCounter = 0;
		isActivityEnded = false;
		isBuildFaild = false;
		numOfSuccessBuilds = 0;
		isBuildSuccess = true;
		
	}
	
	@Override
	public void startActivity(String command)  {
		if(command.contains("@")){
			cmd = command.substring(command.indexOf("@")+1, command.length());
			command = command.substring(0, command.indexOf("@"));
		}else {
			cmd = Constants.EMPTY_STRING;
		}
		String endBuildCommand = " end_activity;";
		super.startActivity(command);
	}

	
}
