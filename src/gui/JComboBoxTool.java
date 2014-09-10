package gui;

import java.util.Arrays;
import java.util.Collections;

import javax.swing.JComboBox;

import enviroment.Constants;
import enviroment.EnviromentHolder;

public class JComboBoxTool extends JComboBox {

	public void reload() {
		removeAllItems();
		load();
	 
	}
	
	public void load() {
		switch (this.getName()) {
		case Constants.DDL_VERSION_NAME :
			this.setModel(new javax.swing.DefaultComboBoxModel(getDdlForVersions()));
			break;

		case Constants.DDL_BB_NAME :
			this.setModel(new javax.swing.DefaultComboBoxModel(getDdlForBB()));
			break;

		case Constants.DDL_TLG_DOMAIN_NAME :
			this.setModel(new javax.swing.DefaultComboBoxModel(EnviromentHolder.getTlgDomains()));
			break;
		default:
			break;
		}
	}

	/**
	 * get ddl versions content
	 * @return
	 */
	public String[] getDdlForVersions() {
		
		String[] versionsString = {"14063"};
		
		if(EnviromentHolder.ddlFillInVersion != null) {
			versionsString = (String[])EnviromentHolder.ddlFillInVersion.values().toArray(new String[EnviromentHolder.ddlFillInVersion.size()]);
		}
		Arrays.sort(versionsString, Collections.reverseOrder());
		
		return versionsString;
	}

	/**
	 * get ddl BB content
	 * @return
	 */
	public String[] getDdlForBB() {
		
		String[] bbsString = {"TlgServer"};
		
		if(EnviromentHolder.ddlFillInBBs != null) {
			bbsString = EnviromentHolder.ddlFillInBBs.values().toArray(new String[EnviromentHolder.ddlFillInBBs.size()]);
		}
	
		return bbsString;
	}

}
