package eventHendlers;

import enviroment.EnviromentHolder;
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
    		///TODO: add handler to handle changing the xml file content for the new username password that typed
    		EnviromentHolder.setUsernamePassword(loginPanel.getUserName(), loginPanel.getPassword());
    		loginPanel.parentContainer.setVisible(false);
    	}
    }

    /**
     * Perform login with provided username and password
     * @return
     */
    private boolean checkConnection() {

    	String host = "snv4914";
    	String username = loginPanel.getUserName();
    	String password = loginPanel.getPassword();

    	Connection conn = null;
    	boolean isAuthenticated = false;

    	conn = new Connection(host);
    	try{
    		conn.connect();
    		isAuthenticated = conn.authenticateWithPassword(username, password);
    		if(!isAuthenticated) {
    			ErrorMsgs.handleException("Login : ", JOptionPane.ERROR_MESSAGE, "Login Faild", ErrorMsgs.USERNAME_OR_PASSWORD_INCORRECT, ErrorMsgs.USERNAME_OR_PASSWORD_INCORRECT);
    		}
    	}
    	catch (IOException iox) {
    		ErrorMsgs.handleException("Login : ", JOptionPane.ERROR_MESSAGE, "Connection refused", ErrorMsgs.FAILD_TO_CREATE_CONNECTION_WITH_MACHINE, ErrorMsgs.FAILD_TO_CREATE_CONNECTION_WITH_MACHINE);
    	}
    	return isAuthenticated;
    }



	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
    
}
