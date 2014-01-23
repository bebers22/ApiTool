/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eventHendlers;

import dataTypes.LogAreaModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author IZHAQB
 */
public class BuildCCHandler implements ActionListener{
     public LogAreaModel logAreaModel;
    
    public BuildCCHandler() {
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        
        logAreaModel = enviroment.EnviromentHolder.getLogs().get("buildCCLog");
        switch(actionCommand){
                case "Run build in CC":
                    logAreaModel.setWorker(actionCommand);
                    break;
                case "End":
                    logAreaModel.stopWorker(actionCommand);
                    break;
                case "ClearLog":
                    break;
                case "Check Log":
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
