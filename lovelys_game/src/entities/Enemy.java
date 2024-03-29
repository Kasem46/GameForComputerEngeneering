package entities;


import static utilz.Constants.SkellyConstants.*;
import static utilz.HelpMethods.*;

import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;

import static utilz.Constants.Directions.*;

import main.Game;

public class Enemy extends Entity{
	protected int aniIndex,enemyState,enemyType;
	protected int aniTick,aniSpeed = 5;
	
	protected boolean firstUpdate = true;
	protected boolean inAir;
	
	protected float fallSpeed;
	protected  float gravity = 0.2f*Game.SCALE;
	
	protected float walkSpeed = 0.4f*Game.SCALE;
	protected int walkDir = LEFT;
	
	protected int tileY;
	protected float attackDistance = Game.TILES_SIZE;
	
	protected int maxHealth;
	protected int currentHealth;

	protected boolean active = true;
	protected boolean attackChecked;
	
	public Enemy(float x, float y, int width, int height,int enemyType) {
		super(x, y, width, height);
		this.enemyType = enemyType;
		initHitbox(x,y,width,height);
		maxHealth = GetMaxHealth(enemyType);
		currentHealth = maxHealth;
		
	}
	
	protected void firstUpdateCheck(int[][] lvlData) {
		if(firstUpdate) {
			if(!isEntityOnFloor(hitbox,lvlData)) {
				inAir = true;
			}
			firstUpdate = false;
		}
	}
	
	protected void updateInAir(int[][] lvlData) {
		if(CanMoveHere(hitbox.x,hitbox.y + fallSpeed,hitbox.width,hitbox.height,lvlData)) {
			hitbox.y += fallSpeed;
			fallSpeed += gravity;
		}else {
			inAir = false;
			hitbox.y = getEntityYPosUnderRoofOrFloor(hitbox,fallSpeed);
			tileY = (int)(hitbox.y/Game.TILES_SIZE);
		}
	}
	
	protected void newState( int enemyState) {
		this.enemyState = enemyState;
		aniIndex = 0;
		aniTick = 0;
	}
	
	protected void move(int[][] lvlData) {
		float xSpeed = 0;
		
		if(walkDir == LEFT) {
			xSpeed = -walkSpeed;
		}else {
			xSpeed = walkSpeed;
		}
		
		if(CanMoveHere(hitbox.x + xSpeed,hitbox.y,hitbox.width,hitbox.height,lvlData)) {
			if(isFloor(hitbox,xSpeed,lvlData)) {
				hitbox.x += xSpeed;
				return;
			}
		}
		
		changeWalkDir();
	}
	
	protected void turnTowardPlayer(Player player) {
		if(player.hitbox.x > hitbox.x) {
			walkDir = RIGHT;
		}else {
			walkDir = LEFT;
		}
	}
	
	protected boolean canSeePlayer(int lvlData[][],Player player) {
		int playerTileY = (int)(player.getHitbox().y / Game.TILES_SIZE);
		if(playerTileY == tileY) {
			if(isPlayerInRange(player)) {
				if(isSightClear(lvlData,hitbox,player.hitbox,tileY)) {
					return true;
				}
			}
		}
		return false;
		
	}

	protected boolean isPlayerInRange(Player player) {
		int absValue = (int)Math.abs(player.hitbox.x - hitbox.x);
		return absValue <= attackDistance*5;
	}
	
	protected boolean isPlayerCloseForAttack(Player player) {
		int absValue = (int)Math.abs(player.hitbox.x - hitbox.x);
		return absValue <= attackDistance;
	}

	protected void updateAnimationTick() {
		aniTick++;
		if(aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if(aniIndex >= GetSpriteAmount(enemyType,enemyState)) {
				aniIndex = 0;
				switch(enemyState) {
					case ATTACKING,HIT -> enemyState = IDLE;
					case DEAD -> active = false;
				}
					
			}
		}
	}
	
	
	
	protected void changeWalkDir() {
		if(walkDir == RIGHT) {
			walkDir = LEFT;
		}else {
			walkDir = RIGHT;
		}
		
	}

	
	
	public  void draw() {
		
	}
	
	public int GetAniIndex() {
		return aniIndex;
	}
	
	public int getEnemyState() {
		return enemyState;
	}
	
	public void hurt(int amount) {
		currentHealth -= amount;
		if(currentHealth <= 0) {
			newState(DEAD);
		}else {
			newState(HIT);
		}
	}
	
	public boolean isActive() {
		return active;
	}
	
	protected void checkEnemyHit(Rectangle2D.Float attackBox1, Rectangle2D.Float attackBox2,Player player) {
		//its in the name, dum-dum
		if(attackBox1.intersects(player.hitbox) || attackBox2.intersects(player.hitbox)) {
			player.changeHealth(-GetEnemyDmg(enemyType));
		}
		attackChecked = true;
		
	}
	
	public void resetEnemy() {
		hitbox.x = x;
		hitbox.y = y;
		firstUpdate = true;
		currentHealth = maxHealth;
		newState(IDLE);
		active = true;
		fallSpeed = 0;
	}

}
