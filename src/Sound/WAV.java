package Sound;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class WAV {

	private Clip clip;

	public WAV (String fileName){
		URL url = getClass().getResource("/res/sounds/" + fileName);
		try {
			clip = AudioSystem.getClip();
			AudioInputStream stream = AudioSystem.getAudioInputStream(url);
			clip.open(stream);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void play(){
		if (clip.isOpen() && SoundManager.isSound){
			clip.setFramePosition(0);
			clip.start();
		}
	}
	
	public void stop(){
		clip.close();
	}
	
	public void loop(int count){
		if (SoundManager.isSound){
			clip.loop(count);
		}
	}

}
