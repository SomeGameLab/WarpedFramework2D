package warp.game.entities.celestial.planet;

import java.util.HashMap;
import java.util.Map;

import warp.utilities.math.Maths;

public enum PlanetEntities {

	DESERT,
	DWARF,
	FROZEN,
	GAS_GIANT,
	MARTIAN,
	OCEAN,
	PROTO,
	RING,
	TERRESTRIAL,
	VENUTIAN,
	
	LENGTH;

	private static Map<Integer, PlanetEntities> map = new HashMap<>();
	static {
        for (PlanetEntities planet : PlanetEntities.values()) {
            map.put(planet.ordinal(), planet);
        }
    }
	public static PlanetEntities get(int index) {
	    return (PlanetEntities) map.get(index);
	}
	public static PlanetEntities getRandomEntitie() {return get(Maths.random.nextInt(LENGTH.ordinal()));}
	
}
