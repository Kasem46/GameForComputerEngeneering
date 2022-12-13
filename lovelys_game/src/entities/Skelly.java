package entities;

import static utilz.Constants.Directions.*;
import static utilz.Constants.SkellyConstants.*;
import static utilz.HelpMethods.*;

import main.Game;

public class Skelly extends Enemy{

	public Skelly(float x, float y) {
		super(x, y, (int)(64*Game.SCALE), (int)(64*Game.SCALE), SKELLY);
		
		
		initHitbox(x ,y,IDLE_HITBOX_WIDTH,IDLE_HITBOX_HEIGHT);
	}
	
	public void update(int[][] lvlData, Player player) {
		updateAnimationTick();
		updateMove(lvlData,player);
	}
	
	private void updateMove(int[][] lvlData, Player player) {
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
				}
		}
	}

}
