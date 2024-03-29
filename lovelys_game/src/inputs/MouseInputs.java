package inputs;

import java.awt.event.*;

import gamestates.Gamestate;
import main.*;

public class MouseInputs implements MouseListener, MouseMotionListener{

	private GamePanel gamePanel;
	
	public MouseInputs(GamePanel gayPanel) {
		gamePanel = gayPanel;
	}
	
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		switch(Gamestate.state) {
		case PLAYING:
			gamePanel.getGame().getPlaying(). mouseDragged(e);
			break;
		default:
			break;
		
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		switch(Gamestate.state) {
		case MENU:
			gamePanel.getGame().getMenu().mouseMoved(e);
			break;
		case PLAYING:
			gamePanel.getGame().getPlaying().mouseMoved(e);
			break;
		default:
			break;
		
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		switch(Gamestate.state) {
		case PLAYING:
			gamePanel.getGame().getPlaying().mouseClicked(e);
			break;
		default:
			break;
		
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		switch(Gamestate.state) {
		case MENU:
			gamePanel.getGame().getMenu().mousePressed(e);
			break;
		case PLAYING:
			gamePanel.getGame().getPlaying().mousePressed(e);
			break;
		default:
			break;
		
		}
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		switch(Gamestate.state) {
		case MENU:
			gamePanel.getGame().getMenu().mouseReleased(e);
			break;
		case PLAYING:
			gamePanel.getGame().getPlaying().mouseReleased(e);
			break;
		default:
			break;
		
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
