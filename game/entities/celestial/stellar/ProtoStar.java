package warp.game.entities.celestial.stellar;

import warp.game.entities.celestial.galaxy.GalaxyEntitie;
import warp.graphics.sprite.spriteSheets.WarpTechSprites;

public class ProtoStar extends StellarEntitie{

	public ProtoStar(GalaxyEntitie parent) {
		super(parent);
		setSprite(WarpTechSprites.hotBlueStars.generateRandomPrimitiveSprite());
	}

	protected void updateObject() {
		// TODO Auto-generated method stub
		
	}

}
