package entities;

import static utilz.Constants.PlayerConstants.*;
import static utilz.HelpMethods.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.Game;
import utilz.LoadSave;

public class Player extends Entity{

	
	private BufferedImage[][] animations;
	private int aniTick, aniIndex, aniSpeed = 5;
	
	private int playerAction = ATTACK;
	private boolean moving, attacking = false;
	
	private boolean left,up,right,down,jump;
	private float playerSpeed = 5.0f;
	
	private float airSpeed = 0f;;
	private float gravity = 0.2f * Game.SCALE;
	private float jumpSpeed = -5f*Game.SCALE;
	private float fallSpeedAfterCollsion = 0.5f*Game.SCALE;
	private boolean inAir = false;
	
	private int[][] lvlData;
	
	private float xDrawOffset = 26*Game.SCALE;
	private float yDrawOffset = 28*Game.SCALE;
	
	public Player(float x, float y,int width,int height) {
		super(x, y,width,height);
		loadAnimations();
		initHitbox(x,y,10*Game.SCALE,20*Game.SCALE);
		
	}
	
	public void update() {
		
		updatePos();
		setAnimation();
		
		
		updateAnimationTick();
	}
	public void render(Graphics g) {
		g.drawImage(animations[playerAction][aniIndex],(int)(hitbox.x - xDrawOffset),(int)(hitbox.y - yDrawOffset), width,height,null);
		drawHitbox(g);
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
	
	public void loadLvlData(int[][] lvlData) {
		this.lvlData = lvlData;
	}
	 
	
	public void updatePos() {
		
		moving = false;
		if(jump) {
			jump();
		}
		
		if(!left && !right && !inAir) {
			return;
		}
		
		float xSpeed = 0;
		
		if(left ) {
			xSpeed -= playerSpeed;
			
		}if(right) {
			xSpeed += playerSpeed;
			
		}
		
		if(inAir) {
			
			if(inAir) {
				if(CanMoveHere(hitbox.x,hitbox.y + airSpeed,hitbox.width,hitbox.height,lvlData)) {
					hitbox.y += airSpeed;
					airSpeed += gravity;
					updateXPos(xSpeed);
				}else {
					hitbox.y = getEntityYPosUnderRoofOrFloor(hitbox,airSpeed);
					if(airSpeed > 0) {
						resetInAir();
					}else {
						airSpeed = fallSpeedAfterCollsion;
					}
					updateXPos(xSpeed);
				}
			}
			
		}else {
			updateXPos(xSpeed);
		}
		moving = true;
	}
	
	private void jump() {
		if(inAir) {
			return;
		}
		inAir = true;
		airSpeed = jumpSpeed;
		
	}

	private void resetInAir() {
		inAir = false;
		airSpeed = 0;
		
	}

	private void updateXPos(float xSpeed) {
		if(CanMoveHere(hitbox.x + xSpeed,hitbox.y,hitbox.width,hitbox.height,lvlData)) {
			hitbox.x += xSpeed;
		}else {
			hitbox.x = GetEntityXPosNextToWall(hitbox,xSpeed);
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
	
	public void setJump(boolean jump) {
		this.jump = jump;
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
