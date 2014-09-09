package gui;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import enviroment.Constants;
import enviroment.EnviromentHolder;
import eventHendlers.BbManagementHandler;

/**
 *
 * @author izhaq
 */
public class BbManagementPanel extends javax.swing.JPanel {

	/**
	 * Creates new form RunBuildCCPanel
	 */
	public BbManagementPanel() {
		initComponents();
		initHandlers();
	}

	private void initHandlers() 
	{
		ChangeProjCCBTN.addActionListener(new BbManagementHandler(this));
		CreateBBBtn.addActionListener(new BbManagementHandler(this));
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

		bbDDL = new javax.swing.JComboBox();
		bbDDL.setName(Constants.DDL_BB_NAME);
		bbDDL.setBounds(75, 6, 128, 26);
		bbDDL.setToolTipText("Select BB to run build");
		bbDDL.setMaximumRowCount(20);
		bbDDL.setModel(new javax.swing.DefaultComboBoxModel(EnviromentHolder.getDdlForBB()));
    	EnviromentHolder.ddlList.add(bbDDL);
		add(bbDDL);

		lblVersion = new JLabel("Version:");
		lblVersion.setBounds(6, 49, 57, 17);
		lblVersion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(lblVersion);

		versionsDDL = new JComboBox();
		versionsDDL.setName(Constants.DDL_VERSION_NAME);
		versionsDDL.setBounds(75, 44, 128, 26);	
		versionsDDL.setToolTipText("Select version");
		versionsDDL.setModel(new javax.swing.DefaultComboBoxModel(EnviromentHolder.getDdlForVersions()));
    	EnviromentHolder.ddlList.add(versionsDDL);
		add(versionsDDL);

		ChangeProjCCBTN = new javax.swing.JButton();
		ChangeProjCCBTN.setBounds(61, 82, 115, 28);

		ChangeProjCCBTN.setText(Constants.CHANGE_PROJ);
		add(ChangeProjCCBTN);
		CreateBBBtn = new javax.swing.JButton();
		CreateBBBtn.setBounds(62, 124, 114, 28);

		CreateBBBtn.setText(Constants.CREATE_NEW_BB);
		add(CreateBBBtn);
		
	}// </editor-fold>//GEN-END:initComponents



	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JComboBox bbDDL;
	private javax.swing.JButton CreateBBBtn;
	private javax.swing.JButton ChangeProjCCBTN;
	private JLabel lblBb;
	private JLabel lblVersion;
	private JComboBox versionsDDL;
	
	public javax.swing.JComboBox getBbDDL() {
		return bbDDL;
	}

	public JComboBox getVersionsDDL() {
		return versionsDDL;
	}
}



