package com.test.monkey;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;

public class Frame extends JFrame {

	private JPanel contentPane;
	public static JTextField textField;
	public static JTextArea textArea;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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

		JComboBox comboBox = new JComboBox();
		comboBox.setToolTipText("Choose device ID");
		comboBox.setBounds(0, 0, 100, 30);
		contentPane.add(comboBox);

		JButton btnNewButton = new JButton("START");
		btnNewButton.setToolTipText("");
		btnNewButton.addActionListener(new ActionListener() {
			int i, n;
			public void actionPerformed(ActionEvent e) {
				Util.startMonkey();
			}
		});
		btnNewButton.setBounds(0, 47, 100, 50);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("Times");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 219, 100, 30);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText("100000");
		textField.setBounds(0, 250, 100, 30);
		contentPane.add(textField);
		textField.setColumns(10);

		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(942, 22, 17, 496);
		contentPane.add(scrollBar);

		JButton btnStop = new JButton("STOP");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Util.stopMonkey();
			}
		});
		btnStop.setBounds(0, 107, 100, 50);
		contentPane.add(btnStop);

		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setBounds(124, 22, 835, 496);
		contentPane.add(textArea);
	}
}

