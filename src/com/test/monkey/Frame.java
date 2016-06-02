package com.test.monkey;

import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JCheckBox;

public class Frame extends JFrame {

	private JPanel contentPane;
	public static JTextField textField;
	public static JTextArea textArea;
	public static JScrollPane scrollPane;
	public static JScrollBar jScrollBar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Init();
					Frame frame = new Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Frame() {
		setResizable(false);
		setTitle("Monkey Tool");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("START");
		btnNewButton.setToolTipText("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Util.startMonkey();
			}
		});
		btnNewButton.setBounds(32, 22, 120, 50);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("Times");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(32, 153, 120, 30);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText("100000");
		textField.setBounds(32, 184, 120, 30);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnStop = new JButton("STOP");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Util.StopMonkey().start();
			}
		});
		btnStop.setBounds(32, 82, 120, 50);
		contentPane.add(btnStop);
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(220, 21, 764, 490);
		jScrollBar = scrollPane.getVerticalScrollBar();
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
			}
		});
		btnClear.setBounds(866, 521, 100, 30);
		contentPane.add(btnClear);
		
		final JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(6, 280, 204, 271);
		contentPane.add(scrollPane_1);
		final PackageList packageList = new PackageList();
		scrollPane_1.setViewportView(packageList);
		
		final JCheckBox chckbxNewCheckBox = new JCheckBox("Select packages");
		chckbxNewCheckBox.setSelected(true);
		chckbxNewCheckBox.setBounds(6, 251, 163, 23);
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean isChecked = chckbxNewCheckBox.isSelected();
				packageList.setAllChecked(isChecked);
			}
		});
		contentPane.add(chckbxNewCheckBox);
		
		ImageIcon icon = new ImageIcon("refresh.png");
		Image image = icon.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
		icon.setImage(image);
		JButton btnNewButton_1 = new JButton(icon);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setBounds(180, 244, 30, 30);
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				packageList.repaintList();
			}
		});
		contentPane.add(btnNewButton_1);
	}
}

