package warp.game.gameContext;

import java.util.ArrayList;
import java.util.Comparator;

import warp.game.GameObject;
import warp.game.gameContext.contextManagers.ContextGroupMethod;

public class ContextGroup<T extends GameObject> {

	private ArrayList<ArrayList<T>> members = new ArrayList<>();
	private ContextGroups id;
	private int memberCount = 0;
	
	public ContextGroup(ContextGroups id) {
		this.id = id;
		addZGroup();
	}
	
	public int getMemberCount() {return memberCount;}
	public ArrayList<ArrayList<T>> getMembers(){return members;}
	public void forEach(ContextGroupMethod method) {members.forEach(z -> {z.forEach(i -> {method.action(i);});});} // applies the method to each game object within the group
	public void addMember(T member) {
		member.setGroupIndex(id);
		members.get(0).add(member);
		memberCount++;
	}
	public void sortGroup() { 
		sortGroupZ();
		sortGroupXY();
	}	
	public void update() {members.forEach(z -> {z.forEach(i -> {i.update();});});};

	
	private void sortGroupZ() {	//organise entities into the first layer of arrays based on their z position as an int
		for(int z = 0; z < members.size(); z++) { // Z axis
			for(int i = 0; i < members.get(z).size(); z++) {// XY axis
				if((int)members.get(z).get(i).getPosition().z == z) continue; //already correct z height
				if(members.get(z).get(i).getPosition().z < z) {// check if z height lower than z group
					addMemberZ((int)members.get(z).get(i).getPosition().z, members.get(z).get(i));
					killMember(z, i);
					continue;
				}
				if((int)members.get(z).get(i).getPosition().z > members.size()) { //check if z height larger than z group
					int dif = (int)members.get(z).get(i).getPosition().z - members.size();
					for(int j = 0; j < dif; j++) addZGroup();
					addMemberZ((int)members.get(z).get(i).getPosition().z, members.get(z).get(i));
					killMember(z, i);
					continue;
				}	
				if((int)members.get(z).get(i).getPosition().z > z) { // check if z height is higher than z group
					addMemberZ((int)members.get(z).get(i).getPosition().z, members.get(z).get(i));
					killMember(z, i);
				}				
			}
		}
		removeDead();		
	}
	
	private void sortGroupXY() {members.forEach(z -> {z.sort(Comparator.comparingInt(GameObject::getRenderPriority));});}
	private void addMemberZ(int z, T member) {members.get(z).add(member);}  // add member to Z array
	private void addZGroup() {members.add(new ArrayList<T>());}	
	private void killMember(int z, int i) {members.get(z).get(i).kill();} // remove member from Z array
	private void removeDead() {members.forEach(z -> {
		z.forEach(i -> {if(!i.isAlive())memberCount--;});
		z.removeIf(i -> !i.isAlive());
		});	
	}
}
