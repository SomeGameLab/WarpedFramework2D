package warp.game.entities.celestial.satellite;

import java.util.HashMap;
import java.util.Map;

import warp.utilities.math.Maths;

public enum SatelliteEntities {
	
	//Stations
	MODIFICATION_STATION,
	REPAIR_STATION,
	TRADE_STATION,
	
	//Moons
	DESERT_MOON,
	FROZEN_MOON,
	OCEAN_MOON,
	TERRESTRIAL_MOON,
	
	LENGTH;
	
	
	private static Map<Integer, SatelliteEntities> map = new HashMap<>();
	static {
        for (SatelliteEntities satellite : SatelliteEntities.values()) {
            map.put(satellite.ordinal(), satellite);
        }
    }
	public static SatelliteEntities get(int index) {
	    return (SatelliteEntities) map.get(index);
	}
	public static SatelliteEntities getRandomEntitie() {return get(Maths.random.nextInt(LENGTH.ordinal()));}
	
}
