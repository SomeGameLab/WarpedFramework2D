package warp.user.actions;

import warp.WarpedFramework2D;
import warp.game.WarpedGame;
import warp.game.gameContext.GameContext;
import warp.properties.FrameworkProperties;

public interface DefaultActions {
/* ---------------------- Default Actions ---------------------- */
	
	public static WarpedAction toggleDebugDisplay = () -> {
		if(FrameworkProperties.DEBUG_DISPLAY) FrameworkProperties.DEBUG_DISPLAY = false;
		else FrameworkProperties.DEBUG_DISPLAY = true;
	};
	public static WarpedAction toggleCapMinSize = () -> {
		if(FrameworkProperties.CAP_MIN_SIZE) FrameworkProperties.CAP_MIN_SIZE = false;
		else FrameworkProperties.CAP_MIN_SIZE = true;
	};
	
	public static WarpedAction closeFramework = () -> {
		try {
			WarpedFramework2D.stop();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	};
	
	//Game Actions
	public static WarpedAction increaseGameSpeed = () -> {WarpedGame.increaseGameSpeed();};
	public static WarpedAction decreaseGameSpeed = () -> {WarpedGame.decreaseGameSpeed();};
	
	//Camera Actions
	public static WarpedAction panUp    = () -> {GameContext.cameraManager.getDefaultCamera().panUp();};	
	public static WarpedAction panDown  = () -> {GameContext.cameraManager.getDefaultCamera().panDown();};	
	public static WarpedAction panLeft  = () -> {GameContext.cameraManager.getDefaultCamera().panLeft();};
	public static WarpedAction panRight = () -> {GameContext.cameraManager.getDefaultCamera().panRight();};
	public static WarpedAction zoomIn   = () -> {GameContext.cameraManager.getDefaultCamera().zoomIn();};
	public static WarpedAction zoomOut  = () -> {GameContext.cameraManager.getDefaultCamera().zoomOut();};
	public static WarpedAction rotateClockwise   	   = () -> {GameContext.cameraManager.getDefaultCamera().rotateClockwise();};
	public static WarpedAction rotateCounterClockwise  = () -> {GameContext.cameraManager.getDefaultCamera().rotateCounterClockwise();};
	
	
}
