/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataTypes;

import java.util.Vector;

import enviroment.Constants;
import enviroment.EnviromentHolder;

/**
 *
 * @author izhaq
 */
public class Actions {
    
    private LogAreaModel logAreaModel;
    private Vector< LogAreaListiner> listners ;

    Actions(LogAreaModel logAreaModel) {
         this.logAreaModel = logAreaModel;
         listners = new Vector<LogAreaListiner>(0,1);
    }
    
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

		for(LogAreaListiner listner : listners) {
			listner.updateLog(str); 
		}

	}
        
        public void startActivity(String command) {
    		EnviromentHolder.getWorkersScheduler().
    		getWorkerScheduler(logAreaModel.getLogAreaId()).setScheduler(command);
//            switch(command){
//                case Constants.START:
//                    logAreaModel.setWorker(command);
//                    break;
//                case Constants.END:
//                    logAreaModel.stopWorker(command);
//                    break;
//                case Constants.CHECK_CC_LOG:
//                    break;
//            }
        }
}
