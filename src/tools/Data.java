package tools;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Data {
	/* ��ϷͼƬ���������������� */
	public static int rows = 8;
	public static int cols = 10;
	
	/* ͼƬ�ĳ������� */
	public static int BlockWidth = 60;
	public static int BlockHeight = 60;
	
	/* ���̵��߿�ľ��� */
	public static int marginWidth = 200 - BlockWidth;
	public static int marginHeight = 100 - BlockHeight;
	
	/* mainFrame�ı���ͼƬ
	 * ����ʱҪע���������Ϊ1000*625 */
	public static Image iBackground = new ImageIcon("image/bg.png").getImage();
	
	/* ��Ϸ����ͼ */
	public static Image[] iBlock = new Image[20];
	public static Image[] iconBlock = new Image[20];
	static {
		for (int i = 0; i < iBlock.length; i++) {
			iBlock[i] = new ImageIcon("image/"+(i+1)+".png").getImage();
			iconBlock[i] = new ImageIcon("image/"+(i+1)+".png").getImage();
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
	public static int BlockCount = 80; //ͼ��ĸ���
	public static int timeCount = 200; //����ʱ
	public static int promptCountAll = 3; //��ʾ����
	public static int refreshCountAll = 3; //���´��Ҵ���
	
	public static int score =  0;
	public static String name;
}
