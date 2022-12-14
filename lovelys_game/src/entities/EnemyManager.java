package entities;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gamestates.Playing;
import main.Game;
import utilz.LoadSave;

import static utilz.Constants.SkellyConstants.*;

public class EnemyManager {
	private Playing playing;
	private BufferedImage[][] skellyArr;
	private ArrayList<Skelly> skellies = new ArrayList<>();
	
	public EnemyManager(Playing playing) {
		
		this.playing = playing;
		loadEnemyImgs();
		addEnemys();
	}
	
	public void update(int[][] lvlData, Player player) {
		for(Skelly s : skellies) {
			if(s.isActive()) {
				s.update(lvlData, player);
			}
		}
	}
	
	public void draw(Graphics g, int xLvlOffset) {
		drawSkellies(g,xLvlOffset);
	}

	private void drawSkellies(Graphics g,int xLvlOffset) {
		for(Skelly s : skellies) {
			if(s.isActive()) {
				switch(s.getEnemyState()) {
				case IDLE:
					g.drawImage(skellyArr[s.getEnemyState()][s.GetAniIndex()], s.flipX() + (int)s.getHitbox().x - IDLE_DRAW_OFFSET_X - xLvlOffset, (int)s.getHitbox().y - IDLE_DRAW_OFFSET_Y, IDLE_WIDTH *s.flipW() ,IDLE_HEIGTH, null);
					break;
				case ATTACKING:
					g.drawImage(skellyArr[s.getEnemyState()][s.GetAniIndex()], s.flipX() + (int)s.getHitbox().x - ATTACKING_DRAW_OFFSET_X - xLvlOffset, (int)s.getHitbox().y - ATTACKING_DRAW_OFFSET_Y, ATTACKING_WIDTH*s.flipW() ,ATTACKING_HEIGHT, null);
					break;
				case DEAD:
					g.drawImage(skellyArr[s.getEnemyState()][s.GetAniIndex()], s.flipX() + (int)s.getHitbox().x - xLvlOffset, (int)s.getHitbox().y, DEAD_WIDTH*s.flipW() ,DEAD_HEIGTH, null);
					break;
				case HIT:
					g.drawImage(skellyArr[s.getEnemyState()][s.GetAniIndex()], s.flipX() + (int)s.getHitbox().x - xLvlOffset, (int)s.getHitbox().y, HIT_WIDTH*s.flipW() ,HIT_HEIGTH, null);
					break;
				case WALK:
					g.drawImage(skellyArr[s.getEnemyState()][s.GetAniIndex()], s.flipX() + (int)s.getHitbox().x -WALK_DRAW_OFFSET_X - xLvlOffset, (int)s.getHitbox().y - WALK_DRAW_OFFSET_Y , WALK_WIDTH*s.flipW(),WALK_HEIGTH, null);
					break;
				
				}
			}
			
			//s.drawHitbox(g);
			//s.drawAttackBox(g, xLvlOffset);
			
		}
		
	} 
	
	public void checkEnemyHit(Rectangle2D.Float attackBox) {
		for(Skelly s : skellies) {
			if(s.isActive()) {
				if(attackBox.intersects(s.getHitbox())) {
					s.hurt(10);
					return;
				}
			}
		}
	}
	
	private void addEnemys() {
		skellies = LoadSave.getSkelled();
	}

	private void loadEnemyImgs() {
		// TODO Auto-generated method stub
		skellyArr = new BufferedImage[5][18];
		BufferedImage tempAttack = LoadSave.GetSpriteAtlas(LoadSave.SKELLY_ATTACK);
		BufferedImage tempIdle = LoadSave.GetSpriteAtlas(LoadSave.SKELLY_IDLE);
		BufferedImage tempHit = LoadSave.GetSpriteAtlas(LoadSave.SKELLY_HIT);
		BufferedImage tempDead = LoadSave.GetSpriteAtlas(LoadSave.SKELLY_DEAD);
		BufferedImage tempWalk = LoadSave.GetSpriteAtlas(LoadSave.SKELLY_RUN);
		
		for(int j = 0; j < skellyArr.length; j++) {
			for(int i = 0; i < skellyArr[j].length;i++) {
				switch(j) {
				case 1:
					skellyArr[j][i] = tempAttack.getSubimage(i*ATTACKING_WIDTH_DEFAULT, 0, ATTACKING_WIDTH_DEFAULT, ATTACKING_HEIGHT_DEFAULT);
					break;
				case 0:
					skellyArr[j][i] = tempIdle.getSubimage(i*IDLE_WIDTH_DEFAULT, 0, IDLE_WIDTH_DEFAULT, IDLE_HEIGHT_DEFAULT);
					break;
				case 2:
					skellyArr[j][i] = tempDead.getSubimage(i*DEAD_WIDTH_DEFAULT, 0, DEAD_WIDTH_DEFAULT, DEAD_HEIGHT_DEFAULT);
					break;
				case 3:
					skellyArr[j][i] = tempWalk.getSubimage(i*WALK_WIDTH_DEFAULT, 0, WALK_WIDTH_DEFAULT, WALK_HEIGHT_DEFAULT);
					break;
				case 4:
					skellyArr[j][i] = tempHit.getSubimage(i*HIT_WIDTH_DEFAULT, 0, HIT_WIDTH_DEFAULT, HIT_HEIGHT_DEFAULT);
					break;
				}
			}
		}
		
	}
	
	public void resetAllEnemies() {
		for(Skelly s: skellies) {
			s.resetEnemy();
		}
	}
}
