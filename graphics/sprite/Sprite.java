package warp.graphics.sprite;

import java.awt.image.BufferedImage;

import warp.utilities.math.Vec2i;

public abstract class Sprite {

	protected BufferedImage raster;
	protected Vec2i size = new Vec2i();
	
	public BufferedImage raster() {return raster;}
	
	public int getWidth() {return size.x;}
	public int getHeight() {return size.y;}
	public Vec2i getSize() {return size;}
	
	public abstract boolean update();
	
}
