package gui;

import javax.swing.JFrame;


public class APIApplicationManager{


	private JFrame ToolFrame;
	private LoginDialogFrame loginDialog;
	private ToolFrame toolFrame;
	
	
	public APIApplicationManager() {
		
		toolFrame = new ToolFrame();
		loginDialog = new LoginDialogFrame(toolFrame,true);
		loginDialog.setTitle("Login to your UNIX account");

	}

	public void Go() {
		
		loginDialog.showDialog();

		if(loginDialog.getIsConnected()) {
			
			toolFrame.LoadToolFrame();;
			toolFrame.setVisible(true);
		}
		
		else {
			System.exit(0);
		}
	}

	public static void main(String args[]) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(ToolFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(ToolFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(ToolFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(ToolFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		
		new APIApplicationManager().Go();

	}
}
