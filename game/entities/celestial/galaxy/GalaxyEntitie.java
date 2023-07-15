package warp.game.entities.celestial.galaxy;

import warp.game.entities.celestial.CelestialEntitie;
import warp.game.entities.celestial.CelestialEntities;
import warp.game.entities.celestial.stellar.StellarEntitie;
import warp.game.gameContext.GameContext;

public abstract class GalaxyEntitie extends CelestialEntitie {
	
	public GalaxyEntitie(double fertility) {
		id = CelestialEntities.GALAXY_ENTITIE;
		setFertility(fertility);
		spawnChildren();
	}
	private void spawnChildren() {
		for(int i = 0; i < fertility; i++) { 
			childCount++;
			GameContext.entitieManager.addMember(StellarEntitie.generateRandomStellarEntitie(this));
		}
	};
	protected void updatePosition() {return;}

}
