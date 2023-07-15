package warp.audio;

import java.io.File;
import java.util.HashMap;

public class AudioFiles {
	
	private static HashMap<Songs, File> songs = new HashMap<>();
	private static HashMap<SoundEffects, File> soundEffects = new HashMap<>();
	
	/* ---------------------- Songs ---------------------- */
	public File landing; 	
	public File traversing; 
	public File trudging; 
	
	/* ---------------------- Sound Effects ---------------------- */
	public File select;
	public File button12;
	public File button5;
	public File button16;
	
	public AudioFiles() {
		loadAudioFiles();
		buildHashMaps();
	}
	
	/* ---------------------- Getters ---------------------- */
	public File getSong(Songs song) {return songs.get(song);}
	public File getSoundEffect(SoundEffects effect) {
		if(soundEffects.containsKey(effect)) return soundEffects.get(effect);
		else System.err.println("AudioFiles -> getSoundEffect() -> could not find file for " + effect); return null;
	}
	
	/* ---------------------- Loading ---------------------- */
	private void loadAudioFiles() {
		System.out.println("AudioFiles -> loadAudioFiles() -> loadingSongs..");
		loadSongs();
		System.out.println("AudioFiles -> loadAudioFiles() -> loadingSoundEffects..");
		loadSFX();
	}
	private void loadSongs() {
		try {			
			//landing    = new File ("assets/warpTech/audio/soundTrack/landing.wav");
			//traversing = new File ("assets/warpTech/audio/soundTrack/traversing.wav");
			//traversing = new File ("assets/warpTech/audio/soundTrack/trudging.wav");
		} catch(Exception e) {
			System.err.println("AudioFiles -> failed to load audio files");
		}
	}
	private void loadSFX() {
		try {			
			select    = new File ("assets/warpTech/audio/soundEffects/select.wav");
			button12  = new File ("assets/warpTech/audio/soundEffects/button12.wav");
			button5  = new File ("assets/warpTech/audio/soundEffects/button5.wav");
			button16  = new File ("assets/warpTech/audio/soundEffects/button16.wav");
		} catch(Exception e) {
			System.err.println("AudioFiles -> failed to load audio files");
		}
	}
	/* ---------------------- Hash Maps ---------------------- */
	private void buildHashMaps() {
		System.out.println("AudioFiles -> buildHashMaps() -> maping files..");
		buildSongMap();
		buildSoundEffectsMap();
	}
	private void buildSongMap() {
		//songs.put(Songs.LANDING, landing);
		//songs.put(Songs.TRAVERSING, traversing);		
	}
	private void buildSoundEffectsMap() {
		soundEffects.put(SoundEffects.DEFAULT_BUTTON_PRESSED, button16);
		soundEffects.put(SoundEffects.DEFAULT_BUTTON_ENTERED, button5);
	}
	
	
	
	
}
