package warp.game.entities.celestial.galaxy;

import warp.graphics.sprite.spriteSheets.WarpTechSprites;

public class RandomGalaxy extends GalaxyEntitie{

	public RandomGalaxy(double fertility) {
		super(fertility);
		setSprite(WarpTechSprites.yellowDarfStars.generateRandomPrimitiveSprite());
	}

	protected void updateObject() {
		
		
	}
}
