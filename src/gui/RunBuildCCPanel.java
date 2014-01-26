/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import enviroment.Constants;
import eventHendlers.BuildCCHandler;
import eventHendlers.localBuildHandler;

import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JComboBox;

import java.awt.FlowLayout;
import java.awt.BorderLayout;

import javax.swing.BoxLayout;

import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import net.miginfocom.swing.MigLayout;

import java.awt.Insets;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;
import java.awt.Rectangle;

/**
 *
 * @author izhaq
 */
public class RunBuildCCPanel extends javax.swing.JPanel {

    /**
     * Creates new form RunBuildCCPanel
     */
    public RunBuildCCPanel() {
        initComponents();
        initHandlers();
    }

    private void initHandlers() 
    {
        checkLogBTN.addActionListener(new BuildCCHandler());
        runBuildCCBTN.addActionListener(new BuildCCHandler());
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
    	bbDDL.setBounds(61, 6, 125, 26);
    	bbDDL.setToolTipText("Select BB to run build");
    	bbDDL.setMaximumRowCount(20);

    	bbDDL.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
    	add(bbDDL);

    	lblVersion = new JLabel("Version:");
    	lblVersion.setBounds(6, 44, 57, 17);
    	lblVersion.setFont(new Font("Tahoma", Font.PLAIN, 13));
    	add(lblVersion);

    	versionsDDL = new JComboBox();
    	versionsDDL.setBounds(61, 44, 125, 26);
    	versionsDDL.setToolTipText("Select version");
    	add(versionsDDL);
    	runBuildCCBTN = new javax.swing.JButton();
    	runBuildCCBTN.setBounds(6, 91, 115, 28);

    	runBuildCCBTN.setText(Constants.RUN_BUILD_IN_CC);
    	add(runBuildCCBTN);
    	checkLogBTN = new javax.swing.JButton();
    	checkLogBTN.setBounds(6, 131, 114, 28);

    	checkLogBTN.setText(Constants.CHECK_LOG);
    	add(checkLogBTN);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox bbDDL;
    private javax.swing.JButton checkLogBTN;
    private javax.swing.JButton runBuildCCBTN;
    private JLabel lblBb;
    private JLabel lblVersion;
    private JComboBox versionsDDL;
}