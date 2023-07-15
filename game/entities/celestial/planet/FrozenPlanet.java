package warp.game.entities.celestial.planet;

import warp.game.entities.celestial.stellar.StellarEntitie;
import warp.graphics.sprite.spriteSheets.WarpTechSprites;

public class FrozenPlanet  extends PlanetEntitie {

	public FrozenPlanet(StellarEntitie parent) {
		super(parent);
		setSprite(WarpTechSprites.frozenPlanets.generateRandomPrimitiveSprite());
	}

	protected void updateObject() {
		// TODO Auto-generated method stub
		
	}

}
