package main;

import javax.swing.JFrame;

public class GameWindow {
	private JFrame jFrame;
	
	public GameWindow(GamePanel gamePanel) {
		//set default window values 
		this.jFrame = new JFrame();
		
		
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setResizable(false);
		jFrame.setLocationRelativeTo(null);
		
		jFrame.add(gamePanel);
		jFrame.pack();
		
		jFrame.setVisible(true);
		
		
		
	}
}
