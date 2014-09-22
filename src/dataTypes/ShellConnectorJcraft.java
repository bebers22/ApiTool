package dataTypes;

import java.io.*;
import java.util.LinkedList;

import ch.ethz.ssh2.Connection;

import com.jcraft.jsch.*;

import dataInfo.MyUserInfo;
import enviroment.EnviromentHolder;


public class ShellConnectorJcraft {

	 static final String knownHostPath = "~/.ssh/known_hosts";
	    static final String idDSAPath = "~/.ssh/id_dsa";
	    static final String idRSAPath = "~/.ssh/id_rsa";
	    private String host = "snv4914";
	    private String username;
	    private String password;
	    private OutputStreamWriter writer = null;
	    public InputStream shellStream;
	    public InputStream commandInputStream;
	    private static final int SSH_PORT = 22;
	    private MyUserInfo userinfo = new MyUserInfo();
	    private static final int TIMEOUT = 30000;
	    private PipedOutputStream pin;
	    String commands;
	    
    private Session userSession = null;
    private ChannelShell channel = null;
    private JSch jsch = new JSch();
    
    
    public InputStream getStream() throws IOException{
        shellStream = channel.getInputStream();;
        return shellStream;
    }
    
//    public boolean setConnectionc(LinkedList<String> lstCommand) {
//        try {
//             conn = new Connection(host);
//             conn.connect();
//             username = EnviromentHolder.getUsernamePassword()[0]; 
//             password = EnviromentHolder.getUsernamePassword()[1];
//             boolean isAuthenticated = conn.authenticateWithPassword(username, password);
//             if (!isAuthenticated) return isAuthenticated;
//             sess = conn.openSession();
//
//             sess.requestPTY("bash");
//             sess.startShell();
//             writer = writer = new OutputStreamWriter(sess.getStdin(), "utf-8");
//             for (String cmd : lstCommand) {
//             	writeCommand(cmd);
// 			}
//
//             System.out.println("Exit status : " + sess.getExitStatus());
//         } catch (Exception e) {
//             e.printStackTrace(System.err);
//             System.exit(2);
//         }
//        
//        return true;
//     }
    
    public ShellConnectorJcraft() {
    }
    
    public boolean setConnection(LinkedList<String> lstCommand) throws Exception {
    	username = EnviromentHolder.getUsernamePassword()[0]; 
        password = EnviromentHolder.getUsernamePassword()[1];
        setSession(username, password, host, SSH_PORT);
        openSession();
        setInputSreamCommands(lstCommand);
        openChannel();
        
        //InputStream in=channel.getInputStream();
        //PrintStream out = cordiantor.getLog().getOutput();
        //MessageConsoleStream msgConsole = cordiantor.getLog().getConsole("API console");
        
        //MessageConsole console = new MessageConsole("My Console", null);
        //console.activate();
        //ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole[]{ console });
        //MessageConsoleStream stream = console.newMessageStream();
        //stream.println("Hello, world!");
        
//        byte[] tmp=new byte[1024];
//        while(true){
//          while(in.available()>0){
//            int i=in.read(tmp, 0, 1024);
//            if(i<0)break;
//                String msg = new String(tmp, 0, i);
//                System.out.print(msg);
//                //out.print(msg);
//                //stream.println(msg);
//                //msgConsole.println(msg);
//                if(msg.toLowerCase().contains("END".toLowerCase())) {
//                    channel.disconnect();
//                    userSession.disconnect(); 
//                }
//          }
//          
//          if(channel.isClosed()){
//            System.out.println("exit-status: "+channel.getExitStatus());
//            break;
//          }
//          try{Thread.sleep(300);}catch(Exception ee){}
//        }
//        channel.disconnect();
//        userSession.disconnect();
        
        return true;
    }
    
    public void setSession(String userName, String password, String host, int port) throws JSchException{
        userSession = jsch.getSession(userName, host, port);
        userSession.setPassword(password);
    }
    
    public void openSession() throws JSchException {
        userSession.setUserInfo(userinfo);
        userSession.setConfig("PreferredAuthentications", "publickey,keyboard-interactive,password");
        userSession.connect(TIMEOUT);
        //userSession.setConfig("StrictHostKeyChecking", "no");
        jsch.setKnownHosts("knowonHosts.txt");
        
    }
    
    public void openChannel() throws JSchException, FileNotFoundException {
        channel= (ChannelShell)userSession.openChannel("shell");
        channel.setInputStream(commandInputStream);
        channel.setOutputStream(getOutput());
        channel.connect(TIMEOUT);
        try {
        	String[] cmds  = commands.split(";");
        	for(String st : cmds){
        		st = st + "\n";
        		pin.write(st.getBytes("UTF-8"));
        	}
//			pin.write(commands.getBytes("UTF-8"));
//			pin.write("echo this is test \n".getBytes("UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void setInputSreamCommands (LinkedList<String> lstCommand) {
    	commands = "";
    	for (String cmd : lstCommand) {
    		commands = commands + cmd;
    		
			}
    	try {
    		//commands = "find ~/bb/TlgServer/v1406_3/amdocs/TlgServer/backend/datalayers_v36 -type f -exec rm -f \'{}\' \\; ;find ~/bb/TlgServer/v1406_3/amdocs/TlgServer/backend/dl_interfaces -type f -exec rm -f \'{}\' \\; ;find ~/bb/TlgServer/v1406_3/amdocs/TlgServer/backend/datatypes -type f -exec rm -i \'{}\' \\;";
    		commandInputStream = new PipedInputStream();
    		pin = new PipedOutputStream((PipedInputStream) commandInputStream);

    		//commandInputStream = new ByteArrayInputStream(commands.getBytes("UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public PrintStream getOutput() throws FileNotFoundException {
        OutputStream out = new FileOutputStream("c:\\test\\output.txt");
        PrintStream outputs = new PrintStream(out);
        
        return outputs;
    }
    
    public void close() {
        //if (writer==null) return;
        try {
            //writer.close();
            channel.disconnect();
            userSession.disconnect();
        } catch(Exception ex) {
        	ex.printStackTrace();
        }
        if (writer!=null) writer = null;
    }
}
