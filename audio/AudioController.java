package warp.audio;

import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import warp.WarpedFramework2D;
import warp.properties.FrameworkProperties;
import warp.utilities.Timer;

public class AudioController implements Runnable {
	//Plays audio clips upon request
	
	public Timer time = new Timer();
	
	private static float musicVolume;
	private static float effectVolume;
	private static FloatControl musicFC;
	
	private static Clip clipSong;
	
	public static AudioFiles files = new AudioFiles();
	private static ArrayList<SoundEffect> soundEffects = new ArrayList<>();
	
	public AudioController() {
		time.setTickSpeed(FrameworkProperties.AUDIO_CONTROLLER_REFRESH_RATE);		
	}
	private void update() {
		for(int i = 0; i < soundEffects.size(); i++) {
			if(!soundEffects.get(i).isAlive()) soundEffects.remove(i);
		}
	}
	
	/* ---------------------- Thread ---------------------- */
	public void run() {
		while(WarpedFramework2D.isRunning()) {			
			if(time.update()) {update();}	
		}
	}
	public synchronized Thread start() {
		if(FrameworkProperties.DEBUG) System.out.println("AudioManager -> Starting AudioManager Thread");
		Thread result = new Thread(this, "AudioManager");
		result.start();
		return result;
	}
	
	/* ---------------------- Song Controls ---------------------- */
	public static void playSong(Songs song)   {
		//TODO Implement a better song structure that can have layered fade between tracks
		// 	   i.e. as one song is fading out the next song is fading in
		closeSong();
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(files.getSong(song));
			clipSong =  AudioSystem.getClip();
			clipSong.open(ais);
			musicFC = (FloatControl)clipSong.getControl(FloatControl.Type.MASTER_GAIN);
		} catch (UnsupportedAudioFileException | IOException e) {e.printStackTrace();
		} catch (LineUnavailableException e) {e.printStackTrace();}
		startSong();	 
	}
	public static void startSong() {clipSong.start();}
	public static void closeSong() {if(clipSong!= null) clipSong.close();}
	
	/* ---------------------- SoundEffect Controls ---------------------- */
	public static void playSoundEffect(SoundEffects effect) {soundEffects.add(new SoundEffect(effect, effectVolume));}
	
	/* ---------------------- Volume Controls ---------------------- */
	public static void musicVolumeUp() {
		musicVolume += 1.0f; 
		if(musicVolume > 6.0f) musicVolume = 6.0f; 
		musicFC.setValue(musicVolume);
	}
	public static void musicVolumeDown() {
		musicVolume -= 1.0f; 
		if(musicVolume < -80.0f) musicVolume = -80.0f;
		musicFC.setValue(musicVolume);
	}
	public static void sfxVolumeUp() {
		effectVolume += 1.0f; 
		if(effectVolume > 6.0f) effectVolume = 6.0f; 
	}
	public static void sfxVolumeDown() {
		effectVolume -= 1.0f; 
		if(effectVolume < -80.0f) effectVolume = -80.0f;
	}
}
