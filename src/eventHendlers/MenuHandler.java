package eventHendlers;

import enviroment.Constants;
import enviroment.EnviromentHolder;
import gui.ToolFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class MenuHandler implements ActionListener{

	public JDialog dialog;
    public JScrollPane scrollPane;
    public ToolFrame toolFrame;
    public JTextArea errorLogTE;
    
    
    public MenuHandler(ToolFrame tf) {
    	toolFrame = tf;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	
    	switch (e.getActionCommand()) {
    	
		case Constants.LOG_MENU_ITEM:
		{
			dialog = new JDialog(toolFrame);
			errorLogTE = new JTextArea(50,50);
			scrollPane = new JScrollPane(errorLogTE);
			scrollPane.setAutoscrolls(true);
			scrollPane.setWheelScrollingEnabled(true);
			errorLogTE.setText(EnviromentHolder.getErrorLogs());
			
			dialog.add(scrollPane);
			dialog.setSize(600,300);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setLocationRelativeTo(null);
			dialog.setResizable(false);
			dialog.setVisible(true);
			
			break;}
		
		case Constants.RELOAD_ENV_MENU_ITEM: 
		{
			toolFrame.LoadToolFrame();
			break;}
    	}

    }

}
