/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.audio;

import codex.j3map.J3map;
import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioData;
import com.jme3.audio.AudioNode;

/**
 *
 * @author gary
 */
public class AudioModel {
	
	public static final String
			BUFFER = "buffer",
			STREAM = "stream";
	
	private String sourceFile;
	private float volume;
	private float pitch;
	private boolean looping;
	private boolean positional;
	private boolean directional;
	private boolean reverb;
	private float timeOffset;
	private float refDistance;
	private float maxDistance;
	private AudioData.DataType dataType;
	
	public AudioModel(AudioNode reference) {
		volume = reference.getVolume();
		pitch = reference.getPitch();
		looping = reference.isLooping();
		positional = reference.isPositional();
		directional = reference.isDirectional();
		reverb = reference.isReverbEnabled();
		timeOffset = reference.getTimeOffset();
		refDistance = reference.getRefDistance();
		maxDistance = reference.getMaxDistance();
		dataType = reference.getType();
	}
	public AudioModel(J3map source) {
		sourceFile = source.getString("source");
		volume = source.getFloat("volume", 1f);
		pitch = source.getFloat("pitch", 1f);
		looping = source.getBoolean("looping", false);
		positional = source.getBoolean("positional", true);
		directional = source.getBoolean("directional", false);
		reverb = source.getBoolean("reverb", false);
		timeOffset = source.getFloat("time_offset", 0f);
		refDistance = source.getFloat("ref_distance", 10f);
		maxDistance = source.getFloat("max_distance", 20f);
		String type = source.getString("data_type", BUFFER);
		if (type.equals(BUFFER)) dataType = AudioData.DataType.Buffer;
		else if (type.equals(STREAM)) dataType = AudioData.DataType.Stream;
		else {
			throw new IllegalArgumentException("Audio data type \""+type+"\" is not supported!");
		}
	}
	public AudioModel(AudioModel model) {
		sourceFile = model.sourceFile;
		volume = model.volume;
		pitch = model.pitch;
		looping = model.looping;
		positional = model.positional;
		directional = model.directional;
		reverb = model.reverb;
		timeOffset = model.timeOffset;
		refDistance = model.refDistance;
		maxDistance = model.maxDistance;
		dataType = model.dataType;
	}
	
	public void setSourceFile(String sourceFile) {
		this.sourceFile = sourceFile;
	}
	public void setVolume(float volume) {
		this.volume = volume;
	}
	public void setPitch(float pitch) {
		this.pitch = pitch;
	}
	public void setLooping(boolean looping) {
		this.looping = looping;
	}
	public void setPositional(boolean positional) {
		this.positional = positional;
	}
	public void setDirectional(boolean directional) {
		this.directional = directional;
	}
	public void setReverb(boolean reverb) {
		this.reverb = reverb;
	}
	public void setTimeOffset(float timeOffset) {
		this.timeOffset = timeOffset;
	}
	public void setRefDistance(float refDistance) {
		this.refDistance = refDistance;
	}
	public void setMaxDistance(float maxDistance) {
		this.maxDistance = maxDistance;
	}
	public void setDataType(AudioData.DataType dataType) {
		this.dataType = dataType;
	}
	
	public String getSourceFile() {
		return sourceFile;
	}
	public float getVolume() {
		return volume;
	}
	public float getPitch() {
		return pitch;
	}
	public boolean isLooping() {
		return looping;
	}
	public boolean isPositional() {
		return positional;
	}
	public boolean isDirectional() {
		return directional;
	}
	public boolean isReverb() {
		return reverb;
	}
	public float getTimeOffset() {
		return timeOffset;
	}
	public float getRefDistance() {
		return refDistance;
	}
	public float getMaxDistance() {
		return maxDistance;
	}	
	public AudioData.DataType getDataType() {
		return dataType;
	}
	@Override
	public String toString() {
		return "AudioModel[...]";
	}
	
	public boolean isStreaming() {
		return dataType == AudioData.DataType.Stream;
	}
	
	public SFXSpeaker toSFXSpeaker(AssetManager assets) {
		return new SFXSpeaker(assets, this);
	}
	public SFXSpeaker toSFXSpeaker(AssetManager assets, String source) {
		return new SFXSpeaker(assets, source, this);
	}
	public AudioNode toAudioNode(AssetManager assets) {
		return toAudioNode(assets, sourceFile);
	}
	public AudioNode toAudioNode(AssetManager assets, String source) {
		AudioNode audio = new AudioNode(assets, source, dataType);
		audio.setVolume(volume);
		audio.setPitch(pitch);
		audio.setLooping(looping);
		audio.setPositional(positional);
		audio.setDirectional(directional);
		audio.setReverbEnabled(reverb);
		audio.setTimeOffset(timeOffset);
		audio.setRefDistance(refDistance);
		audio.setMaxDistance(maxDistance);
		return audio;
	}
	
}
