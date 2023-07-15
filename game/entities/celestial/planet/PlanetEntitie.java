package warp.game.entities.celestial.planet;

import warp.game.entities.celestial.CelestialEntitie;
import warp.game.entities.celestial.CelestialEntities;
import warp.game.entities.celestial.satellite.SatelliteEntitie;
import warp.game.entities.celestial.stellar.StellarEntitie;
import warp.game.gameContext.GameContext;
import warp.properties.FrameworkProperties;
import warp.utilities.math.geometry.Orbit;

public abstract class PlanetEntitie extends CelestialEntitie {

	protected Orbit<PlanetEntitie> orbit;
	public static final double PLANET_FERTILITY = 10.0;
	
	public PlanetEntitie(StellarEntitie parent) {
		id = CelestialEntities.PLANET_ENTITIE;
		this.parent = parent;
		orbit = new Orbit<>(this);
		setFertility(PLANET_FERTILITY);
		spawnChildren();
		System.out.println("PlanetEntitie -> constructor");
	}
	private void spawnChildren() {
		System.out.println("PlanetEntitie -> spawning children");
		for(int i = 0; i < fertility; i++) { 
			childCount++;
			GameContext.entitieManager.addMember(SatelliteEntitie.generateRandomSatelliteEntitie(this));
		}
	};
	
	public void updatePosition() {orbit.update();}
	
	public static PlanetEntitie generateRandomPlanetEntitie(StellarEntitie parent) {
		PlanetEntities entitie = PlanetEntities.getRandomEntitie();
		if(FrameworkProperties.DEBUG)System.out.println("PlanetEntitie -> generateRandomPlanetEntitie() -> : " + entitie);
		switch(entitie) {
		case DESERT: return new DesertPlanet(parent);
		case DWARF: return new DwarfPlanet(parent);
		case FROZEN:return new FrozenPlanet(parent);
		case GAS_GIANT: return new GasGiant(parent);
		case MARTIAN: return new MartianPlanet(parent);
		case OCEAN: return new OceanPlanet(parent);
		case PROTO: return new ProtoPlanet(parent);
		case RING: return new RingPlanet(parent);
		case TERRESTRIAL: return new TerrestrialPlanet(parent);
		case VENUTIAN: return new VenutianPlanet(parent);
		default:System.err.println("PlanetEntitie -> generateRandomPlanetEntitie() -> invalid switch value : " + entitie); return null;		
		}
	}
	
	



}
