package warp.game.entities.celestial.planet;

import warp.game.entities.celestial.stellar.StellarEntitie;
import warp.graphics.sprite.spriteSheets.WarpTechSprites;

public class ProtoPlanet extends PlanetEntitie {

	public ProtoPlanet(StellarEntitie parent) {
		super(parent);
		setSprite(WarpTechSprites.protoPlanets.generateRandomPrimitiveSprite());
	}


	protected void updateObject() {
		// TODO Auto-generated method stub
		
	}

}
