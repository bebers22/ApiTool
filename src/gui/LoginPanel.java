package gui;

import java.awt.Color;

import javax.swing.JButton;

import enviroment.EnviromentHolder;
import eventHendlers.LoginHandler;


public class LoginPanel extends javax.swing.JPanel {
	
	public LoginDialogFrame parentContainer;

    /**
     * Creates new form ApiProfilePanel
     */
    public LoginPanel(LoginDialogFrame parent) {
    	parentContainer = parent;
        initComponents();
        initHandlers();
        initUserPassword();
    }

    private void initHandlers() {
    	btnConnect.addActionListener(new LoginHandler(this));
    	userNameTB.addActionListener(new LoginHandler(this));
    	passwordTB.addActionListener(new LoginHandler(this));
	}

    /**
     * Loads username and password from xml
     */
	private void initUserPassword() {
    	
    	EnviromentHolder.loadUserEnvDetails();
    	String[] usernamePassword = EnviromentHolder.getUsernamePassword();
    	if(usernamePassword != null) {
    		this.userNameTB.setText(usernamePassword[0]);
    		this.passwordTB.setText(usernamePassword[1]);
    	}
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
    	lblUsername = new javax.swing.JLabel();
    	lblUsername.setBounds(10, 11, 70, 33);

    	lblUsername.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
    	lblUsername.setText("User Name  :");
    	lblUsername.setOpaque(true);
    	add(lblUsername);
    	userNameTB = new javax.swing.JTextField();
    	userNameTB.setBounds(101, 10, 91, 33);
    	add(userNameTB);
    	lblPass = new javax.swing.JLabel();
    	lblPass.setBounds(10, 56, 70, 33);

    	lblPass.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
    	lblPass.setText("Password  :");
    	lblPass.setOpaque(true);
    	add(lblPass);
    	passwordTB = new javax.swing.JTextField();
    	passwordTB.setBounds(101, 55, 91, 33);
    	add(passwordTB);

    	lblHost = new javax.swing.JLabel();
    	lblHost.setBounds(10, 101, 40, 33);

    	lblHost.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
    	lblHost.setText("Host  :");
    	lblHost.setOpaque(true);
    	add(lblHost);
    	hostTB = new javax.swing.JTextField();
    	hostTB.setBounds(101, 100, 70, 33);
    	hostTB.setEditable(false);
    	hostTB.setForeground(Color.GRAY);
    	hostTB.setText("snv4914");
    	add(hostTB);

    	btnConnect = new JButton("Connect");
    	btnConnect.setBounds(50, 144, 91, 33);
    	add(btnConnect);

    }// </editor-fold>//GEN-END:initComponents


    private javax.swing.JTextField hostTB;
    private javax.swing.JLabel lblHost;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JLabel lblPass;
	public javax.swing.JTextField passwordTB;
    public javax.swing.JTextField userNameTB;
    private JButton btnConnect;
    
    
    public String getPassword() {
		return passwordTB.getText();
	}

	public String getUserName() {
		return userNameTB.getText();
	}



    
		
}