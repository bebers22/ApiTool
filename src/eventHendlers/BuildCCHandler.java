/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eventHendlers;

import dataTypes.LogAreaModel;
import enviroment.Constants;

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
        
        logAreaModel = enviroment.EnviromentHolder.getLogs().get(Constants.BUILD_CC_LOGS);
        switch(actionCommand){
                case Constants.RUN_BUILD_IN_CC:
                    logAreaModel.setWorker(actionCommand);
                    break;
                case Constants.END:
                    logAreaModel.stopWorker(actionCommand);
                    break;
                case Constants.CHECK_LOG:
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
