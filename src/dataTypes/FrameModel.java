/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataTypes;

import enviroment.Constants;
import enviroment.EnviromentHolder;

import java.util.HashMap;

/**
 *
 * @author izhaq
 */
public class FrameModel {
    
    private HashMap<String, LogAreaModel> Logs = new HashMap<String, LogAreaModel>();
    
    public FrameModel() {
        initiate();
    }
    
    
    public void initiate() {
    	
        String[] logAreaNames = EnviromentHolder.logAreaNames.value.split("@");

        for(int i=0; i<logAreaNames.length; i++) {
        	LogAreaModel logAreaModel = new LogAreaModel(logAreaNames[i]);
            Logs.put(logAreaNames[i], logAreaModel);
        }
        
        EnviromentHolder.setLogs(Logs);
        
        for(int i=0; i<logAreaNames.length; i++) {
        	LogAreaModel logAreaModel = new LogAreaModel(logAreaNames[i]);
            Logs.get(logAreaNames[i]).setAction(EnviromentHolder.getActionType(logAreaNames[i]));;
        }
        
        EnviromentHolder.setLogs(Logs);
    }
}
