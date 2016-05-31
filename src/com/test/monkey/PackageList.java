package com.test.monkey;

import java.awt.Checkbox;
import java.awt.Component;
import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

public class PackageList extends JList {

	static HashMap<String,String> packages;
	static ListData[] listDatas;

	static {
		packages = getPackages();
		int n = packages.size();
		listDatas = new ListData[n];
		Set<String> keys = packages.keySet();
		int i = 0;
		for(String key : keys){
			listDatas[i++] = new ListData(key);
		}
	}
	
	public PackageList() {
		super(listDatas);
		setCellRenderer(new ListItem());
	}

	public static HashMap<String,String> getPackages(){
		HashMap<String,String> pkgs = new HashMap<String,String>();
		pkgs.put("com.android.camera","camera");
		pkgs.put("com.android.camera2","camera");
		pkgs.put("com.android.camera3","camera3");
		return pkgs;
	}
	
}

class ListItem extends JCheckBox implements ListCellRenderer {
	
	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		setText(value.toString());
		return this;
	}
	
}

class ListData {
	
	private String name;
	private boolean isChecked;
	
	public ListData(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}