package gui;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class LoginDialogFrame extends JDialog {

	private LoginPanel loginPanel;
	public Boolean isConnected = false;

	
	public LoginDialogFrame(JFrame toolFrame, boolean b) {
		super(toolFrame, b);
		setLoginDialogFrame();
	}
	
	/**
	 * Create the dialog.
	 */
	public void setLoginDialogFrame() {
		
		loginPanel = new LoginPanel(this);
		setSize(220,220);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().add(loginPanel);
		
	}

	public boolean showDialog() {
		
		setVisible(true);
		return getIsConnected();
	}
	
	public Boolean getIsConnected() {
		return isConnected;
	}

	public void setIsConnected(Boolean isConnected) {
		this.isConnected = isConnected;
	}
}
