package warp.game.entities.celestial.satellite.stations;

import warp.game.entities.celestial.planet.PlanetEntitie;
import warp.graphics.sprite.spriteSheets.WarpTechSprites;

public class TradeStation extends SpaceStation {

	public TradeStation(PlanetEntitie parent) {
		super(parent);
		setSprite(WarpTechSprites.automatedTradeStation.generateRandomPrimitiveSprite());
	}

	@Override
	protected void updateObject() {
		// TODO Auto-generated method stub
		
	}

}
