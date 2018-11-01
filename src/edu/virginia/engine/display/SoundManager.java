package edu.virginia.engine.display;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundManager {
	
	private HashMap<String, File> soundMap;
	
	private SoundManager()
	{
		this.soundMap = new HashMap<String, File>();
	}
	
	public void LoadSoundEffect(String id, String filename)
	{
		File file = new File(filename);
		this.soundMap.put(id, file);
	}
	
	public void PlaySoundEffect(String id)
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

	public void LoadMusic(String id, String filename)
	{ // ask about this description?
		File file = new File(filename);
		this.soundMap.put(id, file);
	}
	
	public void PlayMusic(String id)
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
