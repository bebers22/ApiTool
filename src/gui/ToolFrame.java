/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dataTypes.FrameModel;
import dataTypes.LogAreaListiner;
import dataTypes.TaskSchedulerBoard;
import enviroment.Constants;
import enviroment.EnviromentHolder;
import java.awt.Component;
import java.awt.Container;
import java.util.HashMap;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Dimension;
/**
 *
 * @author izhaq
 */
public class ToolFrame extends javax.swing.JFrame {

    /**
     * Creates new form FramePanel
     */
    public ToolFrame() {
    	getContentPane().setFocusable(false);
    	getContentPane().setName("aacontentPane");
        preLoadEnvierment();
        initComponents();
        postLoadEnvierment();
    }


    private void preLoadEnvierment() {
        FrameModel frameModel = new FrameModel();
        EnviromentHolder.setFrameModel(frameModel);
        TaskSchedulerBoard gameSchedulers = new TaskSchedulerBoard();
        EnviromentHolder.setWorkersScheduler(gameSchedulers);
        
        //EnviermentHolder.setToolFrame(this);
    }
    
    private void postLoadEnvierment() {
        EnviromentHolder.setToolFrame(this);
        HashMap<String, LogAreaListiner> nn = loadOutputLogs(this);
        EnviromentHolder.setComponentMap(nn);
    }
    
public  HashMap<String, LogAreaListiner> loadOutputLogs(final Container c) {
    Component[] comps = c.getComponents();
    HashMap<String, LogAreaListiner> compList = new HashMap<String, LogAreaListiner>();
    for (Component comp : comps) {
        if (comp instanceof OutputPanel){
      compList.put(comp.getName(), (OutputPanel)comp);
        }
      if (comp instanceof Container) {
        compList.putAll(loadOutputLogs((Container) comp));
      }
    }
    return compList;
  }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        outputPanel1 = new gui.OutputPanel();
        commandsAreaPanel = new javax.swing.JPanel();
        commandsPanel = new javax.swing.JPanel();
        commandsTabbedPane = new javax.swing.JTabbedPane();
        localBuildTAB = new javax.swing.JPanel();
        localBuildLog = new gui.OutputPanel();
        runLocalBuildPanel = new gui.RunLocalBuildPanel();
        buildCCPanelTAB = new javax.swing.JPanel();
        runBuildCCPanel = new gui.RunBuildCCPanel();
        buildCCLog = new gui.OutputPanel();
        generalTAB = new javax.swing.JPanel();
        generalPanel = new gui.GeneralPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        apiProfilePanel = new gui.ApiProfilePanel();
        bbManagementPanel = new BbManagementPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        localBuildLog.setName(Constants.LOCAL_BUILD_LOGS); // NOI18N

        javax.swing.GroupLayout localBuildTABLayout = new javax.swing.GroupLayout(localBuildTAB);
        localBuildTABLayout.setHorizontalGroup(
        	localBuildTABLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(localBuildTABLayout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(runLocalBuildPanel, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(localBuildLog, GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE))
        );
        localBuildTABLayout.setVerticalGroup(
        	localBuildTABLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(localBuildTABLayout.createSequentialGroup()
        			.addGroup(localBuildTABLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(localBuildTABLayout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(localBuildLog, GroupLayout.PREFERRED_SIZE, 480, GroupLayout.PREFERRED_SIZE))
        				.addGroup(localBuildTABLayout.createSequentialGroup()
        					.addGap(37)
        					.addComponent(runLocalBuildPanel, GroupLayout.PREFERRED_SIZE, 339, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap(145, Short.MAX_VALUE))
        );
        localBuildTAB.setLayout(localBuildTABLayout);

        commandsTabbedPane.addTab("Local Build", localBuildTAB);

        buildCCLog.setName(Constants.BUILD_CC_LOGS); // NOI18N

        javax.swing.GroupLayout buildCCPanelTABLayout = new javax.swing.GroupLayout(buildCCPanelTAB);
        buildCCPanelTABLayout.setHorizontalGroup(
        	buildCCPanelTABLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(buildCCPanelTABLayout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(runBuildCCPanel, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(buildCCLog, GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE))
        );
        buildCCPanelTABLayout.setVerticalGroup(
        	buildCCPanelTABLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(buildCCPanelTABLayout.createSequentialGroup()
        			.addGroup(buildCCPanelTABLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(buildCCPanelTABLayout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(buildCCLog, GroupLayout.PREFERRED_SIZE, 482, GroupLayout.PREFERRED_SIZE))
        				.addGroup(buildCCPanelTABLayout.createSequentialGroup()
        					.addGap(34)
        					.addComponent(runBuildCCPanel, GroupLayout.PREFERRED_SIZE, 314, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap(143, Short.MAX_VALUE))
        );
        buildCCPanelTAB.setLayout(buildCCPanelTABLayout);

        commandsTabbedPane.addTab("Buld CC", buildCCPanelTAB);
        bbTAB = new javax.swing.JPanel();

        commandsTabbedPane.addTab("BB management", bbTAB);
        
        bbManagementLog = new OutputPanel();
        bbManagementLog.setName(Constants.BB_MANAGMENT_LOG);
        
        
        GroupLayout gl_bbTAB = new GroupLayout(bbTAB);
        gl_bbTAB.setHorizontalGroup(
        	gl_bbTAB.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_bbTAB.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(bbManagementPanel, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(bbManagementLog, GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE)
        			.addContainerGap())
        );
        gl_bbTAB.setVerticalGroup(
        	gl_bbTAB.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_bbTAB.createSequentialGroup()
        			.addGroup(gl_bbTAB.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_bbTAB.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(bbManagementLog, GroupLayout.PREFERRED_SIZE, 482, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_bbTAB.createSequentialGroup()
        					.addGap(33)
        					.addComponent(bbManagementPanel, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap(143, Short.MAX_VALUE))
        );
        bbTAB.setLayout(gl_bbTAB);
        generalOutputArea = new javax.swing.JTextArea();
        generalOutputArea.setMaximumSize(new Dimension(20, 20));
        
                generalOutputArea.setColumns(20);
                generalOutputArea.setRows(5);

        javax.swing.GroupLayout generalTABLayout = new javax.swing.GroupLayout(generalTAB);
        generalTABLayout.setHorizontalGroup(
        	generalTABLayout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(generalTABLayout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(generalPanel, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(generalOutputArea, GroupLayout.PREFERRED_SIZE, 762, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap())
        );
        generalTABLayout.setVerticalGroup(
        	generalTABLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(generalTABLayout.createSequentialGroup()
        			.addGroup(generalTABLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(generalTABLayout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(generalOutputArea, GroupLayout.PREFERRED_SIZE, 470, GroupLayout.PREFERRED_SIZE))
        				.addGroup(generalTABLayout.createSequentialGroup()
        					.addGap(39)
        					.addComponent(generalPanel, GroupLayout.PREFERRED_SIZE, 376, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap(155, Short.MAX_VALUE))
        );
        generalTAB.setLayout(generalTABLayout);

        commandsTabbedPane.addTab("General", generalTAB);
        jPanel16 = new javax.swing.JPanel();
        consolePanel = new javax.swing.JPanel();
        openConsoleBTN = new javax.swing.JButton();
        consoleTAB = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        consoleOutputArea = new javax.swing.JTextArea();
        
                openConsoleBTN.setText("Open Console");
                
                        javax.swing.GroupLayout consolePanelLayout = new javax.swing.GroupLayout(consolePanel);
                        consolePanel.setLayout(consolePanelLayout);
                        consolePanelLayout.setHorizontalGroup(
                            consolePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(consolePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(openConsoleBTN)
                                .addContainerGap(1009, Short.MAX_VALUE))
                        );
                        consolePanelLayout.setVerticalGroup(
                            consolePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(consolePanelLayout.createSequentialGroup()
                                .addComponent(openConsoleBTN)
                                .addGap(0, 12, Short.MAX_VALUE))
                        );
                        
                                consoleOutputArea.setColumns(20);
                                consoleOutputArea.setRows(5);
                                jScrollPane7.setViewportView(consoleOutputArea);
                                
                                        javax.swing.GroupLayout consoleTABLayout = new javax.swing.GroupLayout(consoleTAB);
                                        consoleTABLayout.setHorizontalGroup(
                                        	consoleTABLayout.createParallelGroup(Alignment.LEADING)
                                        		.addGroup(consoleTABLayout.createSequentialGroup()
                                        			.addContainerGap()
                                        			.addComponent(jScrollPane7, GroupLayout.PREFERRED_SIZE, 874, GroupLayout.PREFERRED_SIZE)
                                        			.addContainerGap(168, Short.MAX_VALUE))
                                        );
                                        consoleTABLayout.setVerticalGroup(
                                        	consoleTABLayout.createParallelGroup(Alignment.LEADING)
                                        		.addGroup(consoleTABLayout.createSequentialGroup()
                                        			.addComponent(jScrollPane7, GroupLayout.PREFERRED_SIZE, 534, GroupLayout.PREFERRED_SIZE)
                                        			.addContainerGap(446, Short.MAX_VALUE))
                                        );
                                        consoleTAB.setLayout(consoleTABLayout);
                                        
                                                javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
                                                jPanel16.setLayout(jPanel16Layout);
                                                jPanel16Layout.setHorizontalGroup(
                                                    jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                                                        .addContainerGap()
                                                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(consoleTAB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(consolePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addContainerGap())
                                                );
                                                jPanel16Layout.setVerticalGroup(
                                                    jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                                        .addContainerGap()
                                                        .addComponent(consolePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(consoleTAB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addContainerGap())
                                                );
                                                
                                                        commandsTabbedPane.addTab("Console", jPanel16);

        javax.swing.GroupLayout commandsPanelLayout = new javax.swing.GroupLayout(commandsPanel);
        commandsPanelLayout.setHorizontalGroup(
        	commandsPanelLayout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(Alignment.LEADING, commandsPanelLayout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(commandsTabbedPane, GroupLayout.PREFERRED_SIZE, 984, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(327, Short.MAX_VALUE))
        );
        commandsPanelLayout.setVerticalGroup(
        	commandsPanelLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(commandsPanelLayout.createSequentialGroup()
        			.addComponent(commandsTabbedPane, GroupLayout.PREFERRED_SIZE, 661, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(413, Short.MAX_VALUE))
        );
        commandsPanel.setLayout(commandsPanelLayout);

        commandsTabbedPane.getAccessibleContext().setAccessibleName("CC Build");

        javax.swing.GroupLayout commandsAreaPanelLayout = new javax.swing.GroupLayout(commandsAreaPanel);
        commandsAreaPanelLayout.setHorizontalGroup(
        	commandsAreaPanelLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(commandsAreaPanelLayout.createSequentialGroup()
        			.addComponent(commandsPanel, GroupLayout.PREFERRED_SIZE, 991, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        commandsAreaPanelLayout.setVerticalGroup(
        	commandsAreaPanelLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(commandsAreaPanelLayout.createSequentialGroup()
        			.addComponent(commandsPanel, GroupLayout.PREFERRED_SIZE, 691, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(389, Short.MAX_VALUE))
        );
        commandsAreaPanel.setLayout(commandsAreaPanelLayout);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Amdocs", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, null, new java.awt.Color(255, 0, 0)));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 51, 0));
        jLabel15.setText("API Team");
        jLabel15.setOpaque(true);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3Layout.setHorizontalGroup(
        	jPanel3Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
        			.addContainerGap(483, Short.MAX_VALUE)
        			.addComponent(jLabel15, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
        			.addGap(378))
        );
        jPanel3Layout.setVerticalGroup(
        	jPanel3Layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(jPanel3Layout.createSequentialGroup()
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        			.addComponent(jLabel15))
        );
        jPanel3.setLayout(jPanel3Layout);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(6)
        			.addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, 983, Short.MAX_VALUE)
        			.addContainerGap(328, Short.MAX_VALUE))
        		.addGroup(Alignment.LEADING, layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
        				.addGroup(Alignment.LEADING, layout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(apiProfilePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        				.addComponent(commandsAreaPanel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 989, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(328, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        			.addGap(3)
        			.addComponent(apiProfilePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(commandsAreaPanel, GroupLayout.PREFERRED_SIZE, 681, GroupLayout.PREFERRED_SIZE)
        			.addGap(399))
        );
        getContentPane().setLayout(layout);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
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
        //</editor-fold>
        
        EnviromentHolder.loadPreferences();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ToolFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.ApiProfilePanel apiProfilePanel;
    private gui.OutputPanel buildCCLog;
    private javax.swing.JPanel buildCCPanelTAB;
    private javax.swing.JPanel commandsAreaPanel;
    private javax.swing.JPanel commandsPanel;
    private javax.swing.JTabbedPane commandsTabbedPane;
    private javax.swing.JTextArea consoleOutputArea;
    private javax.swing.JPanel consolePanel;
    private javax.swing.JPanel consoleTAB;
    private javax.swing.JTextArea generalOutputArea;
    private gui.GeneralPanel generalPanel;
    private javax.swing.JPanel generalTAB;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JPanel bbTAB;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane7;
    private gui.OutputPanel localBuildLog;
    private javax.swing.JPanel localBuildTAB;
    private javax.swing.JButton openConsoleBTN;
    private gui.OutputPanel outputPanel1;
    private gui.RunBuildCCPanel runBuildCCPanel;
    private gui.RunLocalBuildPanel runLocalBuildPanel;
    private BbManagementPanel bbManagementPanel;
    private OutputPanel bbManagementLog;

    
    // End of variables declaration//GEN-END:variables
    
    public OutputPanel getLocalBuildLog() {
        return localBuildLog;
    }
    
    public RunLocalBuildPanel getRunLocalBuildPanel() {
        return runLocalBuildPanel;
    }
    
    public RunBuildCCPanel getRunBuildCCPanel() {
        return runBuildCCPanel;
    }
}
