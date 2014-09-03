/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dataTypes;

import ch.ethz.ssh2.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.LinkedList;

import enviroment.Constants;
import enviroment.EnviromentHolder;

/**
 *
 * @author IZHAQB
 */
public class ShellConnector {
    static final String knownHostPath = "~/.ssh/known_hosts";
    static final String idDSAPath = "~/.ssh/id_dsa";
    static final String idRSAPath = "~/.ssh/id_rsa";
    private String host = "snv4914";
    private String username;
    private String password;
    private KnownHosts database = new KnownHosts();
    private OutputStreamWriter writer = null;
    private Connection conn = null;
    private Session sess = null;
    public InputStream shellStream;
    
    public InputStream getStream(){
        shellStream = sess.getStdout();
        return shellStream;
    }
    
    public boolean setConnection(LinkedList<String> lstCommand) {
       try {
            conn = new Connection(host);
            conn.connect();
            username = EnviromentHolder.getUsernamePassword()[Constants.USERNAME_INDEX]; 
            password = EnviromentHolder.getUsernamePassword()[Constants.PASSWORD_INDEX];
            boolean isAuthenticated = conn.authenticateWithPassword(username, password);
            if (!isAuthenticated) return isAuthenticated;
            sess = conn.openSession();

            sess.requestPTY("bash");
            sess.startShell();
            writer = writer = new OutputStreamWriter(sess.getStdin(), "utf-8");
            for (String cmd : lstCommand) {
            	writeCommand(cmd);
			}

            System.out.println("Exit status : " + sess.getExitStatus());
        } catch (Exception e) {
            e.printStackTrace(System.err);
            System.exit(2);
        }
       
       return true;
    }
    
    public void writeCommand(String s) {
        try {
            writer.write(s);
            writer.flush();
        } catch(Exception ex) {
        	ex.printStackTrace();
        }
    }
    public void close() {
        if (writer==null) return;
        try {
            writer.close();
            sess.close();
            conn.close();
        } catch(Exception ex) {
        	ex.printStackTrace();
        }
        if (writer!=null) writer = null;
    }


    class SyncPipe implements Runnable {
        String CVS_VERSION = "$Revision: 1.1 $ $Id: SyncPipe.java,v 1.1 2008-09-30 03:47:56 sabre Exp $ ";
        private final byte[] buffer_;
        private final OutputStream ostrm_;
        private final InputStream istrm_;
        private boolean closeAfterCopy_ = false;
        
        public SyncPipe(InputStream istrm, OutputStream ostrm) { this(istrm, ostrm, 4096);}
        
        public SyncPipe(InputStream istrm, OutputStream ostrm, int bufferSize) {
            if (istrm == null) throw new IllegalArgumentException("'istrm' cannot be null");
            if (ostrm == null) throw new IllegalArgumentException("'ostrm' cannot be null");
            if (bufferSize < 1024) throw new IllegalArgumentException("a buffer size less than 1024 makes little sense");
            istrm_ = istrm;
            ostrm_ = ostrm;
            buffer_ = new byte[bufferSize];
        }
        public void handleException(IOException e) { e.printStackTrace();}
        public SyncPipe setCloseAfterCopy(boolean closeAfterCopy) {
            closeAfterCopy_ = closeAfterCopy;
            return this;
        }
        public void run() {
            try {
                for (int bytesRead = 0; (bytesRead = istrm_.read(buffer_)) != -1;)
                    ostrm_.write(buffer_, 0, bytesRead);
            } catch (IOException ex) {
                Logger.getLogger(ShellConnector.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                ostrm_.flush();
            } catch (IOException ex) {
                Logger.getLogger(ShellConnector.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (closeAfterCopy_) try {
                ostrm_.close();
            } catch (IOException ex) {
                Logger.getLogger(ShellConnector.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
