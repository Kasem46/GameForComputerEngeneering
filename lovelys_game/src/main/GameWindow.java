package main;

import javax.swing.JFrame;

public class GameWindow {
	private JFrame jFrame;
	
	public GameWindow(GamePanel gamePanel) {
		//set default window values 
		this.jFrame = new JFrame();
		
		jFrame.setSize(400,400);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setResizable(false);
		jFrame.setLocationRelativeTo(null);
		
		jFrame.add(gamePanel);
		
		jFrame.setVisible(true);
		
		
		
	}
}
