package com.sf.minesweeper.netlink;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.sf.minesweeper.tools.Tools;

public class netlink implements Runnable{
	public Socket s=null; //由于异常问题，则这里
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
	public void loginRequest(String usrname, String password) {
		ps.println("LOGIN#" + usrname + password);
	}
	
	public void registerRequest(String usrname, String password, String repassword) {
		ps.println("REGISTER#" + usrname + password + repassword);
	}
	
	public void putwinRequset(String usrname, String ip, String time, String score) {
		ps.println("PUTWIN#" + usrname + ip + time + score);
	}//目前还未使用在事件监听操作中
	
	public void getwinRequest() {
		ps.println("GETWIN#");
	}
	
	public void offlineRequest(String usrname) {
		ps.println("OFFLINE#" + usrname);
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
