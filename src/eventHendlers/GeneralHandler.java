package eventHendlers;

import dataTypes.LogAreaModel;
import enviroment.Constants;
import enviroment.EnviromentHolder;
import gui.GeneralPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

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
		
		HashMap<String,String> placeHolderValues = new HashMap<>();
		
		placeHolderValues.put(Constants.PLACE_HOLDER_BB, String.valueOf(generalPanel.getBbDDL().getSelectedItem()));
		String version = String.valueOf(generalPanel.getversionsDDL().getSelectedItem());
		placeHolderValues.put(Constants.PLACE_HOLDER_VERSION,version);
		placeHolderValues.put(Constants.PLACE_HOLDER_VERSION_UNDERSCOR,"v"+ version.substring(0, version.length() - 1) + "_" + version.charAt(version.length()-1));
		
		String preparedCommand = "";
						
		preparedCommand = EnviromentHolder.getCommandsDataInfo().prepareCommand(placeHolderValues, command);
		
		return preparedCommand;
	}
}
