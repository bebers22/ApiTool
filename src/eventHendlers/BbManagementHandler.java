/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eventHendlers;

import dataTypes.LogAreaModel;
import enviroment.Constants;
import enviroment.EnviromentHolder;
import gui.BbManagementPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;

public class BbManagementHandler implements ActionListener{

    public LogAreaModel logAreaModel;
    public BbManagementPanel bbManagementPanel;
    
    public BbManagementHandler(BbManagementPanel bbmp) {
    	bbManagementPanel = bbmp;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	        
        String unixCommand = prepareCommand(((JButton)e.getSource()).getText());
        
        if(unixCommand.isEmpty()) {
        	return;
        }
        
        logAreaModel = enviroment.EnviromentHolder.getLogs().get(Constants.BB_MANAGMENT_LOG);
        
        logAreaModel.setWorker(unixCommand);

    }
    
	private String prepareCommand(String command) {
				
		Map<String,String> placeHolderValues = EnviromentHolder.getCommandsDataInfo().preparePlaceHoldersMap(new HashMap<String, String>(),String.valueOf(bbManagementPanel.getBbDDL().getSelectedItem()), String.valueOf(bbManagementPanel.getVersionsDDL().getSelectedItem()));
		
		String preparedCommand ="";
		
		preparedCommand = EnviromentHolder.getCommandsDataInfo().prepareCommand(placeHolderValues, command);
		
		return preparedCommand;
	}
}
