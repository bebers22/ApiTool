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

import javax.swing.JButton;

/**
 *
 * @author Yuval
 */
public class BuildCCHandler implements ActionListener{
     public LogAreaModel logAreaModel;
     public RunBuildCCPanel runBuildCcPanel;
    
    public BuildCCHandler(RunBuildCCPanel rbcp) {
    	
    	runBuildCcPanel = rbcp;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	        
        String UnixCommand = prepareCommand(((JButton)e.getSource()).getText());
        
        logAreaModel = enviroment.EnviromentHolder.getLogs().get(Constants.BUILD_CC_LOGS);
        
        logAreaModel.setWorker(UnixCommand);

    }

	private String prepareCommand(String command) {
		
		HashMap<String,String> placeHolderValues = new HashMap<>();
		
		placeHolderValues.put(Constants.PLACE_HOLDER_BB, String.valueOf(runBuildCcPanel.getBbDDL().getSelectedItem()));
		placeHolderValues.put(Constants.PLACE_HOLDER_VERSION, String.valueOf(runBuildCcPanel.getVersionsDDL().getSelectedItem()));
		
		String preparedCommand = EnviromentHolder.getCommandsDataInfo().prepareCommand(placeHolderValues, command);
		
		///Call to prepareCommand (CommandsDataInfo)	
		
		return preparedCommand;
	}
}
