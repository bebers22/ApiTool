/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataTypes;

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
        String[] logAreaNames = EnviromentHolder.logAreaNames.toString().split("@");
        
        for(int i=0; i<logAreaNames.length; i++) {
            Logs.put(logAreaNames[i], new LogAreaModel(logAreaNames[i]));
        }
        
        EnviromentHolder.setLogs(Logs);
    }
}
