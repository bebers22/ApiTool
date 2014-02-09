package dataTypes;

import java.io.Serializable;

import enviroment.EnviromentHolder;

import java.util.HashMap;
import java.util.Set;

public class TaskSchedulerBoard implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 216843153967894311L;
    private HashMap<String, TaskScheduler> workers = new HashMap<String, TaskScheduler>();

    /**
     * create board of threads
     */
    public TaskSchedulerBoard() {
        initializeBoard();
    }

    /**
     * initialize the Board
     */
    private void initializeBoard() {

        HashMap<String, LogAreaModel> Logs = EnviromentHolder.getLogs();

        workers = new HashMap<String, TaskScheduler>(Logs.size());

        Set<String> keys = Logs.keySet();
        for (String key : keys) {
            workers.put(key, new TaskScheduler(Logs.get(key).getListenr()));
        }

    }

    public TaskScheduler getWorkerScheduler(String key) {
        return workers.get(key);
    }
    
    public void setWorkerScheduler(String key, Actions lisitner) {
        workers.put(key, new TaskScheduler(lisitner));
    }
    
    public void stopWorker(String key) {
    	workers.get(key).stopTask();
    	workers.remove(key);
    }

    public void killAnimal() {

        for (TaskScheduler worker : workers.values()) {
            worker.killAnimal();
        }

    }
}
