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
	private JButton buttonSure = new JButton("ȷ��");
	private JButton buttonCancel = new JButton("ȡ��");
	MainFrame frame;

	public ParamJDialog(MainFrame frame) {
		super(frame);
		this.frame = frame;
		
		init();
		setTitle("��Ϸ��������");
		this.setSize(new Dimension(430, 210));
		this.setLocationRelativeTo(frame);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setModal(true);
		this.setVisible(true);
	}
	
	/* ����ͼ�ν��� */
	public void init() {
		this.setLayout(null);
		JLabel labelTime = new JLabel("��Ϸʱ�䣺");
		JLabel labelRefresh = new JLabel("ˢ�´�����");
		JLabel labelPrompt = new JLabel("��ʾ������");
		JLabel labelTimeMsg = new JLabel("��(����)����Χ��[10~600]");
		JLabel labelRefreshMsg = new JLabel("��������Χ��[3~20]");
		JLabel labelPromptMsg = new JLabel("��������Χ��[3~20]") ;
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
	
	/* ��Ӧ�����¼� */
	public void initEvent() {
		
		/* ȷ�������¼� */
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
				/* ��ȡ��Ϸʱ�� */
				try {
					timeChange = Integer.parseInt(timeCount);
					if (!(timeChange >= 10 && timeChange <= 600)) { //�ж������Ƿ����
						JOptionPane.showMessageDialog(null, "ʱ�䷶Χ���Ϸ�����Χ��[10~600]", "����", JOptionPane.ERROR_MESSAGE);
					}
				}catch(Exception e2) {
					JOptionPane.showMessageDialog(null, "ʱ��Ϊ��������Χ��[10~600]", "����", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				/* ��ȡˢ�´��� */
				try {
					refreshChange = Integer.parseInt(refreshCount);
					if (!(refreshChange >= 3 && refreshChange <= 20)) {
						JOptionPane.showMessageDialog(null, "ˢ�·�Χ���Ϸ�����Χ��[3~20]", "����", JOptionPane.ERROR_MESSAGE);
					}
				}catch(Exception e2) {
					JOptionPane.showMessageDialog(null, "ˢ�´���Ϊ��������Χ��[3~20]", "����", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				/* ��ȡ��ʾ���� */
				try {
					promptChange = Integer.parseInt(PromptCount);
					if (!(promptChange >= 3 && promptChange <= 20)) {
						JOptionPane.showMessageDialog(null, "��ʾ��Χ���Ϸ�����Χ��[3~20]", "����", JOptionPane.ERROR_MESSAGE);
					}
				}catch(Exception e2) {
					JOptionPane.showMessageDialog(null, "��ʾ����Ϊ��������Χ��[3~20]", "����", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				dispose(); //���ٶԻ���
				Param.timeCount = timeChange;
				Param.refreshCountAll = refreshChange;
				Param.promptCountAll = promptChange;
				frame.restartGame();
			}
		});
		
		/* ���ٶԻ��� */
		buttonCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
	}
}
