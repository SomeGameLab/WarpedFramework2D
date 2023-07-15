package warp.game.entities.celestial.planet;

import warp.game.entities.celestial.stellar.StellarEntitie;
import warp.graphics.sprite.spriteSheets.WarpTechSprites;

public class TerrestrialPlanet extends PlanetEntitie {

	public TerrestrialPlanet(StellarEntitie parent) {
		super(parent);
		setSprite(WarpTechSprites.terrestrialPlanets.generateRandomPrimitiveSprite());
	}

	protected void updateObject() {
		// TODO Auto-generated method stub
		
	}

}
