/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eventHendlers;

import dataTypes.LogAreaModel;
import enviroment.Constants;
import enviroment.EnviromentHolder;
import gui.RunBuildCCPanel;
import gui.RunLocalBuildPanel;
import gui.ToolFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 *
 * @author izhaq
 */
public class localBuildHandler implements ActionListener{

    public LogAreaModel logAreaModel;
    public RunLocalBuildPanel runLocalBuildPanel;
    
    public localBuildHandler(RunLocalBuildPanel rlbp) {
    	runLocalBuildPanel = rlbp;
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
                case Constants.CHECK_LOCAL_BUILD_LOG:
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
    
	private String prepareCommand(String command) {
		
		HashMap<String,String> placeHolderValues = new HashMap<>();
		
		//placeHolderValues.put(Constants.PLACE_HOLDER_BB, String.valueOf(runBuildCcPanel.getBbDDL().getSelectedItem()));
		//placeHolderValues.put(Constants.PLACE_HOLDER_VERSION, String.valueOf(runBuildCcPanel.getVersionsDDL().getSelectedItem()));
		
		String preparedCommand = EnviromentHolder.getCommandsDataInfo().prepareCommand(placeHolderValues, command);
		
		///Call to prepareCommand (CommandsDataInfo)	
		
		return preparedCommand;
	}
}
