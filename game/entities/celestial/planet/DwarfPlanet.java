package warp.game.entities.celestial.planet;

import warp.game.entities.celestial.stellar.StellarEntitie;
import warp.graphics.sprite.spriteSheets.WarpTechSprites;

public class DwarfPlanet extends PlanetEntitie {

	public DwarfPlanet(StellarEntitie parent) {
		super(parent);
		setSprite(WarpTechSprites.terrestrialPlanets.generateRandomPrimitiveSprite());
	}

	protected void updateObject() {
		// TODO Auto-generated method stub
		
	}

}
