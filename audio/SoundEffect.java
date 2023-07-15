package warp.audio;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundEffect {
	private static final int TIME_OUT = 1000000000;
	private boolean alive = true;
	private Clip effectClip;
	private int tick = 0;
	private FloatControl effectFC;
	
	SoundEffect(SoundEffects effect, float effectVolume){
		try {
			File file = AudioController.files.getSoundEffect(effect);
			if(file == null) { System.err.println("Sound Effect -> no file was loaded"); return;}
			AudioInputStream ais = AudioSystem.getAudioInputStream(file);
			effectClip = AudioSystem.getClip();
			effectClip.open(ais);
			effectFC = (FloatControl)effectClip.getControl(FloatControl.Type.MASTER_GAIN);
			effectFC.setValue(effectVolume);
		} catch (UnsupportedAudioFileException | IOException e) {e.printStackTrace();
		} catch (LineUnavailableException e) {e.printStackTrace();}
	
		effectClip.start();	
		
		while(effectClip.isActive()) {
			tick++;
			if(tick >= TIME_OUT) {
				System.err.println("SoundEffect -> sound effect " + effect + " timed out");
				effectClip.stop();
				alive = false;
			}
		} alive = false;
	}	
	
	public boolean isAlive() {if(alive)return true; return false;}
	
	
}
