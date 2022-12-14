package entities;

import static utilz.Constants.PlayerConstants.*;
import static utilz.HelpMethods.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import gamestates.Playing;
import main.Game;
import utilz.LoadSave;

public class Player extends Entity{

	
	private BufferedImage[][] animations;
	private int aniTick, aniIndex, aniSpeed = 5;
	
	private int playerAction = ATTACK;
	private boolean moving, attacking = false;
	
	private boolean left,up,right,down,jump;
	private float playerSpeed = 2.5f*Game.SCALE;
	
	private float airSpeed = 0f;;
	private float gravity = 0.2f * Game.SCALE;
	private float jumpSpeed = -5f*Game.SCALE;
	private float fallSpeedAfterCollsion = 0.5f*Game.SCALE;
	private boolean inAir = false;
	
	private int[][] lvlData;
	
	private float xDrawOffset = 26*Game.SCALE;
	private float yDrawOffset = 28*Game.SCALE;
	
	//bar moment
	private BufferedImage statusBarImg;
	
	private int statusBarWidth = (int)(192*Game.SCALE);
	private int statusBarHeight = (int)(58*Game.SCALE);
	private int statusBarX = (int)(10*Game.SCALE);
	private int statusBarY = (int)(10*Game.SCALE);
	
	private int healthBarWidth = (int)(150*Game.SCALE);
	private int healthBarHeight = (int)(4*Game.SCALE);
	private int healthBarXStart = (int)(34*Game.SCALE); 
	private int healthBarYStart = (int)(14*Game.SCALE);
	
	private int maxHealth =100;
	private int currentHealth = maxHealth;
	private int healthWidth = healthBarWidth;
	
	//me when attack
	private Rectangle2D.Float attackBox;
	
	private int flipX = 0;
	private int flipW = 1;
	
	private boolean attackChecked = false;
	
	private Playing playing;
	
	public Player(float x, float y,int width,int height,Playing playing) {
		super(x, y,width,height);
		this.playing = playing;
		loadAnimations();
		initHitbox(x,y,(int)(10*Game.SCALE),(int)(20*Game.SCALE));
		initAttackBox();
		
	}
	
	private void initAttackBox() {
		attackBox = new Rectangle2D.Float(x,y,(int)(18*Game.SCALE),(int)(28*Game.SCALE));
		
	}

	public void update() {
		if(currentHealth <= 0) {
			playing.setGameOver(true);
			return;
		}
		updateHealthBar();
		updateAttackBox();
		
		updatePos();
		if(attacking) {
			checkAttack();
		}
		
		setAnimation();
		
		
		updateAnimationTick();
	}
	
	private void checkAttack() {
		if(attackChecked || (aniIndex != 2 && aniIndex != 3)) {
			return;
		}
		attackChecked = true;
		playing.checkEnemyHit(attackBox);
		
		
	}

	private void updateAttackBox() {
		if(right) {
			attackBox.x = hitbox.x + hitbox.width + (int)(5*Game.SCALE);
			
		}else if(left) {
			attackBox.x = hitbox.x - hitbox.width - (int)(10*Game.SCALE);
		}
		attackBox.y = hitbox.y - (int)(8 *Game.SCALE);
		
	}

	private void updateHealthBar() {
		healthWidth =(int)((currentHealth/(float)maxHealth)*healthBarWidth);
		
	}

	public void render(Graphics g,int lvlOffset) {
		g.drawImage(animations[playerAction][aniIndex],(int)(hitbox.x - xDrawOffset) - lvlOffset + flipX,(int)(hitbox.y - yDrawOffset), width*flipW,height,null);
		//drawHitbox(g);
		//drawAttackBox(g,lvlOffset);
		drawUI(g);
	}
	
	private void drawAttackBox(Graphics g, int lvlOffset) {
		g.setColor(Color.RED);
		
		g.drawRect((int)attackBox.x - lvlOffset, (int)attackBox.y, (int)attackBox.width, (int)attackBox.height);
		
	}

	private void drawUI(Graphics g) {
		g.drawImage(statusBarImg, statusBarX, statusBarY, statusBarWidth, statusBarHeight, null);
		
		g.setColor(Color.red);
		
		g.fillRect(healthBarXStart + statusBarX, healthBarYStart + statusBarY, healthWidth, healthBarHeight);

		
	}

	private void loadAnimations() {
		
		BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
		animations = new BufferedImage[8][8];
			
		for(int j = 0; j < animations.length; j++) {
			for(int i = 0; i < animations[j].length;i++) {
				animations[j][i] = img.getSubimage(i*64, j*64, 64, 64);
				
			}
		}
			
		statusBarImg = LoadSave.GetSpriteAtlas(LoadSave.STATUS_BAR);
		
		
	}
	
	public void loadLvlData(int[][] lvlData) {
		this.lvlData = lvlData;
		if(!isEntityOnFloor(hitbox,lvlData)) {
			inAir = true;
		}
	}
	 
	
	public void updatePos() {
		
		moving = false;
		if(jump) {
			jump();
		}
		
		//if(!left && !right && !inAir) {
		//	return;
		//}
		if(!inAir) {
			if((!left && !right) || (right && left)) {
				return;
			}
		}
		
		
		float xSpeed = 0;
		
		if(left ) {
			xSpeed -= playerSpeed;
			flipX = width;
			flipW = -1;
			
		}if(right) {
			xSpeed += playerSpeed;
			flipX = 0;
			flipW = 1;
			
		}
		if(!inAir) {
			if(!isEntityOnFloor(hitbox,lvlData)) {
				inAir = true;
			}
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
	
	public void changeHealth(int value) {
		currentHealth += value; 
		
		if(currentHealth <= 0) {
			currentHealth = 0;
			//gameOver();
		}else if(currentHealth >= maxHealth){
			currentHealth = maxHealth;
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
				attackChecked = false;
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
		if(inAir) {
			playerAction = FALL;
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
	
	public void resetAll() {
		resetDirBooleans();
		inAir = false;
		attacking = false;
		moving = false;
		playerAction = IDLE;
		currentHealth = maxHealth;
		
		hitbox.x = x;
		hitbox.y = y;
		
		
		if(!isEntityOnFloor(hitbox,lvlData)) {
			inAir = true;
		}
	}
	
}
