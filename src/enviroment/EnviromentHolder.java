/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enviroment;

import dataTypes.FrameModel;
import dataTypes.LogAreaListiner;
import dataTypes.LogAreaModel;
import dataTypes.TaskScheduler;
import dataTypes.TaskSchedulerBoard;
import gui.OutputPanel;
import gui.ToolFrame;

import java.awt.Component;
import java.awt.Container;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.omg.CORBA.StringHolder;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
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
    public static StringHolder logAreaNames = new StringHolder(Constants.LOCAL_BUILD_LOGS+"@"+Constants.BUILD_CC_LOGS); //localBuildLog@buildCCLog@generalLog@consolLog);
    public static HashMap<String, LogAreaModel> Logs;
    public static HashMap componentMap = new HashMap<String, LogAreaListiner>();
    
    //private HashMap<String, OutputPanel> consols = new HashMap<String, OutputPanel>();
    public static FrameModel frameModel;
    private static TaskSchedulerBoard workersScheduler;
    
    private static HashMap<String,String> ddlFillInBBs;
    private static HashMap<String,String> ddlFillInVersion;
    
    
    public static void loadPreferences() {
     	try {

    		File fXmlFile = new File(Constants.BB_AND_VERSIONS_XML);
    		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fXmlFile);
    		
    		ddlFillInBBs = parsePropertiestoMap("BB",doc);
    		ddlFillInVersion = parsePropertiestoMap("Version",doc);

    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

    
    
    private static HashMap<String, String> parsePropertiestoMap(String tagName, Document doc) {

		NodeList nList = doc.getElementsByTagName(tagName);
		
		int nListLenght = nList.getLength();
		
		HashMap<String, String> mapToFill = new HashMap<String,String>(nListLenght);
		
		for(int i=0 ; i<nListLenght ; i++) {
			Node node = nList.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				
				Element el = (Element)node;
				//System.out.println(el.getAttribute("id"));
				//System.out.println(el.getAttribute("name"));
				mapToFill.put(el.getAttribute("id"), el.getAttribute("name"));
				
			}
		}
		
		return mapToFill;
		
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
    

    public static void registerLisitners() {

        Set<Map.Entry<String, String>> entrySet = componentMap.entrySet();
        for (Entry entry : entrySet) {
            ((OutputPanel)entry.getValue()).setLogAreaModel(Logs.get((String)entry.getKey()));
        }
    }
}
