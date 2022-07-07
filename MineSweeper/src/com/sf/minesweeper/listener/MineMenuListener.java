package com.sf.minesweeper.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.sf.minesweeper.dialog.About;
import com.sf.minesweeper.frame.SartFrame;
import com.sf.minesweeper.tools.Tools;

public class MineMenuListener implements ActionListener {
	SartFrame sartFrame;
	
	public MineMenuListener(SartFrame sartFrame){
		this.sartFrame=sartFrame;
	}
	
	
public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("����(N)") && Tools.canLogin){
			this.sartFrame.restart();
		}
		if(e.getActionCommand().equals("����(B)")&& Tools.canLogin){
				Tools.totalx = 9;
				Tools.totaly = 9;
				Tools.totalMine = 10;
				Tools.currentLevel = Tools.LOWER_LEVEL;
				this.sartFrame.restart();

		}
		if(e.getActionCommand().equals("�м�(I)")&& Tools.canLogin){
				Tools.totalx = 16;
				Tools.totaly = 16;
				Tools.totalMine = 40;
				Tools.currentLevel = Tools.MIDDLE_LEVEL;
				this.sartFrame.restart();
			}
		if(e.getActionCommand().equals("�߼�(E)")&& Tools.canLogin){
					Tools.totalx = 16;
					Tools.totaly = 30;
					Tools.totalMine = 99;
					Tools.currentLevel = Tools.HEIGHT_LEVEL;
					this.sartFrame.restart();
				}
		 if(e.getActionCommand().equals("�Զ���(C)")&& Tools.canLogin){
			new About(sartFrame);
				}
		if(Tools.canLogin==false) { //���û�е�¼
				JOptionPane.showMessageDialog(this.sartFrame,"��⵽��δ��¼�����¼"); //��������
			}
		 }

}
		