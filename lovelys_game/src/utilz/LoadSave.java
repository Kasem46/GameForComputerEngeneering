package utilz;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class LoadSave {
	
	public static final String PLAYER_ATLAS = "PlayerSheet.png";
	public static final String LEVEL_ATLAS = "Terrain.png";

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
		
}