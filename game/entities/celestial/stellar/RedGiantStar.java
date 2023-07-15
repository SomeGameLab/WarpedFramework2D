package warp.game.entities.celestial.stellar;

import warp.game.entities.celestial.galaxy.GalaxyEntitie;
import warp.graphics.sprite.spriteSheets.WarpTechSprites;

public class RedGiantStar extends StellarEntitie{

	public RedGiantStar(GalaxyEntitie parent) {
		super(parent);
		setSprite(WarpTechSprites.redDwarfStars.generateRandomPrimitiveSprite());
	}
	
	protected void updateObject() {
		// TODO Auto-generated method stub
		
	}

	
}
