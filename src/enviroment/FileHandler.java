package enviroment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class FileHandler {

	public final static String XML_TAG_PLACEHOLDERS = "PlaceHolder";
	public final static String XML_TAG_USERNAME = "username";
	public final static String XML_TAG_PASSWORD = "password";
	public final static String XML_TAG_NAME = "name";
	public final static String XML_TAG_ID = "id";
	public final static String XML_TAG_USER = "User";
	public final static String XML_TAG_BB = "BB";
	public static final String XML_TAG_FILE = "File";
	public static final String XML_TAG_LOCATION = "location";
	public final static String XML_TAG_VERSION_UNDERSCORE = "Version_";
	public final static String XML_TAG_VERSION = "Version";
	public final static String XML_TAG_COMMAND = "command";
	public final static String XML_TAG_TLG_DOMAIN = "Tlg_domain";
	public final static String LOCATIONS_XML = "V:/API Knowledge Base/prop/Locations.xml";
	public final static String LOCAL_LOCATIONS_XML = "Locations.xml";
	public final static String USERNAME_PASSWORD_XML = "xmlsFolder/usernamePassword.xml";
	public final static String BB_AND_VERSIONS_XML = "BB_AND_VERSIONS_XML";
	public final static String COMMANDS_XML = "COMMANDS_XML";
	public final static String SUCESS_CONNECT_ICON = "SUCESS_CONNECT_ICON";
	public final static String LOCAL_XML_USER_DETAILS_FILE = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"no\"?><UserDetails> <User id=\"1\" password=\"somePassword\" type=\"string\" username=\"guest\"/> </UserDetails>";
	public final static String ICON = "ICON";
	
	
	/**
	 * loading the versions an BBs from xml file
	 */
	public static void loadDdlDetails() {
	
		try {
			File fXmlFile = new File(EnviromentHolder.fileLocations.get(FileHandler.BB_AND_VERSIONS_XML));
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fXmlFile);
	
			EnviromentHolder.ddlFillInBBs = FileHandler.parsePropertiestoMap(FileHandler.XML_TAG_BB,doc);
			EnviromentHolder.ddlFillInVersion = FileHandler.parsePropertiestoMap(FileHandler.XML_TAG_VERSION,doc);
			
			
		} catch (SAXException | ParserConfigurationException sx) {
			ErrorMsgs.handleException("", JOptionPane.WARNING_MESSAGE, ErrorMsgs.TITLE_FAILD_TO_LOAD_FILE, ErrorMsgs.USER_PROPERTIES_FILE_ERROR, sx);
		} catch (IOException ioe) {
			ErrorMsgs.handleException("", JOptionPane.WARNING_MESSAGE, ErrorMsgs.TITLE_FAILD_TO_LOAD_FILE, ErrorMsgs.FAILD_TO_OPEN_FILE +": user properties", ioe);
		} catch (Exception e) {
			ErrorMsgs.handleException("", JOptionPane.WARNING_MESSAGE, ErrorMsgs.TITLE_FAILD_TO_LOAD_FILE, ErrorMsgs.FILE_IS_EMPTY +": versions and bb", e);
		}
	}

	/**
	 * Creating the local XML file of username and password
	 */
	public static void createLocalUserDetailsXMLFile() {
		
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(FileHandler.USERNAME_PASSWORD_XML, true))) {
			
			writer.write(FileHandler.LOCAL_XML_USER_DETAILS_FILE);
			
		} catch (IOException e) {
			ErrorMsgs.handleException("", JOptionPane.WARNING_MESSAGE, ErrorMsgs.TITLE_FAILD_TO_CREATE_FILE, ErrorMsgs.FAILD_TO_CREATE_FILE +": user properties", e);
		}
		
		EnviromentHolder.setUsernamePassword("", "");
	}

	static void loadfileLocations() {
		try {
	
			File filesLocations = new File(EnviromentHolder.DEBUG_MODE ? FileHandler.LOCAL_LOCATIONS_XML : FileHandler.LOCATIONS_XML);
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(filesLocations);
			EnviromentHolder.fileLocations = FileHandler.parsePropertiestoMap(FileHandler.XML_TAG_FILE,doc);

		} catch (SAXException | ParserConfigurationException sx) {
			ErrorMsgs.handleException("", JOptionPane.WARNING_MESSAGE, ErrorMsgs.TITLE_FAILD_TO_LOAD_FILE,  ErrorMsgs.LOCATIONS_FILE + (EnviromentHolder.DEBUG_MODE ? FileHandler.LOCAL_LOCATIONS_XML : FileHandler.LOCATIONS_XML) , sx);
		} catch (IOException ioe) {
			ErrorMsgs.handleException("", JOptionPane.WARNING_MESSAGE, ErrorMsgs.TITLE_FAILD_TO_LOAD_FILE, ErrorMsgs.FAILD_TO_OPEN_FILE +": locations", ioe);
			createLocalUserDetailsXMLFile();
		} catch (Exception e) {
			ErrorMsgs.handleException("", JOptionPane.WARNING_MESSAGE, ErrorMsgs.TITLE_FAILD_TO_LOAD_FILE, ErrorMsgs.FILE_IS_EMPTY +": locations", e);
		}	
	}

	/**
	 * Loading the user details
	 */
	public static void loadUserEnvDetails() {
		try {
			
		   	File usernamePasswordfile = new File(FileHandler.USERNAME_PASSWORD_XML);
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(usernamePasswordfile);
			NodeList  nList = doc.getElementsByTagName(FileHandler.XML_TAG_USER);
			if( nList.getLength() != 0 ) {
				Element el = (Element) nList.item(0);
				EnviromentHolder.setUsernamePassword(el.getAttribute(FileHandler.XML_TAG_USERNAME), el.getAttribute(FileHandler.XML_TAG_PASSWORD));
			}
		} catch (SAXException | ParserConfigurationException sx) {
			ErrorMsgs.handleException("", JOptionPane.WARNING_MESSAGE, ErrorMsgs.TITLE_FAILD_TO_LOAD_FILE,  ErrorMsgs.USER_PROPERTIES_FILE_ERROR, sx);
		} catch (IOException ioe) {
			ErrorMsgs.handleException("", JOptionPane.WARNING_MESSAGE, ErrorMsgs.TITLE_FAILD_TO_LOAD_FILE, ErrorMsgs.FAILD_TO_OPEN_FILE +": user properties", ioe);
			createLocalUserDetailsXMLFile();
		} catch (Exception e) {
			ErrorMsgs.handleException("", JOptionPane.WARNING_MESSAGE, ErrorMsgs.TITLE_FAILD_TO_LOAD_FILE, ErrorMsgs.FILE_IS_EMPTY +": user properties", e);
		}	
	}

	/**
	 * Parse the xml of the environments and versions
	 * @param tagName
	 * @param doc
	 * @return
	 */
	static HashMap<String, String> parsePropertiestoMap(String tagName, Document doc) {
	
		NodeList nList = doc.getElementsByTagName(tagName);
		
		int nListLenght = nList.getLength();
		
		HashMap<String, String> mapToFill = new HashMap<String,String>(nListLenght);
		
		for(int i=0 ; i<nListLenght ; i++) {
			Node node = nList.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				Element el = (Element)node;
				mapToFill.put(el.getAttribute(FileHandler.XML_TAG_ID), el.getAttribute(FileHandler.XML_TAG_NAME));
			}
		}
		return mapToFill;
	}


}
