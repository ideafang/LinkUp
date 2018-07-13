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

	JMenuItem menuItemData = new JMenuItem("游戏数据");
	JMenuItem menuItemJoke = new JMenuItem("不要点");
	
	MainFrame mainFrame;
	public MenuBar(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.init();
	}
	
	private void init() {
		JMenu menuSet = new JMenu("设置");
		menuSet.add(menuItemData);
		//menuSet.addSeparator();
		
		JMenu menuAbout = new JMenu("关于");
		menuAbout.add(menuItemJoke);
		
		this.add(menuSet);
		this.add(menuAbout);
		
		/* 游戏参数修改响应 */
		menuItemData.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new DataJDialog(mainFrame);
			}
			
		});
		
		/* 关于菜单响应 */
		menuItemJoke.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null,"Xiaofangdyd NB!");
			}
		});
	}
}
