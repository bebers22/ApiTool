package gui;

import java.lang.ref.WeakReference;
import javax.swing.JPanel;
import enviroment.EnviromentHolder;

public class JPanelTool extends JPanel {

	protected JComboBoxTool createDropDownList(String ddlVersionName, String toolTipText) {
		JComboBoxTool ddl = new JComboBoxTool();
		ddl.setName(ddlVersionName);
		ddl.setToolTipText(toolTipText);
		ddl.load();

    	EnviromentHolder.ddlList.add(new WeakReference<JComboBoxTool>(ddl));
		return ddl;
	}

}
