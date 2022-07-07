package com.sf.minesweeper.dialog;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sf.minesweeper.frame.SartFrame;
import com.sf.minesweeper.tools.Tools;

public class Win extends JDialog {
	private static final long serialVersionUID = 5748133165731210306L;
	SartFrame sartFrame;
	private JTextField text;
	
	
	public Win(SartFrame sartFrame){
		this.sartFrame = sartFrame;
		this.setTitle("提示框");
		this.setLocationRelativeTo(null);
		this.setSize(200, 150);
		this.init();
		this.setVisible(true);
	}

	private void init() {
		
		/**
		 * 存放记入
		 * 
		 */
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4,1));
		JLabel label = new JLabel("请留下你的大名");
		
		panel.add(label);
		
		text = new JTextField();
		panel.add(text);
		//times = sartFrame.getTimer().getTimes();
		JLabel labTime = new JLabel("你所使用的时间："+Tools.time);
		panel.add(labTime);
		
		JButton butStore = new JButton("保存");
		butStore.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {

				
				String ip = sartFrame.getNetlink().s.getInetAddress().toString();
				int BlockNum = Tools.totalx * Tools.totaly;
				String score = ""+ Tools.magnification * Tools.totalMine / BlockNum * Tools.time;
				sartFrame.getNetlink().putwinRequset(text.getText(), ip, ""+Tools.time, score);
				
				Win.this.dispose();
				
			}
		});
		panel.add(butStore);
		this.add(panel);
		
	}
	public JTextField getText() {
		return text;
	}

}


