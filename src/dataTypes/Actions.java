/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataTypes;

import java.util.Vector;

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
            switch(command){
                case "Start":
                    logAreaModel.setWorker(command);
                    break;
                case "End":
                    logAreaModel.stopWorker(command);
                    break;
                case "ClearLog":
                    break;
                case "CheckLog":
                    break;
            }
        }
}
