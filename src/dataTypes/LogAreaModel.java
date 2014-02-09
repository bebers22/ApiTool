/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataTypes;

import enviroment.EnviromentHolder;

/**
 *
 * @author izhaq
 */
public class LogAreaModel {
    private Actions actionListenr ;
    private String LogAreaId;
    
    public LogAreaModel(String LogAreaId) {
        this.LogAreaId = LogAreaId;
        actionListenr = new Actions(this);
    }
    
    public Actions getListenr() {
		return actionListenr;
	}
    
    public void setWorker(String command) {
//		EnviromentHolder.getWorkersScheduler().
//		getWorkerScheduler(LogAreaId).setScheduler();
    	actionListenr.startActivity(command);
	}

    public void stopWorker(String command) {
      EnviromentHolder.getWorkersScheduler().
		getWorkerScheduler(LogAreaId).stopTask();
    }
    
    public void addTaskToWorker(String command) {
      EnviromentHolder.getWorkersScheduler().
		getWorkerScheduler(LogAreaId).addTask(command);
    }
}
