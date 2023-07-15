package warp.game.gameContext;

import warp.game.depthFields.DepthField;
import warp.game.entities.Entitie;
import warp.game.gameContext.contextCoordinates.ContextCoordinate;
import warp.game.gameContext.contextCoordinates.ContextCoordinates;
import warp.game.gameContext.contextManagers.ContextManager;
import warp.game.gameContext.managers.CameraManager;
import warp.game.gameContext.managers.SelectionManager;
import warp.game.gui.GUIElement;

public class GameContext {

	//Coordinate system that can be applied by VPLRender methods
	public static ContextCoordinate          coordinateSystem  = new ContextCoordinate(ContextCoordinates.PLAN);
	
	//Specific managers to handle less abstract concepts
	public static CameraManager 			 cameraManager     = new CameraManager();
	public static SelectionManager			 selectionManager  = new SelectionManager();
	
	//Generic Context managers - contains game objects for the declared type
	public static ContextManager<Entitie> 	 entitieManager    = new ContextManager<>();
	public static ContextManager<DepthField> depthFieldManager = new ContextManager<>();	
	public static ContextManager<GUIElement> guiManager 	   = new ContextManager<>();
	
	public static void update() {updateManagers();} 
	
	protected static void updateManagers() {
		entitieManager.update();
		depthFieldManager.update();
		cameraManager.update();
	}
	
}
