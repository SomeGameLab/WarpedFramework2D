package warp.graphics.camera;

import warp.game.GameObject;
import warp.properties.FrameworkProperties;
import warp.utilities.math.Maths;
import warp.utilities.math.Vec2d;

public class WarpedCamera {
	
	protected boolean trackTarget = false;
	protected GameObject target;
	protected WarpedCameras id;
	protected Vec2d position = new Vec2d();
	protected double zoom = 1.0;
	protected double rotation = 0.0;
	
	protected double moveSpeed = FrameworkProperties.DEFAULT_CAMERA_MOVE_SPEED;
	protected double zoomSpeed = zoom / 10.0;
	protected double rotationSpeed = Maths.TWO_PI / 1024;
	
	protected final double ROT_RANGE_MAX = Maths.TWO_PI;
	protected final double ROT_RANGE_MIN = 0.0;
	protected final double ZOOM_RANGE_MAX = 1.0;
	protected final double ZOOM_RANGE_MIN = 0.000001;
	
	public WarpedCamera(WarpedCameras id) {setID(id);}
	
	//Coordinate Transformations 
	public void positionTransformation(Vec2d pos, Vec2d size) {
	pos.add(position); // offset for camera position
	pos.round();
	pos.scale(zoom); // scale the position to account for the size i.e. if object is twice the distance away it will appear to move half the distance
	pos.round();
	}
	public Vec2d sizeTransformation(Vec2d size) {
		Vec2d result = new Vec2d(size.x * zoom, size.y * zoom);
		if(FrameworkProperties.CAP_MIN_SIZE && result.anyLessThan(1.0)) result.set(1.0);
		return result;
		}
	
	//Getters and setters
	public WarpedCameras getID() {return id;}
	public void setID(WarpedCameras id) {this.id = id;}
	public void setTarget(GameObject target) {this.target = target; trackTarget = true;}
	public void startTracking() {if(target != null) trackTarget = true;}
	public void stopTracking() {trackTarget = false;}
	public void setTracking(boolean trackTarget) {
		if(target != null) this.trackTarget = trackTarget;
		else System.err.println( "WarpedCamera -> setTracking() -> tried to track without a target");
	}
	public void setRotation(double rotation) {this.rotation = rotation;}
	public Vec2d getPosition(){return position;}
	public double getZoom() {return zoom;}
	public double getMoveSpeed() {return moveSpeed;}
	public double getZoomSpeed() {return zoomSpeed;}
	public double getRotation() {return rotation;}
	
	//Camera Controls
	public void setPosition(Vec2d position) {this.position = position;}
	public void setZoom(double zoom) {this.zoom = zoom;}
	public void move(Vec2d vec) {position.x += vec.x; position.y += vec.y;}
	public void move(double x, double y) {position.x += x; position.y += y;}
	public void panUp()    {position.y += moveSpeed;}
	public void panDown()  {position.y -= moveSpeed;}
	public void panLeft()  {position.x += moveSpeed;}	
	public void panRight() {position.x -= moveSpeed;}
	public void zoomIn()   {
		zoom += zoomSpeed;
		if(zoom > ZOOM_RANGE_MAX) zoom = ZOOM_RANGE_MAX;
		setZoomSpeed();
		setMoveSpeed();
	}
	public void zoomOut()  {
		zoom -= zoomSpeed;
		if(zoom < ZOOM_RANGE_MIN) zoom = ZOOM_RANGE_MIN; 
		setZoomSpeed();
		setMoveSpeed();
	}
	public void rotateClockwise() {
		rotation += rotationSpeed;
		if(rotation > ROT_RANGE_MAX) rotation = ROT_RANGE_MIN; // reset range to min at 360 degree rotation
		//Screen.setScreenRotation(rotation);
	}
	public void rotateCounterClockwise() {
		rotation -= rotationSpeed;
		if(rotation < ROT_RANGE_MIN) rotation = ROT_RANGE_MAX; // reset range to max at 360 degree rotation
		//Screen.setScreenRotation(rotation);
	}
	
	public void update() {
		if(trackTarget) {
			position.x = -target.getPosition().x -target.getSizeOffset().x;
			position.y = -target.getPosition().y -target.getSizeOffset().y;}
		}
	
	protected void setZoomSpeed() {zoomSpeed = zoom / FrameworkProperties.DEFAULT_CAMERA_ZOOM_D;}
	protected void setMoveSpeed() {moveSpeed = FrameworkProperties.DEFAULT_CAMERA_MOVE_SPEED / zoom;}
}
