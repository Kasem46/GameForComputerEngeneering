package inputs;

import java.awt.event.*;

import main.*;
import static utilz.Constants.Directions.*;

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
				gamePanel.getGame().getPlayer().setDirection(UP);
				break;
			case KeyEvent.VK_A:
				gamePanel.getGame().getPlayer().setDirection(LEFT);
				break;
			case KeyEvent.VK_S:
				gamePanel.getGame().getPlayer().setDirection(DOWN);
				break;
			case KeyEvent.VK_D:
				gamePanel.getGame().getPlayer().setDirection(RIGHT);
				break;
			
		
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_W:
		case KeyEvent.VK_A:
		case KeyEvent.VK_S:
		case KeyEvent.VK_D:
			gamePanel.getGame().getPlayer().setMoving(false);
			break;
		
	
		}
		
	}
	
}
