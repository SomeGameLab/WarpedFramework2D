package warp.game.entities.celestial.satellite;

import warp.game.entities.celestial.planet.PlanetEntitie;
import warp.graphics.sprite.spriteSheets.WarpTechSprites;

public class FrozenMoon extends SatelliteEntitie{

	public FrozenMoon(PlanetEntitie parent) {
		super(parent);
		setSprite(WarpTechSprites.moons.generateRandomPrimitiveSprite());
	}

	@Override
	protected void updateObject() {
		// TODO Auto-generated method stub
		
	}

}
