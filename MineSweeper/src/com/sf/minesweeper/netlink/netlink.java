package com.sf.minesweeper.netlink;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import com.sf.minesweeper.frame.SartFrame;
import com.sf.minesweeper.tools.Tools;

public class netlink implements Runnable{
	public Socket s=null; //由于异常问题，则这里
	public BufferedReader br=null;
	public PrintStream ps=null;
	public SartFrame sartframe=null;
	private String name = "";
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public netlink(SartFrame sartframe) throws IOException {
		this.sartframe=sartframe;
		this.s=new Socket("127.0.0.1",9999);
		this.br=new BufferedReader(new InputStreamReader(s.getInputStream()));//用于读取信息的缓冲区
        this.ps = new PrintStream(s.getOutputStream());
        new Thread(this).start();//在创建线程的过程中就启动线程
	}
	
	/**
	 * 可以发送的请求：
	 * 	LOGIN#登录请求
	 * 	REGISTER#注册请求
	 * 	PUTWIN#写入排行榜请求
	 * 	GETWIN#得到排行榜请求
	 *  OFFLINE#退出请求
	 */
	public void loginRequest(String usrname, String password){
		ps.println("LOGIN#" + usrname + "#" + password);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void registerRequest(String usrname, String password, String repassword) {
		ps.println("REGISTER#" + usrname + "#" + password + "#" + repassword);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void putwinRequset(String usrname, String ip, String time, String score) {
		ps.println("PUTWIN#" + usrname + "#" + ip + "#" + time + "#" + score);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//目前还未使用在事件监听操作中
	
	public void getwinRequest() {
		ps.println("GETWIN#");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void offlineRequest(String usrname, String OfflineType) {
		ps.println("OFFLINE#" + usrname + "#" + OfflineType);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 可以接收的请求：
	 * 	ACCEPTLOGIN#允许登录，此时正确登录
	 * 	REFUSELOGIN#拒绝登录，此时没有正确登录
	 * 	ACCEPTREGISTER#正确注册
	 * 	REFUSEREGISTER#拒绝注册
	 * 	OFFLINE#下线请求，服务器踢出玩家
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
				}else if(readMsg[0].equals("OFFLINE")){//OFFLINE
					if(readMsg[2].equals("KICK")) {
						JOptionPane.showMessageDialog(this.sartframe,"哦豁，你被服务器踢出去了，别玩了小伙子！");//可以使用
					}
					System.exit(0);
				}else if(readMsg[0].equals("GETWINRESULT")) { //得到返回的结果
					Tools.name1=readMsg[1];
					Tools.ip1=readMsg[2];
					Tools.time1=Integer.parseInt(readMsg[3]);
					Tools.scores1=readMsg[4];
					Tools.name2=readMsg[5];
					Tools.ip2=readMsg[6];
					Tools.time2=Integer.parseInt(readMsg[7]);
					Tools.scores2=readMsg[8];
					Tools.name3=readMsg[9];
					Tools.ip3=readMsg[10];
					Tools.time3=Integer.parseInt(readMsg[11]);
					Tools.scores3=readMsg[12];
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	
	

	
}
