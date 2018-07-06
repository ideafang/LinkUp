package tools;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Param {
	/* 游戏图片的总行数和总列数 */
	public static int rows = 8;
	public static int cols = 10;
	
	/* 图片的长宽像素 */
	public static int chessWidth = 60;
	public static int chessHeight = 60;
	
	/* 棋盘到边框的距离 */
	public static int marginWidth = 200 - chessWidth;
	public static int marginHeight = 100 - chessHeight;
	
	/* mainFrame的背景图片
	 * 更换时要注意调整像素为1000*625 */
	public static Image iBackground = new ImageIcon("image/bg.png").getImage();
	
	/* 游戏的贴图 */
	public static Image[] iChess = new Image[20];
	public static Image[] iconChess = new Image[20];
	static {
		for (int i = 0; i < iChess.length; i++) {
			iChess[i] = new ImageIcon("image/chess/"+(i+1)+".png").getImage();
			iconChess[i] = new ImageIcon("image/chess/"+(i+1)+".png").getImage();
		}
	}
	
	public static Image About;
	
	/* 游戏状态
	 * 0：游戏未开始
	 * 1：正在游戏
	 * 2：游戏暂停
	 * 3：游戏结束
	 * 4: 下一关
	 * */
	public static int gameStatus = 0; 
	public static int chessCount = 80; //图标的个数
	public static int timeCount = 200; //倒计时
	public static int promptCountAll = 3; //提示次数
	public static int refreshCountAll = 3; //重新打乱次数
	
	public static int score =  0;
	public static String name;
}
