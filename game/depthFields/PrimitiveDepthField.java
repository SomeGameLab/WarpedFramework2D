package warp.game.depthFields;

import java.awt.image.BufferedImage;

import warp.graphics.sprite.PrimitiveSprite;

public class PrimitiveDepthField extends DepthField{

	PrimitiveSprite sprite;
	
	public PrimitiveDepthField(BufferedImage image) {
		sprite = new PrimitiveSprite(image);
		setRaster(sprite.raster());
	}
	
	protected void updateRaster() {return;}

	
	protected void updateObject() {return;}

	protected void updatePosition() {return;}
				
}

	

	

