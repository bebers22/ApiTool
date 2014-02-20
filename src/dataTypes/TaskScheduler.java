package dataTypes;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;

import static java.lang.Thread.MIN_PRIORITY;

import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class responsible timing of actions
 * @author
 *
 */
/**
 * @author
 *
 */
public class TaskScheduler extends Thread implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3216432196473216841L;
	private LinkedList<Integer> tasks;
	private LinkedList<String> ActionToPreform;
	private boolean isRunning;
	private Actions action;
        private String status = "NEW";
        private final byte[] buffer_;
        private final OutputStream ostrm_;
        private  InputStream istrm_;
        private ShellConnector shellConnector;
        String output;
        private boolean isCommandRunning = false;

	/**
	 * Constructe the thread.
	 * @param action
	 */
	public TaskScheduler(Actions action) {
		this.action = action;
		isRunning = false;
                buffer_ = new byte[4096];
                ostrm_ = null;
                istrm_ = null;
                tasks = new LinkedList<Integer>();
                shellConnector = new ShellConnector();
                output = null;
	}

//	/**
//	 * Activated the thread.
//	 * @param MealsforDay - number of meals in day.
//	 * @param SurviveTime - number of days the animal can survive without food. 
//	 */
	public void setScheduler(String command){
	buildTasks(command);
	System.out.println(command);
	shellConnector.setConnection(ActionToPreform);
	istrm_ = shellConnector.getStream();
	this.start();
	
	}

//		isRunning = true;
//		State x =  this.getState();
//		if(this.getState() != Thread.State.NEW) {
//			synchronized (this){
//				if(ActionToPreform.size() > 0)
//					return;
//				int p = ActionToPreform.size();
//			}
//			buildTasks();
//			shellConnector.setConnection();
//			istrm_ = shellConnector.getStream();
//			synchronized (this){
//				int p = ActionToPreform.size();
//				status = "RUN";
//				this.notify();
//
//			}
//		}
//		else {
//			buildTasks();
//			shellConnector.setConnection();
//			istrm_ = shellConnector.getStream();
//			status = "RUN";
//			this.start();
//		}
//
//	}

	/**
	 * Build array of tasks to preform
	 */
	public void buildTasks(String command){
		tasks = new LinkedList<Integer>();
				
		ActionToPreform = new LinkedList<String>();
		ActionToPreform.add(command);
		tasks.add(1);
		isRunning = true;

	}

	/**
	 * Add task to array of tasks.
	 * @param taskTime
	 */
	public void addTask(int taskTime) {				
		synchronized (this) {

		}		
	}

	@Override
	/**
	 * the method that make the thread awake and sleep
	 */
	public void run() {
		while (isRunning) {

			if (!isRunning) {
				break;
			}
			
			sentToOutput();
		}
		ActionToPreform.removeAll(ActionToPreform);
		actionToPerform("End of action.");
	}
	

	public boolean sentToOutput(){
		boolean logout = false;

		String msg = "";
		try {
			if(istrm_.available() > 0) {
				int i = istrm_.read(buffer_, 0, 1024);
				if (i > 0) {
					msg = new String(buffer_, 0, i);
					actionToPerform(msg);
				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (msg.contains("logout")) stopTask();

		return logout; 
	}




	/**
	 * sending request for action to be done
	 * @param ToDo
	 */
	public void actionToPerform(String ToDo){

		if(!ActionToPreform.isEmpty()) {
			if(isRunning){
				this.action.updateLog(ToDo);

			} 
		}else{
			this.action.updateLog("bey bey");
			//killAnimal();
			//this.action.setCells(1, 12);
		}
	}
	
	 /**
	 * kill the thread 
	 */
	public synchronized void killAnimal(){
		isRunning = false;
		try{
			this.notifyAll();
		}catch (Exception e) {}
	}

	/**
	 * activate the thread 
	 */
	public void ReturnToLife(){
		isRunning = true;
		start();
	}

	public void stopTask(){
		synchronized (this) {
			isRunning = false;
			shellConnector.close();
			ActionToPreform.removeAll(ActionToPreform);
			tasks.removeAll(tasks);
		}
	}

	public void addTask(String task){
		synchronized (this) {
			ActionToPreform.addFirst(task);
			tasks.addFirst(1);
		}
	}

}
