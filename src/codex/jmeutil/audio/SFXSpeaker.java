/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.audio;

import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioContext;
import com.jme3.audio.AudioData;
import com.jme3.audio.AudioKey;
import com.jme3.audio.AudioParam;
import com.jme3.audio.AudioRenderer;
import com.jme3.audio.AudioSource;
import com.jme3.audio.Filter;
import com.jme3.math.Matrix3f;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/**
 *
 * @author gary
 */
public class SFXSpeaker extends Node implements AudioSource {
	
	AudioModel model;
	AudioData data;
	int channel = -1;
	Filter dryfilter;
	Filter reverbfilter;
	AudioSource.Status status = AudioSource.Status.Stopped;
	Vector3f velocity = new Vector3f();
	Vector2f angles = new Vector2f(360f, 360f); // inner, outer
	float volumeFactor = 1f;
	
	public SFXSpeaker(AudioData data, AudioModel model) {
		this.data = data;
		this.model = model;
	}
	public SFXSpeaker(AssetManager assets, AudioModel model) {
		this(assets, model.getSourceFile(), model, true);
	}
	public SFXSpeaker(AssetManager assets, String name, AudioModel model) {
		this(assets, name, model, true);
	}
	public SFXSpeaker(AssetManager assets, AudioModel model, boolean cache) {
		this(assets, model.getSourceFile(), model, cache);
	}
	public SFXSpeaker(AssetManager assets, String name, AudioModel model, boolean cache) {
		AudioKey key = new AudioKey(name, model.isStreaming(), true);
		data = assets.loadAudio(key);
		this.model = model;
	}
	
	public void playInstance() {
		if (isPositional() && !supportsPositional()) {
			throw new IllegalStateException("Positional audio only supports one channel!");
		}
		getRenderer().playSourceInstance(this);
	}
	public void play() {
		if (isPositional() && !supportsPositional()) {
			throw new IllegalStateException("Positional audio only supports one channel!");
		}
		getRenderer().playSource(this);
	}
	public void stop() {
		getRenderer().stopSource(this);
	}
	public void pause() {
		getRenderer().pauseSource(this);
	}
	
	protected AudioRenderer getRenderer() {
        AudioRenderer renderer = AudioContext.getAudioRenderer();
        if (renderer == null) {
            throw new IllegalStateException("No audio renderer available!");
		}
        return renderer;
    }
	protected void updateSourceParam(AudioParam param) {
		if (isPlaying()) {
			getRenderer().updateSourceParam(this, param);
		}
	}
	public AudioModel getModel() {
		return model;
	}
	public boolean supportsPositional() {
		return data.getChannels() == 1;
	}
	public boolean isPlaying() {
		return channel >= 0;
	}
	public float getVolumeFactor() {
		return volumeFactor;
	}
	
	public void setDryFilter(Filter dryfilter) {
		this.dryfilter = dryfilter;
		updateSourceParam(AudioParam.DryFilter);
	}
	public void setReverbFilter(Filter reverbfilter) {
		this.reverbfilter = reverbfilter;
		updateSourceParam(AudioParam.ReverbFilter);
	}
	public void setVelocity(Vector3f velocity) {
		this.velocity.set(velocity);
		updateSourceParam(AudioParam.Velocity);
	}
	public void setInnerAngle(float inner) {
		angles.x = inner;
		updateSourceParam(AudioParam.InnerAngle);
	}
	public void setOuterAngle(float outer) {
		angles.y = outer;
		updateSourceParam(AudioParam.OuterAngle);
	}
	public void setAngles(float inner, float outer) {
		setInnerAngle(inner);
		setOuterAngle(outer);
	}
	public void setVolumeFactor(float volFactor) {
		this.volumeFactor = volFactor;
		updateSourceParam(AudioParam.Volume);
	}
	
	@Override
	public void setLocalTranslation(Vector3f translation) {
		super.setLocalTranslation(translation);
		updateSourceParam(AudioParam.Position);
	}
	@Override
	public void setLocalTranslation(float x, float y, float z) {
		super.setLocalTranslation(x, y, z);
		updateSourceParam(AudioParam.Position);
	}
	@Override
	public void setLocalRotation(Quaternion rotation) {
		super.setLocalRotation(rotation);
		updateSourceParam(AudioParam.Direction);
	}
	@Override
	public void setLocalRotation(Matrix3f rotation) {
		super.setLocalRotation(rotation);
		updateSourceParam(AudioParam.Direction);
	}
	@Override
	public SFXSpeaker move(Vector3f move) {
		super.move(move);
		updateSourceParam(AudioParam.Position);
		return this;
	}
	@Override
	public SFXSpeaker move(float x, float y, float z) {
		super.move(x, y, z);
		updateSourceParam(AudioParam.Position);
		return this;
	}
	@Override
	public SFXSpeaker rotate(Quaternion rotate) {
		super.rotate(rotate);
		updateSourceParam(AudioParam.Direction);
		return this;
	}
	@Override
	public SFXSpeaker rotate(float xAngle, float yAngle, float zAngle) {
		super.rotate(xAngle, yAngle, zAngle);
		updateSourceParam(AudioParam.Direction);
		return this;
	}
	
	@Override
	public void setChannel(int channel) {
		this.channel = channel;
	}
	@Override
	public int getChannel() {
		return channel;
	}
	@Override
	public Filter getDryFilter() {
		return dryfilter;
	}
	@Override
	public AudioData getAudioData() {
		return data;
	}
	@Override
	public void setStatus(Status status) {
		this.status = status;
	}
	@Override
	public Status getStatus() {
		return status;
	}
	@Override
	public boolean isLooping() {
		return model.isLooping();
	}
	@Override
	public float getPitch() {
		return model.getPitch();
	}
	@Override
	public float getVolume() {
		return model.getVolume()*getVolumeFactor();
	}
	@Override
	public float getTimeOffset() {
		return model.getTimeOffset();
	}
	@Override
	public float getPlaybackTime() {
		if (channel >= 0) return getRenderer().getSourcePlaybackTime(this);
		else return 0f;
	}
	@Override
	public Vector3f getPosition() {
		return getWorldTranslation();
	}
	@Override
	public Vector3f getVelocity() {
		return velocity;
	}
	@Override
	public boolean isReverbEnabled() {
		return model.isReverb();
	}
	@Override
	public Filter getReverbFilter() {
		return reverbfilter;
	}
	@Override
	public float getMaxDistance() {
		return model.getMaxDistance();
	}
	@Override
	public float getRefDistance() {
		return model.getRefDistance();
	}
	@Override
	public boolean isDirectional() {
		return model.isDirectional();
	}
	@Override
	public Vector3f getDirection() {
		return getWorldRotation().getRotationColumn(2);
	}
	@Override
	public float getInnerAngle() {
		return angles.x;
	}
	@Override
	public float getOuterAngle() {
		return angles.y;
	}
	@Override
	public boolean isPositional() {
		return model.isPositional();
	}
	
}
