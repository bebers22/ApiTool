package gui;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ApiProfilePanel{


	private JFrame ToolFrame;
	private LoginDialogFrame loginDialog;
	private ToolFrame toolFrame;
	
	
	public ApiProfilePanel() {
		
		toolFrame = new ToolFrame();
		loginDialog = new LoginDialogFrame(toolFrame,true);


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
		
		new ApiProfilePanel().Go();

	}
}









///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package gui;
//
//import java.awt.GridBagLayout;
//import java.awt.GridBagConstraints;
//import java.awt.Insets;
//import javax.swing.JLabel;
//import java.awt.Color;
//import javax.swing.JButton;
//import enviroment.EnviromentHolder;
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;
//
///**
// *
// * @author izhaq
// */
//public class ApiProfilePanel extends javax.swing.JPanel {
//
//    /**
//     * Creates new form ApiProfilePanel
//     */
//    public ApiProfilePanel() {
//    	setForeground(Color.RED);
//        initComponents();
//        initUserPassword();
//    }
//
//    private void initUserPassword() {
//    	
//    	String[] usernamePassword = EnviromentHolder.getUsernamePassword();
//    	if(usernamePassword != null) {
//    		this.userNameTB.setText(usernamePassword[0]);
//    		this.passwordTB.setText(usernamePassword[1]);
//    	}
//	}
//
//	/**
//     * This method is called from within the constructor to initialize the form.
//     * WARNING: Do NOT modify this code. The content of this method is always
//     * regenerated by the Form Editor.
//     */
//    @SuppressWarnings("unchecked")
//    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
//    private void initComponents() {
//    	setLayout(null);
//    	lblUsername = new javax.swing.JLabel();
//    	lblUsername.setBounds(0, 0, 70, 33);
//
//    	lblUsername.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
//    	lblUsername.setText("User Name  :");
//    	lblUsername.setOpaque(true);
//    	add(lblUsername);
//    	userNameTB = new javax.swing.JTextField();
//    	userNameTB.setBounds(80, 0, 91, 33);
//    	add(userNameTB);
//    	lblPass = new javax.swing.JLabel();
//    	lblPass.setBounds(181, 0, 70, 33);
//
//    	lblPass.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
//    	lblPass.setText("Password  :");
//    	lblPass.setOpaque(true);
//    	add(lblPass);
//    	passwordTB = new javax.swing.JTextField();
//    	passwordTB.setBounds(251, 0, 84, 33);
//    	add(passwordTB);
//
//    	jLabel12 = new javax.swing.JLabel();
//    	jLabel12.setBounds(345, 0, 40, 33);
//
//    	jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
//    	jLabel12.setText("Host  :");
//    	jLabel12.setOpaque(true);
//    	add(jLabel12);
//    	hostTB = new javax.swing.JTextField();
//    	hostTB.setBounds(390, 0, 70, 33);
//    	hostTB.setEditable(false);
//    	hostTB.setForeground(Color.GRAY);
//    	hostTB.setText("snv4914");
//    	add(hostTB);
//
//    	btnConnect = new JButton("Connect");
//    	btnConnect.setBounds(469, 4, 91, 23);
//    	add(btnConnect);
//    	lblBB = new javax.swing.JLabel();
//    	lblBB.setBounds(643, 0, 23, 33);
//
//    	lblBB.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
//    	lblBB.setText("BB  :");
//    	lblBB.setOpaque(true);
//    	add(lblBB);
//
//    	lblLblbuildingblock = new JLabel("1");
//    	lblLblbuildingblock.setBounds(678, 3, 40, 24);
//    	lblLblbuildingblock.setForeground(Color.RED);
//    	add(lblLblbuildingblock);
//    	lblHost = new javax.swing.JLabel();
//    	lblHost.setBounds(784, 0, 45, 33);
//
//    	lblHost.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
//    	lblHost.setText("CCVR  :");
//    	lblHost.setOpaque(true);
//    	add(lblHost);
//
//    	lblLblccversion = new JLabel("1");
//    	lblLblccversion.setBounds(841, 3, 40, 24);
//    	lblLblccversion.setForeground(Color.RED);
//    	add(lblLblccversion);
//
//    }// </editor-fold>//GEN-END:initComponents
//
//
//    private javax.swing.JTextField hostTB;
//    private javax.swing.JLabel jLabel12;
//    private javax.swing.JLabel lblUsername;
//    private javax.swing.JLabel lblPass;
//    private javax.swing.JLabel lblHost;
//    private javax.swing.JLabel lblBB;
//    private javax.swing.JTextField passwordTB;
//    private javax.swing.JTextField userNameTB;
//    private JLabel lblLblbuildingblock;
//    private JLabel lblLblccversion;
//    private JButton btnConnect;
//		
//}
