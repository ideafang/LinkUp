package Linkup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import Dialog.DataJDialog;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {

	JMenuItem menuItemData = new JMenuItem("��Ϸ����");
	JMenuItem menuItemJoke = new JMenuItem("��Ҫ��");
	
	MainFrame mainFrame;
	public MenuBar(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.init();
	}
	
	private void init() {
		JMenu menuSet = new JMenu("����");
		menuSet.add(menuItemData);
		//menuSet.addSeparator();
		
		JMenu menuAbout = new JMenu("����");
		menuAbout.add(menuItemJoke);
		
		this.add(menuSet);
		this.add(menuAbout);
		
		/* ��Ϸ�����޸���Ӧ */
		menuItemData.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new DataJDialog(mainFrame);
			}
			
		});
		
		/* ���ڲ˵���Ӧ */
		menuItemJoke.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null,"Xiaofangdyd NB!");
			}
		});
	}
}
