package com.test.monkey;

import java.awt.Checkbox;
import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;

public class PackageList extends JList {

	String[] packages;

	public PackageList(String[] packages) {
		super(packages);
		this.packages = packages;
		setCellRenderer(new ListItem());
	}

	public static String[] getPackages(){
		String[] strings = new String[10];
		strings[0] = "com.android.camera";
		return strings;
	}

	class ListItem extends JCheckBox implements ListCellRenderer {

		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			setText("aaaaaaaaaaaaaa");
			return this;
		}

	}
}

class CellData {
	private String name;
	private boolean isChecked;
	@Override
	public String toString() {
		return name;
	}
}
