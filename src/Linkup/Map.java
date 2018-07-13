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

import tools.Algorithm;
import tools.Data;

@SuppressWarnings("serial")
public class Map extends JPanel implements MouseListener {
	Block[][] arr = null; //��������
	Stroke stroke = new BasicStroke(3.0f);//���û���
	
	/* �������ѡ�еĵ��λ�� */
	Point firstPoint;
	Point secondPoint;
	MainFrame mainFrame;
	
	/* ��mainFrame��Ϊ�������� */
	public Map(MainFrame mainFrame){
		this.mainFrame = mainFrame;
		initBoard(); //��ʼ������
		
		this.addMouseListener(this); //������궯��
		this.setCursor(new Cursor(Cursor.HAND_CURSOR)); //�������ͼ��
	}
	
	/* ��ʼ���������� */
	/*
	 * ���޸�ͼƬ��������Ҫ��������i��count��while�еı߽磬�Լ�Data��BlockCount����
	 * ����Data�����޸����̴�С����ҪΪͼƬ���ࣨĬ��Ϊ20���������������޸ı���count��while�еı߽�
	 * �����������ΧһȦΪ�գ��Ҳ���ʾ��Ϊ�����㷨Ԥ����
	 * @xiaofangdyd
	 * */
	public void initBoard(){
		arr = new Block[Data.rows + 2][Data.cols + 2];
		Random random = new Random();
		for (int i = 1; i <= 20; i++){
			int count = 0; 
			while(count < 4){ //����ÿ��ͼƬӦ�ó��ִ�����һ��Ϊż��
				int x = random.nextInt(Data.rows)+1; //default: 1 - 8
				int y = random.nextInt(Data.cols)+1; //default: 1 - 10
				
				if (arr[x][y] == null){
					arr[x][y] = new Block(i);
					count++;
				}
			}
		}
		/* ��ʼ������ΧһȦ����ʾ��ͼƬ���� */
		for (int i = 0; i < arr[0].length; i++){
			arr[0][i] = new Block(0);
			arr[arr.length - 1][i] = new Block(0);
		}
		for (int i = 0; i < arr.length; i++){
			arr[i][0] = new Block(0);
			arr[i][arr[0].length - 1] = new Block(0);
		}
	}
	
	public void paint(Graphics g){
		super.paint(g);
		g.drawImage(Data.iBackground, 0, 0, this); //���ñ���ͼƬ
		g.setColor(Color.red);
		g.setFont(new Font("����",Font.BOLD,36));
		
		if (Data.gameStatus == 0) {
			g.drawString("��Ϸ��δ��ʼ��", 400, 300);
		}else if(Data.gameStatus == 2) {
			g.drawString("��Ϸ��ͣ��", 400, 300);
		}else if(Data.gameStatus == 3) {
			g.drawString("��Ϸ������", 400, 300);
		}else{
			/* ������������ϷͼƬ���� */
			for(int i = 1; i < arr.length; i++) {
				for (int j = 1; j < arr[0].length; j++) {
					if (arr[i][j].getStatus() != 0) {
						int x = j * Data.BlockWidth + Data.marginWidth;
						int y = i * Data.BlockHeight + Data.marginHeight;
						g.drawImage(Data.iconBlock[arr[i][j].getStatus() - 1], x, y, this);
						g.setColor(Color.green);
						g.drawRect(x, y, Data.BlockWidth, Data.BlockHeight);
					}
				}
			}
			/* �ж����click��һ���㲻Ϊ�� */
			/* Ϊѡ�е�ͼƬ����ɫ�ڿ� */
			if (firstPoint != null) {
				Graphics2D g2d = (Graphics2D) g;
				g2d.setStroke(stroke);
				g2d.setColor(Color.blue);
				int rowX = firstPoint.y * Data.BlockWidth + Data.marginWidth;
				int rowY = firstPoint.x * Data.BlockHeight + Data.marginHeight;
				g2d.drawRect(rowX + 2, rowY + 2, Data.BlockWidth - 4, Data.BlockHeight - 4);
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
		int rowX = p.y * Data.BlockWidth + Data.marginWidth;
		int rowY = p.x * Data.BlockHeight + Data.marginHeight;
		g2d.drawRect(rowX + 2, rowY + 2, Data.BlockWidth - 4, Data.BlockHeight - 4);
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
		if (Data.gameStatus != 1) {
			return;
		}
		if (e.getModifiers() != InputEvent.BUTTON1_MASK) {
			return;
		}
		
		/* ������������ */
		int x = e.getX();
		int y = e.getY();
		
		int j = (x - Data.marginWidth) / Data.BlockWidth; //��������ͼ��Ϊ��j��
		int i = (y - Data.marginHeight) / Data.BlockHeight; //��������ͼ��Ϊ��i��
		//int rowX = j * Data.BlockWidth + Data.marginWidth; //�����ͼ�����ʼ�������
		//int rowY = i * Data.BlockHeight + Data.marginHeight; //�����ͼ�����ʼ�߶�����
		
		if (arr[i][j].getStatus() == 0) { //�������ͼ��Ϊ�������ģ�����Ӧ
			return;
		}
		
		/* �ж����ѡ�������Ƿ�����Ϸ�� */
		if ((x >= Data.marginWidth + Data.BlockWidth 
				&& x <= Data.marginWidth + Data.cols * Data.BlockWidth + Data.BlockWidth)
				&& (y >= Data.marginHeight + Data.BlockHeight 
				&& y <= Data.marginHeight + Data.rows * Data.BlockHeight + Data.BlockHeight)) {
			
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
			 * �ȵ���Algorithm�е��ж��㷨
			 * �������������عյ��List<Point> 
			 * �����ܣ�����null */
			List<Point> list = Algorithm.checkLinked(arr, firstPoint, secondPoint);
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
			Data.score += 10;
			mainFrame.setScore(Data.score);
			Data.BlockCount -= 2;
			
			if (Data.BlockCount == 0) {
				mainFrame.StopTimer();
				JOptionPane.showMessageDialog(null, "��ʼ��һ��", "��Ϣ", JOptionPane.PLAIN_MESSAGE);
				Data.gameStatus = 4;
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
			int ax = a.y * Data.BlockWidth + Data.marginWidth + Data.BlockWidth / 2;
			int ay = a.x * Data.BlockHeight + Data.marginHeight + Data.BlockHeight / 2;
			int bx = b.y * Data.BlockWidth + Data.marginWidth + Data.BlockWidth / 2;
			int by = b.x * Data.BlockHeight + Data.marginHeight + Data.BlockHeight / 2;
			g2d.drawLine(ax, ay, bx, by);
		}
		if (list.size() == 3) { //�ж��Ƿ�Ϊ1�յ����
			Point a = list.get(0);
			Point b = list.get(1);
			Point c = list.get(2);
			int ax = a.y * Data.BlockWidth + Data.marginWidth + Data.BlockWidth / 2;
			int ay = a.x * Data.BlockHeight + Data.marginHeight + Data.BlockHeight / 2;
			int bx = b.y * Data.BlockWidth + Data.marginWidth + Data.BlockWidth / 2;
			int by = b.x * Data.BlockHeight + Data.marginHeight + Data.BlockHeight / 2;
			int cx = c.y * Data.BlockWidth + Data.marginWidth + Data.BlockWidth / 2;
			int cy = c.x * Data.BlockHeight + Data.marginHeight + Data.BlockHeight / 2;
			g2d.drawLine(ax, ay, cx, cy);
			g2d.drawLine(cx, cy, bx, by);
		}
		if (list.size() == 4) { //�ж��Ƿ�Ϊ2�յ����
			Point a = list.get(0);
			Point b = list.get(1);
			Point c = list.get(2);
			Point d = list.get(3);
			int ax = a.y * Data.BlockWidth + Data.marginWidth + Data.BlockWidth / 2;
			int ay = a.x * Data.BlockHeight + Data.marginHeight + Data.BlockHeight / 2;
			int bx = b.y * Data.BlockWidth + Data.marginWidth + Data.BlockWidth / 2;
			int by = b.x * Data.BlockHeight + Data.marginHeight + Data.BlockHeight / 2;
			int cx = c.y * Data.BlockWidth + Data.marginWidth + Data.BlockWidth / 2;
			int cy = c.x * Data.BlockHeight + Data.marginHeight + Data.BlockHeight / 2;
			int dx = d.y * Data.BlockWidth + Data.marginWidth + Data.BlockWidth / 2;
			int dy = d.x * Data.BlockHeight + Data.marginHeight + Data.BlockHeight / 2;
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
