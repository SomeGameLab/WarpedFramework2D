package warp.game.entities.celestial.planet;

import warp.game.entities.celestial.stellar.StellarEntitie;
import warp.graphics.sprite.spriteSheets.WarpTechSprites;

public class MartianPlanet extends PlanetEntitie {

	public MartianPlanet(StellarEntitie parent) {
		super(parent);
		setSprite(WarpTechSprites.martianPlanets.generateRandomPrimitiveSprite());
	}

	protected void updateObject() {
		// TODO Auto-generated method stub
		
	}

}
