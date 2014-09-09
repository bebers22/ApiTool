package gui;

import enviroment.Constants;
import enviroment.EnviromentHolder;
import eventHendlers.GeneralHandler;

import java.awt.Font;

import javax.swing.AbstractListModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

/**
 *
 * @author izhaq
 */
public class GeneralPanel extends javax.swing.JPanel {


	/**
     * Creates new form GeneralPanel
     */
    public GeneralPanel() {
        initComponents();
        initHandlers();
    }

	private void initHandlers() {
		clearBackendBTN.addActionListener(new GeneralHandler(this));
		clearClassesBTN.addActionListener(new GeneralHandler(this));
		
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
    	bbDDL.setName(Constants.DDL_VERSION_NAME);
    	versionsDDL.setBounds(75, 44, 128, 26);
    	versionsDDL.setToolTipText("Select version");
    	versionsDDL.setModel(new javax.swing.DefaultComboBoxModel(EnviromentHolder.getDdlForVersions()));
    	EnviromentHolder.ddlList.add(versionsDDL);
    	add(versionsDDL);
    	
    	lblweblogic = new JLabel(Constants.TLG_DOMAIN);
    	lblweblogic.setBounds(6, 87, 73, 16);
    	lblweblogic.setFont(new Font("Tahoma", Font.PLAIN, 13));
    	add(lblweblogic);
    	
    	tlgDomainDDL = new JComboBox();
    	tlgDomainDDL.setBounds(75, 82, 128, 26);
    	tlgDomainDDL.setModel(new javax.swing.DefaultComboBoxModel(EnviromentHolder.getTlgDomains()));
    	add(tlgDomainDDL);

    	stopTlgServerBTN = new javax.swing.JButton();
    	stopTlgServerBTN.setBounds(30, 255, 105, 28);

    	stopTlgServerBTN.setText("StopTlgServer");

    	clearBackendBTN = new javax.swing.JButton();
    	clearBackendBTN.setBounds(16, 126, 167, 28);
    	clearBackendBTN.setText(Constants.CLEAR_BACKEND_FILES);
    	add(clearBackendBTN);
    	add(stopTlgServerBTN);
    	
    	clearClassesBTN = new JButton(Constants.CLEAR_CLASSES);
    	clearClassesBTN.setBounds(40, 159, 114, 28);
    	add(clearClassesBTN);

    	refreahLocalBTN = new javax.swing.JButton();
    	refreahLocalBTN.setBounds(43, 319, 102, 28);
    	refreahLocalBTN.setText("RefreshLocal");
    	add(refreahLocalBTN);

    	refreshTlgServerBTN = new javax.swing.JButton();
    	refreshTlgServerBTN.setBounds(30, 288, 124, 28);

    	refreshTlgServerBTN.setText("RefreshTlgServer");
    	add(refreshTlgServerBTN);

    	startTlgServerBTN = new javax.swing.JButton();
    	startTlgServerBTN.setBounds(30, 228, 105, 28);
    	startTlgServerBTN.setText("StartTlgServer");
    	add(startTlgServerBTN);
    	


    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton refreahLocalBTN;
    private javax.swing.JButton refreshTlgServerBTN;
    private javax.swing.JButton clearBackendBTN;
    private javax.swing.JButton startTlgServerBTN;
    private javax.swing.JButton stopTlgServerBTN;
    private JLabel lblVersion;
    private JButton clearClassesBTN;
    private JLabel lblBb;
	private JComboBox bbDDL;
	private JLabel lblweblogic;
	private JComboBox tlgDomainDDL;
	private JComboBox versionsDDL;
	
	public JComboBox getversionsDDL() {
		return versionsDDL;
	}

	public JComboBox getBbDDL() {
		return bbDDL;
	}
    public JComboBox getTlgDomainDDL() {
		return tlgDomainDDL;
	}



    // End of variables declaration//GEN-END:variables

	
}
