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
	Chess[][] arr = null; //��������
	Stroke stroke = new BasicStroke(3.0f);//���û���
	
	/* �������ѡ�еĵ��λ�� */
	Point firstPoint;
	Point secondPoint;
	MainFrame mainFrame;
	
	/* ��mainFrame��Ϊ�������� */
	public MapPanel(MainFrame mainFrame){
		this.mainFrame = mainFrame;
		initBoard(); //��ʼ������
		
		this.addMouseListener(this); //������궯��
		this.setCursor(new Cursor(Cursor.HAND_CURSOR)); //�������ͼ��
	}
	
	/* ��ʼ���������� */
	/*
	 * ���޸�ͼƬ��������Ҫ��������i��count��while�еı߽磬�Լ�Param��chessCount����
	 * ����Param�����޸����̴�С����ҪΪͼƬ���ࣨĬ��Ϊ20���������������޸ı���count��while�еı߽�
	 * �����������ΧһȦΪ�գ��Ҳ���ʾ��Ϊ�����㷨Ԥ����
	 * @xiaofangdyd
	 * */
	public void initBoard(){
		arr = new Chess[Param.rows + 2][Param.cols + 2];
		Random random = new Random();
		for (int i = 1; i <= 20; i++){
			int count = 0; 
			while(count < 4){ //����ÿ��ͼƬӦ�ó��ִ�����һ��Ϊż��
				int x = random.nextInt(Param.rows)+1; //default: 1 - 8
				int y = random.nextInt(Param.cols)+1; //default: 1 - 10
				
				if (arr[x][y] == null){
					arr[x][y] = new Chess(i);
					count++;
				}
			}
		}
		/* ��ʼ������ΧһȦ����ʾ��ͼƬ���� */
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
		g.drawImage(Param.iBackground, 0, 0, this); //���ñ���ͼƬ
		g.setColor(Color.red);
		g.setFont(new Font("����",Font.BOLD,36));
		
		if (Param.gameStatus == 0) {
			g.drawString("��Ϸ��δ��ʼ��", 400, 300);
		}else if(Param.gameStatus == 2) {
			g.drawString("��Ϸ��ͣ��", 400, 300);
		}else if(Param.gameStatus == 3) {
			g.drawString("��Ϸ������", 400, 300);
		}else{
			/* ������������ϷͼƬ���� */
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
			/* �ж����click��һ���㲻Ϊ�� */
			/* Ϊѡ�е�ͼƬ����ɫ�ڿ� */
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
	
	/* Ϊָ���������ָ����ɫ���ڿ� */
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
		
		/* ���������Ƿ��������� */
		if (Param.gameStatus != 1) {
			return;
		}
		if (e.getModifiers() != InputEvent.BUTTON1_MASK) {
			return;
		}
		
		/* ������������ */
		int x = e.getX();
		int y = e.getY();
		
		int j = (x - Param.marginWidth) / Param.chessWidth; //��������ͼ��Ϊ��j��
		int i = (y - Param.marginHeight) / Param.chessHeight; //��������ͼ��Ϊ��i��
		//int rowX = j * Param.chessWidth + Param.marginWidth; //�����ͼ�����ʼ�������
		//int rowY = i * Param.chessHeight + Param.marginHeight; //�����ͼ�����ʼ�߶�����
		
		if (arr[i][j].getStatus() == 0) { //�������ͼ��Ϊ�������ģ�����Ӧ
			return;
		}
		
		/* �ж����ѡ�������Ƿ�����Ϸ�� */
		if ((x >= Param.marginWidth + Param.chessWidth 
				&& x <= Param.marginWidth + Param.cols * Param.chessWidth + Param.chessWidth)
				&& (y >= Param.marginHeight + Param.chessHeight 
				&& y <= Param.marginHeight + Param.rows * Param.chessHeight + Param.chessHeight)) {
			
			/* ����ʱΪ��һ�ε�������ε���ķ�����ͬ
			 * ����Ǵ˴ε������ */
			if (firstPoint == null || (firstPoint.x == i && firstPoint.y == j)) {
				firstPoint = new Point(i, j);
				drawMyRect(firstPoint,Color.blue);
				return;
			}
			
			/* ʣ�����Ϊ���ε���Ĳ�ͬ�ķ��� */
			secondPoint = new Point(i, j);
			drawMyRect(secondPoint,Color.red);
			
			/* �ж����ε����ͼƬ�ܷ����� 
			 * �ȵ���Core�е��ж��㷨
			 * �������������عյ��List<Point> 
			 * �����ܣ�����null */
			List<Point> list = Core.checkLinked(arr, firstPoint, secondPoint);
			if (list == null) { //���������򽫵ڶ�������Ϊ��һ�ε���ĵ㣬ִ���ػ����
				firstPoint = secondPoint;
				drawMyRect(firstPoint,Color.blue);
				mainFrame.repaint();
				return;
			}
			
			/* ��������ͨ��ִ�����в��� 
			 * 1. ����ͼ��״ֵ̬��0
			 * 2. ��ʼ������
			 * 3. ����������
			 * 4. ��ʾ�÷�*/
			arr[firstPoint.x][firstPoint.y].setStatus(0);
			arr[secondPoint.x][secondPoint.y].setStatus(0);
			
			firstPoint = null;
			secondPoint = null;
			
			drawLinkedLine(list); //����������
			
			mainFrame.repaint();
			Param.score += 10;
			mainFrame.setScore(Param.score);
			Param.chessCount -= 2;
			
			if (Param.chessCount == 0) {
				mainFrame.StopTimer();
				JOptionPane.showMessageDialog(null, "��ʼ��һ��", "��Ϣ", JOptionPane.PLAIN_MESSAGE);
				Param.gameStatus = 4;
				mainFrame.restartGame();
				mainFrame.repaint();
			}
		}
	}
	/* ���������� */
	private void drawLinkedLine(List<Point> list) {
		// TODO Auto-generated method stub
		Graphics2D g2d = (Graphics2D) this.getGraphics();
		g2d.setColor(Color.blue);
		g2d.setStroke(stroke);
		if (list.size() == 2) { //�ж��Ƿ�Ϊ0�յ����
			Point a = list.get(0);
			Point b = list.get(1);
			int ax = a.y * Param.chessWidth + Param.marginWidth + Param.chessWidth / 2;
			int ay = a.x * Param.chessHeight + Param.marginHeight + Param.chessHeight / 2;
			int bx = b.y * Param.chessWidth + Param.marginWidth + Param.chessWidth / 2;
			int by = b.x * Param.chessHeight + Param.marginHeight + Param.chessHeight / 2;
			g2d.drawLine(ax, ay, bx, by);
		}
		if (list.size() == 3) { //�ж��Ƿ�Ϊ1�յ����
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
		if (list.size() == 4) { //�ж��Ƿ�Ϊ2�յ����
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
