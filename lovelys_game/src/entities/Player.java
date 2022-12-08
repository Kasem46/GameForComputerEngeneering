package entities;

import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.Directions.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import utilz.LoadSave;

public class Player extends Entity{

	
	private BufferedImage[][] animations;
	private int aniTick, aniIndex, aniSpeed = 5;
	
	private int playerAction = ATTACK;
	private boolean moving, attacking = false;
	
	private boolean left,up,right,down;
	private float playerSpeed = 5.0f;
	
	public Player(float x, float y) {
		super(x, y);
		loadAnimations();
		
	}
	
	public void update() {
		
		updatePos();
		setAnimation();
		
		
		updateAnimationTick();
	}
	public void render(Graphics g) {
		g.drawImage(animations[playerAction][aniIndex],(int)x,(int)y, 128,128,null);
	}
	
	private void loadAnimations() {
		
		BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
		animations = new BufferedImage[8][8];
			
		for(int j = 0; j < animations.length; j++) {
			for(int i = 0; i < animations[j].length;i++) {
				animations[j][i] = img.getSubimage(i*64, j*64, 64, 64);
				
			}
		}
			
		
		
		
	}
	 
	
	public void updatePos() {
		
		moving = false;
		
		if(left && !right) {
			x -= playerSpeed;
			moving = true;
		}else if(!left && right) {
			x += playerSpeed;
			moving = true;
		}
		
		if(up && !down) {
			y -= playerSpeed;
			moving = true;
		}else if(!up && down) {
			y += playerSpeed;
			moving = true;
		}
	}
	
	public void resetDirBooleans() {
		up = false;
		left = false;
		right = false;
		down = false;
	}
	
	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}
	
	
	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	private void updateAnimationTick() {
		aniTick++;
		if(aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if(aniIndex >= GetSpriteAmount(playerAction)) {
				aniIndex = 0;
				attacking = false;
			}
		}
		
		
	}
	
	private void setAnimation() {
		
		int startAni = playerAction;
		
		if(moving) {
			playerAction = RUNNING;
		}else {
			playerAction = IDLE;
		}
		if(attacking) {
			playerAction = ATTACK;
		}
		
		if(startAni != playerAction) {
			resetAniTick();
		}
	}

	private void resetAniTick() {
		aniTick = 0;
		aniIndex = 0;
	}
	
}
