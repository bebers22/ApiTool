package dataInfo;

import java.io.File;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import enviroment.Constants;

public class CommandsDataInfo {

	private HashMap<String,String> commands;  ///name of command - the unix command (with place holders)
	//private HashMap<String,String> placeHolderValue; //name of place holder - value of it


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
		String preparedCommand = ""; //TODO: change it after erro handling
		
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
			///TODO: create an event that preventing continue
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
	 * load place holders
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
				}
				case Constants.XML_TAG_BB : {

					Constants.PLACE_HOLDER_BB = el.getAttribute(Constants.XML_TAG_NAME);
				}
				case Constants.XML_TAG_VERSION_UNDERSCORE : {

					Constants.PLACE_HOLDER_VERSION_UNDERSCOR = el.getAttribute(Constants.XML_TAG_NAME);
				}
				
				case Constants.XML_TAG_TLG_DOMAIN: {
					
					Constants.PLACE_HOLDER_TLG_DOMAIN = el.getAttribute(Constants.XML_TAG_NAME);
				}

				}
			}

		} catch (Exception e) {
			//TODO : error handling
			System.out.println("FAILD to load place holder file - using default values");
		}

	}

	/**
	 * load commands
	 */
	private void loadCommands() {

		try {
			File commandsFile = new File(Constants.COMMANDS_XML);
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(commandsFile);
			
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
			
		} catch (Exception e) {
			//TODO : error handling
			System.out.println("FAILD to load Commands file - using default values");
		}

	}

//	public HashMap<String,String> getPlaceHolderValue() {
//		return (HashMap<String, String>) placeHolderValue.clone();
//	}

}


