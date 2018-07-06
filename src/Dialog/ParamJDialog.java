package Dialog;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Linkup.MainFrame;
import tools.Param;

@SuppressWarnings("serial")
public class ParamJDialog extends JDialog {
	private JTextField time = new JTextField(Param.timeCount+"");
	private JTextField refresh = new JTextField(Param.refreshCountAll+"");
	private JTextField prompt = new JTextField(Param.promptCountAll+"");
	private JButton buttonSure = new JButton("确定");
	private JButton buttonCancel = new JButton("取消");
	MainFrame frame;

	public ParamJDialog(MainFrame frame) {
		super(frame);
		this.frame = frame;
		
		init();
		setTitle("游戏参数设置");
		this.setSize(new Dimension(430, 210));
		this.setLocationRelativeTo(frame);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setModal(true);
		this.setVisible(true);
	}
	
	/* 构建图形界面 */
	public void init() {
		this.setLayout(null);
		JLabel labelTime = new JLabel("游戏时间：");
		JLabel labelRefresh = new JLabel("刷新次数：");
		JLabel labelPrompt = new JLabel("提示次数：");
		JLabel labelTimeMsg = new JLabel("秒(整数)，范围：[10~600]");
		JLabel labelRefreshMsg = new JLabel("整数，范围：[3~20]");
		JLabel labelPromptMsg = new JLabel("整数，范围：[3~20]") ;
		labelTimeMsg.setForeground(Color.red);
		labelRefreshMsg.setForeground(Color.red);
		labelPromptMsg.setForeground(Color.red);
		labelTime.setBounds(60, 20, 70, 25);
		time.setBounds(121, 20, 60, 25);
		labelTimeMsg.setBounds(182, 20, 200, 25);
		
		labelRefresh.setBounds(60, 60, 70, 25);
		refresh.setBounds(121, 60, 60, 25);
		labelRefreshMsg.setBounds(182, 60, 200, 25);

		labelPrompt.setBounds(60, 100, 70, 25);
		prompt.setBounds(121, 100, 60, 25);
		labelPromptMsg.setBounds(182, 100, 200, 25);

		buttonSure.setBounds(80, 140, 90, 25);
		buttonCancel.setBounds(260, 140, 90, 25);

		this.add(labelTime);
		this.add(time);
		this.add(labelTimeMsg);

		this.add(labelRefresh);
		this.add(refresh);
		this.add(labelRefreshMsg);

		this.add(labelPrompt);
		this.add(prompt);
		this.add(labelPromptMsg);

		this.add(buttonSure);
		this.add(buttonCancel);
		initEvent();
	}
	
	/* 响应动作事件 */
	public void initEvent() {
		
		/* 确定按键事件 */
		buttonSure.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String timeCount = time.getText();
				String refreshCount = refresh.getText();
				String PromptCount = prompt.getText();
				int timeChange = 0;
				int refreshChange = 0;
				int promptChange = 0;
				/* 获取游戏时间 */
				try {
					timeChange = Integer.parseInt(timeCount);
					if (!(timeChange >= 10 && timeChange <= 600)) { //判断数据是否合理
						JOptionPane.showMessageDialog(null, "时间范围不合法，范围：[10~600]", "错误", JOptionPane.ERROR_MESSAGE);
					}
				}catch(Exception e2) {
					JOptionPane.showMessageDialog(null, "时间为整数，范围：[10~600]", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				/* 获取刷新次数 */
				try {
					refreshChange = Integer.parseInt(refreshCount);
					if (!(refreshChange >= 3 && refreshChange <= 20)) {
						JOptionPane.showMessageDialog(null, "刷新范围不合法，范围：[3~20]", "错误", JOptionPane.ERROR_MESSAGE);
					}
				}catch(Exception e2) {
					JOptionPane.showMessageDialog(null, "刷新次数为整数，范围：[3~20]", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				/* 获取提示次数 */
				try {
					promptChange = Integer.parseInt(PromptCount);
					if (!(promptChange >= 3 && promptChange <= 20)) {
						JOptionPane.showMessageDialog(null, "提示范围不合法，范围：[3~20]", "错误", JOptionPane.ERROR_MESSAGE);
					}
				}catch(Exception e2) {
					JOptionPane.showMessageDialog(null, "提示次数为整数，范围：[3~20]", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				dispose(); //销毁对话框
				Param.timeCount = timeChange;
				Param.refreshCountAll = refreshChange;
				Param.promptCountAll = promptChange;
				frame.restartGame();
			}
		});
		
		/* 销毁对话框 */
		buttonCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
	}
}
