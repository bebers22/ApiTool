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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 *
 * @author izhaq
 */
public class EnviromentHolder {
    
    public static ToolFrame toolFrame; 
    public static String logAreaNames = "localBuildLog@buildCCLog@generalLog@consolLog";
    public static HashMap<String, LogAreaModel> Logs;
    public static HashMap componentMap = new HashMap<String, LogAreaListiner>();
    //private HashMap<String, OutputPanel> consols = new HashMap<String, OutputPanel>();
    public static FrameModel frameModel;
    private static TaskSchedulerBoard workersScheduler;
    
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
        return logAreaNames;
    }

    public static void setLogAreaNames(String logAreaNames) {
        EnviromentHolder.logAreaNames = logAreaNames;
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

    public static void registerLisitners() {

        Set<Map.Entry<String, String>> entrySet = componentMap.entrySet();
        for (Entry entry : entrySet) {
            ((OutputPanel)entry.getValue()).setLogAreaModel(Logs.get((String)entry.getKey()));
        }
    }
}
