package gui;

import enviroment.Constants;
import enviroment.EnviromentHolder;
import eventHendlers.BuildCCHandler;

import javax.swing.JLabel;

import java.awt.Font;

import java.awt.Color;

/**
 *
 * @author izhaq
 */
public class RunBuildCCPanel extends JPanelTool {

    /**
     * Creates new form RunBuildCCPanel
     */
    public RunBuildCCPanel() {
        initComponents();
        initHandlers();
    }

    private void initHandlers() 
    {
        checkLogBTN.addActionListener(new BuildCCHandler(this));
        runBuildCCBTN.addActionListener(new BuildCCHandler(this));
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    	setLayout(null);

    	lblBb = new JLabel("BB:");
    	lblBb.setBounds(6, 6, 25, 26);
    	lblBb.setFont(new Font("Tahoma", Font.PLAIN, 13));
    	add(lblBb);

    	bbDDL = createDropDownList(Constants.DDL_BB_NAME, "Select BB to run build");
    	bbDDL.setBounds(75, 6, 128, 26);
    	add(bbDDL);

    	lblVersion = new JLabel("Version:");
    	lblVersion.setBounds(6, 49, 57, 17);
    	lblVersion.setFont(new Font("Tahoma", Font.PLAIN, 13));
    	add(lblVersion);

    	versionsDDL = createDropDownList(Constants.DDL_VERSION_NAME, "Select version");
    	versionsDDL.setBounds(75, 44, 128, 26);
    	add(versionsDDL);
    	
    	runBuildCCBTN = new javax.swing.JButton();
    	runBuildCCBTN.setBounds(61, 82, 115, 28);

    	runBuildCCBTN.setText(Constants.RUN_BUILD_IN_CC);
    	add(runBuildCCBTN);
    	checkLogBTN = new javax.swing.JButton();
    	checkLogBTN.setBounds(62, 146, 114, 28);

    	checkLogBTN.setText(Constants.CHECK_CC_LOG);
    	add(checkLogBTN);
    	
    	informationlbl = new JLabel("Build in CC will build all the BBs");
    	informationlbl.setForeground(Color.BLUE);
    	informationlbl.setBounds(6, 121, 180, 17);
    	add(informationlbl);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JComboBoxTool bbDDL;
	private javax.swing.JButton checkLogBTN;
    private javax.swing.JButton runBuildCCBTN;
    private JLabel lblBb;
    private JLabel lblVersion;
    private JComboBoxTool versionsDDL;
    private JLabel informationlbl;
    
    
    public JComboBoxTool getBbDDL() {
		return bbDDL;
	}

	public void setBbDDL(JComboBoxTool bbDDL) {
		this.bbDDL = bbDDL;
	}

	public JComboBoxTool getVersionsDDL() {
		return versionsDDL;
	}

	public void setVersionsDDL(JComboBoxTool versionsDDL) {
		this.versionsDDL = versionsDDL;
	}
}
