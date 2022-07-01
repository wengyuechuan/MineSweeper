package com.sf.minesweeper.panel;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

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
			//System.out.println(y);

			if(x==rowx && y==coly){
				i--;
			}
			else if(mineLabel[x][y].isMine()){
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




	public void setMineLabel(MineLabel[][] mineLabel) {
		this.mineLabel = mineLabel;
	}




	public MouseListener getMouseListener() {
		return mouseListener;
	}
	
	

}
