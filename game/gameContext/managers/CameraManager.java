package warp.game.gameContext.managers;

import java.util.HashMap;

import warp.graphics.camera.WarpedCamera;
import warp.graphics.camera.WarpedCameras;
import warp.utilities.generalisedEnum.Directions;
import warp.utilities.math.Vec2d;

public class CameraManager {

	private HashMap<WarpedCameras, WarpedCamera> cameras = new HashMap<>();
	
	public CameraManager() {
		addCamera(new WarpedCamera(WarpedCameras.DEFAULT));
	}
	public void update() {cameras.forEach((e, c) -> {c.update();});}
	
	//Getters and Setters
	public WarpedCamera getDefaultCamera() {return cameras.get(WarpedCameras.DEFAULT);}
	public WarpedCamera getCamera(WarpedCameras id) {return cameras.get(id);}
	public void addCamera(WarpedCamera camera) {cameras.put(camera.getID(), camera);} 
	public void setCameras(HashMap<WarpedCameras, WarpedCamera> cameras)  {this.cameras = cameras;}
	public void clearCameras() {cameras.clear();}
	
	//Collective camera operations
	public void setAllCameraPositions(Vec2d position) { cameras.forEach((e, c) -> {	c.setPosition(position);});}
	public void setAllCameraZooms(double zoom) {cameras.forEach((e, c) -> {c.setZoom(zoom);});}
	public void moveAllCameras(Vec2d vec) {cameras.forEach((e, c) -> {c.move(vec);});}
	public void zoomInAllCameras() {cameras.forEach((e, c) -> {c.zoomIn();});}
	public void panAllCameras(Directions dir) {
		switch(dir) {
		case UP:         cameras.forEach((e, c) -> {c.panUp();}); 	 return;
		case DOWN:       cameras.forEach((e, c) -> {c.panDown();});  return;
		case LEFT:       cameras.forEach((e, c) -> {c.panLeft();});  return;
		case RIGHT:      cameras.forEach((e, c) -> {c.panRight();}); return;
		case UP_LEFT:    cameras.forEach((e, c) -> {c.panUp();   c.panLeft();});  return;
		case UP_RIGHT:   cameras.forEach((e, c) -> {c.panUp();   c.panRight();}); return;
		case DOWN_LEFT:  cameras.forEach((e, c) -> {c.panDown(); c.panLeft();});  return;
		case DOWN_RIGHT: cameras.forEach((e, c) -> {c.panDown(); c.panRight();}); return;
		default: System.err.println("CameraManager -> panAllCameras() -> invalid switch value : " + dir);		
		}
	}
	
	
	
}
