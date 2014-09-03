/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataTypes;

import java.util.Vector;

import javax.swing.JOptionPane;

import enviroment.Constants;
import enviroment.EnviromentHolder;
import enviroment.ErrorMsgs;

/**
 *
 * @author izhaq
 */
public abstract class Actions {
    
    private LogAreaModel logAreaModel;
    private Vector< LogAreaListiner> listners ;

    Actions(LogAreaModel logAreaModel) {
         this.logAreaModel = logAreaModel;
         listners = new Vector<LogAreaListiner>(0,1);
    }
    
    public abstract String checkLog(String str);
    
    public abstract int getEndMsgCounter();
    
    public abstract void resetActivity();
    
    public  Vector<LogAreaListiner> getListners() {
		return listners;
	}

	public  void setListners(Vector<LogAreaListiner> listners) {
		this.listners = listners;
	}
	
	public void addCellListner( LogAreaListiner listner) {
		listners.add(listner);
	}
	
	public void removeCellListner( LogAreaListiner listner) {
		listners.remove(listner);
	}
	
	
    public void updateLog(String str) {
    	
    	str = checkLog(str);
		for(LogAreaListiner listner : listners) {
			listner.updateLog(str); 
		}
		
		System.out.println();
		System.out.println("Tessssssssttttttttttttttttttttttttttttttttttttttttttttttt          " + getEndMsgCounter());
		System.out.println();

	}
        
    public void startActivity(String command) {
    	if(isActivityIsNotRunning() ){
    	    resetActivity();
    		EnviromentHolder.getWorkersScheduler().setWorkerScheduler(logAreaModel.getLogAreaId(), logAreaModel.getListenr());
    		EnviromentHolder.getWorkersScheduler().
    		getWorkerScheduler(logAreaModel.getLogAreaId()).setScheduler(command);
    	}
    	else {
    		ErrorMsgs.handleException(command,JOptionPane.WARNING_MESSAGE ,ErrorMsgs.INSTANCE_IS_ALREADY_RUNNING,ErrorMsgs.ACTIVITY_IS_RUNNING, ErrorMsgs.ACTIVITY_IS_RUNNING_DESCRIPTION  );
    	}

        }

	private boolean isActivityIsNotRunning() {
		return EnviromentHolder.getWorkersScheduler().
    		getWorkerScheduler(logAreaModel.getLogAreaId()) == null;
	}
    
    public void stopActivity(String command)
    {
    	if(!isActivityIsNotRunning()){
    	EnviromentHolder.getWorkersScheduler().stopWorker(logAreaModel.getLogAreaId());
    	}
    	else {
    		ErrorMsgs.handleException(command,JOptionPane.WARNING_MESSAGE ,ErrorMsgs.NO_INSTANCE_IS_RUNNING,ErrorMsgs.ACTIVITY_IS_RUNNING, ErrorMsgs.ACTIVITY_IS_NOT_RUNNING_DESCRIPTION  );
    	}
    }
    
}
