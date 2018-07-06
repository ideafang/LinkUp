package Linkup;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import tools.Core;
import tools.Param;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	MapPanel mapPanel = new MapPanel(this);
	int prompt = Param.promptCountAll; //提示次数
	int refresh = Param.refreshCountAll; //刷新次数
	
	JLabel labelStart = new LinkedJLabel("开始");
	JLabel labelPrompt = new LinkedJLabel("提示(" + prompt + ")");
	JLabel labelRefresh = new LinkedJLabel("刷新(" + refresh + ")");
	JLabel labelScore = new JLabel("当前成绩：");
	JLabel labelRestart = new LinkedJLabel("重新开始");
	
	TimerJProgressbar timerJProgressbar = new TimerJProgressbar(this);
	
	MenuBar menuBar = new MenuBar(this);
	
	public MainFrame() {
		init();
		
		this.setJMenuBar(menuBar); //添加菜单栏
		this.setTitle("连连看");
		this.setSize(1015,690);
		this.setLocationRelativeTo(null); //自动适配到屏幕中间
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //设置关闭模式
		this.setResizable(false);  //固定窗口大小
		this.setVisible(true); //设置可见
	}
	
	public void init() {
		this.setLayout(null);
		labelStart.setFont(new Font("宋体", Font.BOLD, 16));
		labelPrompt.setFont(new Font("宋体", Font.BOLD, 16));
		labelRefresh.setFont(new Font("宋体", Font.BOLD, 16));
		labelScore.setFont(new Font("宋体", Font.BOLD, 16));
		labelRestart.setFont(new Font("宋体", Font.BOLD, 16));
		
		labelStart.setBounds(20, 20, 80, 25);
		labelRestart.setBounds(90, 20, 100, 25);
		labelPrompt.setBounds(200, 20, 80, 25);
		labelRefresh.setBounds(300, 20, 80, 25);
		labelScore.setBounds(400, 20, 150, 25);
		timerJProgressbar.setBounds(650, 20, 200, 23);
		
		this.add(labelStart);
		this.add(labelRestart);
		this.add(labelPrompt);
		this.add(labelRefresh);
		this.add(labelScore);
		this.add(timerJProgressbar);
		
		mapPanel.setBounds(0, 0, 1000, 625);
		this.add(mapPanel);
		
		/* 开始游戏 */
		labelStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String text = labelStart.getText();
				if ("开始".equals(text)) {
					Param.gameStatus = 1;
					labelStart.setText("暂停");
					
					timerJProgressbar.start();
					repaint();
				}else if ("暂停".equals(text)) {
					Param.gameStatus = 2;
					labelStart.setText("继续");
					
					timerJProgressbar.stop();
					repaint();
				}else if ("继续".equals(text)) {
					Param.gameStatus = 1;
					labelStart.setText("暂停");
					
					timerJProgressbar.start();
					repaint();
				}
			}
		});
		
		/* 刷新 */
		labelRefresh.addMouseListener(new MouseAdapter() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void mousePressed(MouseEvent e) {
				if (Param.gameStatus != 1) {
					JOptionPane.showMessageDialog(MainFrame.this, "操作非法！请先开始游戏");
					return;
				}
				if (e.getModifiers() != InputEvent.BUTTON1_MASK) {
					return;
				}
				
				if (refresh > 0) {
					refresh--;
					Core.refreshArr(mapPanel.arr);
					repaint();
					labelRefresh.setText("刷新(" + refresh + ")");
				}else {
					JOptionPane.showMessageDialog(MainFrame.this, "刷新次数已用完！");
					return;
				}
			}

		}); 
		
		/* 提示 
		 * 调用Core中的promptArr实现
		 * 若成功，返回两个图标的list<Point>
		 * 若失败，返回null，执行刷新图标操作 */
		labelPrompt.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mousePressed(MouseEvent e) {
				if (Param.gameStatus != 1) {
					JOptionPane.showMessageDialog(MainFrame.this, "操作非法！请先开始游戏");
					return;
				}
				if (e.getModifiers() != InputEvent.BUTTON1_MASK) {
					return;
				}
				
				List<Point> list = Core.promptArr(mapPanel.arr);
				if(list != null) {
					if (prompt > 0) {
						prompt--;
						labelPrompt.setText("提示(" + prompt + ")");
						mapPanel.drawMyRect(list.get(0), Color.yellow);
						mapPanel.drawMyRect(list.get(1), Color.yellow);
					}else {
						JOptionPane.showMessageDialog(MainFrame.this, "提示次数已用完！");
						return;
					}
				}else {
					JOptionPane.showMessageDialog(MainFrame.this, "无可连接的图标，已刷新界面");
				}
			}

		});
		
		/* 重新开始 */
		labelRestart.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			public void mousePressed(MouseEvent e) {
				if (e.getModifiers() != InputEvent.BUTTON1_MASK) {
					return;
				}
				restartGame();
			}
		});
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainFrame();
	}
	
	public void restartGame() {
		if (Param.gameStatus == 1) {
			Param.score = 0;
			prompt = Param.promptCountAll;
			refresh = Param.refreshCountAll;
		}else if(Param.gameStatus == 4) {
			Param.gameStatus = 1;
			prompt++;
			refresh++;
		}
		Param.chessCount = 80;
		timerJProgressbar.reset();
		labelPrompt.setText("提示(" + prompt + ")");
		labelRefresh.setText("刷新(" + refresh + ")");
		labelScore.setText("当前成绩：" + Param.score);
		mapPanel.initBoard();
		labelStart.setText("暂停");
		timerJProgressbar.start();
		repaint();
	}
	/* 重绘界面，供MapPanel调用 */
	public void PaintFrame() {
		repaint();
	}
	
	/* 暂停倒计时，供MapPanel调用 */
	public void StopTimer() {
		timerJProgressbar.stop();
	}
	
	/* 设置成绩 */
	public int setScore(int score) {
		// TODO Auto-generated method stub
		labelScore.setText("当前成绩：" + score);
		return score;
	}
	

}
