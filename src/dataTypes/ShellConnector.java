/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dataTypes;

import ch.ethz.ssh2.*;
import ch.ethz.ssh2.KnownHosts;
import ch.ethz.ssh2.Session;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author IZHAQB
 */
public class ShellConnector {
    static final String knownHostPath = "~/.ssh/known_hosts";
    static final String idDSAPath = "~/.ssh/id_dsa";
    static final String idRSAPath = "~/.ssh/id_rsa";
    private String host = "snv4914";
    private String username = "yuvalsi";
    private String password = "Unix11!";
    private KnownHosts database = new KnownHosts();
    private OutputStreamWriter writer = null;
    private Connection conn = null;
    private Session sess = null;
    public InputStream shellStream;
    
    public InputStream getStream(){
        shellStream = sess.getStdout();
        return shellStream;
    }
    
    public void setConnection() {
       try {
            conn = new Connection(host);
            conn.connect();
            boolean isAuthenticated = conn.authenticateWithPassword(username, password);
            if (!isAuthenticated) throw new IOException("Authentication failed.");
            sess = conn.openSession();

            //new Thread(new SyncPipe(sess.getStderr(), System.err)).start();
            //new Thread(new SyncPipe(sess.getStdout(), System.out)).start();
            sess.requestPTY("bash");
            sess.startShell();
//            BufferedReader x = new BufferedReader(new InputStreamReader(sess.getStdout()));
//            String s = x.readLine();
//            sess.getStdout();
            writer = new OutputStreamWriter(sess.getStdin(), "utf-8");
            writeCommand("cd weblogic/tlg_domain \n");
            writeCommand("ant_build_bb -c \n");
            writeCommand("ant_build_bb \n");
            writeCommand("ll \n");
            writeCommand("exit \n");
            //writeCommand("ant_build_bb -c \n");
            //writeCommand("ant_build_bb  \n");
//            sess.waitForCondition(ChannelCondition.CLOSED | ChannelCondition.EOF |
//                    ChannelCondition.EXIT_STATUS, 10000);
            System.out.println("Exit status : " + sess.getExitStatus());
        } catch (Exception e) {
            e.printStackTrace(System.err);
            System.exit(2);
        }
    }
    
    public void writeCommand(String s) {
        try {
            writer.write(s);
            writer.flush();
        } catch(Exception ex) {}
    }
    public void close() {
        if (writer==null) return;
        try {
            writer.close();
            sess.close();
            conn.close();
        } catch(Exception ex) {}
        if (writer!=null) writer = null;
    }
//    public void run() {
//        try {
//            conn = new Connection(host);
//            conn.connect();
//            boolean isAuthenticated = conn.authenticateWithPassword(username, password);
//            if (!isAuthenticated) throw new IOException("Authentication failed.");
//            sess = conn.openSession();
//            new Thread(new SyncPipe(sess.getStderr(), System.err)).start();
//            new Thread(new SyncPipe(sess.getStdout(), System.out)).start();
//            sess.requestPTY("bash");
//            sess.startShell();
//            writer = new OutputStreamWriter(sess.getStdin(), "utf-8");
//            writeCommand("cd weblogic/tlg_domain \n");
//            writeCommand("refreshLocal.sh \n");
//            writeCommand("ll \n");
//            writeCommand("exit \n");
//            //writeCommand("ant_build_bb -c \n");
//            //writeCommand("ant_build_bb  \n");
//            sess.waitForCondition(ChannelCondition.CLOSED | ChannelCondition.EOF |
//                    ChannelCondition.EXIT_STATUS, 10000);
//            System.out.println("Exit status : " + sess.getExitStatus());
//        } catch (Exception e) {
//            e.printStackTrace(System.err);
//            System.exit(2);
//        }
//    }

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
