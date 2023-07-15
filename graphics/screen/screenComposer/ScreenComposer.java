package warp.graphics.screen.screenComposer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import warp.WarpedFramework2D;
import warp.graphics.screen.screenComposer.viewPort.ViewPortLayer;
import warp.graphics.screen.screenComposer.viewPort.ViewPortLayers;
import warp.properties.FrameworkProperties;
import warp.user.WarpedMouseEvent;
import warp.utilities.Timer;
import warp.utilities.math.Vec2i;

public class ScreenComposer implements Runnable {
	
	private ScreenComposers type;
	private boolean visible = true;
	private Vec2i size = new Vec2i();
	private Vec2i position = new Vec2i();
	
	public Timer screenComposerTime = new Timer(120);
	
	private BufferedImage raster;    //graphic output
	private ViewPortLayer renderLayer;
	private HashMap<ViewPortLayers, ViewPortLayer> viewPortLayers = new HashMap<>(); //graphic input
	private ArrayList<ViewPortLayers> layerStack = new ArrayList<>();
	private static Color clearColor = new Color(0, true);
	
	//public ReadWriteLock lock = new ReentrantReadWriteLock();
	
	private Vec2i lp;
	private Vec2i sz;
	
	public ScreenComposer(ScreenComposers type, Vec2i frameSize, Vec2i framePosition) { /*GameContext must be defined at this point*/
		this.type = type;
		this.size = frameSize;
		this.position = framePosition; 
		
		if(FrameworkProperties.DEBUG) {
			System.out.println("SceenComposer -> Creating " + type);
			System.out.println("ScreenComposer -> " + this.type + " : Creating frame");
		}
		raster      = new BufferedImage(size.x, size.y, FrameworkProperties.BUFFERED_IMAGE_TYPE);
	}
	
	private void render() {	
		//clear();
		Graphics g = raster().getGraphics();
		for(int i = 0; i < layerStack.size(); i++) {
			renderLayer = viewPortLayers.get(layerStack.get(i));
			if(renderLayer.isVisible()) {				
				renderLayer.render();
				lp = renderLayer.getPosition();
				sz = renderLayer.getSize();
				g.drawImage(renderLayer.raster(), lp.x, lp.y, sz.x, sz.y , null);		
			}
		}
		g.dispose();
	}
	
	/* ---------------------- getters setters ---------------------- */
	public boolean isVisible() {return visible;}
	public void visible()   {visible = true;}
	public void invisible() {visible = false;}
	public ScreenComposers getType() {return type;}
	public Vec2i getPosition() {return position;}
	public Vec2i getSize() {return size;}
	public HashMap<ViewPortLayers, ViewPortLayer> getViewPortLayers(){return viewPortLayers;};
	public BufferedImage raster() {return raster;}
	public void clear() {
		Graphics2D g = raster().createGraphics();
		g.setBackground(clearColor);
		g.clearRect(0, 0, size.x, size.y);
		g.dispose();
	}
	
	/* ---------------------- Mouse Event ---------------------- */
	public void MouseEvent(WarpedMouseEvent mouseEvent) {
		mouseEvent.setRelativePoint(this);
		viewPortLayers.forEach((e, l) -> {
			if(l.isHit(mouseEvent.getRelativePoint())) l.mouseEvent(mouseEvent); 
		});
	}
	public boolean isHit(Point relativePoint) { // check if click is contained within the bounds of the composer
		if(relativePoint.x > position.x && relativePoint.x < (position.x + size.x)
		&& relativePoint.y > position.y && relativePoint.y < (position.y + size.y)) return true;
		return false;
	}
	//public Point getRelativePoint(WarpedMouseEvent mouseEvent) {return new Point(mouseEvent.getX() - position.x, mouseEvent.getY() - position.y);}
	
	/* ---------------------- Add / Remove VPLs ---------------------- */
	public void addViewPortLayer(ViewPortLayer vpl) {
		if(FrameworkProperties.DEBUG) System.out.println("Screen Composer : " + type + " -> addViewPortLayer() -> adding new view port : " + vpl.getType());
		viewPortLayers.put(vpl.getType(), vpl);
		for(int i = 0; i < layerStack.size(); i++) {
			if(layerStack.get(i) == vpl.getType()) {
				System.err.println("ScreenComposer -> addViewPortLayer -> Render Stack already contains instance of VPL"); 
				return;
			} 
		}
		layerStack.add(vpl.getType());		
		System.out.println("Layer Stack : ");
		for(int i = 0; i < layerStack.size(); i++) {
			System.out.println(layerStack.get(i));
		}
	}
	public void removeViewPortLayer(ViewPortLayers key) {
		if(FrameworkProperties.DEBUG) System.out.println("Screen Composer : " + type + " ->removeViewPortLayer() -> removing view port : " + key);
		viewPortLayers.remove(key);
		for(int i = 0; i < layerStack.size(); i++) {
			if(layerStack.get(i) == key) {
				layerStack.remove(i);				
				return;
			} 
		}
		System.err.println("ScreenComposer -> removeViewPortLayer() -> No instance of vpl in render stack to be removed : " + key);	
	}
	
	/* ---------------------- Thread ---------------------- */
	public synchronized Thread start() {
		if(FrameworkProperties.DEBUG) System.out.println("ScreenComposer : " + type + " : Starting Thread");
		Thread result = new Thread(this, "ScreenComposer");
		result.start();
		return result;
	}
	
	public void run() {
		while(WarpedFramework2D.isRunning()) {			
			if(screenComposerTime.update()) {
			//	lock.writeLock().lock();
				render();
			//	lock.writeLock().unlock();
			}
		}
	}
	
	

	
	
	
	
	
	
	

}
