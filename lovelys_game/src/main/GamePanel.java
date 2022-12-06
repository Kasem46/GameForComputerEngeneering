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
	
	private int x;
	private int y;
	
	private BufferedImage img;
	private BufferedImage[][] animations;
	private int aniTick, aniIndex, aniSpeed = 5;
	
	private int playerAction = ATTACK;
	private int playerDir = -1;
	private boolean moving = false;
	
	public GamePanel() {
		myMouseInputs = new MouseInputs(this);
		
		importImg();
		loadAnimations();
		
		setPanelSize();
		
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(myMouseInputs);
		addMouseMotionListener(myMouseInputs);
		
	}
	
	private void loadAnimations() {
		animations = new BufferedImage[8][8];
		
		for(int j = 0; j < animations.length; j++)
		for(int i = 0; i < animations[j].length;i++) {
			animations[j][i] = img.getSubimage(i*64, j*64, 64, 64);
		}
		
	}

	private void importImg() {
		InputStream is = getClass().getResourceAsStream("/PlayerSheet.png");
		
		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			
			e.printStackTrace();
		}finally {
			try {
				is.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
	}

	private void setPanelSize() {
		Dimension size = new Dimension(1280,800);
		setPreferredSize(size);
		
	}
	
	public void setDirection(int Direction) {
		this.playerDir = Direction;
		moving = true;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	
	public void updatePos() {
		if(moving) {
			switch(playerDir) {
			case LEFT:
				x -= 5;
				break;
			case RIGHT:
				x += 5;
				break;
			case UP:
				y -= 5;
				break;
			case DOWN:
				y += 5;
				break;
			}
		}
	}
	
	public void updateGame() {
		setAnimation();
		updatePos();
		
		updateAnimationTick();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		
		
		g.drawImage(animations[playerAction][aniIndex], x, y, 128*2,128*2,null);
		
	}

	private void updateAnimationTick() {
		aniTick++;
		if(aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if(aniIndex >= GetSpriteAmount(playerAction)) {
				aniIndex = 0;
			}
		}
		
		
	}
	
	private void setAnimation() {
		if(moving) {
			playerAction = RUNNING;
		}else {
			playerAction = IDLE;
		}
	}
}
