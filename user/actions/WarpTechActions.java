package warp.user.actions;

import warp.game.gameContext.GameContext;
import warp.graphics.camera.WarpedCameras;

public interface WarpTechActions {

	/* ---------------------- WarpTech Actions ---------------------- */
	//WarpTech Actions
	public static WarpedAction wtLeftClick = () -> {
		System.out.println("UserActions -> wtLeftCLick");
	};
	
	public static WarpedAction wtRightClick = () -> {
		System.out.println("UserActions -> wtRightCLick");
	};

	public static WarpedAction wtWheelClick = () -> {
		System.out.println("UserActions -> wtWheelCLick");
	};
	public static WarpedAction wtZoomIn   = () -> {GameContext.cameraManager.getCamera(WarpedCameras.GALACTIC_MAP_CAMERA).zoomIn();};
	public static WarpedAction wtZoomOut  = () -> {GameContext.cameraManager.getCamera(WarpedCameras.GALACTIC_MAP_CAMERA).zoomOut();};
	public static WarpedAction wtPanUp    = () -> {GameContext.cameraManager.getCamera(WarpedCameras.GALACTIC_MAP_CAMERA).panUp();};	
	public static WarpedAction wtPanDown  = () -> {GameContext.cameraManager.getCamera(WarpedCameras.GALACTIC_MAP_CAMERA).panDown();};	
	public static WarpedAction wtPanLeft  = () -> {GameContext.cameraManager.getCamera(WarpedCameras.GALACTIC_MAP_CAMERA).panLeft();};
	public static WarpedAction wtPanRight = () -> {GameContext.cameraManager.getCamera(WarpedCameras.GALACTIC_MAP_CAMERA).panRight();};
	/* ----------------------   ---------------------- */
}
