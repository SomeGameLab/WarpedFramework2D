package warp.game.entities.celestial.satellite;

import warp.game.entities.celestial.planet.PlanetEntitie;
import warp.graphics.sprite.spriteSheets.WarpTechSprites;

public class DesertMoon extends SatelliteEntitie {

	
	public DesertMoon(PlanetEntitie parent) {
		super(parent);
		setSprite(WarpTechSprites.moons.generateRandomPrimitiveSprite());
	}

	protected void updateObject() {
		// TODO Auto-generated method stub
		
	}

}
