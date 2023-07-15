package warp.game.entities.celestial.stellar;

import warp.game.entities.celestial.galaxy.GalaxyEntitie;
import warp.graphics.sprite.spriteSheets.WarpTechSprites;

public class SuperGiantStar extends StellarEntitie{

	public SuperGiantStar(GalaxyEntitie parent) {
		super(parent);
		setSprite(WarpTechSprites.hotBlueStars.generateRandomPrimitiveSprite());
	}

	protected void updateObject() {
		// TODO Auto-generated method stub
		
	}

}
