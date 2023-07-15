package warp.game.entities.celestial.stellar;

import warp.game.entities.celestial.galaxy.GalaxyEntitie;
import warp.graphics.sprite.spriteSheets.WarpTechSprites;

public class WhiteDwarfStar extends StellarEntitie {

	public WhiteDwarfStar(GalaxyEntitie parent) {
		super(parent);
		setSprite(WarpTechSprites.whiteDwarfStars.generateRandomPrimitiveSprite());
	}
	
	protected void updateObject() {
		// TODO Auto-generated method stub
		
	}

	
}
