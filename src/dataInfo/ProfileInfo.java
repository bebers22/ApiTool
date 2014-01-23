/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataInfo;

/**
 *
 * @author izhaq
 */
public class ProfileInfo implements IProfile{
    
    private String userName = null;
    private String password = null;
    private String host = null;
    private String ccvr = null;
    private String bb = null;
    private String user = null;

    public ProfileInfo(String userName, String password, 
            String host, String ccvr, String bb, String user) {
        
        this.userName = userName;
        this.password = password;
        this.host = host;
        this.ccvr = ccvr;
        this.bb = bb;
        this.user = user;
    }
    
    public ProfileInfo(ProfileInfo otherProfileInfo) {
        
        this.userName = otherProfileInfo.userName;
        this.password = otherProfileInfo.password;
        this.host = otherProfileInfo.host;
        this.ccvr = otherProfileInfo.ccvr;
        this.bb = otherProfileInfo.bb;
        this.user = otherProfileInfo.user;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getCcvr() {
        return ccvr;
    }

    public void setCcvr(String ccvr) {
        this.ccvr = ccvr;
    }

    public String getBb() {
        return bb;
    }

    public void setBb(String bb) {
        this.bb = bb;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    
}
