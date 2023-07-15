package warp.game.entities;

import warp.game.GameObject;
import warp.graphics.sprite.Sprite;
import warp.utilities.NameGenerator;

public abstract class Entitie extends GameObject {
	
	protected String name = NameGenerator.getRandomLatin();
	private Sprite sprite;
	
	public String getName() {return name;}
	
	protected void updateRaster() {
		if(sprite.update())	raster = sprite.raster();
	}
	protected void setSprite(Sprite sprite) {this.sprite = sprite; setRaster(sprite.raster());}
	
}
