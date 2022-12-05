package main;


import java.awt.Graphics;

import javax.swing.JPanel;

import inputs.*;

public class GamePanel  extends JPanel{

	private MouseInputs myMouseInputs;
	
	private int deltaX;
	private int deltaY;
	
	public GamePanel() {
		myMouseInputs = new MouseInputs(this);
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(myMouseInputs);
		addMouseMotionListener(myMouseInputs);
		
	}
	
	public void changeX(int value ) {
		deltaX += value;
		 
		
	}
	public void changeY(int value) {
		deltaY += value;
		 
	}
	public void setRectPos(int x, int y) {
		this.deltaX = x;
		this.deltaY = y;
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.fillRect(0 + deltaX,0 + deltaY,50,50);
		repaint();
	}
}
