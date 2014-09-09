package gui;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import enviroment.Constants;
import enviroment.EnviromentHolder;

public class JPanelTool extends JPanel {

	protected JComboBox createDropDownList(String ddlVersionName, String toolTipText) {
		JComboBox ddl = new JComboBox();
		ddl.setName(ddlVersionName);
		ddl.setToolTipText(toolTipText);
		switch (ddlVersionName) {
		case Constants.DDL_VERSION_NAME:
			ddl.setModel(new javax.swing.DefaultComboBoxModel(EnviromentHolder.getDdlForVersions()));
			break;
		case Constants.DDL_BB_NAME:
			ddl.setModel(new javax.swing.DefaultComboBoxModel(EnviromentHolder.getDdlForBB()));
			break;
		case Constants.DDL_TLG_DOMAIN_NAME:
			ddl.setModel(new javax.swing.DefaultComboBoxModel(EnviromentHolder.getTlgDomains()));
			break;
		default:
			break;
		}
    	EnviromentHolder.ddlList.add(ddl);
		return ddl;
	}

}
