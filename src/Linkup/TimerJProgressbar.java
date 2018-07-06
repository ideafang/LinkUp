package Linkup;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JProgressBar;
import javax.swing.Timer;

import tools.Param;

@SuppressWarnings("serial")
public class TimerJProgressbar extends JProgressBar implements ActionListener {

	int count = Param.timeCount;
	Timer timer = new Timer(1000, this);
	MainFrame mainFrame;
	
	public TimerJProgressbar(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.setMaximum(count);
		this.setValue(count);
		this.setString(count + "√Î");
		this.setStringPainted(true);
		this.setForeground(Color.green);
	}
	
	public void start() {
		timer.start();
	}
	
	public void stop() {
		timer.stop();
	}
	
	public void reset() {
		timer.stop();
		count = Param.timeCount;
		this.setMaximum(count);
		this.setValue(count);
		this.setString(count + "√Î");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		count--;
		if (count >= 0) {
			this.setValue(count);
			this.setString(count + "√Î");
		}else if (count < 0) {
			timer.stop();
			Param.gameStatus = 3;
			mainFrame.PaintFrame();
		}
	}

}
