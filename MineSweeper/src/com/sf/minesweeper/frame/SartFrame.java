package com.sf.minesweeper.frame;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.UIManager;

import com.sf.minesweeper.menu.MineMenu;
import com.sf.minesweeper.netlink.netlink;
import com.sf.minesweeper.panel.MineField;
import com.sf.minesweeper.panel.MineState;
import com.sf.minesweeper.timer.Timers;
import com.sf.minesweeper.tools.Tools;

public class SartFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1585043387266273492L;
	/**
	 * @param args
	 */
	
	private MineState mineState; // 记雷数
	private MineField mineField; // 布置labble和雷数
	private MineMenu mineMenu;
	private Timer timer;
	private Timers timers;
	private netlink Netlink;//netlink线程，用于连接服务器
	/**
	 * 游戏是否开始
	 */
	private boolean isStart;
	JLabel jLabel_start = new JLabel(); // 开始图片
	
	
	public SartFrame() throws IOException {
		//改变系统默认字体
		Font font = new Font("Dialog", Font.PLAIN, 12);
		java.util.Enumeration keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof javax.swing.plaf.FontUIResource) {
				UIManager.put(key, font);
			}
		}
		this.setTitle("扫雷");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		setIconImage(Tools.iicon); // 利用tools来做的作法

		this.setResizable(false); // 这样让窗口不会可放大
		
		//加一个关闭监听窗口
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				Netlink.offlineRequest(Netlink.getName());
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		// ..................状态栏.....................
		mineState = new MineState(this);
		this.add(mineState, BorderLayout.NORTH);

		// ...................雷区......................
		mineField = new MineField(this);
		this.add(mineField, BorderLayout.CENTER);

		jLabel_start.setIcon(Tools.start); //此时如果没有开始则是当前的效果
		this.add(jLabel_start, BorderLayout.CENTER);

		// ....................菜单栏................
		mineMenu = new MineMenu(this);
		this.setJMenuBar(mineMenu);
		
		// .....................时间................
		Tools.time = 0;
		timers = new Timers(mineState);
		timer = new Timer(1000, timers);

		//.....................netlink线程................
		Netlink = new netlink(this);
		
		pack();
		this.setVisible(true);
	}


	// 重新布局
	public void restart() {

		this.remove(mineState);

		this.remove(mineField);

		this.remove(jLabel_start);
		
		

		// ..................状态栏.....................
		mineState = new MineState(this);
		this.add(mineState, BorderLayout.NORTH);
		

		
		// ...................雷区......................
		mineField = new MineField(this);
		this.add(mineField, BorderLayout.CENTER);

		
		// .....................时间................
		Tools.time = 0;
		Timers timers = new Timers(mineState);
		timer = new Timer(1000, timers);
		
		
		pack();
		validate();//刷新窗体
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public MineState getMineState() {
		return mineState;
	}



	public MineField getMineField() {
		return mineField;
	}



	public MineMenu getMineMenu() {
		return mineMenu;
	}



	public Timer getTimer() {
		return timer;
	}



	public Timers getTimers() {
		return timers;
	}

	
	public boolean isStart() {
		return isStart;
	}

	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}
	
	public netlink getNetlink() {
		return Netlink;
	}


	public void setNetlink(netlink netlink) {
		Netlink = netlink;
	}
	
	public static void main(String[] args) throws IOException {
		new SartFrame();
	}


	
	
	
	
	

	
	
}
