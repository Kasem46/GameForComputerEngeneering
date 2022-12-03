package main;


import java.awt.Graphics;

import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

public class GamePanel  extends JPanel{
	public GamePanel() {
		addKeyListener(new KeyboardInputs());
		addMouseListener(new MouseInputs());
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.fillRect(100,100,200,50);
	}
}
