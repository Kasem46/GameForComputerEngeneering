package ui;

import java.awt.Graphics;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import gamestates.Playing;
import main.Game;
import utilz.LoadSave;

import static utilz.Constants.UI.PauseButtons.*;
import static utilz.Constants.UI.URMButtons.*;
public class PauseOverlay {
	
	private BufferedImage backgroundImg;
	private int bgX,bgY,bgWidth,bgHeight;
	
	private SoundButton musicButton, SFXButton;
	private UrmButton menuB,replayB,unpauseB;
	
	private Playing playing;

	public PauseOverlay(Playing playing) {
		this.playing = playing;
		loadBackground();
		createSoundButtons();
		createURMButtons();
	}
	
	private void createURMButtons() {
		int menuX = (int)(313*Game.SCALE);
		int replayX = (int)(387*Game.SCALE);
		int unPauseX = (int)(462*Game.SCALE);
		int URMY = (int)(325*Game.SCALE);
		
		menuB = new UrmButton(menuX,URMY,URM_SIZE,URM_SIZE,2);
		replayB = new UrmButton(replayX,URMY,URM_SIZE,URM_SIZE,1);
		unpauseB = new UrmButton(unPauseX,URMY,URM_SIZE,URM_SIZE,0);
	}

	private void createSoundButtons() {
		int soundX = (int)(450*Game.SCALE);
		int musicY = (int)(140*Game.SCALE);
		int SFXY = (int)(186*Game.SCALE);
		musicButton = new SoundButton(soundX,musicY,SOUND_SIZE,SOUND_SIZE);
		SFXButton = new SoundButton(soundX,SFXY,SOUND_SIZE,SOUND_SIZE);
		
	}

	private void loadBackground() {
		backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PAUSE_BACKGROUND);
		
		bgWidth = (int)(backgroundImg.getWidth() * Game.SCALE);
		bgHeight = (int)(backgroundImg.getHeight() * Game.SCALE);
		
		bgX = Game.GAME_WIDTH/2 - bgWidth/2;
		bgY = (int)(25*Game.SCALE);
	}

	public void update() {
		musicButton.update();
		SFXButton.update();
		
		menuB.update();
		replayB.update();
		unpauseB.update();
	}
	
	public void draw(Graphics g) {
		
		g.drawImage(backgroundImg, bgX, bgY, bgWidth, bgHeight, null);
		
		musicButton.draw(g);
		SFXButton.draw(g);
		
		menuB.draw(g);
		replayB.draw(g);
		unpauseB.draw(g);
	}
	
	public void mouseDragged(MouseEvent e) {
		
	}
	
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(isIn(e,musicButton)) {
			 musicButton.setMousePressed(true);
		}else if(isIn(e,SFXButton)) {
			SFXButton.setMousePressed(true);
		}else if(isIn(e,menuB)) {
			 menuB.setMousePressed(true);
		}else if(isIn(e,replayB)) {
			replayB.setMousePressed(true);
		}else if(isIn(e,unpauseB)) {
			unpauseB.setMousePressed(true);
		}
		
		
	}

	public void mouseReleased(MouseEvent e) {
		if(isIn(e,musicButton)) {
			 if(musicButton.isMousePressed()) {
				 musicButton.setMuted(!musicButton.isMuted());
			 }
		}else if(isIn(e,SFXButton)) {
			if(SFXButton.isMousePressed()) {
				 SFXButton.setMuted(!SFXButton.isMuted());
			 }
		}else if(isIn(e,menuB)) {
			if(menuB.isMousePressed()) {
				Gamestate.state = Gamestate.MENU; 
				playing.setPaused(false);
			 }
		}else if(isIn(e,replayB)) {
			if(replayB.isMousePressed()) {
				 System.out.println("replay time");
			 }
		}else if(isIn(e,unpauseB)) {
			if(unpauseB.isMousePressed()) {
				playing.setPaused(false);
			 }
		}
		
		musicButton.resetBools();
		SFXButton.resetBools();
		unpauseB.resetBools();
		menuB.resetBools();
		replayB.resetBools();
		
	}

	
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		musicButton.setMouseOver(false);
		SFXButton.setMouseOver(false);
		unpauseB.setMouseOver(false);
		menuB.setMouseOver(false);
		replayB.setMouseOver(false);
		
		if(isIn(e,musicButton)) {
			 musicButton.setMouseOver(true);
		}else if(isIn(e,SFXButton)) {
			SFXButton.setMouseOver(true);
		}else if(isIn(e,unpauseB)) {
			unpauseB.setMouseOver(true);
		}else if(isIn(e,menuB)) {
			menuB.setMouseOver(true);
		}else if(isIn(e,replayB)) {
			replayB.setMouseOver(true);
		}
	}
	
	private boolean isIn(MouseEvent e, PauseButton b) {
		return b.getBounds().contains(e.getX(),e.getY());
	}
	
}
