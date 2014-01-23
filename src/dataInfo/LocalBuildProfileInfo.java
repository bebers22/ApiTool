/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataInfo;

/**
 *
 * @author izhaq
 */
public class LocalBuildProfileInfo extends ProfileInfo{
    
    private String command = null;
    private boolean isRefreshLocal = true;
    private boolean isRestartServer = true;
    private boolean isRefreshTlgServer = false;
    
    public LocalBuildProfileInfo(ProfileInfo otherProfileInfo, String command,
            boolean isRefreshLocal, boolean isRestartServer, 
            boolean isRefreshTlgServer) {
  
        super(otherProfileInfo);
        this.command = command;
        this.isRefreshLocal = isRefreshLocal;
        this.isRestartServer = isRestartServer;
        this.isRefreshTlgServer = isRefreshTlgServer;      
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public boolean isIsRefreshLocal() {
        return isRefreshLocal;
    }

    public void setIsRefreshLocal(boolean isRefreshLocal) {
        this.isRefreshLocal = isRefreshLocal;
    }

    public boolean isIsRestartServer() {
        return isRestartServer;
    }

    public void setIsRestartServer(boolean isRestartServer) {
        this.isRestartServer = isRestartServer;
    }

    public boolean isIsRefreshTlgServer() {
        return isRefreshTlgServer;
    }

    public void setIsRefreshTlgServer(boolean isRefreshTlgServer) {
        this.isRefreshTlgServer = isRefreshTlgServer;
    }
    
}
