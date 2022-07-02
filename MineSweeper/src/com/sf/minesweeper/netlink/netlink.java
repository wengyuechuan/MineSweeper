package com.sf.minesweeper.netlink;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.sf.minesweeper.tools.Tools;

public class netlink implements Runnable{
	public Socket s=null; //�����쳣���⣬������
	public BufferedReader br=null;
	public PrintStream ps=null;
	
	private String name = "";
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public netlink() throws IOException {
		this.s=new Socket("127.0.0.1",9999);
		this.br=new BufferedReader(new InputStreamReader(s.getInputStream()));//���ڶ�ȡ��Ϣ�Ļ�����
        this.ps = new PrintStream(s.getOutputStream());
        new Thread(this).start();//�ڴ����̵߳Ĺ����о������߳�
	}
	
	/**
	 * ���Է��͵�����
	 * 	LOGIN#��¼����
	 * 	REGISTER#ע������
	 * 	PUTWIN#д�����а�����
	 * 	GETWIN#�õ����а�����
	 *  OFFLINE#�˳�����
	 */
	public void loginRequest(String usrname, String password) {
		ps.println("LOGIN#" + usrname + password);
	}
	
	public void registerRequest(String usrname, String password, String repassword) {
		ps.println("REGISTER#" + usrname + password + repassword);
	}
	
	public void putwinRequset(String usrname, String ip, String time, String score) {
		ps.println("PUTWIN#" + usrname + ip + time + score);
	}//Ŀǰ��δʹ�����¼�����������
	
	public void getwinRequest() {
		ps.println("GETWIN#");
	}
	
	public void offlineRequest(String usrname) {
		ps.println("OFFLINE#" + usrname);
	}
	
	/**
	 * ���Խ��յ�����
	 * 	ACCEPTLOGIN#�����¼����ʱ��ȷ��¼
	 * 	REFUSELOGIN#�ܾ���¼����ʱû����ȷ��¼
	 * 	ACCEPTREGISTER#��ȷע��
	 * 	REFUSEREGISTER#�ܾ�ע��
	 * 	OFFLINE#�������󣬷������߳����
	 */
	@Override
	public void run() {
		while(true) {
			try {
				String readInf = br.readLine();
				String[] readMsg = readInf.split("#");
				if(readMsg[0].equals("ACCEPTLOGIN")) {
					Tools.canLogin = true;
				}else if(readMsg[0].equals("REFUSELOGIN")) {
					Tools.canLogin = false;
				}else if(readMsg[0].equals("ACCEPTREGISTER")) {
					Tools.canRegister = true;
				}else if(readMsg[0].equals("REFUSEREGISTER")) {
					Tools.canRegister = false;
					Tools.errorMsg = readMsg[1];
				}else {//OFFLINE
					System.exit(0);
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	
	

	
}
