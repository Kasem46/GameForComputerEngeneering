package entities;

public class Enemy extends Entity{
	private int aniIndex,enemyState,enemyType;
	private int aniTick,aniSpeed = 25;

	public Enemy(float x, float y, int width, int height,int enemyType) {
		super(x, y, width, height);
		this.enemyType = enemyType;
		initHitbox(x,y,width,height);
		
	}
	
	private void updateAnimationTick() {
		aniTick++;
		if(aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if(aniIndex >= 9999) {
				aniIndex = 0;
					
			}
		}
	}
	
	private void update() {
		updateAnimationTick();
	}
	
	private void draw() {
		
	}
	
	private int GetAniIndex() {
		return aniIndex;
	}
	
	public int getEnemyState() {
		return enemyState;
	}
	
	

}
