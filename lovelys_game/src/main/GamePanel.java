package main;


import java.awt.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import inputs.*;
import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.Directions.*;

public class GamePanel  extends JPanel{

	private MouseInputs myMouseInputs;
	
	private Game game;
	
	public GamePanel(Game game) {
		myMouseInputs = new MouseInputs(this);
		
		this.game = game;
		
		setPanelSize();
		
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(myMouseInputs);
		addMouseMotionListener(myMouseInputs);
		
	}
	

	private void setPanelSize() {
		Dimension size = new Dimension(1280,800);
		setPreferredSize(size);
		
	}
	
	
	
	
	
	public void updateGame() {
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		game.render(g);
					
	}
	
	public Game getGame() {
		return game;
	}
}
