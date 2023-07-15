package warp.game.entities.celestial.planet;

import warp.game.entities.celestial.stellar.StellarEntitie;
import warp.graphics.sprite.spriteSheets.WarpTechSprites;

public class DesertPlanet extends PlanetEntitie {

	public DesertPlanet(StellarEntitie parent) {
		super(parent);
		setSprite(WarpTechSprites.desertPlanets.generateRandomPrimitiveSprite());
	}

	protected void updateObject() {
		// TODO Auto-generated method stub
		
	}

}
