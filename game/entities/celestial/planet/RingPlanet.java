package warp.game.entities.celestial.planet;

import warp.game.entities.celestial.stellar.StellarEntitie;
import warp.graphics.sprite.spriteSheets.WarpTechSprites;

public class RingPlanet extends PlanetEntitie {

	public RingPlanet(StellarEntitie parent) {
		super(parent);
		setSprite(WarpTechSprites.ringPlanets.generateRandomPrimitiveSprite());
	}

	
	protected void updateObject() {
		// TODO Auto-generated method stub
		
	}

}
