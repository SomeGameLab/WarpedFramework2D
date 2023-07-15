package warp.warpedGames;

import java.awt.event.KeyEvent;

import warp.game.WarpedGame;
import warp.game.entities.celestial.galaxy.RandomGalaxy;
import warp.game.gameContext.GameContext;
import warp.game.gui.GUIButton;
import warp.graphics.camera.WarpedCameras;
import warp.graphics.camera.customCameras.GalacticMapCamera;
import warp.graphics.sprite.spriteSheets.WarpTechSprites;
import warp.user.Keyboard;
import warp.user.actions.DefaultActions;
import warp.user.actions.WarpTechActions;
import warp.utilities.math.Vec3d;

public class WarpTech extends WarpedGame implements WarpTechActions{
	
	public static final double GALAXY_FERTILITY = 150;

	protected void defineGame() {
		
		GameContext.entitieManager.addMember(new RandomGalaxy(GALAXY_FERTILITY));
		
		GameContext.guiManager.addMember(new GUIButton(WarpTechSprites.mainMenuButtons.generatePrimitiveSprite(0, 0), new Vec3d()));
		GameContext.guiManager.addMember(new GUIButton(WarpTechSprites.mainMenuButtons.generatePrimitiveSprite(0, 1), new Vec3d(0,50,0)));
		GameContext.guiManager.addMember(new GUIButton(WarpTechSprites.mainMenuButtons.generatePrimitiveSprite(0, 2), new Vec3d(0,100,0)));
		GUIButton exitButton = new GUIButton(WarpTechSprites.mainMenuButtons.generatePrimitiveSprite(0, 3), new Vec3d(0,150,0));
		exitButton.setReleasedAction(DefaultActions.closeFramework);
		GameContext.guiManager.addMember(exitButton);
		
		GameContext.cameraManager.addCamera(new GalacticMapCamera(WarpedCameras.GALACTIC_MAP_CAMERA));
		Keyboard.addKeyPressBinding(KeyEvent.VK_SUBTRACT, wtZoomOut);
		Keyboard.addKeyPressBinding(KeyEvent.VK_ADD, wtZoomIn);
		Keyboard.addKeyPressBinding(KeyEvent.VK_W, wtPanUp);
		Keyboard.addKeyPressBinding(KeyEvent.VK_S, wtPanDown);
		Keyboard.addKeyPressBinding(KeyEvent.VK_A, wtPanLeft);
		Keyboard.addKeyPressBinding(KeyEvent.VK_D, wtPanRight);
		
	}
}
