package warp.game.entities.celestial.satellite;

import warp.game.entities.celestial.CelestialEntitie;
import warp.game.entities.celestial.CelestialEntities;
import warp.game.entities.celestial.planet.PlanetEntitie;
import warp.game.entities.celestial.satellite.stations.ModificationStation;
import warp.game.entities.celestial.satellite.stations.RepairStation;
import warp.game.entities.celestial.satellite.stations.TradeStation;
import warp.properties.FrameworkProperties;
import warp.utilities.math.geometry.Orbit;

public abstract class SatelliteEntitie extends CelestialEntitie {

	protected Orbit<SatelliteEntitie> orbit;
	
	public SatelliteEntitie(PlanetEntitie parent) {
		id = CelestialEntities.SATELLITE_ENTITIE;
		this.parent = parent;
		orbit = new Orbit<>(this);
	}
	
	public void updatePosition() {orbit.update();}
		
	public static SatelliteEntitie generateRandomSatelliteEntitie(PlanetEntitie parent){
		SatelliteEntities entitie = SatelliteEntities.getRandomEntitie();
		if(FrameworkProperties.DEBUG)System.out.println("SatelliteEntitie -> generateRandomSatelliteEntitie() -> : " + entitie);
		switch(entitie) {
		case DESERT_MOON: return new DesertMoon(parent);
		case FROZEN_MOON: return new FrozenMoon(parent);
		case MODIFICATION_STATION: return new ModificationStation(parent);
		case OCEAN_MOON: return new OceanMoon(parent);
		case REPAIR_STATION: return new RepairStation(parent);
		case TERRESTRIAL_MOON: return new TerrestrialMoon(parent);
		case TRADE_STATION: return new TradeStation(parent);
		default: System.err.println("SatelliteEntitie -> generateRandomSatellite() -> invalid switch value : " + entitie); return null;
		}
	}

	
	
}
