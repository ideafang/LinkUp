package Linkup;

import java.awt.Cursor;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class LinkedJLabel extends JLabel {
	
	public LinkedJLabel(String text) {
		super(text);
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
}
