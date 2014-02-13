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
		
		HashMap<String,String> placeHolderValues = new HashMap<>();
		
		placeHolderValues.put(Constants.PLACE_HOLDER_BB, String.valueOf(bbManagementPanel.getBbDDL().getSelectedItem()));
		String version = String.valueOf(bbManagementPanel.getVersionsDDL().getSelectedItem());
		placeHolderValues.put(Constants.PLACE_HOLDER_VERSION,version);
		placeHolderValues.put(Constants.PLACE_HOLDER_VERSION_UNDERSCOR,"v"+ version.substring(0, version.length() - 1) + "_" + version.charAt(version.length()-1));
		
		String preparedCommand = EnviromentHolder.getCommandsDataInfo().prepareCommand(placeHolderValues, command);

		///Call to prepareCommand (CommandsDataInfo)	
		
		return preparedCommand;
	}
}
