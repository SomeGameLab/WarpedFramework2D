package warp.game.entities.celestial.planet;

import warp.game.entities.celestial.stellar.StellarEntitie;
import warp.graphics.sprite.spriteSheets.WarpTechSprites;

public class OceanPlanet extends PlanetEntitie {

	public OceanPlanet(StellarEntitie parent) {
		super(parent);
		setSprite(WarpTechSprites.oceanPlanets.generateRandomPrimitiveSprite());
	}

	protected void updateObject() {
		// TODO Auto-generated method stub
		
	}

}
