package com.sf.minesweeper.dialog;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;

import javax.swing.*;

import com.sf.minesweeper.frame.SartFrame;
import com.sf.minesweeper.tools.Tools;

public class RegisterDialog extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6688186545326020940L;
	private JLabel username;//账号标签
	private JLabel password;//密码标签
	private JLabel repassword;//密码标签
	private JButton submit;//提交按钮
    JTextField uname=new JTextField("请输入你的用户名",16); //账号框
    JPasswordField passwordField=new JPasswordField(16);//密码框
    JPasswordField repasswordField=new JPasswordField(16);//密码框
	private JPanel jpanel1;//账号的排版
	private JPanel jpanel2;//密码的排版
	private JPanel jpanel3;//按钮的排布
	private JPanel jpanel4;//按钮的排布
	private JPanel jpanel5;//设计底层的布局
	private SartFrame sartsrame;
	public RegisterDialog(SartFrame sartsrame) {
		 this.sartsrame=sartsrame;
		 this.setTitle("注册");
		 this.setVisible(true);
		 this.setSize(300, 300);
		 this.setResizable(false);//不可以通过调整窗口大小
		 this.setLocationRelativeTo(sartsrame);
		 this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		 init();
	 }
	
	void init() {
		username=new JLabel("用户名：");
		password=new JLabel("密码：");
		repassword=new JLabel("重复密码：");
		final JDialog that=this;
	    submit=new JButton(new AbstractAction("注册") {
	        /**
			 * 
			 */
			private static final long serialVersionUID = 2958397878893239465L;

			@Override
	        public void actionPerformed(ActionEvent e) {
	            String username1=uname.getText();
	            String password1= String.valueOf(passwordField.getPassword());
	            String repassword1=String.valueOf(repasswordField.getPassword());
	            sartsrame.getNetlink().registerRequest(username1, password1, repassword1);
	            
	            if(Tools.canRegister) {
	            	JOptionPane.showMessageDialog(that,"注册成功");
	            }else {
	            	JOptionPane.showMessageDialog(that,Tools.errorMsg);
	            }
	            
	           /*
	            if(!password1.equals(repassword1)) {
	            	JOptionPane.showMessageDialog(that,"两次输入密码不一致");
	            }else if(username1.equals("wyc") ) {
	            	JOptionPane.showMessageDialog(that,"用户名存在");
	            }else {
	            	JOptionPane.showMessageDialog(that,"注册成功");
	            }*/
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
	    jpanel4=new JPanel();
	    jpanel4.add(repassword);
	    jpanel4.add(repasswordField);
	    jpanel5=new JPanel();
	    jpanel5.setLayout(new BorderLayout());
	    jpanel5.add(jpanel1,BorderLayout.NORTH);
	    jpanel5.add(jpanel2,BorderLayout.CENTER);
	    this.add(jpanel5,BorderLayout.NORTH);
	    this.add(jpanel4,BorderLayout.CENTER);
	    this.add(jpanel3,BorderLayout.SOUTH);

	}
}
