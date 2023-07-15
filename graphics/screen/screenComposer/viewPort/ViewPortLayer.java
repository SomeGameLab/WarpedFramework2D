package warp.graphics.screen.screenComposer.viewPort;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.util.Arrays;

import warp.game.GameObject;
import warp.game.VoidObject;
import warp.game.gameContext.GameContext;
import warp.game.gameContext.contextManagers.ContextManager;
import warp.graphics.camera.WarpedCamera;
import warp.properties.FrameworkProperties;
import warp.user.WarpedMouseEvent;
import warp.utilities.math.Vec2d;
import warp.utilities.math.Vec2i;

public class ViewPortLayer implements VPLRender {
	
	protected ViewPortLayers type;
	private boolean visible = true;
	protected Vec2i size = new Vec2i();
	private Vec2i position = new Vec2i();
	private ContextManager<? extends GameObject> target; // graphic input
	protected BufferedImage raster; // graphic output
	private ViewPortRender render = (vpl) -> {System.err.println("ViewPortLayer -> renderMethod() -> no render method was ever set for : " + type);};
	
	/*test code*/
	private static Color clearColor = new Color(0, true);
	private int [] clearPixels;
	private Raster clearRaster;
	private BufferedImage clearImage;
	AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.CLEAR, 0.0f);
	//////////////
	
	public  WarpedCamera camera = GameContext.cameraManager.getDefaultCamera();
	
	//private static final int MAX_EVENT_ATTEMPTS = 5;
	public WarpedMouseEvent mouseEvent; 
	public Point relativePoint = new Point();
	public GameObject renderObject = new VoidObject();
	//private int eventPassCount = 0;
	
	protected volatile Vec2d rp = new Vec2d();
	protected volatile Vec2d sz = new Vec2d();
	
	public ViewPortLayer(ViewPortLayers type, ContextManager<? extends GameObject> target, Vec2i size, Vec2i position) {
		this.size = size;
		this.target = target;
		this.position = position;
		this.type = type;
		raster = new BufferedImage(size.x, size.y, FrameworkProperties.BUFFERED_IMAGE_TYPE);
		
		clearPixels = new int[raster.getWidth() * raster.getHeight()];
		Arrays.fill(clearPixels, 0);

		clearImage = new BufferedImage(size.x, size.y, FrameworkProperties.BUFFERED_IMAGE_TYPE);
		clearRaster = clearImage.getData().createCompatibleWritableRaster();
	}
	
	public ViewPortLayer(ViewPortLayers type, ContextManager<? extends GameObject> target, Vec2i size, Vec2i position, WarpedCamera camera) {
		this.size = size;
		this.target = target;
		this.camera = camera;
		this.position = position;
		this.type = type;
		raster = new BufferedImage(size.x, size.y, FrameworkProperties.BUFFERED_IMAGE_TYPE);	
		
		clearPixels = new int[raster.getWidth() * raster.getHeight()];
		Arrays.fill(clearPixels, 0);
		
		clearImage = new BufferedImage(size.x, size.y, FrameworkProperties.BUFFERED_IMAGE_TYPE);
		clearRaster = clearImage.getData().createCompatibleWritableRaster();
	}
	
	/**Calls the render method set for the instance VPL*/
	public void render() {render.render(this);}
	
	/* ---------------------- Mouse Event ---------------------- */
	public void mouseEvent(WarpedMouseEvent mouseEvent) {
		mouseEvent.setRelativePoint(this);
		relativePoint.setLocation(mouseEvent.getRelativePoint().getX(), mouseEvent.getRelativePoint().getY());
		this.mouseEvent = mouseEvent;
				
	} //called from composer 
	public boolean isHit(Point relativePoint) { // check if click is contained within the bounds of the composer
		if(relativePoint.x > position.x && relativePoint.x < (position.x + size.x)
		&& relativePoint.y > position.y && relativePoint.y < (position.y + size.y)) return true;
		return false;
	}
	public Point getRelativePoint(Point relativePoint) {return new Point(relativePoint.x - position.x, relativePoint.y - position.y);}
	public void handleMouse() {
		if(isEvent()) {
			if(isOverObject()) {				
				mouseEvent.setRelativePoint(rp);
				renderObject.hovered();;
				renderObject.mouseEvent(mouseEvent);
				mouseEvent = null;
			} else {				
				renderObject.unhovered();
			}
		} 
		
		if(!isOverObject()) {
			renderObject.unhovered();
		}
	}
	/*	 
	//DO NOT CALL! outside of VPLRender methods
	public void resetMouse() {
		if(mouseEvent!= null) {			
			eventPassCount++;
			if(eventPassCount > MAX_EVENT_ATTEMPTS) {
				eventPassCount = 0;
				mouseEvent = null;
			}
		}
	}
	*/
	
	public boolean isEvent() {if(mouseEvent == null) return false; return true;}
	public boolean isOverObject(){
		if(!renderObject.isVisible()) return false;
		if(relativePoint.x > rp.x && relativePoint.x < (rp.x + sz.x)
		&& relativePoint.y > rp.y && relativePoint.y < (rp.y + sz.y)) return true;
		return false;
	}
	
	
	/* ---------------------- getters setters ---------------------- */
	public ViewPortLayers getType() {return type;}
	public ContextManager<? extends GameObject> getTarget() {return target;}
	public BufferedImage raster() {return raster;}
	public WarpedCamera getCamera() {return camera;}
	public Vec2i getPosition() {return position;}
	public Vec2i getSize() {return size;}
	public Graphics getGraphics() {return raster.getGraphics();}
	public void setRenderMethod(ViewPortRender renderMethod) {this.render = renderMethod;}
	public boolean isVisible() {return visible;}
	public void visible(){visible = true;}
	public void invisible() {visible = false;}
	public void clear() {
		//Arrays.fill(clearPixels, 0);
		//raster.setRGB(0, 0, raster.getWidth(), raster.getHeight(), clearPixels, 0, raster.getWidth());
		
		//clearRaster = clearImage.getData().createCompatibleWritableRaster();
		//raster.setData(clearRaster);
		
		//raster = new BufferedImage(size.x, size.y, FrameworkProperties.BUFFERED_IMAGE_TYPE);
		
		Graphics2D g = raster.createGraphics();
		
		//g.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));
		//g.setColor(new Color(0, 0, 0, 0));
		//g.fillRect(0,0,size.x, size.y);
		//g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, size.x, size.y);
		
		//g.setColor(clearColor);
		//g.fillRect(0, 0, size.x, size.y);
		
		//g.setBackground(clearColor);
		//g.clearRect(0, 0, size.x, size.y);
		
		g.dispose();
	}
		

		
}
