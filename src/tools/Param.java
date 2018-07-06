package tools;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Param {
	/* ��ϷͼƬ���������������� */
	public static int rows = 8;
	public static int cols = 10;
	
	/* ͼƬ�ĳ������� */
	public static int chessWidth = 60;
	public static int chessHeight = 60;
	
	/* ���̵��߿�ľ��� */
	public static int marginWidth = 200 - chessWidth;
	public static int marginHeight = 100 - chessHeight;
	
	/* mainFrame�ı���ͼƬ
	 * ����ʱҪע���������Ϊ1000*625 */
	public static Image iBackground = new ImageIcon("image/bg.png").getImage();
	
	/* ��Ϸ����ͼ */
	public static Image[] iChess = new Image[20];
	public static Image[] iconChess = new Image[20];
	static {
		for (int i = 0; i < iChess.length; i++) {
			iChess[i] = new ImageIcon("image/chess/"+(i+1)+".png").getImage();
			iconChess[i] = new ImageIcon("image/chess/"+(i+1)+".png").getImage();
		}
	}
	
	public static Image About;
	
	/* ��Ϸ״̬
	 * 0����Ϸδ��ʼ
	 * 1��������Ϸ
	 * 2����Ϸ��ͣ
	 * 3����Ϸ����
	 * 4: ��һ��
	 * */
	public static int gameStatus = 0; 
	public static int chessCount = 80; //ͼ��ĸ���
	public static int timeCount = 200; //����ʱ
	public static int promptCountAll = 3; //��ʾ����
	public static int refreshCountAll = 3; //���´��Ҵ���
	
	public static int score =  0;
	public static String name;
}
