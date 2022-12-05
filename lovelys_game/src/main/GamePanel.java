package main;


import java.awt.Graphics;

import javax.swing.JPanel;

import inputs.*;

public class GamePanel  extends JPanel{

	private MouseInputs myMouseInputs;
	
	private int deltaX;
	private int deltaY;
	
	private int frames = 0;
	private long lastCheck = 0;
	
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
		frames++;
		if(System.currentTimeMillis() - lastCheck >= 1000) {
			lastCheck = System.currentTimeMillis();
			System.out.println("FPS: " + frames);	
			frames = 0;
		}
		
		repaint();
	}
}
