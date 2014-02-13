/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eventHendlers;

import enviroment.ErrorMsgs;
import gui.LoginPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JOptionPane;
import ch.ethz.ssh2.Connection;

public class LoginHandler implements ActionListener{


    public LoginPanel loginPanel;
    
    public LoginHandler(LoginPanel apilp) {
    	loginPanel = apilp;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

    	loginPanel.parentContainer.isConnected = false;

    	if(checkConnection()) {
    		loginPanel.parentContainer.isConnected = true;
    		loginPanel.parentContainer.setVisible(false);
    	}

    }

    /**
     * Perform login with provided username and password
     * @return
     */
	private boolean checkConnection() {
		
	    String host = "snv4914";
	    String username = loginPanel.userNameTB.getText();
	    String password = loginPanel.passwordTB.getText();
	    
	    Connection conn = null;
	    boolean isAuthenticated = false;
	    
        conn = new Connection(host);
        try{
            conn.connect();
            isAuthenticated = conn.authenticateWithPassword(username, password);
            if(!isAuthenticated) {
            	throw new Exception();
            }
        }
        catch (IOException iox) {

	    	JOptionPane.showMessageDialog(null,ErrorMsgs.FAILD_TO_CREATE_CONNECTION_WITH_MACHINE,"Connection refused",JOptionPane.ERROR_MESSAGE);
        }
        catch (Exception ex) {
        	
        	JOptionPane.showMessageDialog(null,ErrorMsgs.USERNAME_OR_PASSWORD_INCORRECT,"Login Faild",JOptionPane.ERROR_MESSAGE);
        }

        return isAuthenticated;

	}
    
}
