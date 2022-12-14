package entities;

import static utilz.Constants.Directions.*;
import static utilz.Constants.SkellyConstants.*;
import static utilz.HelpMethods.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import main.Game;

public class Skelly extends Enemy{
	
	private Rectangle2D.Float attackBox1;
	private Rectangle2D.Float attackBox2;
	private int attackboxOffsetX1;
	private int attackboxOffsetX2;
	private int attackboxOffsetY1;
	private int attackboxOffsetY2;
	

	public Skelly(float x, float y) {
		super(x, y, (int)(64*Game.SCALE), (int)(64*Game.SCALE), SKELLY);
		
		
		initHitbox(x ,y,IDLE_HITBOX_WIDTH,IDLE_HITBOX_HEIGHT);
		initAttackBox();
	}
	
	private void initAttackBox() {
		attackBox1 = new Rectangle2D.Float(x,y,(int)(34*Game.SCALE),(int)(8*Game.SCALE));
		attackBox2 = new Rectangle2D.Float(x,y,(int)(23*Game.SCALE),(int)(28*Game.SCALE));
		
		attackboxOffsetX1 = (int)(-5*Game.SCALE);
		attackboxOffsetX2 = (int)(10*Game.SCALE);
		
		attackboxOffsetY1 = (int)(12*Game.SCALE);
		attackboxOffsetY2 = (int)(5*Game.SCALE);
		
	}

	public void update(int[][] lvlData, Player player) {
		
		updateBehavoir (lvlData,player);
		updateAnimationTick();
		updateAttackBox();
	}
	
	private void updateAttackBox() {
		if(walkDir  == LEFT) {
			attackBox1.x = hitbox.x - attackboxOffsetX1 - (int)(25*Game.SCALE);
			attackBox2.x = hitbox.x - attackboxOffsetX2 - (int)(15*Game.SCALE);
			attackBox1.y = hitbox.y - attackboxOffsetY1;
			attackBox2.y = hitbox.y - attackboxOffsetY2;
		}else if(walkDir  == RIGHT) {
			attackBox1.x = hitbox.x + attackboxOffsetX1;
			attackBox2.x = hitbox.x + attackboxOffsetX2;
			attackBox1.y = hitbox.y - attackboxOffsetY1;
			attackBox2.y = hitbox.y - attackboxOffsetY2;
		}
		
	}

	private void updateBehavoir(int[][] lvlData, Player player) {
		firstUpdateCheck(lvlData);
		if(inAir) {
			updateInAir(lvlData);
		}else {
				switch(enemyState) {
				case IDLE:
					newState(WALK);
					
					break;
				case WALK:
					if(canSeePlayer(lvlData,player)) {
						 turnTowardPlayer(player);
					}
					if(isPlayerCloseForAttack(player)) {
						newState(ATTACKING);
					}
					move(lvlData);
					break;
				case ATTACKING:
					if(aniIndex == 0) {
						attackChecked = false;
					}
					if(aniIndex == 7 && !attackChecked) {
						checkEnemyHit(attackBox1,attackBox2,player);
					}
					break;
				case HIT:
					break;
				}
		}
	}
	
	

	public void drawAttackBox(Graphics g,int lvlOffset) {
		g.setColor(Color.red);
		g.drawRect((int)(attackBox1.x - lvlOffset), (int)attackBox1.y, (int)attackBox1.width,(int)attackBox1.height);
		g.drawRect((int)(attackBox2.x - lvlOffset), (int)attackBox2.y, (int)attackBox2.width,(int)attackBox2.height);
	}
	
	public int flipX() {
		if(walkDir == LEFT) {
			switch(enemyState) {
			default:
			case IDLE:
				return IDLE_WIDTH - IDLE_DRAW_OFFSET_X*2;
			case WALK:
				return WALK_WIDTH - WALK_DRAW_OFFSET_X*2;
				
			case HIT:
				return HIT_WIDTH;
			case ATTACKING:
				return ATTACKING_WIDTH - ATTACKING_DRAW_OFFSET_X*2;
			case DEAD:
				return DEAD_WIDTH;
			}
		}else {
			return 0;
		}
	}
	
	public int flipW() {
		if(walkDir == LEFT) {
			return -1;
		}else {
			return 1;
		}
	}

}
