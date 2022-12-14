package utilz;

import main.Game;

public class Constants {
	
	public static class SkellyConstants{
		public static final int SKELLY = 0;
		
		public static final int IDLE = 0;
		public static final int ATTACKING = 1;
		public static final int DEAD = 2;
		public static final int WALK = 3;
		public static final int HIT = 4;
		
		public static final int ATTACKING_WIDTH_DEFAULT = 43;
		public static final int ATTACKING_HEIGHT_DEFAULT = 37;
		public static final int ATTACKING_WIDTH = (int)(ATTACKING_WIDTH_DEFAULT*Game.SCALE);
		public static final int ATTACKING_HEIGHT = (int)(ATTACKING_HEIGHT_DEFAULT*Game.SCALE);
		public static final int ATTACKING_DRAW_OFFSET_X = (int)(9*Game.SCALE);
		public static final int ATTACKING_DRAW_OFFSET_Y = (int)(14*Game.SCALE);
		
		public static final int IDLE_WIDTH_DEFAULT = 24;
		public static final int IDLE_HEIGHT_DEFAULT = 32;
		public static final int IDLE_WIDTH = (int)(IDLE_WIDTH_DEFAULT*Game.SCALE);
		public static final int IDLE_HEIGTH = (int)(IDLE_HEIGHT_DEFAULT*Game.SCALE);
		public static final int IDLE_HITBOX_WIDTH = (int)(10*Game.SCALE);
		public static final int IDLE_HITBOX_HEIGHT = (int)(24*Game.SCALE);
		public static final int IDLE_DRAW_OFFSET_X = (int)(6*Game.SCALE);
		public static final int IDLE_DRAW_OFFSET_Y = (int)(9*Game.SCALE);
		
		public static final int DEAD_WIDTH_DEFAULT = 32;
		public static final int DEAD_HEIGHT_DEFAULT = 32;
		public static final int DEAD_WIDTH = (int)(DEAD_WIDTH_DEFAULT*Game.SCALE);
		public static final int DEAD_HEIGTH = (int)(DEAD_HEIGHT_DEFAULT*Game.SCALE);
		
		public static final int HIT_WIDTH_DEFAULT = 30;
		public static final int HIT_HEIGHT_DEFAULT = 32;
		public static final int HIT_WIDTH = (int)(HIT_WIDTH_DEFAULT*Game.SCALE);
		public static final int HIT_HEIGTH = (int)(HIT_HEIGHT_DEFAULT*Game.SCALE);
		
		public static final int WALK_WIDTH_DEFAULT = 22;
		public static final int WALK_HEIGHT_DEFAULT = 33;
		public static final int WALK_WIDTH = (int)(WALK_WIDTH_DEFAULT*Game.SCALE);
		public static final int WALK_HEIGTH = (int)(WALK_HEIGHT_DEFAULT*Game.SCALE);
		public static final int WALK_HITBOX_WIDTH = (int)(10*Game.SCALE);
		public static final int WALK_HITBOX_HEIGHT = (int)(25*Game.SCALE);
		public static final int WALK_DRAW_OFFSET_X = (int)(3*Game.SCALE);
		public static final int WALK_DRAW_OFFSET_Y = (int)(9*Game.SCALE);
		
		
				
		public static int GetSpriteAmount(int EnemyType,int EnemyState) {
			switch(EnemyType) {
			case SKELLY:
				switch(EnemyState) {
				case IDLE:
					return 11;
				case WALK:
					return 13;
				case HIT:
					return 8;
				case ATTACKING:
					return 18;
				case DEAD:
					return 15;
				}
			}
			
			return 0;
		}
		
		public static int GetMaxHealth(int enemy_type) {
			switch(enemy_type) {
			case SKELLY:
				return 10;
			default:
				return 1;
			}
		}
		
		public static int GetEnemyDmg(int enemy_type) {
			switch(enemy_type) {
			case SKELLY:
				return 30;
			default:
				return 0;
			}
		}
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
