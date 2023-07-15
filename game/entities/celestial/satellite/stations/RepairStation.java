package warp.game.entities.celestial.satellite.stations;

import warp.game.entities.celestial.planet.PlanetEntitie;
import warp.graphics.sprite.spriteSheets.WarpTechSprites;

public class RepairStation extends SpaceStation {

	public RepairStation(PlanetEntitie parent) {
		super(parent);
		setSprite(WarpTechSprites.automatedRepairStation.generateRandomPrimitiveSprite());
	}

	protected void updateObject() {
		// TODO Auto-generated method stub
		
	}

}
