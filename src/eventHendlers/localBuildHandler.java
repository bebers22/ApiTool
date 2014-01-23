/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eventHendlers;

import dataTypes.LogAreaModel;
import enviroment.EnviromentHolder;
import gui.ToolFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author izhaq
 */
public class localBuildHandler implements ActionListener{

    public LogAreaModel logAreaModel;
    
    public localBuildHandler() {
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        
        logAreaModel = enviroment.EnviromentHolder.getLogs().get("localBuildLog");
        switch(actionCommand){
                case "Start":
                    logAreaModel.setWorker(actionCommand);
                    break;
                case "End":
                    logAreaModel.stopWorker(actionCommand);
                    break;
                case "ClearLog":
                    break;
                case "CheckLog":
                    logAreaModel.addTaskToWorker(actionCommand);
                    break;
            }
//        ToolFrame x = EnviermentHolder.getToolFrame();
//        if(logAreaModel.getListenr().getListners().capacity()<1)
//        logAreaModel.getListenr().
//                addCellListner(EnviermentHolder.getToolFrame().getLocalBuildLog());
        //logAreaModel.setWorker(actionCommand); 
        //logAreaModel.getListenr().updateLog();
    }
    
}
