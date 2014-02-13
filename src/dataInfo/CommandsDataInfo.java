package dataInfo;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import enviroment.Constants;
import enviroment.EnviromentHolder;
import enviroment.ErrorMsgs;

public class CommandsDataInfo {

	private HashMap<String,String> commands = new HashMap<>(0);  ///name of command - the unix command (with place holders)

	public CommandsDataInfo() {
		loadPlaceHolders();
		loadCommands();
	}
	
	/**
	 * Prepare the Command
	 * takes the unix command and change all the placeHolders in the command
	 * 
	 * @return a string representing the command
	 */
	public String prepareCommand(HashMap<String, String> placeHoldersValue , String commandToPerform) {
		
		Set<Entry<String, String>> placeHoldersEntrySet = placeHoldersValue.entrySet();
		String preparedCommand = "";
		
		if(commands.containsKey(commandToPerform)) {
			preparedCommand = new String(commands.get(commandToPerform));

			for (Entry<String, String> entry : placeHoldersEntrySet) {
				String placeHolder = entry.getKey();
				String value = entry.getValue();
					
				preparedCommand = preparedCommand.replace("{"+placeHolder+"}", value);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, ErrorMsgs.COMMAND_IS_MISSING, ErrorMsgs.TITLE_FAILD_TO_LOAD_FILE, JOptionPane.WARNING_MESSAGE);
			EnviromentHolder.writeToErrorLog(commandToPerform + ErrorMsgs.COMMAND_IS_MISSING_DESCRIPTION);
		}
		
		return preparedCommand;
	}
	
	/**
	 * Add commands to handle the weblogic
	 * @param commandToRun
	 * @param refreshTlgServer
	 * @param refreshLocal
	 * @param restartTlgServer
	 * @return
	 */
	public String addWeblogicCommands(String commandToRun, boolean refreshTlgServer, boolean refreshLocal, boolean restartTlgServer) {
		
		if(restartTlgServer) {
		
			commandToRun = refreshTlgServer ? commandToRun + commands.get(Constants.REFRESH_TLG_SERVER) : commandToRun;
			commandToRun = refreshLocal ? commandToRun + commands.get(Constants.REFRESH_LOCAL) : commandToRun;
			commandToRun = commandToRun + commands.get(Constants.RESTART_TLG_SERVER);
				
		}
		
		return commandToRun + "/n";				
	}

	
	/**
	 * load place holders from commands file
	 */
	private void loadPlaceHolders() {

		try {

			File commandsFile = new File(Constants.COMMANDS_XML);
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(commandsFile);
			NodeList  nList = doc.getElementsByTagName(Constants.XML_TAG_PLACEHOLDERS);

			if(nList.getLength() == 0) {
				throw new Exception();
			}

			for(int i=0;i<nList.getLength();i++) {
				Element el = (Element) nList.item(i);
				String attr = el.getAttribute(Constants.XML_TAG_ID);
				switch (attr) {

				case Constants.XML_TAG_VERSION : {
					Constants.PLACE_HOLDER_VERSION = el.getAttribute(Constants.XML_TAG_NAME);
					break;
				}
				case Constants.XML_TAG_BB : {

					Constants.PLACE_HOLDER_BB = el.getAttribute(Constants.XML_TAG_NAME);
					break;
				}
				case Constants.XML_TAG_VERSION_UNDERSCORE : {

					Constants.PLACE_HOLDER_VERSION_UNDERSCOR = el.getAttribute(Constants.XML_TAG_NAME);
					break;
				}

				case Constants.XML_TAG_TLG_DOMAIN: {

					Constants.PLACE_HOLDER_TLG_DOMAIN = el.getAttribute(Constants.XML_TAG_NAME);
					break;
				}

				}
			}
		} catch (SAXException | ParserConfigurationException sx) {
			JOptionPane.showMessageDialog(null, ErrorMsgs.COMMANDS_FILE_PARSING_ERROR_PH, ErrorMsgs.TITLE_FAILD_TO_LOAD_FILE, JOptionPane.WARNING_MESSAGE);
			EnviromentHolder.writeToErrorLog(sx.toString());
		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(null, ErrorMsgs.FAILD_TO_OPEN_FILE+": Commands file", ErrorMsgs.TITLE_FAILD_TO_LOAD_FILE, JOptionPane.WARNING_MESSAGE);
			EnviromentHolder.writeToErrorLog(ioe.toString());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, ErrorMsgs.FILE_IS_EMPTY, ErrorMsgs.TITLE_FAILD_TO_LOAD_FILE, JOptionPane.WARNING_MESSAGE);
			EnviromentHolder.writeToErrorLog(e.toString());
		}


	}

	/**
	 * load commands from file
	 */
	private void loadCommands() {

	try {
			File commandsFile = new File(Constants.COMMANDS_XML);
			Document doc;

			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(commandsFile);

			
			NodeList  nList = doc.getElementsByTagName(Constants.XML_TAG_COMMAND);
			commands = new HashMap<>();
			
			if(nList.getLength() == 0) {
				throw new Exception();
			}
			
			for(int i=0;i<nList.getLength();i++) {
				Element el = (Element) nList.item(i);
				String command_name = el.getAttribute(Constants.XML_TAG_NAME);
				String command = el.getAttribute(Constants.XML_TAG_COMMAND);
				commands.put(command_name, command);
			}
			
			} catch (SAXException | ParserConfigurationException sx) {
				JOptionPane.showMessageDialog(null, ErrorMsgs.COMMANDS_FILE_PARSING_ERROR_CO, ErrorMsgs.TITLE_FAILD_TO_LOAD_FILE, JOptionPane.WARNING_MESSAGE);
				EnviromentHolder.writeToErrorLog(sx.toString());
			} catch (IOException ioe) {
				JOptionPane.showMessageDialog(null, ErrorMsgs.FAILD_TO_OPEN_FILE+": Commands file", ErrorMsgs.TITLE_FAILD_TO_LOAD_FILE, JOptionPane.WARNING_MESSAGE);
				EnviromentHolder.writeToErrorLog(ioe.toString());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, ErrorMsgs.FILE_IS_EMPTY, ErrorMsgs.TITLE_FAILD_TO_LOAD_FILE, JOptionPane.WARNING_MESSAGE);
				EnviromentHolder.writeToErrorLog(e.toString());
			}

	}

}


