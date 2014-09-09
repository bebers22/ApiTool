package enviroment;

import dataInfo.CommandsDataInfo;
import dataInfo.MyUserInfo;
import dataTypes.Actions;
import dataTypes.FrameModel;
import dataTypes.LocalBuildAction;
import dataTypes.LogAreaListiner;
import dataTypes.LogAreaModel;
import dataTypes.TaskSchedulerBoard;
import gui.OutputPanel;
import gui.ToolFrame;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.omg.CORBA.StringHolder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class EnviromentHolder {
    
    public static ToolFrame toolFrame; 
    public static StringHolder logAreaNames = new StringHolder(Constants.GENERAL_LOGS+"@"+Constants.LOCAL_BUILD_LOGS+"@"+Constants.BUILD_CC_LOGS+"@"+Constants.BB_MANAGMENT_LOG); //localBuildLog@buildCCLog@generalLog@consolLog);
    public static HashMap<String, LogAreaModel> Logs;
    public static HashMap componentMap = new HashMap<String, LogAreaListiner>();
    public static ArrayList<JComboBox> ddlList = new ArrayList<JComboBox>();
    
    public static HashMap<String, String> fileLocations = new HashMap<String, String>();
    public static boolean DEBUG_MODE = false;
    
    public static FrameModel frameModel;
    private static TaskSchedulerBoard workersScheduler;
    
    private static String[] usernamePassword = new String[2]; ///[0] username , [1] password
    
	private static HashMap<String,String> ddlFillInBBs;
    private static HashMap<String,String> ddlFillInVersion;
    private static CommandsDataInfo commandsDataInfo;
    
    private static String[] webLogicDomains; 
    
    ///////////// error logs handling//////////////////
    private static ArrayList<String> errorLogs = new ArrayList<>();
    
    public static void writeToErrorLog(String error) {
    	errorLogs.add(Constants.LOG_SEPARATOR + error);
    }
    
    public static String getErrorLogs() {
    	return errorLogs.toString();
    }
    ///////////////////////////////////////////////////
    
    /**
     * here we are loading all the preferences from the xml files
     */
	public static void loadPreferences() {

		loadfileLocations();
		loadDdlDetails();
		commandsDataInfo = new CommandsDataInfo();
		webLogicDomains = getWeblogicDomains(new File("\\\\snv4914\\"+getUsernamePassword()[Constants.USERNAME_INDEX]+"\\weblogic"));

	}


	private static void loadfileLocations() {
		try {

			File filesLocations = new File(DEBUG_MODE ? Constants.LOCAL_LOCATIONS_XML : Constants.LOCATIONS_XML);
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(filesLocations);
			NodeList  nList = doc.getElementsByTagName(Constants.XML_TAG_FILE);
			for(int i=0; i<nList.getLength(); i++) {

				Element el = (Element) nList.item(i);
				EnviromentHolder.fileLocations.put(el.getAttribute(Constants.XML_TAG_NAME), el.getAttribute(Constants.XML_TAG_LOCATION));
			}
		} catch (SAXException | ParserConfigurationException sx) {
			ErrorMsgs.handleException("", JOptionPane.WARNING_MESSAGE, ErrorMsgs.TITLE_FAILD_TO_LOAD_FILE,  ErrorMsgs.LOCATIONS_FILE + (DEBUG_MODE ? Constants.LOCAL_LOCATIONS_XML : Constants.LOCATIONS_XML) , sx.toString());
		} catch (IOException ioe) {
			ErrorMsgs.handleException("", JOptionPane.WARNING_MESSAGE, ErrorMsgs.TITLE_FAILD_TO_LOAD_FILE, ErrorMsgs.FAILD_TO_OPEN_FILE +": locations", ioe.toString());
			createLocalUserDetailsXMLFile();
		} catch (Exception e) {
			ErrorMsgs.handleException("", JOptionPane.WARNING_MESSAGE, ErrorMsgs.TITLE_FAILD_TO_LOAD_FILE, ErrorMsgs.FILE_IS_EMPTY +": locations", e.toString());
		}	
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

	/**
     * Loading the user details
     */
    public static void loadUserEnvDetails() {
    	try {
    		
    	   	File usernamePasswordfile = new File(Constants.USERNAME_PASSWORD_XML);
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(usernamePasswordfile);
			NodeList  nList = doc.getElementsByTagName(Constants.XML_TAG_USER);
			if( nList.getLength() != 0 ) {
				Element el = (Element) nList.item(0);
				setUsernamePassword(el.getAttribute(Constants.XML_TAG_USERNAME), el.getAttribute(Constants.XML_TAG_PASSWORD));
			}
		} catch (SAXException | ParserConfigurationException sx) {
			ErrorMsgs.handleException("", JOptionPane.WARNING_MESSAGE, ErrorMsgs.TITLE_FAILD_TO_LOAD_FILE,  ErrorMsgs.USER_PROPERTIES_FILE_ERROR, sx.toString());
		} catch (IOException ioe) {
			ErrorMsgs.handleException("", JOptionPane.WARNING_MESSAGE, ErrorMsgs.TITLE_FAILD_TO_LOAD_FILE, ErrorMsgs.FAILD_TO_OPEN_FILE +": user properties", ioe.toString());
			createLocalUserDetailsXMLFile();
		} catch (Exception e) {
			ErrorMsgs.handleException("", JOptionPane.WARNING_MESSAGE, ErrorMsgs.TITLE_FAILD_TO_LOAD_FILE, ErrorMsgs.FILE_IS_EMPTY +": user properties", e.toString());
		}	
	}

    /**
     * Creating the local XML file of username and password
     */
    public static void createLocalUserDetailsXMLFile() {
    	
    	try(BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.USERNAME_PASSWORD_XML, true))) {
    		
    		writer.write(Constants.LOCAL_XML_USER_DETAILS_FILE);
    		
    	} catch (IOException e) {
    		ErrorMsgs.handleException("", JOptionPane.WARNING_MESSAGE, ErrorMsgs.TITLE_FAILD_TO_CREATE_FILE, ErrorMsgs.FAILD_TO_CREATE_FILE +": user properties", e.toString());
		}
		
    	setUsernamePassword("", "");
	}

	/**
     * loading the versions an BBs from xml file
     */
    public static void loadDdlDetails() {

    	try {
    		File fXmlFile = new File(EnviromentHolder.fileLocations.get(Constants.BB_AND_VERSIONS_XML));
    		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fXmlFile);

    		ddlFillInBBs = parsePropertiestoMap(Constants.XML_TAG_BB,doc);
    		ddlFillInVersion = parsePropertiestoMap(Constants.XML_TAG_VERSION,doc);
    		
    		
    	} catch (SAXException | ParserConfigurationException sx) {
    		ErrorMsgs.handleException("", JOptionPane.WARNING_MESSAGE, ErrorMsgs.TITLE_FAILD_TO_LOAD_FILE, ErrorMsgs.USER_PROPERTIES_FILE_ERROR, sx.toString());
    	} catch (IOException ioe) {
    		ErrorMsgs.handleException("", JOptionPane.WARNING_MESSAGE, ErrorMsgs.TITLE_FAILD_TO_LOAD_FILE, ErrorMsgs.FAILD_TO_OPEN_FILE +": user properties", ioe.toString());
    	} catch (Exception e) {
    		ErrorMsgs.handleException("", JOptionPane.WARNING_MESSAGE, ErrorMsgs.TITLE_FAILD_TO_LOAD_FILE, ErrorMsgs.FILE_IS_EMPTY +": versions and bb", e.toString());
    	}

    }
    
    /**
     * Parse the xml of the environments and versions
     * @param tagName
     * @param doc
     * @return
     */
    private static HashMap<String, String> parsePropertiestoMap(String tagName, Document doc) {

		NodeList nList = doc.getElementsByTagName(tagName);
		
		int nListLenght = nList.getLength();
		
		HashMap<String, String> mapToFill = new HashMap<String,String>(nListLenght);
		
		for(int i=0 ; i<nListLenght ; i++) {
			Node node = nList.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				Element el = (Element)node;
				mapToFill.put(el.getAttribute(Constants.XML_TAG_ID), el.getAttribute(Constants.XML_TAG_NAME));
			}
		}
		return mapToFill;
	}

    /**
     * get ddl BB content
     * @return
     */
    public static String[] getDdlForBB() {
    	
    	String[] bbsString = {"TlgServer"};
    	
    	if(ddlFillInBBs != null) {
    		bbsString = ddlFillInBBs.values().toArray(new String[ddlFillInBBs.size()]);
    	}

    	return bbsString;
    }
    
    /**
     * get ddl versions content
     * @return
     */
    public static String[] getDdlForVersions() {
    	
    	String[] versionsString = {"14063"};
    	
    	if(ddlFillInVersion != null) {
    		versionsString = (String[])ddlFillInVersion.values().toArray(new String[ddlFillInVersion.size()]);
    	}
    	Arrays.sort(versionsString, Collections.reverseOrder());
    	
    	return versionsString;
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
    
	public static void updateDdl(JComboBox ddl) {
		ddl.showPopup();
		ddl.hidePopup();
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
