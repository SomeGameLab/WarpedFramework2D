package warp.game.entities.celestial.planet;

import warp.game.entities.celestial.stellar.StellarEntitie;
import warp.graphics.sprite.spriteSheets.WarpTechSprites;

public class VenutianPlanet extends PlanetEntitie{


	
	public VenutianPlanet(StellarEntitie parent) {
		super(parent);
		setSprite(WarpTechSprites.venutianPlanets.generateRandomPrimitiveSprite());
	}


	protected void spawnChildren() {
		// TODO Auto-generated method stub
		
	}

	
	protected void updateObject() {
		// TODO Auto-generated method stub
		
	}

}
