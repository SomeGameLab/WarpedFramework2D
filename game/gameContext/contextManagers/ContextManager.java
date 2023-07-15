package warp.game.gameContext.contextManagers;

import java.util.ArrayList;
import java.util.HashMap;

import warp.game.GameObject;
import warp.game.gameContext.ContextGroup;
import warp.game.gameContext.ContextGroups;

public class ContextManager<T extends GameObject> {
 
	private HashMap<ContextGroups, ContextGroup<T>> groups = new HashMap<>();	
	private ArrayList<ContextGroups> activeIDs = new ArrayList<>();
		
	public ContextManager() {
		putGroup(ContextGroups.DEFAULT); //default group
		activeIDs.add(ContextGroups.DEFAULT);
	}
	public void setActiveGroupIDs(ArrayList<ContextGroups> activeIDs) {
		for(int i = 0; i < activeIDs.size(); i++) {
			ContextGroups j = activeIDs.get(i);
			if(j.ordinal() < 0 || !groups.containsKey(j)) {
				System.err.println("EntitieManager -> setActiveGroups() -> tried to set an active group that does not exist : " + j + " -> Canceling Operation");
				return;
			}
		}
		this.activeIDs = activeIDs;
	}
	/**destroys all game data within the context Manager*/
	public void clearGroups() {groups.clear(); activeIDs.clear();}
	/**Default add -> adds a member to the default group*/
	public void addMember(T member) {groups.get(ContextGroups.DEFAULT).addMember(member);} 
	/**adds a member to the specified group*/
	public void addMember(ContextGroups id, T member) { 
		if(!groups.containsKey(id)){System.err.println("ContextManager -> addMember -> invalid index : " + id); return;}
		else groups.get(id).addMember(member);
	}
	/**adds a new context group to keep some game elements*/
	public void putGroup(ContextGroups group) {groups.put(group, new ContextGroup<T>(group));} 
	/**update all groups listed in activeIDs*/ 
	public void update() {activeIDs.forEach(id -> {groups.get(id).update();});} 
	public ContextGroups getActiveID(int index) {return activeIDs.get(index);}
	public ArrayList<ContextGroups> getActiveIndicies() {return activeIDs;}
	public HashMap<ContextGroups, ContextGroup<T>> getGroups() {return groups;}
	public ContextGroup<T> getGroup(ContextGroups id) {return groups.get(id);}
	/**@param int -> the index of the id contained within activeGroups
	 * @return ContextGroup<T> the active context group*/
	public ContextGroup<T> getActiveGroup(int IDIndex){return groups.get(activeIDs.get(IDIndex));}
	/** applies the method to each game object in all active groups*/
	public void forEachActiveGroup(ContextGroupMethod method) {activeIDs.forEach(id ->{groups.get(id).forEach(method);});}
	
}
