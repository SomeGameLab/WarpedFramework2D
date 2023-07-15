package warp.game.entities.celestial.stellar;

import warp.game.entities.celestial.galaxy.GalaxyEntitie;
import warp.graphics.sprite.spriteSheets.WarpTechSprites;

public class Blackhole extends StellarEntitie {

	public Blackhole(GalaxyEntitie parent) {
		super(parent);
		setSprite(WarpTechSprites.blackHoles.generateRandomPrimitiveSprite());
	}
	
	protected void updateObject() {
		// TODO Auto-generated method stub
		
	}

	
	
}
