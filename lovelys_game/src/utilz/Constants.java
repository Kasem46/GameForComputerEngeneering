package utilz;

public class Constants {
	
	public static class Directions{
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 3;
	}
	
	public static class PlayerConstants{
		public static final int RUNNING = 1;
		public static final int IDLE = 0;
		public static final int JUMP = 2;
		public static final int FALL = 3;
		public static final int ATTACK = 4;
		public static final int HURT = 5;
		public static final int DEAD = 6;
		public static final int BLOCK = 7;
		
		public static int GetSpriteAmount(int player_action) {
			
			switch(player_action) {
			case RUNNING:
				return 8;
			case IDLE:
				return 5;
			case JUMP:
				return 3;
			case FALL:
				return 2;
			case ATTACK:
				return 6;
			case HURT:
				return 1;
			case DEAD:
				return 7;
			case BLOCK:
				return 2;
			default:
				return 1;
			}
		}
	}
}
