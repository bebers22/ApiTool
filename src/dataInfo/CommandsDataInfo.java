package dataInfo;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
	private static String endBuildCommand = " end_activity;";

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
	public String prepareCommand(Map<String, String> placeHoldersValue , String commandToPerform) {
		
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
			ErrorMsgs.handleException(commandToPerform,JOptionPane.WARNING_MESSAGE ,ErrorMsgs.TITLE_FAILD_TO_LOAD_FILE,ErrorMsgs.COMMAND_IS_MISSING, ErrorMsgs.COMMAND_IS_MISSING_DESCRIPTION  );
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
	public String addWeblogicCommands(String commandToRun, boolean refreshTlgServer, boolean refreshLocal,
			boolean restartTlgServer, Map<String,String> placeHolderValues) {
		
		/*if(restartTlgServer) {*/
		
		
		commandToRun = refreshTlgServer ? commandToRun + EnviromentHolder.getCommandsDataInfo().prepareCommand(placeHolderValues, Constants.REFRESH_TLG_SERVER) : commandToRun;
		commandToRun = refreshLocal ? commandToRun + EnviromentHolder.getCommandsDataInfo().prepareCommand(placeHolderValues, Constants.REFRESH_LOCAL) : commandToRun;
		commandToRun = restartTlgServer ? commandToRun + EnviromentHolder.getCommandsDataInfo().prepareCommand(placeHolderValues, Constants.RESTART_TLG_SERVER) : commandToRun;
			

	/*}*/

	return commandToRun + endBuildCommand+" \n";			
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
			ErrorMsgs.handleException("", JOptionPane.WARNING_MESSAGE, ErrorMsgs.TITLE_FAILD_TO_LOAD_FILE, ErrorMsgs.COMMANDS_FILE_PARSING_ERROR_PH, sx.toString());
		} catch (IOException ioe) {
			ErrorMsgs.handleException("", JOptionPane.WARNING_MESSAGE,  ErrorMsgs.TITLE_FAILD_TO_LOAD_FILE, ErrorMsgs.FAILD_TO_OPEN_FILE+": Commands file", ioe.toString());
		} catch (Exception e) {
			ErrorMsgs.handleException("", JOptionPane.WARNING_MESSAGE, ErrorMsgs.TITLE_FAILD_TO_LOAD_FILE, ErrorMsgs.FILE_IS_EMPTY, e.toString());
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
				ErrorMsgs.handleException("", JOptionPane.WARNING_MESSAGE, ErrorMsgs.TITLE_FAILD_TO_LOAD_FILE, ErrorMsgs.COMMANDS_FILE_PARSING_ERROR_CO, sx.toString());
			} catch (IOException ioe) {
				ErrorMsgs.handleException("", JOptionPane.WARNING_MESSAGE, ErrorMsgs.TITLE_FAILD_TO_LOAD_FILE, ErrorMsgs.FAILD_TO_OPEN_FILE+": Commands file", ioe.toString());
			} catch (Exception e) {
				ErrorMsgs.handleException("", JOptionPane.WARNING_MESSAGE,  ErrorMsgs.TITLE_FAILD_TO_LOAD_FILE, ErrorMsgs.FILE_IS_EMPTY, e.toString());
			}

	}
	
	/**
	 * prepare the map of the placeHolders values
	 * @param placeHolderMap - an instance of the place holder
	 * @param bbValue - the value of the bb
	 * @param versionValue - the value of the version
	 * @param tlgDomain - folder of tld domain
	 * @return a filled form with the values that should be replaced in the command
	 */
	public Map<String,String> preparePlaceHoldersMap(Map<String,String> placeHolderMap, String bbValue, String versionValue, String tlgDomain) {
		
		placeHolderMap.put(Constants.PLACE_HOLDER_BB,bbValue);
		placeHolderMap.put(Constants.PLACE_HOLDER_VERSION,versionValue);
		placeHolderMap.put(Constants.PLACE_HOLDER_VERSION_UNDERSCOR,"v"+ versionValue.substring(0, versionValue.length() - 1) + "_" + versionValue.charAt(versionValue.length()-1));
		placeHolderMap.put(Constants.PLACE_HOLDER_TLG_DOMAIN,tlgDomain);
		
		return placeHolderMap;
	}

}


