package eventHendlers;

import enviroment.Constants;
import enviroment.EnviromentHolder;
import enviroment.ErrorMsgs;
import enviroment.FileHandler;
import gui.LoginPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
    		updateLocalDetails(loginPanel.getUserName(), loginPanel.getPassword());
    		EnviromentHolder.DEBUG_MODE = loginPanel.getDebugMode();
    		loginPanel.parentContainer.setVisible(false);
    	}
    }

    /**
     * This method is updating the user name and password in the local XML file<BR>
     *  according to the last successfully connect
     * @param userName
     * @param password
     */
    private void updateLocalDetails(String userName, String password) {
    	
		if(userName.equals(EnviromentHolder.getUsernamePassword() != null ? EnviromentHolder.getUsernamePassword()[Constants.USERNAME_INDEX] : "") 
				&& password.equals(EnviromentHolder.getUsernamePassword() != null ? EnviromentHolder.getUsernamePassword()[Constants.PASSWORD_INDEX] : "")) {
			return;
		}
		
    	try{ 
    		File usernamePasswordfile = new File(FileHandler.USERNAME_PASSWORD_XML);
    		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(usernamePasswordfile);
    		NodeList  nList = doc.getElementsByTagName(FileHandler.XML_TAG_USER);
    		
    		if(nList.getLength() != 0 ) {
    			Element el = (Element) nList.item(0);
    			el.setAttribute(FileHandler.XML_TAG_USERNAME, userName);
    			el.setAttribute(FileHandler.XML_TAG_PASSWORD, password);
    		}
    		
    		Transformer transformer = TransformerFactory.newInstance().newTransformer();
    		Result output = new StreamResult(new File(FileHandler.USERNAME_PASSWORD_XML));
    		Source input = new DOMSource(doc);
    		transformer.transform(input, output);
    		
    	} catch (SAXException | ParserConfigurationException sx) {
    		ErrorMsgs.handleException("", JOptionPane.WARNING_MESSAGE, ErrorMsgs.TITLE_FAILD_TO_LOAD_FILE,  ErrorMsgs.USER_PROPERTIES_FILE_ERROR, sx);
    	} catch (IOException ioe) {
    		ErrorMsgs.handleException("", JOptionPane.WARNING_MESSAGE, ErrorMsgs.TITLE_FAILD_TO_LOAD_FILE, ErrorMsgs.FAILD_TO_OPEN_FILE +": user properties", ioe);
    	} catch (Exception e) {
    		ErrorMsgs.handleException("", JOptionPane.WARNING_MESSAGE, ErrorMsgs.TITLE_FAILD_TO_LOAD_FILE, ErrorMsgs.FILE_IS_EMPTY +": user properties", e);
    	}
    	
    	EnviromentHolder.setUsernamePassword(loginPanel.getUserName(), loginPanel.getPassword());
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
