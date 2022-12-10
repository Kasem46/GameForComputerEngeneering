package utilz;

import java.awt.geom.Rectangle2D;

import main.Game;

public class HelpMethods {
	
	public static boolean CanMoveHere(float x, float y, float width, float height, int[][] lvlData) {
		
		if(!IsSolid(x,y,lvlData)) {
			if(!IsSolid(x + width,y+height,lvlData)) {
				if(!IsSolid(x+width,y,lvlData)) {
					if(!IsSolid(x,y + height,lvlData)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private static boolean  IsSolid(float x, float y, int[][] lvlData) {
		if(x < 0 || x >= Game.GAME_WIDTH) {
			return true;
		}
		if(y < 0 || y >= Game.GAME_HEIGHT) {
			return true;
		}
		
		float xIndex = x/Game.TILES_SIZE;
		float yIndex = y/Game.TILES_SIZE;
		
		int value  = lvlData[(int)yIndex][(int)xIndex];
		if(value >= 48 || value < 0|| value != 11) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public static float GetEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed) {
		int currentTile = (int)(hitbox.x/Game.TILES_SIZE);
		
		if(xSpeed > 0) {
			//right
			int tileXPos = currentTile * Game.TILES_SIZE;
			int xOffSet = (int)(Game.TILES_SIZE- hitbox.width);
			
			return tileXPos + xOffSet - 1;
		}else {
			//left
			return currentTile* Game.TILES_SIZE;
		}
	
	}
	
	public static float getEntityYPosUnderRoofOrFloor(Rectangle2D.Float hitbox,float ySpeed) {
		int currentTile = (int)(hitbox.y/Game.TILES_SIZE);
		
		if(ySpeed > 0) {
			//down
			int tileYPos = currentTile * Game.TILES_SIZE;
			int yOffSet = (int)(Game.TILES_SIZE- hitbox.height);
			
			return tileYPos + yOffSet - 1;
		}else {
			//up
			return currentTile* Game.TILES_SIZE;
		}
	}
	
	public static boolean isEntityOnFloor(Rectangle2D.Float hitbox,int[][] lvlData) {
		if(!IsSolid(hitbox.x,hitbox.y + hitbox.height + 1,lvlData)) {
			if(!IsSolid(hitbox.x + hitbox.width,hitbox.y + 1 +hitbox.height,lvlData)) {
				return false;
				
			}
		}
		
		
		return true;
	}
}
