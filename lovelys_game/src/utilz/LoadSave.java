package utilz;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entities.Skelly;
import main.Game;

import static utilz.Constants.SkellyConstants.*;

public class LoadSave {
	
	public static final String PLAYER_ATLAS = "PlayerSheet.png";
	public static final String LEVEL_ATLAS = "Terrain.png";
	//public static final String LEVEL_ONE = "level_one_data.png";
	public static final String LEVEL_ONE = "level_one_data_long.png";
	public static final String MENU_BUTTONS = "button_atlas.png";
	public static final String MENU_BACKGROUND = "menu_background.png";
	public static final String PAUSE_BACKGROUND = "pause_menu.png";
	public static final String SOUND_BUTTONS = "sound_button.png";
	public static final String URM_BUTTONS = "urm_buttons.png";
	public static final String VOLUME_BUTTONS = "volume_buttons.png";
	public static final String PLAYING_BG_IMG = "playing_bg_img.png";
	public static final String BIG_CLOUDS = "big_clouds.png";
	public static final String SMALL_CLOUDS = "small_clouds.png";
	public static final String STATUS_BAR = "health_power_bar.png";
	
	//enemy sheneanigains 
	public static final String SKELLY_RUN = "Skeleton_Walk.png";
	public static final String SKELLY_DEAD = "Skeleton_Dead.png";
	public static final String SKELLY_IDLE = "Skeleton_Idle.png";
	public static final String SKELLY_HIT = "Skeleton_Hit.png";
	public static final String SKELLY_ATTACK = "Skeleton_Attack.png";
	
	public static BufferedImage GetSpriteAtlas(String fileName) {
		BufferedImage img = null;
		
		InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);		
		
		try {
			img = ImageIO.read(is);
		
		} catch (IOException e) {
			
			e.printStackTrace();
		}finally {
			try {
				is.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
		return img;
	}
	
	public static ArrayList<Skelly> getSkelled(){
		BufferedImage img = GetSpriteAtlas(LEVEL_ONE);
		
		ArrayList<Skelly> list = new ArrayList<>();
		
		for(int j = 0; j < img.getHeight(); j++) {
			for(int i = 0; i < img.getWidth();i++) {
				Color colour = new Color(img.getRGB(i, j));
				int value = colour.getGreen(); 
				if(value == SKELLY) {
					list.add(new Skelly(i*Game.TILES_SIZE,j*Game.TILES_SIZE));
				}
			}
		}
		
		System.out.println("# of skellies: " + list.size());
		
		return list;
		
	}
	
	public static int[][] GetLevelData(){
		
		BufferedImage img = GetSpriteAtlas(LEVEL_ONE);
		int [][] lvlData = new int[img.getHeight()][img.getWidth()];
		
		
		for(int j = 0; j < img.getHeight(); j++) {
			for(int i = 0; i < img.getWidth();i++) {
				Color colour = new Color(img.getRGB(i, j));
				int value = colour.getRed(); 
				if(value >= 48) {
					value = 0;
				}
				lvlData[j][i] = value;
			}
		}
		
		return lvlData;
		
		
	}
		
}
