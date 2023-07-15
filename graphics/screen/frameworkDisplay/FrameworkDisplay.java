package warp.graphics.screen.frameworkDisplay;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import warp.WarpedFramework2D;
import warp.game.WarpedGame;
import warp.game.gameContext.ContextGroups;
import warp.game.gameContext.GameContext;
import warp.graphics.camera.WarpedCameras;
import warp.graphics.screen.Screen;
import warp.properties.FrameworkProperties;
import warp.user.UserInput;

public class FrameworkDisplay {
	
	@SuppressWarnings("unused")
	private ArrayList<FrameworkLog> logList = new ArrayList<>();
	private String title = "Framework Debug : " + WarpedFramework2D.game.getClass().getSimpleName();
	
	private static int mainCycles = 0;
	private static int gameCycles = 0;
	private static int gameUpdates = 0;
	private static int audioCycles = 0;
	private static int audioUpdates = 0;
	private static int userInputCycles = 0;
	private static int userInputUpdates = 0;
	private static int screenCycles = 0;
	private static int screenUpdates = 0;
	
	
	//Title Font
	private final int titleFontSize = 20;
	private final String titleFontType = "Ariel";
	private final int titleFontStyle = Font.ITALIC;
	private final Color titleColor = new Color(255,131,0);
	//Sub Title Font
	private final int subTitleFontSize = 15;
	private final String subTitleFontType = "Ariel";
	private final int subTitleFontStyle = Font.ITALIC;
	private final Color subTitleColor = new Color(255,181,102);
	//Text Font
	private final int bodyFontSize = 12;
	private final String bodyFontType = "Courier";
	private final int bodyFontStyle = Font.PLAIN;
	private final Color bodyColor = Color.WHITE;
	
	private final Font titleFont = new Font(titleFontType, titleFontStyle, titleFontSize);
	private final Font subTitleFont = new Font(subTitleFontType, subTitleFontStyle, subTitleFontSize);
	private final Font bodyFont = new Font(bodyFontType, bodyFontStyle, bodyFontSize);
	
	private Point titlePoint = new Point(FrameworkProperties.WINDOW_WIDTH/2 - titleFontSize / 4 * title.length(), titleFontSize + 5);
	private int yOffset = 20;
	private Point tip = new Point(10,  titlePoint.y + yOffset);
	private Point mip = new Point(130, titlePoint.y + yOffset);
	private Point kip = new Point(130, titlePoint.y + 125);
	private Point sip = new Point(260, titlePoint.y + yOffset);
	private Point cip = new Point(550, titlePoint.y + yOffset);
	private Point caip = new Point(260, titlePoint.y + 125);
	
	private Point cvp = new Point(FrameworkProperties.SCALE_WINDOW_WIDTH / 2 - 1, FrameworkProperties.SCALE_WINDOW_HEIGHT / 2 - 10);
	private Point chp = new Point(FrameworkProperties.SCALE_WINDOW_WIDTH / 2 - 10, FrameworkProperties.SCALE_WINDOW_HEIGHT / 2 - 1);
	
	public FrameworkDisplay() {
		
	}
	
	public void setMainData(int mainCycles) {FrameworkDisplay.mainCycles = mainCycles;}
	public void setGameData(int gameCycles, int gameUpdates) {FrameworkDisplay.gameCycles = gameCycles; FrameworkDisplay.gameUpdates = gameUpdates;}
	public void setAudioData(int audioCycles, int audioUpdates) {FrameworkDisplay.audioCycles = audioCycles; FrameworkDisplay.audioUpdates = audioUpdates;}
	public void setUserInputData(int userInputCycles, int userInputUpdates) {FrameworkDisplay.userInputCycles = userInputCycles; FrameworkDisplay.userInputUpdates = userInputUpdates;}
	public void setScreenData(int screenCycles, int screenUpdates) {FrameworkDisplay.screenCycles = screenCycles; FrameworkDisplay.screenUpdates = screenUpdates;}
	
	protected int composerCount = 2;
	
	public void render(Graphics g) {
		if(FrameworkProperties.DEBUG_DISPLAY) {
			/* ====================== Title ====================== */
			g.setColor(titleColor);
			g.setFont(titleFont);
			g.drawString(title, titlePoint.x, titlePoint.y);
			/* ====================== Body ====================== */
			/* ---------------------- Thread Information ----------------------  */ 
			g.setColor(subTitleColor);
			g.setFont(subTitleFont);
			g.drawString("Thread Data", tip.x, tip.y);
			g.setColor(bodyColor);
			g.setFont(bodyFont);
			g.drawString("(Updates, Cycles)", tip.x, tip.y + bodyFontSize * 1);
			g.drawString("Main Thread", tip.x, tip.y + bodyFontSize * 2);
			g.drawString("(" + 1 + ", " + mainCycles + ")", tip.x, tip.y + bodyFontSize * 3);
			g.drawString("Game Thread", tip.x, tip.y + bodyFontSize * 4);
			g.drawString("(" + gameUpdates + ", " + gameCycles + ")", tip.x, tip.y + bodyFontSize * 5);
			g.drawString("Game Speed : " + WarpedGame.time.getTickSpeed(), tip.x, tip.y + bodyFontSize * 6);
			g.drawString("Audio Thread", tip.x, tip.y + bodyFontSize * 7);
			g.drawString("(" + audioUpdates + ", " + audioCycles + ")", tip.x, tip.y + bodyFontSize * 8);
			g.drawString("UserInput Thread", tip.x, tip.y + bodyFontSize * 9);
			g.drawString("(" + userInputUpdates + ", " + userInputCycles + ")", tip.x, tip.y + bodyFontSize * 10);
			g.drawString("Screen Thread", tip.x, tip.y + bodyFontSize * 11);
			g.drawString("(" + screenUpdates + ", " + screenCycles + ")", tip.x, tip.y + bodyFontSize * 12);
			/* ---------------------- Mouse Information ----------------------  */
			g.setColor(subTitleColor);
			g.setFont(subTitleFont);
			g.drawString("Mouse Data", mip.x, mip.y);
			g.setColor(bodyColor);
			g.setFont(bodyFont);
			/*
			g.drawString("(X , Y) : (" + Mouse.getX() + " , " + Mouse.getY() + ")", mip.x, mip.y + bodyFontSize * 1);
			g.drawString("Button Press : " + Mouse.getButtonPress(), mip.x, mip.y + bodyFontSize * 2);
			g.drawString("Button Click : " + Mouse.getButtonClick(), mip.x, mip.y + bodyFontSize * 3);
			g.drawString("Rotation Direction : " + Mouse.getRotationDirection(), mip.x, mip.y + bodyFontSize * 4);
			g.drawString("Wheel Rotation : " + Mouse.getWheelRotation(), mip.x, mip.y + bodyFontSize * 5);
			g.drawString("is draging : " + Mouse.isDragged(), mip.x, mip.y + bodyFontSize * 6);
			g.drawString("in window : " + Mouse.isInWindow(), mip.x, mip.y + bodyFontSize * 7);
			*/
			/* ---------------------- Keyboard Information ---------------------- */
			g.setColor(subTitleColor);
			g.setFont(subTitleFont);
			g.drawString("Keyboard Data", kip.x, kip.y);
			g.setColor(bodyColor);
			g.setFont(bodyFont);
			g.drawString("Key Presses : ", kip.x, kip.y + bodyFontSize * 1);
			String keyPresses = ""; 
			ArrayList<KeyEvent> keys = UserInput.keyboard.getKeys();
			for(int i = 0; i < keys.size(); i++) {
				if(keys.get(i) != null)keyPresses += keys.get(i).getKeyChar() + ", ";
			}
			g.drawString(keyPresses, kip.x, kip.y + bodyFontSize * 2);
			/* ---------------------- Screen Information ---------------------- */
			g.setColor(subTitleColor);
			g.setFont(subTitleFont);
			g.drawString("Screen Data", sip.x, sip.y);
			g.setColor(bodyColor);
			g.setFont(bodyFont);
			Screen.getComposers().forEach((s,c) -> {				
				composerCount++;
				g.drawString("Screen Composer : " + s, sip.x, sip.y + bodyFontSize * composerCount);
				composerCount++;
				g.drawString("Pos , Size : " + c.getPosition().getPrintln() + c.getSize().getPrintln(), sip.x, sip.y + bodyFontSize * composerCount);
				c.getViewPortLayers().forEach((s2, vpl) -> {
					composerCount++;
					g.drawString("View Port Layer : " + s2, sip.x + 20, sip.y + bodyFontSize * composerCount);
					composerCount++;
					g.drawString("Pos , Size : " + vpl.getPosition().getPrintln() + vpl.getSize().getPrintln(), sip.x + 20, sip.y + bodyFontSize * composerCount);
				});
			});
			composerCount = 0;
			/* ---------------------- Camera Information ---------------------- */
			g.setColor(subTitleColor);
			g.setFont(subTitleFont);
			g.drawString("Camera Data", caip.x, caip.y);
			g.setColor(bodyColor);
			g.setFont(bodyFont);
			g.drawString("WT_camera pos  : " + GameContext.cameraManager.getCamera(WarpedCameras.GALACTIC_MAP_CAMERA).getPosition().getPrintln(), caip.x, caip.y + bodyFontSize * 1);
			g.drawString("Def_camera zoom : " + GameContext.cameraManager.getDefaultCamera().getZoom(), caip.x, caip.y + bodyFontSize * 2);
			g.drawString("Def_camera move speed : " + GameContext.cameraManager.getDefaultCamera().getMoveSpeed(), caip.x, caip.y + bodyFontSize * 3);
			g.drawString("Def_camera zoom speed : " + GameContext.cameraManager.getDefaultCamera().getZoomSpeed(), caip.x, caip.y + bodyFontSize * 4);
			g.drawString("Def_camera rotation : " + GameContext.cameraManager.getDefaultCamera().getRotation(), caip.x, caip.y + bodyFontSize * 5);
			/* ---------------------- Context Manager Information ---------------------- */
			g.setColor(subTitleColor);
			g.setFont(subTitleFont);
			g.drawString("Context Managers", cip.x, cip.y);
			g.setColor(bodyColor);
			g.setFont(bodyFont);
			//Entitie Manager
			g.drawString("Entitie Manager", cip.x, cip.y + bodyFontSize * 1);
			g.drawString("Group Count : " + GameContext.entitieManager.getGroups().size(), cip.x, cip.y + bodyFontSize * 2);
			for(int i = 0; i < GameContext.entitieManager.getActiveIndicies().size(); i++) {
				ContextGroups groupID = GameContext.entitieManager.getActiveID(i);
				String activeGroups = "Active Group : ";
				activeGroups += groupID;
				g.drawString(activeGroups, cip.x, cip.y + bodyFontSize * (i + 3));
				g.drawString("Entitie Count : " + GameContext.entitieManager.getGroup(groupID).getMemberCount(), cip.x, cip.y + bodyFontSize * (i + 4));
			}
		
			/* ---------------------- Window Bounds ---------------------- */
			g.setColor(Color.PINK);
			//TODO draw window bounds here
			/* ---------------------- Cross Hair ---------------------- */
			g.fillRect(cvp.x, cvp.y, 2, 20);
			g.fillRect(chp.x, chp.y, 20, 2);
		}
	}
	
	public void update() {
		
	}

}
