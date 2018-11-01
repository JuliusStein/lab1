package edu.virginia.engine.display;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundManager {
	
	private HashMap<String, File> soundMap;
	
	public SoundManager()
	{
		this.soundMap = new HashMap<String, File>();
	}
	
	public void loadSoundEffect(String id, String filename)
	{
		File file = new File(filename);
		this.soundMap.put(id, file);
	}
	
	public void playSoundEffect(String id)
	{
		try 
		{
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(soundMap.get(id)));
			clip.start();
		} 
		catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) 
		{
			e.printStackTrace();
		}
	}

	public void loadMusic(String id, String filename)
	{ // ask about this description?
		File file = new File(filename);
		this.soundMap.put(id, file);
	}
	
	public void playMusic(String id)
	{ // why would this be different than sound effect
		try 
		{
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(soundMap.get(id)));
			clip.start();
		} 
		catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) 
		{
			e.printStackTrace();
		}
	}
}
