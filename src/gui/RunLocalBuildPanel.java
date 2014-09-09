package gui;

import enviroment.Constants;
import enviroment.EnviromentHolder;
import eventHendlers.localBuildHandler;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;


/**
 *
 * @author izhaq
 */
public class RunLocalBuildPanel extends JPanelTool {



	/**
     * Creates new form RunLocalBuildPanel
     */
    public RunLocalBuildPanel() {
        initComponents();
        initHandlers();
    }
    
    private void initHandlers() 
    {
        localBuildBTN.addActionListener(new localBuildHandler(this));
        endBTN.addActionListener(new localBuildHandler(this));
        checkLogBTN.addActionListener(new localBuildHandler(this));
        quickBuildBTN.addActionListener(new localBuildHandler(this));
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
    	
        refreshLocalCB = new javax.swing.JCheckBox();
        refreshLocalCB.setBounds(6, 200, 96, 22);
        refreshTlgServerCB = new javax.swing.JCheckBox();
        refreshTlgServerCB.setBounds(6, 170, 121, 18);
        restartServerCB = new javax.swing.JCheckBox();
        restartServerCB.setBounds(6, 140, 117, 18);
        
        checkLogBTN = new javax.swing.JButton();
        checkLogBTN.setBounds(6, 297, 112, 28);
        localBuildBTN = new javax.swing.JButton();
        localBuildBTN.setBounds(6, 257, 110, 28);
        endBTN = new javax.swing.JButton();
        endBTN.setBounds(122, 257, 81, 28);
        
       
        refreshLocalCB.setText(Constants.REFRESH_LOCAL);
        refreshTlgServerCB.setText(Constants.REFRESH_TLG_SERVER);
        restartServerCB.setText(Constants.RESTART_TLG_SERVER);
        
        checkLogBTN.setText(Constants.CHECK_LOCAL_BUILD_LOG);
        localBuildBTN.setText(Constants.RUN_LOCAL_BUILD);
        endBTN.setText(Constants.END);
        
        add(endBTN);
        add(checkLogBTN);
        add(localBuildBTN);
        add(restartServerCB);
        add(refreshTlgServerCB);
        add(refreshLocalCB);
        
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
    	
    	quickBuildBTN = new JButton(Constants.RUN_QUICK_BUILD);
    	quickBuildBTN.setBounds(4, 337, 112, 28);
    	add(quickBuildBTN);
    	
    	lblweblogic = new JLabel(Constants.TLG_DOMAIN);
    	lblweblogic.setBounds(6, 87, 73, 16);
    	lblweblogic.setFont(new Font("Tahoma", Font.PLAIN, 13));
    	add(lblweblogic);
    	
    	tlgDomainDDL = createDropDownList(Constants.DDL_TLG_DOMAIN_NAME, "");
    	tlgDomainDDL.setBounds(75, 82, 128, 26);
    	add(tlgDomainDDL);


    }// </editor-fold>//GEN-END:initComponents


	// Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton checkLogBTN;
    private javax.swing.JButton endBTN;
    public javax.swing.JCheckBox refreshLocalCB;
    public javax.swing.JCheckBox refreshTlgServerCB;
    public javax.swing.JCheckBox restartServerCB;
    private javax.swing.JButton localBuildBTN;
    private JLabel lblVersion;
	private JComponent lblBb;
	private JComboBox bbDDL;
	private JComboBox versionsDDL;
	private JButton quickBuildBTN;
	private JLabel lblweblogic;
	private JComboBox tlgDomainDDL;
	
	
	public JComboBox getBbDDL() {
		return bbDDL;
	}

	public JComboBox getVersionsDDL() {
		return versionsDDL;
	}
	
    public JComboBox getTlgDomainDDL() {
		return tlgDomainDDL;
	}
}
