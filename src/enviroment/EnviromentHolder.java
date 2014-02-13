/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enviroment;

import dataInfo.CommandsDataInfo;
import dataTypes.FrameModel;
import dataTypes.LogAreaListiner;
import dataTypes.LogAreaModel;
import dataTypes.TaskSchedulerBoard;
import gui.OutputPanel;
import gui.ToolFrame;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.omg.CORBA.StringHolder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author izhaq
 */
public class EnviromentHolder {
    
    public static ToolFrame toolFrame; 
    public static StringHolder logAreaNames = new StringHolder(Constants.LOCAL_BUILD_LOGS+"@"+Constants.BUILD_CC_LOGS+"@"+Constants.BB_MANAGMENT_LOG); //localBuildLog@buildCCLog@generalLog@consolLog);
    public static HashMap<String, LogAreaModel> Logs;
    public static HashMap componentMap = new HashMap<String, LogAreaListiner>();
    
    //private HashMap<String, OutputPanel> consols = new HashMap<String, OutputPanel>();
    public static FrameModel frameModel;
    private static TaskSchedulerBoard workersScheduler;
    
    private static String[] usernamePassword; ///[0] username , [1] password
    
	private static HashMap<String,String> ddlFillInBBs;
    private static HashMap<String,String> ddlFillInVersion;
    private static CommandsDataInfo commandsDataInfo;
    


	public static void loadPreferences() {
     	try {

     		//loadUserEnvDetails();
     		loadDdlDetails();
     		commandsDataInfo = new CommandsDataInfo();
     		
    	} catch (Exception e) {
    		e.printStackTrace();
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
				usernamePassword = new String[2];
				usernamePassword[0] = el.getAttribute(Constants.XML_TAG_USERNAME);
				usernamePassword[1] = el.getAttribute(Constants.XML_TAG_PASSWORD);
			}
		
    	} catch (Exception e) {
    		//TODO : error handling
			System.out.println("FAILD to load local User details file - using default values");
		}
	}

    /**
     * loadind the versions an BBs from xml file
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
	public static void loadDdlDetails() throws SAXException, IOException, ParserConfigurationException {
    	
		File fXmlFile = new File(Constants.BB_AND_VERSIONS_XML);
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fXmlFile);
		
		ddlFillInBBs = parsePropertiestoMap(Constants.XML_TAG_BB,doc);
		ddlFillInVersion = parsePropertiestoMap(Constants.XML_TAG_VERSION,doc);
    	
    }
    
    /**
     * Parse the xml of the eviroments and versions
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
    
    public static HashMap getComponentMap() {
        return componentMap;
    }

    public static void setComponentMap(HashMap componentMap) {
        EnviromentHolder.componentMap = componentMap;
        registerLisitners();
    }
    
    public static String[] getDdlForBB() {
    	
    	String[] bbsString = ddlFillInBBs.values().toArray(new String[ddlFillInBBs.size()]);
    		
    	return bbsString;
    }
    
    public static String[] getDdlForVersions() {
    	
    	String[] versionsString = (String[])ddlFillInVersion.values().toArray(new String[ddlFillInVersion.size()]);
    		
    	return versionsString;
    }
    
    public static String[] getUsernamePassword() {
		return usernamePassword;
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
}
