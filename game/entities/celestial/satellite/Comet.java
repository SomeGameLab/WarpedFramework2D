package warp.game.entities.celestial.satellite;

import warp.game.entities.celestial.planet.PlanetEntitie;
import warp.graphics.sprite.spriteSheets.WarpTechSprites;

public class Comet extends SatelliteEntitie{

	public Comet(PlanetEntitie parent) {
		super(parent);
		setSprite(WarpTechSprites.comets.generateRandomPrimitiveSprite());
	}


	protected void updateObject() {
		// TODO Auto-generated method stub
		
	}

}
