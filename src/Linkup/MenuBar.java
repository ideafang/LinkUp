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

	JMenuItem menuItemParam = new JMenuItem("游戏参数");
	JMenuItem menuItemJoke = new JMenuItem("不要点");
	
	MainFrame mainFrame;
	public MenuBar(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.init();
	}
	
	private void init() {
		JMenu menuSet = new JMenu("设置");
		menuSet.add(menuItemParam);
		//menuSet.addSeparator();
		
		JMenu menuAbout = new JMenu("关于");
		menuAbout.add(menuItemJoke);
		
		this.add(menuSet);
		this.add(menuAbout);
		
		/* 游戏参数修改响应 */
		menuItemParam.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ParamJDialog(mainFrame);
			}
			
		});
		
		/* 关于菜单响应 */
		menuItemJoke.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null,"方宏林NB!");
			}
		});
	}
}
