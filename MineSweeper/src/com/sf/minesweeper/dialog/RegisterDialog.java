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
	private JLabel username;//�˺ű�ǩ
	private JLabel password;//�����ǩ
	private JLabel repassword;//�����ǩ
	private JButton submit;//�ύ��ť
    JTextField uname=new JTextField("����������û���",16); //�˺ſ�
    JPasswordField passwordField=new JPasswordField(16);//�����
    JPasswordField repasswordField=new JPasswordField(16);//�����
	private JPanel jpanel1;//�˺ŵ��Ű�
	private JPanel jpanel2;//������Ű�
	private JPanel jpanel3;//��ť���Ų�
	private JPanel jpanel4;//��ť���Ų�
	private JPanel jpanel5;//��Ƶײ�Ĳ���
	private SartFrame sartsrame;
	public RegisterDialog(SartFrame sartsrame) {
		 this.sartsrame=sartsrame;
		 this.setTitle("ע��");
		 this.setVisible(true);
		 this.setSize(300, 300);
		 this.setResizable(false);//������ͨ���������ڴ�С
		 this.setLocationRelativeTo(sartsrame);
		 this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		 init();
	 }
	
	void init() {
		username=new JLabel("�û�����");
		password=new JLabel("���룺");
		repassword=new JLabel("�ظ����룺");
		final JDialog that=this;
	    submit=new JButton(new AbstractAction("ע��") {
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
	            	JOptionPane.showMessageDialog(that,"ע��ɹ�");
	            }else {
	            	JOptionPane.showMessageDialog(that,Tools.errorMsg);
	            }
	            
	           /*
	            if(!password1.equals(repassword1)) {
	            	JOptionPane.showMessageDialog(that,"�����������벻һ��");
	            }else if(username1.equals("wyc") ) {
	            	JOptionPane.showMessageDialog(that,"�û�������");
	            }else {
	            	JOptionPane.showMessageDialog(that,"ע��ɹ�");
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
