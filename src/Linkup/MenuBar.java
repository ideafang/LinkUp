package Linkup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import Dialog.ParamJDialog;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {

	JMenuItem menuItemParam = new JMenuItem("��Ϸ����");
	JMenuItem menuItemJoke = new JMenuItem("��Ҫ��");
	
	MainFrame mainFrame;
	public MenuBar(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.init();
	}
	
	private void init() {
		JMenu menuSet = new JMenu("����");
		menuSet.add(menuItemParam);
		//menuSet.addSeparator();
		
		JMenu menuAbout = new JMenu("����");
		menuAbout.add(menuItemJoke);
		
		this.add(menuSet);
		this.add(menuAbout);
		
		/* ��Ϸ�����޸���Ӧ */
		menuItemParam.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ParamJDialog(mainFrame);
			}
			
		});
		
		/* ���ڲ˵���Ӧ */
		menuItemJoke.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null,"������NB!");
			}
		});
	}
}
