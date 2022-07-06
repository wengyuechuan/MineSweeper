package com.sf.minesweeper.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

import com.sf.minesweeper.bean.MineLabel;
import com.sf.minesweeper.frame.SartFrame;
import com.sf.minesweeper.listener.MouseListener;
import com.sf.minesweeper.tools.Tools;




public class MineField extends JPanel {
	
	SartFrame sartFrame;
	MouseListener mouseListener;
	private MineLabel mineLabel[][];
	public  MineField(SartFrame sartFrame){
		
		this.sartFrame=sartFrame;
		this.setLayout(new BorderLayout());
		
		mineLabel=new MineLabel[Tools.totalx][Tools.totaly];

		
		JPanel jPanel2=new JPanel();
		jPanel2.setLayout(new GridLayout(Tools.totalx,Tools.totaly));

		mouseListener=new MouseListener(sartFrame);
		
		for(int i=0;i<Tools.totalx;i++){
			for(int j=0;j<Tools.totaly;j++){

				mineLabel[i][j]=new MineLabel(i,j);
				mineLabel[i][j].setIcon(Tools.iiblank);

				jPanel2.add(mineLabel[i][j]);
				
				mineLabel[i][j].addMouseListener(mouseListener);

			}
		}

	
		this.add(jPanel2);		
	}

	
	
	
	/**
	 * 布雷及算周围雷数
	 */
	public void buildMine(int rowx, int coly) {
		/**
		 * 布雷(去除当前点击位置，使第一次永远不会点到雷)
		 */

		for (int i = 0; i < Tools.totalMine; i++) {
			
			int x = (int) (Math.random() * Tools.totalx);
			int y = (int) (Math.random() * Tools.totaly);
			

			if((x==rowx && y==coly) || mineLabel[x][y].isMine() || judgeIfRound(x,y)){ //rowx和coly是点击的位置的长和宽
				i--;
			}else{
				mineLabel[x][y].setMine(true);
			}
		}
	
		
		/**
		 * 算周围雷数
		 */
		for (int i = 0; i < Tools.totalx; i++) {
			for (int j = 0; j < Tools.totaly; j++) {
				 int count = 0;
				if (!mineLabel[i][j].isMine()) {
	              
					
					for (int x = Math.max(i - 1, 0); x <= Math.min(i + 1,
							Tools.totalx - 1); x++) {
						for (int y = Math.max(j - 1, 0); y <= Math.min(j + 1,
								Tools.totaly - 1); y++) {
							if (mineLabel[x][y].isMine())
								count++;
						}
					}
					mineLabel[i][j].setMineCount(count);

					
					
				}
			}
		}
		
		
	}




	public MineLabel[][] getMineLabel() {
		return mineLabel;
	}

	/**
	 * 判断当前位置是否可以生成雷
	 * @param x 当前的横坐标
	 * @param y	当前的纵坐标
	 * @return
	 */
	public boolean judgeIfRound(int x,int y) { 
		boolean result;
		boolean flag1=true,flag2=true,flag3=true,flag4=true,flag5=true,flag6=true,flag7=true,flag8=true;
		flag1=getFlag(x,y);
		flag2=getFlag(x-2,y);
		flag3=getFlag(x,y-2);
		flag4=getFlag(x-2,y-2);
		flag5=getFlag(x,y-1);
		flag6=getFlag(x-1,y);
		flag7=getFlag(x-2,y-1);
		flag8=getFlag(x-1,y-2);
		result=flag1||flag2||flag3||flag4||flag5||flag6||flag7||flag8;  //有一种不行就不行
			
		return result;
	}

	public boolean getFlag(int x1,int y1) {
		boolean flag=true;
		for(int i=Math.max(x1,0);i<=Math.min(x1+2, Tools.totalx-1);i++) {
			for(int j=Math.max(y1, 0);j<=Math.min(Tools.totaly-1, y1+2);j++) {
				if(!mineLabel[i][j].isMine() && i!=x1+1 && j!=y1+1) { //如果不是雷
					flag=false;
					break;
				}
			}
		}
		return flag;
	}
	public void setMineLabel(MineLabel[][] mineLabel) {
		this.mineLabel = mineLabel;
	}




	public MouseListener getMouseListener() {
		return mouseListener;
	}
	
	

}
