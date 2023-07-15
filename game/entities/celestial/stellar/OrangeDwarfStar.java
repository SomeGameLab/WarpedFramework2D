package warp.game.entities.celestial.stellar;

import warp.game.entities.celestial.galaxy.GalaxyEntitie;
import warp.graphics.sprite.spriteSheets.WarpTechSprites;

public class OrangeDwarfStar extends StellarEntitie {

	public OrangeDwarfStar(GalaxyEntitie parent) {
		super(parent);
		setSprite(WarpTechSprites.orangeDwarfStars.generateRandomPrimitiveSprite());
	}
	
	protected void updateObject() {
		// TODO Auto-generated method stub
		
	}

}
