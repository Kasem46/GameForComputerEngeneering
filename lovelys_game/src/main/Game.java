package main;

public class Game implements Runnable{
	
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread;
	
	private final int FPS_SET = 60;
	
	public Game() {
		
		gamePanel = new GamePanel();
		gameWindow = new GameWindow(gamePanel);
		
		gamePanel.requestFocus(true);
	}

	@Override
	public void run() {
		
		double timePerFrame = 1/FPS_SET;
		while(true) {
			
		}
		
		
	}

}
