package com.sf.minesweeper.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sf.minesweeper.frame.SartFrame;
import com.sf.minesweeper.tools.Tools;

public class ShowWin extends JDialog {
	
			
		/**
	 * 
	 */
	private static final long serialVersionUID = -4694171092569144080L;
		private JLabel jlabelTime;
		private JLabel jlabelUser;
		
		private JLabel jlabelIp;
		private JLabel jlabelScore;

		private JLabel time1;
		private JLabel time2;
		private JLabel time3;
		
		private JLabel name1;
		private JLabel name2;
		private JLabel name3;
		
		private JLabel ip1;
		private JLabel ip2;
		private JLabel ip3;
		
		private JLabel scores1;
		private JLabel scores2;
		private JLabel scores3;
		
		private JButton jbutton1;
		
		private SartFrame sartsrame;
		private JPanel jpanel;
		
		public ShowWin(SartFrame sartsrame){
			this.setSartsrame(sartsrame);
			this.setTitle("扫雷排行榜");
			this.setVisible(true);
			this.setSize(400,300);
			this.setResizable(false);
			this.setLocationRelativeTo(sartsrame);
			this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			init();
		}
		
		public void init(){
			HeroListener heroListener = new HeroListener();

			jlabelTime = new JLabel(" 时间");
			jlabelUser = new JLabel(" 玩家");
			jlabelIp=new JLabel("ip");
			jlabelScore=new JLabel("得分");

			time1 = new JLabel(""+Tools.time1);
			time2 = new JLabel(""+Tools.time2);
			time3 = new JLabel(""+Tools.time3);
			name1 = new JLabel(" "+Tools.name1);
			name2 = new JLabel(" "+Tools.name2);
			name3 = new JLabel(" "+Tools.name3);
			ip1=new JLabel(" "+Tools.ip1);
			ip2=new JLabel(" "+Tools.ip2);
			ip3=new JLabel(" "+Tools.ip3);
			scores1=new JLabel(" "+Tools.scores1);
			scores2=new JLabel(" "+Tools.scores2);
			scores3=new JLabel(" "+Tools.scores3);
			jbutton1=new JButton("确定");
			jbutton1.addActionListener(heroListener);
			
			jpanel=new JPanel();
			Box box1 = Box.createVerticalBox();//垂直组件
			box1.add(jlabelUser);//玩家
			box1.add(Box.createVerticalStrut(10));//垂直间隔
			box1.add(name1);
			box1.add(Box.createVerticalStrut(10));
			box1.add(name2);
			box1.add(Box.createVerticalStrut(10));
			box1.add(name3);
			Box box2 = Box.createVerticalBox();
			box2.add(jlabelIp);//ip
			box2.add(Box.createVerticalStrut(10));
			box2.add(ip1);
			box2.add(Box.createVerticalStrut(10));
			box2.add(ip2);
			box2.add(Box.createVerticalStrut(10));
			box2.add(ip3);
			Box box3 = Box.createVerticalBox();
			box3.add(jlabelTime);//时间
			box3.add(Box.createVerticalStrut(10));
			box3.add(time1);
			box3.add(Box.createVerticalStrut(10));
			box3.add(time2);
			box3.add(Box.createVerticalStrut(10));
			box3.add(time3);
			Box box7 = Box.createVerticalBox();
			box7.add(jlabelScore);//得分
			box7.add(Box.createVerticalStrut(10));
			box7.add(scores1);
			box7.add(Box.createVerticalStrut(10));
			box7.add(scores2);
			box7.add(Box.createVerticalStrut(10));
			box7.add(scores3);
			Box box4 = Box.createHorizontalBox();//水平组件
			box4.add(jbutton1);//确定
			
			
			Box box5 = Box.createHorizontalBox();
			box5.add(box1);//玩家
			box5.add(box2);//ip
			box5.add(box3);//时间
			box5.add(box7);//得分
			Box box6 = Box.createVerticalBox();
			box6.add(Box.createVerticalStrut(20));
			box6.add(box5);
			box6.add(Box.createVerticalStrut(20));
			box6.add(box4);
			jpanel.add(box6);
			this.add(jpanel);
		}
		class HeroListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				dispose();
				
			}
		}
		

		public void setSartsrame(SartFrame sartsrame) {
			this.sartsrame = sartsrame;
		}
	}

	

