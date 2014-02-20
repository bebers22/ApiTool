/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eventHendlers;

import dataTypes.LogAreaModel;
import enviroment.Constants;
import enviroment.EnviromentHolder;
import gui.RunLocalBuildPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;


public class localBuildHandler implements ActionListener{

    public LogAreaModel logAreaModel;
    public RunLocalBuildPanel runLocalBuildPanel;
    
    public localBuildHandler(RunLocalBuildPanel rlbp) {
    	runLocalBuildPanel = rlbp;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	        
        String unixCommand = prepareCommand(((JButton)e.getSource()).getText());
        
        if(unixCommand.isEmpty()) {
        	return;
        }
        
        logAreaModel = enviroment.EnviromentHolder.getLogs().get(Constants.LOCAL_BUILD_LOGS);
        
        logAreaModel.setWorker(unixCommand);

    }
    
	private String prepareCommand(String command) {
		
		Map<String,String> placeHolderValues = EnviromentHolder.getCommandsDataInfo().preparePlaceHoldersMap(new HashMap<String, String>(),String.valueOf(runLocalBuildPanel.getBbDDL().getSelectedItem()), String.valueOf(runLocalBuildPanel.getVersionsDDL().getSelectedItem()));

		String preparedCommand = "";
		
		preparedCommand = EnviromentHolder.getCommandsDataInfo().prepareCommand(placeHolderValues, command);
				
		if(Constants.RUN_LOCAL_BUILD.equals(command)) {
			///if TLG server is set
			preparedCommand = EnviromentHolder.getCommandsDataInfo().addWeblogicCommands(preparedCommand, runLocalBuildPanel.refreshTlgServerCB.isSelected(),
					runLocalBuildPanel.refreshLocalCB.isSelected(), runLocalBuildPanel.restartServerCB.isSelected());
		}
		if(Constants.RUN_QUICK_BUILD.equals(command)) {
			preparedCommand  = EnviromentHolder.getCommandsDataInfo().addWeblogicCommands(preparedCommand, runLocalBuildPanel.refreshTlgServerCB.isSelected(),
					false, runLocalBuildPanel.restartServerCB.isSelected());
		}
				
		return preparedCommand;
	}


}
