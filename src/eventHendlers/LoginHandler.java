/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eventHendlers;

import enviroment.ErrorMsgs;
import gui.LoginPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import ch.ethz.ssh2.Connection;

public class LoginHandler implements ActionListener,KeyListener{


    public LoginPanel loginPanel;
    
    public LoginHandler(LoginPanel apilp) {
    	loginPanel = apilp;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	
    	doAction();
    }
    
	@Override
	public void keyPressed(KeyEvent kv) {
		if(kv.getKeyChar() == KeyEvent.VK_ENTER) {
			doAction();
		}
	}
    
    public void doAction() {

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
        	ErrorMsgs.handleException("Login : ", JOptionPane.ERROR_MESSAGE, "Connection refused", ErrorMsgs.FAILD_TO_CREATE_CONNECTION_WITH_MACHINE, ErrorMsgs.FAILD_TO_CREATE_CONNECTION_WITH_MACHINE);
	    	//JOptionPane.showMessageDialog(null,ErrorMsgs.FAILD_TO_CREATE_CONNECTION_WITH_MACHINE,"Connection refused",JOptionPane.ERROR_MESSAGE);
        }
        catch (Exception ex) {
        	ErrorMsgs.handleException("Login : ", JOptionPane.ERROR_MESSAGE, "Login Faild", ErrorMsgs.USERNAME_OR_PASSWORD_INCORRECT, ErrorMsgs.USERNAME_OR_PASSWORD_INCORRECT);
        	//JOptionPane.showMessageDialog(null,ErrorMsgs.USERNAME_OR_PASSWORD_INCORRECT,"Login Faild",JOptionPane.ERROR_MESSAGE);
        }

        return isAuthenticated;

	}



	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
    
}
