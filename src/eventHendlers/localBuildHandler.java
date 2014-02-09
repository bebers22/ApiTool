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

import javax.swing.JButton;

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
        
        String UnixCommand = prepareCommand(((JButton)e.getSource()).getText());
        
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

    }
    
	private String prepareCommand(String command) {
		
		HashMap<String,String> placeHolderValues = new HashMap<>();
		
		placeHolderValues.put(Constants.PLACE_HOLDER_BB, String.valueOf(runLocalBuildPanel.getBbDDL().getSelectedItem()));
		String version = String.valueOf(runLocalBuildPanel.getVersionsDDL().getSelectedItem());
		placeHolderValues.put(Constants.PLACE_HOLDER_VERSION,version);
		placeHolderValues.put(Constants.PLACE_HOLDER_VERSION_UNDERSCOR,"v"+ version.substring(0, version.length() - 1) + "_" + version.charAt(version.length()-1));
		
		String preparedCommand = EnviromentHolder.getCommandsDataInfo().prepareCommand(placeHolderValues, command);
		
		if(Constants.RUN_LOCAL_BUILD.equals(command)) {
			///if TLG server is set
			preparedCommand = EnviromentHolder.getCommandsDataInfo().addWeblogicCommands(preparedCommand, runLocalBuildPanel.refreshTlgServerCB.isSelected(),
					runLocalBuildPanel.refreshLocalCB.isSelected(), runLocalBuildPanel.restartServerCB.isSelected());
		}
		if(Constants.RUN_QUICK_BUILD.equals(command)) {
			preparedCommand  = EnviromentHolder.getCommandsDataInfo().addWeblogicCommands(preparedCommand, runLocalBuildPanel.refreshTlgServerCB.isSelected(),
					false, runLocalBuildPanel.restartServerCB.isSelected());
		}
		///Call to prepareCommand (CommandsDataInfo)	
		
		return preparedCommand;
	}
}
