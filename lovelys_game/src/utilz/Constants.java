package utilz;

import main.Game;

public class Constants {
	
	public static class SkellyConstants{
		public final int SKELLY = 0;
		
		public final int IDLE = 0;
		public final int ATTACKING = 1;
		public final int DEAD = 2;
		public final int WALK = 3;
		public final int HIT = 4;
	}
	
	public static class Enviroment{
		public static final int BIG_CLOUD_WIDTH_DEFAULT = 448;
		public static final int BIG_CLOUD_HEIGHT_DEFAULT = 101;
		
		public static final int BIG_CLOUD_WIDTH = (int)(BIG_CLOUD_WIDTH_DEFAULT*Game.SCALE);
		public static final int BIG_CLOUD_HEIGHT = (int)(BIG_CLOUD_HEIGHT_DEFAULT*Game.SCALE);
		
		
		public static final int SMALL_CLOUD_WIDTH_DEFAULT = 74;
		public static final int SMALL_CLOUD_HEIGHT_DEFAULT = 24;
		
		public static final int SMALL_CLOUD_WIDTH = (int)(SMALL_CLOUD_WIDTH_DEFAULT*Game.SCALE);
		public static final int SMALL_CLOUD_HEIGHT = (int)(SMALL_CLOUD_HEIGHT_DEFAULT*Game.SCALE);
	}
	
	public static class UI{
		public static class Buttons{
			public static final int B_WIDTH_DEFAULT = 140;
			public static final int B_HEIGHT_DEFAULT = 56;
			public static final int B_WIDTH = (int)(B_WIDTH_DEFAULT*Game.SCALE);
			public static final int B_HEIGHT = (int)(B_HEIGHT_DEFAULT*Game.SCALE);
		}
		public static class PauseButtons{
			public static final int SOUND_SIZE_DEFAULT = 42;
			public static final int SOUND_SIZE = (int)(SOUND_SIZE_DEFAULT*Game.SCALE);
			
		}
		public static class URMButtons{
			public static final int URM_SIZE_DEFAULT = 56;
			public static final int URM_SIZE = (int)(URM_SIZE_DEFAULT*Game.SCALE);
		}
		public static class VolumeButtons{
			public static final int VOLUME_WIDTH_DEFAULT = 28;
			public static final int VOLUME_WIDTH = (int)(VOLUME_WIDTH_DEFAULT*Game.SCALE);
			
			public static final int SLIDER_WIDTH_DEFAULT = 215;
			public static final int SLIDER_WIDTH = (int)(SLIDER_WIDTH_DEFAULT*Game.SCALE);
			
			public static final int VOLUME_HEIGHT_DEFAULT = 44;
			public static final int VOLUME_HEIGHT = (int)(VOLUME_HEIGHT_DEFAULT*Game.SCALE);
			
			
		}
	}
	
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
