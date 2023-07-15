package warp.game.entities.celestial.stellar;

import warp.game.entities.celestial.CelestialEntitie;
import warp.game.entities.celestial.CelestialEntities;
import warp.game.entities.celestial.galaxy.GalaxyEntitie;
import warp.game.entities.celestial.planet.PlanetEntitie;
import warp.game.gameContext.GameContext;
import warp.properties.FrameworkProperties;
import warp.utilities.math.geometry.Orbit;

public abstract class StellarEntitie extends CelestialEntitie {
 
	protected Orbit<StellarEntitie> orbit;
	public static final double STELLAR_FERTILITY = 20;
		
	public StellarEntitie(GalaxyEntitie parent) {
		id = CelestialEntities.STELLAR_ENTITIE;
		this.parent = parent;
		orbit = new Orbit<>(this);
		setFertility(STELLAR_FERTILITY);
		spawnChildren();
	}
	private void spawnChildren() {
		for(int i = 0; i < fertility; i++) { 
			childCount++;
			GameContext.entitieManager.addMember(PlanetEntitie.generateRandomPlanetEntitie(this));
		}
	};
	
	public void updatePosition() {orbit.update();} 
	
	public static StellarEntitie generateRandomStellarEntitie(GalaxyEntitie parent) {
		StellarEntities entitie = StellarEntities.getRandomEntitie();
		if(FrameworkProperties.DEBUG)System.out.println("StellarEntities -> generateRandomStellarEntitie() -> : " + entitie);
		switch(entitie) {
		case BLACKHOLE: 		return new Blackhole(parent);
		case BLUE_GIANT_STAR: 	return new BlueGiantStar(parent);
		case NEUTRON_STAR:	 	return new NeutronStar(parent);
		case ORANGE_DWARF_STAR: return new OrangeDwarfStar(parent);
		case PULSAR: 		 	return new Pulsar(parent);
		case RED_DWARF_STAR: 	return new RedDwarfStar(parent);
		case RED_GIANT_STAR: 	return new RedGiantStar(parent);
		case SUPER_GIANT_STAR: 	return new SuperGiantStar(parent);
		case WHITE_DWARF_STAR: 	return new WhiteDwarfStar(parent);
		default: System.err.println("StellarEntitie -> getRandomStellarEntitie -> invalid switch value : " + entitie); return null;
		}
	}
	

}
