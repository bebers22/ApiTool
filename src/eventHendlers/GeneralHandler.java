package eventHendlers;

import dataTypes.LogAreaModel;
import enviroment.Constants;
import enviroment.EnviromentHolder;
import gui.GeneralPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;

public class GeneralHandler implements ActionListener{

    public LogAreaModel logAreaModel;
    public GeneralPanel generalPanel;
    
    public GeneralHandler(GeneralPanel gp) {
    	generalPanel = gp;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	        
        String unixCommand = prepareCommand(((JButton)e.getSource()).getText());
        
        if(unixCommand.isEmpty()) {
        	return;
        }
        
        logAreaModel = enviroment.EnviromentHolder.getLogs().get(Constants.GENERAL_LOGS);
        
        logAreaModel.setWorker(unixCommand);

    }
    
	private String prepareCommand(String command) {
			
		Map<String,String> placeHolderValues = EnviromentHolder.getCommandsDataInfo().preparePlaceHoldersMap(new HashMap<String, String>(),
				String.valueOf(generalPanel.getBbDDL().getSelectedItem()), String.valueOf(generalPanel.getversionsDDL().getSelectedItem()), 
				String.valueOf(generalPanel.getTlgDomainDDL().getSelectedItem()));
	
		String preparedCommand = "";
						
		preparedCommand = EnviromentHolder.getCommandsDataInfo().prepareCommand(placeHolderValues, command);
		
		preparedCommand += " \n";
		
		return preparedCommand;
	}
}
