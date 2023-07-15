package warp.game.entities.celestial.satellite;

import warp.game.entities.celestial.planet.PlanetEntitie;
import warp.graphics.sprite.spriteSheets.WarpTechSprites;

public class TerrestrialMoon extends SatelliteEntitie {

	public TerrestrialMoon(PlanetEntitie parent) {
		super(parent);
		setSprite(WarpTechSprites.moons.generateRandomPrimitiveSprite());
	}

	@Override
	protected void updateObject() {
		// TODO Auto-generated method stub
		
	}

}
