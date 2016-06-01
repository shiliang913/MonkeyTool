package com.test.monkey;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Set;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class PackageList extends JList {

	static HashMap<String,String> packages;
	static ListData[] listDatas;

	public void updateList() {
		repaintList();
	}

	public PackageList() {
		repaintList();
	}

	public void repaintList() {
		packages = Packages.getPackages();
		int n = packages.size();
		listDatas = new ListData[n];
		Set<String> keys = packages.keySet();
		int i = 0;
		for(String key : keys)
			listDatas[i++] = new ListData(key);
		setListData(listDatas);
		setCellRenderer(new ListItem());
		Listener checkListener = new Listener(this);
		addMouseListener(checkListener);
		addKeyListener(checkListener);
		repaint();
	}
}

class Listener implements MouseListener, KeyListener{

	PackageList packageList;

	public Listener(PackageList packageList) {
		this.packageList = packageList;
	}

	private void doCheck() {
		int index = packageList.getSelectedIndex(); 
		if (index < 0) 
			return; 
		ListData listData = (ListData) packageList.getModel().getElementAt(index);
		listData.invertChecked();
		packageList.repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyChar() == ' ')
			doCheck();
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getX() < 200)
			doCheck();
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}

class ListItem extends JCheckBox implements ListCellRenderer {

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		setBackground(isSelected ? list.getSelectionBackground() : list 
				.getBackground()); 

		ListData data = (ListData) value; 
		setText(data.toString());
		setSelected(data.getChecked()); 

		return this;
	}

}

class ListData {

	private String name;
	private boolean isChecked;

	public ListData(String name) {
		this.name = name;
		isChecked = true;
	}

	public void invertChecked(){
		isChecked = !isChecked;
	}

	public boolean getChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	@Override
	public String toString() {
		return name;
	}
}