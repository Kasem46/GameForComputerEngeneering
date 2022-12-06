package entities;

import static utilz.Constants.Directions.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Player extends Entity{

	
	private BufferedImage[][] animations;
	private int aniTick, aniIndex, aniSpeed = 5;
	
	private int playerAction = ATTACK;
	private int playerDir = -1;
	private boolean moving = false;
	
	public Player(float x, float y) {
		super(x, y);
		loadAnimations();
		
	}
	
	public void update() {
		setAnimation();
		updatePos();
		
		updateAnimationTick();
	}
	public void render(Graphics g) {
		g.drawImage(animations[playerAction][aniIndex], 128*2,128*2,null);
	}
	
	private void loadAnimations() {
		InputStream is = getClass().getResourceAsStream("/PlayerSheet.png");		
		
		try {
			BufferedImage img = ImageIO.read(is);
			animations = new BufferedImage[8][8];
			
			for(int j = 0; j < animations.length; j++)
			for(int i = 0; i < animations[j].length;i++) {
				animations[j][i] = img.getSubimage(i*64, j*64, 64, 64);
			}
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
