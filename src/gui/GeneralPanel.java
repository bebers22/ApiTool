/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

import enviroment.Constants;
import enviroment.EnviromentHolder;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JComboBox;

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
    }

	/**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    	GridBagLayout gridBagLayout = new GridBagLayout();
    	gridBagLayout.columnWidths = new int[]{74, 131, 0, 0};
    	gridBagLayout.rowHeights = new int[]{23, 23, 23, 0, 0, 0, 0};
    	gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
    	gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
    	setLayout(gridBagLayout);
    	
    	lblVersion = new JLabel("Version:");
    	GridBagConstraints gbc_lblVersion = new GridBagConstraints();
    	gbc_lblVersion.insets = new Insets(0, 0, 5, 5);
    	lblVersion.setFont(new Font("Tahoma", Font.PLAIN, 13));
    	gbc_lblVersion.gridx = 0;
    	gbc_lblVersion.gridy = 0;
    	add(lblVersion, gbc_lblVersion);
    	
    	ddlVersion = new JComboBox();
    	GridBagConstraints gbc_ddlVersion = new GridBagConstraints();
    	gbc_ddlVersion.fill = GridBagConstraints.HORIZONTAL;
    	gbc_ddlVersion.insets = new Insets(0, 0, 5, 5);
    	gbc_ddlVersion.gridx = 1;
    	gbc_ddlVersion.gridy = 0;
    	ddlVersion.setModel(new javax.swing.DefaultComboBoxModel(EnviromentHolder.getDdlForVersions()));
    	add(ddlVersion, gbc_ddlVersion);
    	
    	setTlgEnvBTN = new javax.swing.JButton();
    	setTlgEnvBTN.setText(Constants.CLEAR_BACKEND_FILES);
    	GridBagConstraints gbc_setTlgEnvBTN = new GridBagConstraints();
    	gbc_setTlgEnvBTN.anchor = GridBagConstraints.NORTH;
    	gbc_setTlgEnvBTN.insets = new Insets(0, 0, 5, 0);
    	gbc_setTlgEnvBTN.gridx = 2;
    	gbc_setTlgEnvBTN.gridy = 0;
    	add(setTlgEnvBTN, gbc_setTlgEnvBTN);
    	
    	startTlgServerBTN = new javax.swing.JButton();
    	startTlgServerBTN.setText("StartTlgServer");
    	GridBagConstraints gbc_startTlgServerBTN = new GridBagConstraints();
    	gbc_startTlgServerBTN.insets = new Insets(0, 0, 5, 5);
    	gbc_startTlgServerBTN.anchor = GridBagConstraints.NORTHWEST;
    	gbc_startTlgServerBTN.gridx = 1;
    	gbc_startTlgServerBTN.gridy = 2;
    	add(startTlgServerBTN, gbc_startTlgServerBTN);
    	stopTlgServerBTN = new javax.swing.JButton();

    	stopTlgServerBTN.setText("StopTlgServer");

    	stopTlgServerBTN.addActionListener(new java.awt.event.ActionListener() {
    		public void actionPerformed(java.awt.event.ActionEvent evt) {
    			stopTlgServerBTNActionPerformed(evt);
    		}
    	});
    	GridBagConstraints gbc_stopTlgServerBTN = new GridBagConstraints();
    	gbc_stopTlgServerBTN.insets = new Insets(0, 0, 5, 5);
    	gbc_stopTlgServerBTN.anchor = GridBagConstraints.NORTHWEST;
    	gbc_stopTlgServerBTN.gridx = 1;
    	gbc_stopTlgServerBTN.gridy = 3;
    	add(stopTlgServerBTN, gbc_stopTlgServerBTN);
    	
    	    	refreshTlgServerBTN = new javax.swing.JButton();
    	    	
    	    	    	refreshTlgServerBTN.setText("RefreshTlgServer");
    	    	    	GridBagConstraints gbc_refreshTlgServerBTN = new GridBagConstraints();
    	    	    	gbc_refreshTlgServerBTN.insets = new Insets(0, 0, 5, 5);
    	    	    	gbc_refreshTlgServerBTN.anchor = GridBagConstraints.NORTHWEST;
    	    	    	gbc_refreshTlgServerBTN.gridx = 1;
    	    	    	gbc_refreshTlgServerBTN.gridy = 4;
    	    	    	add(refreshTlgServerBTN, gbc_refreshTlgServerBTN);
    	    	    	
    	    	    	refreahLocalBTN = new javax.swing.JButton();
    	    	    	refreahLocalBTN.setText("RefreshLocal");
    	    	    	GridBagConstraints gbc_refreahLocalBTN = new GridBagConstraints();
    	    	    	gbc_refreahLocalBTN.insets = new Insets(0, 0, 0, 5);
    	    	    	gbc_refreahLocalBTN.anchor = GridBagConstraints.NORTHWEST;
    	    	    	gbc_refreahLocalBTN.gridx = 1;
    	    	    	gbc_refreahLocalBTN.gridy = 5;
    	    	    	add(refreahLocalBTN, gbc_refreahLocalBTN);

    }// </editor-fold>//GEN-END:initComponents

    private void stopTlgServerBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopTlgServerBTNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stopTlgServerBTNActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton refreahLocalBTN;
    private javax.swing.JButton refreshTlgServerBTN;
    private javax.swing.JButton setTlgEnvBTN;
    private javax.swing.JButton startTlgServerBTN;
    private javax.swing.JButton stopTlgServerBTN;
    private JLabel lblVersion;
    private JComboBox ddlVersion;
    // End of variables declaration//GEN-END:variables
}
