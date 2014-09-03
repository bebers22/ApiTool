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

	public LocalBuildAction(LogAreaModel logAreaModel) {
		super(logAreaModel);
	}

	@Override
	public String checkLog(String str) {
		if(isActivityEnded){
			this.stopActivity("End");
			if(isBuildSuccess && !isBuildFaild){
				InfoMsgs.handleMsg(Constants.RUN_LOCAL_BUILD, JOptionPane.INFORMATION_MESSAGE, 
						Constants.BUILD_SUCCESSFUL, Constants.BUILD_SUCCESSFUL, Constants.BUILD_SUCCESSFUL);
			}
			
			if(isBuildFaild){
				ErrorMsgs.handleException(Constants.RUN_LOCAL_BUILD, JOptionPane.ERROR_MESSAGE, 
						ErrorMsgs.BUILD_FAILD, ErrorMsgs.BUILD_FAILD, ErrorMsgs.BUILD_FAILD);
			}
			resetActivity();
			return Constants.EMPTY_STRING;
		}
		
		if(str.contains(Constants.BUILD_SUCCESSFUL)){
			numOfSuccessBuilds ++;
		}
		
		if(str.contains(Constants.END_ACTIVITY_COMMANED)){
			isActivityEnded = true;
		}
		
		if(numOfSuccessBuilds > 1){
			isBuildSuccess = true;
			numOfSuccessBuilds = 0;
		}
		
		if(str.contains(Constants.BUILD_FAILED)){
			isActivityEnded = true;
			isBuildFaild = true;
			//this.stopActivity("End");
		}
		
		if(activityEndMsgCounter > 0){
			isActivityEnded = true;
		}

		return str;
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

}
