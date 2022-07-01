package com.sf.minesweeper.dialog;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.sf.minesweeper.frame.SartFrame;
import com.sf.minesweeper.tools.Tools;

public class LoginDialog extends JDialog{
	private JLabel username;//账号标签
	private JLabel password;//密码标签
	private JButton submit;//提交按钮
    JTextField uname=new JTextField("请输入你的用户名",16); //账号框
    JPasswordField passwordField=new JPasswordField(16);//密码框
	private JPanel jpanel1;//账号的排版
	private JPanel jpanel2;//密码的排版
	private JPanel jpanel3;//按钮的排布
	private SartFrame sartsrame;
	public LoginDialog(SartFrame sartsrame) {
		 this.sartsrame=sartsrame;
		 this.setTitle("登录");
		 this.setVisible(true);
		 this.setSize(220, 210);
		 this.setResizable(false);//不可以通过调整窗口大小
		 this.setLocationRelativeTo(sartsrame);
		 this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		 init();
	 }
	void init() {
		username=new JLabel("用户名：");
		password=new JLabel("密码：");
		final JDialog that=this;
	    submit=new JButton(new AbstractAction("登录") {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            String username1=uname.getText();
	            String password1= String.valueOf(passwordField.getPassword());
	            if(username1.equals("wyc") && password1.equals("123") ) {
	            	Tools.ifLogin=true;//如果账号为wyc密码为123则修改状态为已经登录
	            	JOptionPane.showMessageDialog(that,"登录成功");
	            	
	            }else {
	            	JOptionPane.showMessageDialog(that,"您输入的用户名或者密码错误,请重新输入");
	            }
	        }
	    });
	    jpanel1=new JPanel();
	    jpanel1.add(username);
	    jpanel1.add(uname);
	    jpanel2=new JPanel();
	    jpanel2.add(password);
	    jpanel2.add(passwordField);
	    jpanel3=new JPanel();
	    jpanel3.add(submit);
	    this.add(jpanel1,BorderLayout.NORTH);
	    this.add(jpanel2,BorderLayout.CENTER);
	    this.add(jpanel3,BorderLayout.SOUTH);

	}
}
