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
	/**
	 * 
	 */
	private static final long serialVersionUID = -4230226090291787863L;
	private JLabel username;//�˺ű�ǩ
	private JLabel password;//�����ǩ
	private JButton submit;//�ύ��ť
    JTextField uname=new JTextField("����������û���",16); //�˺ſ�
    JPasswordField passwordField=new JPasswordField(16);//�����
	private JPanel jpanel1;//�˺ŵ��Ű�
	private JPanel jpanel2;//������Ű�
	private JPanel jpanel3;//��ť���Ų�
	private SartFrame sartsrame;
	public LoginDialog(SartFrame sartsrame) {
		 this.sartsrame=sartsrame;
		 this.setTitle("��¼");
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
		final JDialog that=this;
	    submit=new JButton(new AbstractAction("��¼") {
	        /**
			 * 
			 */
			private static final long serialVersionUID = -3262524165999072279L;

			@Override
	        public void actionPerformed(ActionEvent e) {
	        	if(Tools.islogin) {
	        		JOptionPane.showMessageDialog(that,"���Ѿ���¼���ˣ������Ҫ�����˺����˳����µ�½");
	        		
	        	}else {
	        		String username1=uname.getText();
		            String password1= String.valueOf(passwordField.getPassword());
		            sartsrame.getNetlink().loginRequest(username1, password1);
		            
		            if(Tools.canLogin) {
		            	JOptionPane.showMessageDialog(that,"��¼�ɹ�");
		            	sartsrame.getNetlink().setName(username1);
		            	Tools.islogin = true;
		            }else {
		            	JOptionPane.showMessageDialog(that,"��������û��������������,����������");
		            }
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
