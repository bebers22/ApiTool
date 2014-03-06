/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eventHendlers;

import dataTypes.LogAreaModel;
import enviroment.Constants;
import enviroment.EnviromentHolder;
import gui.RunBuildCCPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;

public class BuildCCHandler implements ActionListener{
     public LogAreaModel logAreaModel;
     public RunBuildCCPanel runBuildCcPanel;
    
    public BuildCCHandler(RunBuildCCPanel rbcp) {
    	
    	runBuildCcPanel = rbcp;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	        
        String unixCommand = prepareCommand(((JButton)e.getSource()).getText());
        
        if(unixCommand.isEmpty()) {
        	return;
        }
        
        logAreaModel = enviroment.EnviromentHolder.getLogs().get(Constants.BUILD_CC_LOGS);
        
        logAreaModel.setWorker(unixCommand);

    }

	private String prepareCommand(String command) {
		
		Map<String,String> placeHolderValues = EnviromentHolder.getCommandsDataInfo().preparePlaceHoldersMap(new HashMap<String, String>(),
				String.valueOf(runBuildCcPanel.getBbDDL().getSelectedItem()), String.valueOf(runBuildCcPanel.getVersionsDDL().getSelectedItem()), "");
		
		String preparedCommand ="";
		
		preparedCommand = EnviromentHolder.getCommandsDataInfo().prepareCommand(placeHolderValues, command);
		
		return preparedCommand;
	}
}
