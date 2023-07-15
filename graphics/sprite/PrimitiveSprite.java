package warp.graphics.sprite;

import java.awt.image.BufferedImage;

public class PrimitiveSprite extends Sprite {

	public PrimitiveSprite(BufferedImage image) {
		raster = image;
		size.x  = image.getWidth();
		size.y = image.getHeight();
	}
	public boolean update() {return false;}
	
}
