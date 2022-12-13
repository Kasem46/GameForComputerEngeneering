package main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

public class GameWindow {
	private JFrame jFrame;
	
	public GameWindow(GamePanel gamePanel) {
		//set default window values 
		this.jFrame = new JFrame();
		
		
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setResizable(false);
		
		
		jFrame.add(gamePanel);
		jFrame.pack();
		
		jFrame.setLocationRelativeTo(null);
		
		jFrame.setVisible(true);
		jFrame.addWindowFocusListener(new WindowFocusListener() {

			@Override
			public void windowGainedFocus(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowLostFocus(WindowEvent e) {
				// TODO Auto-generated method stub
				gamePanel.getGame().windowFocusLost();
			}
			
		});
		
		
		
	}
}
