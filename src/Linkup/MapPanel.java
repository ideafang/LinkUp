package Linkup;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tools.Core;
import tools.Param;

@SuppressWarnings("serial")
public class MapPanel extends JPanel implements MouseListener {
	Chess[][] arr = null; //设置棋子
	Stroke stroke = new BasicStroke(3.0f);//设置画笔
	
	/* 定义鼠标选中的点的位置 */
	Point firstPoint;
	Point secondPoint;
	MainFrame mainFrame;
	
	/* 将mainFrame作为参数传入 */
	public MapPanel(MainFrame mainFrame){
		this.mainFrame = mainFrame;
		initBoard(); //初始化棋盘
		
		this.addMouseListener(this); //监听鼠标动作
		this.setCursor(new Cursor(Cursor.HAND_CURSOR)); //设置鼠标图标
	}
	
	/* 初始化棋盘数组 */
	/*
	 * 若修改图片个数，需要调整变量i和count在while中的边界，以及Param中chessCount变量
	 * 若在Param类中修改棋盘大小，需要为图片种类（默认为20）的整数倍，并修改变量count在while中的边界
	 * 棋盘添加最外围一圈为空，且不显示，为消除算法预留。
	 * @xiaofangdyd
	 * */
	public void initBoard(){
		arr = new Chess[Param.rows + 2][Param.cols + 2];
		Random random = new Random();
		for (int i = 1; i <= 20; i++){
			int count = 0; 
			while(count < 4){ //定义每张图片应该出现次数，一定为偶数
				int x = random.nextInt(Param.rows)+1; //default: 1 - 8
				int y = random.nextInt(Param.cols)+1; //default: 1 - 10
				
				if (arr[x][y] == null){
					arr[x][y] = new Chess(i);
					count++;
				}
			}
		}
		/* 初始化最外围一圈不显示的图片窗格 */
		for (int i = 0; i < arr[0].length; i++){
			arr[0][i] = new Chess(0);
			arr[arr.length - 1][i] = new Chess(0);
		}
		for (int i = 0; i < arr.length; i++){
			arr[i][0] = new Chess(0);
			arr[i][arr[0].length - 1] = new Chess(0);
		}
	}
	
	public void paint(Graphics g){
		super.paint(g);
		g.drawImage(Param.iBackground, 0, 0, this); //设置背景图片
		g.setColor(Color.red);
		g.setFont(new Font("宋体",Font.BOLD,36));
		
		if (Param.gameStatus == 0) {
			g.drawString("游戏还未开始！", 400, 300);
		}else if(Param.gameStatus == 2) {
			g.drawString("游戏暂停！", 400, 300);
		}else if(Param.gameStatus == 3) {
			g.drawString("游戏结束！", 400, 300);
		}else{
			/* 绘制连连看游戏图片区域 */
			for(int i = 1; i < arr.length; i++) {
				for (int j = 1; j < arr[0].length; j++) {
					if (arr[i][j].getStatus() != 0) {
						int x = j * Param.chessWidth + Param.marginWidth;
						int y = i * Param.chessHeight + Param.marginHeight;
						g.drawImage(Param.iconChess[arr[i][j].getStatus() - 1], x, y, this);
						g.setColor(Color.green);
						g.drawRect(x, y, Param.chessWidth, Param.chessHeight);
					}
				}
			}
			/* 判断鼠标click第一个点不为空 */
			/* 为选中的图片加蓝色内框 */
			if (firstPoint != null) {
				Graphics2D g2d = (Graphics2D) g;
				g2d.setStroke(stroke);
				g2d.setColor(Color.blue);
				int rowX = firstPoint.y * Param.chessWidth + Param.marginWidth;
				int rowY = firstPoint.x * Param.chessHeight + Param.marginHeight;
				g2d.drawRect(rowX + 2, rowY + 2, Param.chessWidth - 4, Param.chessHeight - 4);
			}
		}
	}
	
	/* 为指定方格添加指定颜色的内框 */
	public void drawMyRect(Point p, Color c) {
		// TODO Auto-generated method stub
		Graphics g = getGraphics();
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(stroke);
		g2d.setColor(c);
		int rowX = p.y * Param.chessWidth + Param.marginWidth;
		int rowY = p.x * Param.chessHeight + Param.marginHeight;
		g2d.drawRect(rowX + 2, rowY + 2, Param.chessWidth - 4, Param.chessHeight - 4);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("deprecation")
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
		/* 检查鼠标点击是否满足条件 */
		if (Param.gameStatus != 1) {
			return;
		}
		if (e.getModifiers() != InputEvent.BUTTON1_MASK) {
			return;
		}
		
		/* 获得鼠标点击坐标 */
		int x = e.getX();
		int y = e.getY();
		
		int j = (x - Param.marginWidth) / Param.chessWidth; //算出点击的图像为第j列
		int i = (y - Param.marginHeight) / Param.chessHeight; //算出点击的图像为第i行
		//int rowX = j * Param.chessWidth + Param.marginWidth; //算出该图像的起始宽度像素
		//int rowY = i * Param.chessHeight + Param.marginHeight; //算出该图像的起始高度像素
		
		if (arr[i][j].getStatus() == 0) { //若点击的图像为已消除的，则不响应
			return;
		}
		
		/* 判断鼠标选中区域是否在游戏区 */
		if ((x >= Param.marginWidth + Param.chessWidth 
				&& x <= Param.marginWidth + Param.cols * Param.chessWidth + Param.chessWidth)
				&& (y >= Param.marginHeight + Param.chessHeight 
				&& y <= Param.marginHeight + Param.rows * Param.chessHeight + Param.chessHeight)) {
			
			/* 若此时为第一次点击或两次点击的方块相同
			 * 仅标记此次点击方块 */
			if (firstPoint == null || (firstPoint.x == i && firstPoint.y == j)) {
				firstPoint = new Point(i, j);
				drawMyRect(firstPoint,Color.blue);
				return;
			}
			
			/* 剩余情况为两次点击的不同的方块 */
			secondPoint = new Point(i, j);
			drawMyRect(secondPoint,Color.red);
			
			/* 判断两次点击的图片能否消除 
			 * 先调用Core中的判断算法
			 * 若能消除，返回拐点的List<Point> 
			 * 若不能，返回null */
			List<Point> list = Core.checkLinked(arr, firstPoint, secondPoint);
			if (list == null) { //不能消除则将第二个点置为第一次点击的点，执行重绘操作
				firstPoint = secondPoint;
				drawMyRect(firstPoint,Color.blue);
				mainFrame.repaint();
				return;
			}
			
			/* 若可以连通，执行下列操作 
			 * 1. 两点图标状态值置0
			 * 2. 初始化两点
			 * 3. 绘制连接线
			 * 4. 显示得分*/
			arr[firstPoint.x][firstPoint.y].setStatus(0);
			arr[secondPoint.x][secondPoint.y].setStatus(0);
			
			firstPoint = null;
			secondPoint = null;
			
			drawLinkedLine(list); //绘制连接线
			
			mainFrame.repaint();
			Param.score += 10;
			mainFrame.setScore(Param.score);
			Param.chessCount -= 2;
			
			if (Param.chessCount == 0) {
				mainFrame.StopTimer();
				JOptionPane.showMessageDialog(null, "开始下一关", "消息", JOptionPane.PLAIN_MESSAGE);
				Param.gameStatus = 4;
				mainFrame.restartGame();
				mainFrame.repaint();
			}
		}
	}
	/* 绘制连接线 */
	private void drawLinkedLine(List<Point> list) {
		// TODO Auto-generated method stub
		Graphics2D g2d = (Graphics2D) this.getGraphics();
		g2d.setColor(Color.blue);
		g2d.setStroke(stroke);
		if (list.size() == 2) { //判断是否为0拐点情况
			Point a = list.get(0);
			Point b = list.get(1);
			int ax = a.y * Param.chessWidth + Param.marginWidth + Param.chessWidth / 2;
			int ay = a.x * Param.chessHeight + Param.marginHeight + Param.chessHeight / 2;
			int bx = b.y * Param.chessWidth + Param.marginWidth + Param.chessWidth / 2;
			int by = b.x * Param.chessHeight + Param.marginHeight + Param.chessHeight / 2;
			g2d.drawLine(ax, ay, bx, by);
		}
		if (list.size() == 3) { //判断是否为1拐点情况
			Point a = list.get(0);
			Point b = list.get(1);
			Point c = list.get(2);
			int ax = a.y * Param.chessWidth + Param.marginWidth + Param.chessWidth / 2;
			int ay = a.x * Param.chessHeight + Param.marginHeight + Param.chessHeight / 2;
			int bx = b.y * Param.chessWidth + Param.marginWidth + Param.chessWidth / 2;
			int by = b.x * Param.chessHeight + Param.marginHeight + Param.chessHeight / 2;
			int cx = c.y * Param.chessWidth + Param.marginWidth + Param.chessWidth / 2;
			int cy = c.x * Param.chessHeight + Param.marginHeight + Param.chessHeight / 2;
			g2d.drawLine(ax, ay, cx, cy);
			g2d.drawLine(cx, cy, bx, by);
		}
		if (list.size() == 4) { //判断是否为2拐点情况
			Point a = list.get(0);
			Point b = list.get(1);
			Point c = list.get(2);
			Point d = list.get(3);
			int ax = a.y * Param.chessWidth + Param.marginWidth + Param.chessWidth / 2;
			int ay = a.x * Param.chessHeight + Param.marginHeight + Param.chessHeight / 2;
			int bx = b.y * Param.chessWidth + Param.marginWidth + Param.chessWidth / 2;
			int by = b.x * Param.chessHeight + Param.marginHeight + Param.chessHeight / 2;
			int cx = c.y * Param.chessWidth + Param.marginWidth + Param.chessWidth / 2;
			int cy = c.x * Param.chessHeight + Param.marginHeight + Param.chessHeight / 2;
			int dx = d.y * Param.chessWidth + Param.marginWidth + Param.chessWidth / 2;
			int dy = d.x * Param.chessHeight + Param.marginHeight + Param.chessHeight / 2;
			g2d.drawLine(ax, ay, cx, cy);
			g2d.drawLine(cx, cy, dx, dy);
			g2d.drawLine(dx, dy, bx, by);
		}
		
		try {
			Thread.sleep(200);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
