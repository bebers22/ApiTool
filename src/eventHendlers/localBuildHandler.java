/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eventHendlers;

import dataTypes.LogAreaModel;
import enviroment.Constants;
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
        
        logAreaModel = enviroment.EnviromentHolder.getLogs().get(Constants.LOCAL_BUILD_LOGS);
        switch(actionCommand){
                case Constants.START:
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
