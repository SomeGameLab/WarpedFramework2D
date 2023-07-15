package warp.game.entities.celestial.stellar;

import warp.game.entities.celestial.galaxy.GalaxyEntitie;
import warp.graphics.sprite.spriteSheets.WarpTechSprites;

public class Pulsar extends StellarEntitie{

	public Pulsar(GalaxyEntitie parent) {
		super(parent);
		setSprite(WarpTechSprites.pulsars.generateRandomPrimitiveSprite());
	}
	
	protected void updateObject() {
		// TODO Auto-generated method stub
	}
}
