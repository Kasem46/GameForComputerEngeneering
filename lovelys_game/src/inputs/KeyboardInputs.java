package inputs;

import java.awt.event.*;

import main.*;

public class KeyboardInputs implements KeyListener{

	
	private GamePanel gamePanel;
	
	public KeyboardInputs(GamePanel gayPanel) {
		gamePanel = gayPanel;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		switch(e.getKeyCode()) {
			case KeyEvent.VK_W:
				gamePanel.changeY(-5);
				System.out.println("W pressed");
				break;
			case KeyEvent.VK_A:
				gamePanel.changeX(-5);
				System.out.println("A pressed");
				break;
			case KeyEvent.VK_S:
				gamePanel.changeY(5);
				System.out.println("S pressed");
				break;
			case KeyEvent.VK_D:
				gamePanel.changeX(5);
				System.out.println("D pressed");
				break;
			
		
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
