/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataInfo;

/**
 *
 * @author izhaq
 */
public class BuildCCProfileInfo extends ProfileInfo{

    private String selectedBB = null;
    private String version = null;
            
    public BuildCCProfileInfo(String userName, String password, String host, 
            String ccvr, String bb, String user, String selectedBB,
            String version) {
        super(userName, password, host, ccvr, bb, user);
        this.selectedBB = selectedBB;
        this.version = version;
    }

    public BuildCCProfileInfo(ProfileInfo otherProfileInfo, String selectedBB
            , String version) {
        super(otherProfileInfo);
        this.selectedBB = selectedBB;
        this.version = version;
    }

    public String getSelectedBB() {
        return selectedBB;
    }

    public void setSelectedBB(String selectedBB) {
        this.selectedBB = selectedBB;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
    
}
