package com.sf.minesweeper.netlink;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class netlink implements Runnable{
	public Socket s=null; //�����쳣���⣬������
	public BufferedReader br=null;
	public PrintStream ps=null;
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
	 */
	
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
		
	}

	
}
