package warp.game;

import java.awt.image.BufferedImage;

import warp.game.gameContext.ContextGroups;
import warp.user.WarpedMouseEvent;
import warp.utilities.generalisedEnum.Directions;
import warp.utilities.math.Vec2d;
import warp.utilities.math.Vec3d;

public abstract class GameObject {

	protected BufferedImage raster;
	private boolean visible = true;
	protected boolean alive = true;	// determines when game object will be removed
	
	//These are the real values before any coordinate transformation / camera transformation is applied 
	protected Vec2d size = new Vec2d();		
	protected Vec2d sizeOffset = new Vec2d(); 
	protected Vec3d position = new Vec3d();	//3d location used by all game objects //some games may want to ignore the z axis;
	
	
	protected ContextGroups id = ContextGroups.DEFAULT;
	protected int renderPriority = 0;
		
	private Directions direction = Directions.UP;
	
	/**@return boolean true if alive*/
	public boolean isAlive() {if(alive)return true; return false;} 
	/**Will remove the object from the game*/
	public void kill() {alive = false;}
	/**@return boolean if true game object will be rendered*/
	public boolean isVisible() {if(visible)return true; return false;}
	/**The game object will be rendered only if visible*/
	public void visible()   {visible = true;}
	/**Will stop the game object from rendering in all VPLs*/
	public void invisible() {visible = false;}
	
	//Getters
	/**@return Vec2d containing size of the game object*/
	public Vec2d getSize() {return size;}
	/**@return Vec2d half the game objects size*/
	public Vec2d getSizeOffset() {return sizeOffset;}
	/**@return Vec3d the position of the Game object in the world*/
	public Vec3d getPosition() {return position;}
	public int getRenderPriority() {return renderPriority;}
	public BufferedImage raster()  {return raster;}	
	public Directions getDirection() {return direction;}
	
	//Setters
	public void setSize(Vec2d size) {this.size = size;};	
	public void setSize(int x, int y) {size.x = x; size.y = y;}
	public void setPosition(Vec2d position) {this.position.x = position.x; this.position.y = position.y;}
	public void setPosition(Vec3d position) {this.position = position;}
	public void setPosition(double x, double y, double z) {position.x = x; position.y = y; position.z = z;}
	public void setPosition(double x, double y) {position.x = x; position.y = y;}
	public void setDirection(Directions direction) {this.direction = direction;}
	private void setRenderPriority() {/*TODO implement set render priority function*/}
	public void setGroupIndex(ContextGroups id) {this.id = id;}
	public void setRaster(BufferedImage raster) {
		this.raster = raster;
		size.x = raster.getWidth(); 
		size.y = raster.getHeight();
		sizeOffset.x = size.x / 2;
		sizeOffset.y = size.y / 2;
	}
	
	
	public void update() {
		updateRaster();	
		updatePosition();
		updateObject();
		setRenderPriority(); // probably want to do this after changing position instead of every update cycle
	};
	
	
	/* ---------------------- Mouse Handleing ---------------------- */
	private boolean hovered = false;
	/**DO NOT CALL! outside of ViewPortLayer*/
	public void mouseEvent(WarpedMouseEvent mouseEvent) {
		switch(mouseEvent.getID()) {
		case BUTTON_PRESS: mousePressed(mouseEvent); break;
		case BUTTON_RELEASE: mouseReleased(mouseEvent); break;
		case MOVE: mouseMoved(mouseEvent);break;
		case DRAG: mouseDragged(mouseEvent); break;
		case WHEEL_ROTATION: mouseRotation(mouseEvent); break;
		default: System.err.println("Game Object -> MouseEvent() -> invalid switch case : " + mouseEvent.getID()); break;
		}
	}
	/**DO NOT CALL! outside of ViewPortLayer*/
	public boolean isHovered() {if(hovered)return true;return false;}
	/**DO NOT CALL! outside of ViewPortLayer*/
	public void hovered() {
		if(!hovered) {			
			mouseEntered();
			hovered = true;
		}
	}
	/**DO NOT CALL! outside of ViewPortLayer*/
	public void unhovered() {
		if(hovered) {			
			mouseExited();
			hovered = false;
		}
	}
	
	/* ---------------------- User Defined Mouse Functionality ---------------------- */
	//Custom mouse functionality for all game objects - everything is a button
	protected abstract void mouseEntered();
	protected abstract void mouseExited();
	protected abstract void mouseMoved(WarpedMouseEvent mouseEvent);
	protected abstract void mouseDragged(WarpedMouseEvent mouseEvent);
	protected abstract void mousePressed(WarpedMouseEvent mouseEvent);
	protected abstract void mouseReleased(WarpedMouseEvent mouseEvent);
	protected abstract void mouseRotation(WarpedMouseEvent mouseEvent);
	
	/* ---------------------- User Object Functionality ---------------------- */
	protected abstract void updateRaster();
	protected abstract void updateObject();
	protected abstract void updatePosition();
	
	
	
	
}
