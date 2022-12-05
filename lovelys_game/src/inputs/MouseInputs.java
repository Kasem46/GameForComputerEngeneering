package inputs;

import java.awt.event.*;
import main.*;

public class MouseInputs implements MouseListener, MouseMotionListener{

	private GamePanel gamePanel;
	
	public MouseInputs(GamePanel gayPanel) {
		gamePanel = gayPanel;
	}
	
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		gamePanel.setRectPos(e.getX(), e.getY());
		System.out.println("mouse moved");
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("mouse clicked");
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
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