package warp.game.entities.celestial.stellar;

import java.util.HashMap;
import java.util.Map;

import warp.utilities.math.Maths;

public enum StellarEntities {
	  
	BLACKHOLE,
	BLUE_GIANT_STAR,
	NEUTRON_STAR,
	ORANGE_DWARF_STAR,
	//PROTO_STAR,   gas field 
	PULSAR,
	RED_DWARF_STAR,
	RED_GIANT_STAR,
	SUPER_GIANT_STAR,
	WHITE_DWARF_STAR,
	
	LENGTH;
	
	private static Map<Integer, StellarEntities> map = new HashMap<>();
	static {
        for (StellarEntities stellar : StellarEntities.values()) {
            map.put(stellar.ordinal(), stellar);
        }
    }
	public static StellarEntities get(int index) {
	    return (StellarEntities) map.get(index);
	}
	public static StellarEntities getRandomEntitie() {return get(Maths.random.nextInt(LENGTH.ordinal()));}
}
