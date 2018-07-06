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
	int prompt = Param.promptCountAll; //��ʾ����
	int refresh = Param.refreshCountAll; //ˢ�´���
	
	JLabel labelStart = new LinkedJLabel("��ʼ");
	JLabel labelPrompt = new LinkedJLabel("��ʾ(" + prompt + ")");
	JLabel labelRefresh = new LinkedJLabel("ˢ��(" + refresh + ")");
	JLabel labelScore = new JLabel("��ǰ�ɼ���");
	JLabel labelRestart = new LinkedJLabel("���¿�ʼ");
	
	TimerJProgressbar timerJProgressbar = new TimerJProgressbar(this);
	
	MenuBar menuBar = new MenuBar(this);
	
	public MainFrame() {
		init();
		
		this.setJMenuBar(menuBar); //��Ӳ˵���
		this.setTitle("������");
		this.setSize(1015,690);
		this.setLocationRelativeTo(null); //�Զ����䵽��Ļ�м�
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //���ùر�ģʽ
		this.setResizable(false);  //�̶����ڴ�С
		this.setVisible(true); //���ÿɼ�
	}
	
	public void init() {
		this.setLayout(null);
		labelStart.setFont(new Font("����", Font.BOLD, 16));
		labelPrompt.setFont(new Font("����", Font.BOLD, 16));
		labelRefresh.setFont(new Font("����", Font.BOLD, 16));
		labelScore.setFont(new Font("����", Font.BOLD, 16));
		labelRestart.setFont(new Font("����", Font.BOLD, 16));
		
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
		
		/* ��ʼ��Ϸ */
		labelStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String text = labelStart.getText();
				if ("��ʼ".equals(text)) {
					Param.gameStatus = 1;
					labelStart.setText("��ͣ");
					
					timerJProgressbar.start();
					repaint();
				}else if ("��ͣ".equals(text)) {
					Param.gameStatus = 2;
					labelStart.setText("����");
					
					timerJProgressbar.stop();
					repaint();
				}else if ("����".equals(text)) {
					Param.gameStatus = 1;
					labelStart.setText("��ͣ");
					
					timerJProgressbar.start();
					repaint();
				}
			}
		});
		
		/* ˢ�� */
		labelRefresh.addMouseListener(new MouseAdapter() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void mousePressed(MouseEvent e) {
				if (Param.gameStatus != 1) {
					JOptionPane.showMessageDialog(MainFrame.this, "�����Ƿ������ȿ�ʼ��Ϸ");
					return;
				}
				if (e.getModifiers() != InputEvent.BUTTON1_MASK) {
					return;
				}
				
				if (refresh > 0) {
					refresh--;
					Core.refreshArr(mapPanel.arr);
					repaint();
					labelRefresh.setText("ˢ��(" + refresh + ")");
				}else {
					JOptionPane.showMessageDialog(MainFrame.this, "ˢ�´��������꣡");
					return;
				}
			}

		}); 
		
		/* ��ʾ 
		 * ����Core�е�promptArrʵ��
		 * ���ɹ�����������ͼ���list<Point>
		 * ��ʧ�ܣ�����null��ִ��ˢ��ͼ����� */
		labelPrompt.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mousePressed(MouseEvent e) {
				if (Param.gameStatus != 1) {
					JOptionPane.showMessageDialog(MainFrame.this, "�����Ƿ������ȿ�ʼ��Ϸ");
					return;
				}
				if (e.getModifiers() != InputEvent.BUTTON1_MASK) {
					return;
				}
				
				List<Point> list = Core.promptArr(mapPanel.arr);
				if(list != null) {
					if (prompt > 0) {
						prompt--;
						labelPrompt.setText("��ʾ(" + prompt + ")");
						mapPanel.drawMyRect(list.get(0), Color.yellow);
						mapPanel.drawMyRect(list.get(1), Color.yellow);
					}else {
						JOptionPane.showMessageDialog(MainFrame.this, "��ʾ���������꣡");
						return;
					}
				}else {
					JOptionPane.showMessageDialog(MainFrame.this, "�޿����ӵ�ͼ�꣬��ˢ�½���");
				}
			}

		});
		
		/* ���¿�ʼ */
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
		labelPrompt.setText("��ʾ(" + prompt + ")");
		labelRefresh.setText("ˢ��(" + refresh + ")");
		labelScore.setText("��ǰ�ɼ���" + Param.score);
		mapPanel.initBoard();
		labelStart.setText("��ͣ");
		timerJProgressbar.start();
		repaint();
	}
	/* �ػ���棬��MapPanel���� */
	public void PaintFrame() {
		repaint();
	}
	
	/* ��ͣ����ʱ����MapPanel���� */
	public void StopTimer() {
		timerJProgressbar.stop();
	}
	
	/* ���óɼ� */
	public int setScore(int score) {
		// TODO Auto-generated method stub
		labelScore.setText("��ǰ�ɼ���" + score);
		return score;
	}
	

}
