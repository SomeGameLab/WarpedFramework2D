package warp.game.entities.celestial.satellite;

import warp.game.entities.celestial.planet.PlanetEntitie;
import warp.graphics.sprite.spriteSheets.WarpTechSprites;
import warp.utilities.math.Maths;

public class Asteroid extends SatelliteEntitie {

	public Asteroid(PlanetEntitie parent) {
		super(parent);
		int r = Maths.random.nextInt(3);
		if(r == 0) setSprite(WarpTechSprites.smallAsteroids.generateRandomPrimitiveSprite());
		if(r == 1) setSprite(WarpTechSprites.mediumAsteroids.generateRandomPrimitiveSprite());
		if(r == 2) setSprite(WarpTechSprites.largeAsteroids.generateRandomPrimitiveSprite());
	}


	protected void updateObject() {
		// TODO Auto-generated method stub
		
	}




}
