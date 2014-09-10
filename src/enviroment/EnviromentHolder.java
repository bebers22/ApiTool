package enviroment;

import dataInfo.CommandsDataInfo;
import dataInfo.MyUserInfo;
import dataTypes.Actions;
import dataTypes.FrameModel;
import dataTypes.LocalBuildAction;
import dataTypes.LogAreaListiner;
import dataTypes.LogAreaModel;
import dataTypes.TaskSchedulerBoard;
import gui.JComboBoxTool;
import gui.OutputPanel;
import gui.ToolFrame;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JOptionPane;

import org.omg.CORBA.StringHolder;

public class EnviromentHolder {
    
    public static ToolFrame toolFrame; 
    public static StringHolder logAreaNames = new StringHolder(Constants.GENERAL_LOGS+"@"+Constants.LOCAL_BUILD_LOGS+"@"+Constants.BUILD_CC_LOGS+"@"+Constants.BB_MANAGMENT_LOG); //localBuildLog@buildCCLog@generalLog@consolLog);
    public static HashMap<String, LogAreaModel> Logs;
    public static HashMap componentMap = new HashMap<String, LogAreaListiner>();
    public static ArrayList<WeakReference<JComboBoxTool>> ddlList = new ArrayList<>();
    
    public static HashMap<String, String> fileLocations = new HashMap<String, String>();
    public static boolean DEBUG_MODE = false;
    
    public static FrameModel frameModel;
    private static TaskSchedulerBoard workersScheduler;
    
    private static String[] usernamePassword = new String[2]; ///[0] username , [1] password
    
	public static HashMap<String,String> ddlFillInBBs;
    public static HashMap<String,String> ddlFillInVersion;
    private static CommandsDataInfo commandsDataInfo;
    
    private static String[] webLogicDomains; 
    
    ///////////// error logs handling//////////////////
    static ArrayList<String> errorLogs = new ArrayList<>();
    
    public static String getErrorLogs() {
    	return errorLogs.toString();
    }
    ///////////////////////////////////////////////////
    
    /**
     * here we are loading all the preferences from the xml files
     */
	public static void loadPreferences() {

		FileHandler.loadfileLocations();
		FileHandler.loadDdlDetails();
		commandsDataInfo = new CommandsDataInfo();
		webLogicDomains = getWeblogicDomains(new File("\\\\snv4914\\"+getUsernamePassword()[Constants.USERNAME_INDEX]+"\\weblogic"));

	}


	/**
	 * 
	 * @param dir - the path of the weblogic directorys
	 * @return
	 */
	public static String[] getWeblogicDomains(File dir) {
		
		File[] files = dir.listFiles();
		if(files != null) {
			String[] str= new String[dir.listFiles().length];

			for(int i=0; dir.listFiles().length>i;i++) {

				if(files[i].isDirectory()) {

					str[i]= files[i].getName();
				}
			}
			return str;
		}
		else {
			ErrorMsgs.handleException("Get weblogic folders: ", JOptionPane.WARNING_MESSAGE, ErrorMsgs.TITLE_CONNECTION_REFUSED,  ErrorMsgs.FAILD_TO_CREATE_CONNECTION_WITH_MACHINE, ErrorMsgs.FAILD_TO_CREATE_CONNECTION_WITH_MACHINE);
			return new String[0];
		}
	}

	public static CommandsDataInfo getCommandsDataInfo() {
		return commandsDataInfo;
	}


	public static void setCommandsDataInfo(CommandsDataInfo commandsDataInfo) {
		EnviromentHolder.commandsDataInfo = commandsDataInfo;
	}

	public static ToolFrame getToolFrame() {
        return toolFrame;
    }

    public static void setToolFrame(ToolFrame toolFrame) {
        EnviromentHolder.toolFrame = toolFrame;
    }
    
    public static FrameModel getFrameModel() {
        return frameModel;
    }

    public static void setFrameModel(FrameModel frameModel) {
        EnviromentHolder.frameModel = frameModel;
    }
            
    public static String getLogAreaNames() {
        return logAreaNames.toString();
    }

    public static void setLogAreaNames(String logAreaNames) {
        EnviromentHolder.logAreaNames = new StringHolder(logAreaNames);
    }

    public static HashMap<String, LogAreaModel> getLogs() {
        return Logs;
    }

    public static void setLogs(HashMap<String, LogAreaModel> Logs) {
        EnviromentHolder.Logs = Logs;
    }

    public static TaskSchedulerBoard getWorkersScheduler() {
        return workersScheduler;
    }

    public static void setWorkersScheduler(TaskSchedulerBoard workersScheduler) {
        EnviromentHolder.workersScheduler = workersScheduler;
    }
    
	public static String[] getTlgDomains() {
		return webLogicDomains;
	}
    
    public static HashMap getComponentMap() {
        return componentMap;
    }

    public static void setComponentMap(HashMap componentMap) {
        EnviromentHolder.componentMap = componentMap;
        registerLisitners();
    }
    
    
    public static String[] getUsernamePassword() {
		return usernamePassword;
	}
    
	public static void setUsernamePassword(String userName, String password) {
		EnviromentHolder.usernamePassword = new String[] {userName,password};
	}

	public static void setUserInfo(String userName, String password) {	
		MyUserInfo myUserInfo = new MyUserInfo();
		//myUserInfo.
		EnviromentHolder.usernamePassword = new String[] {userName,password};
	}
	
	public static MyUserInfo getUserInfo() {
		return null;
	}

    public static void registerLisitners() {

        Set<Map.Entry<String, String>> entrySet = componentMap.entrySet();
       
        for (Entry entry : entrySet) {
            ((OutputPanel)entry.getValue()).setLogAreaModel(Logs.get((String)entry.getKey()));
        }
    }


    /**
     * change the versions text from 14023 to v1402_3
     * @param ddlForVersions
     * @return
     */
	public static String[] setVersionWithUnderScor(String[] ddlForVersions) {
		
		for(int i=0;i<ddlForVersions.length;i++) {
			ddlForVersions[i] = "v"+ ddlForVersions[i].substring(0, ddlForVersions[i].length() - 1) + "_" + ddlForVersions[i].charAt(ddlForVersions[i].length()-1);
			
		}
				
		return ddlForVersions;
	}
	
    public static Actions getActionType (String type) {
    	Actions actionType = null;
    	switch(type) {
    	case Constants.LOCAL_BUILD_LOGS:
			actionType = new LocalBuildAction(EnviromentHolder.Logs.get(type));
    		break;
    	case Constants.BUILD_CC_LOGS:
    		actionType = new LocalBuildAction(EnviromentHolder.Logs.get(type));
    		break;
    	case Constants.BB_MANAGMENT_LOG:
    		actionType = new LocalBuildAction(EnviromentHolder.Logs.get(type));
    		break;
    	case Constants.GENERAL_LOGS:
    		actionType = new LocalBuildAction(EnviromentHolder.Logs.get(type));
    		break;
    	}
    	return actionType;
    }

}
