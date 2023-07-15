package warp.game.entities.celestial.planet;

import warp.game.entities.celestial.stellar.StellarEntitie;
import warp.graphics.sprite.spriteSheets.WarpTechSprites;

public class GasGiant extends PlanetEntitie {

	public GasGiant(StellarEntitie parent) {
		super(parent);
		setSprite(WarpTechSprites.gasGiants.generateRandomPrimitiveSprite());
	}


	protected void updateObject() {
		// TODO Auto-generated method stub
		
	}

}
