/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.audio;

import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioParam;

/**
 *
 * @author gary
 */
public class RevSpeaker extends SFXSpeaker {
    
    float fadespeed = .1f;
    int direction = 0;
    
    public RevSpeaker(AssetManager assets, AudioModel model) {
        super(assets, model);
        volumeFactor = 0f;
    }
    public RevSpeaker(AssetManager assets, AudioModel model, float fadespeed) {
        this(assets, model);
        this.fadespeed = fadespeed;
    }
    
    public float getFadeSpeed() {
        return fadespeed;
    }
    public void setFadeSpeed(float fadespeed) {
        this.fadespeed = fadespeed;
    }
    
    @Override
    public void play() {
        super.play();
        direction = 1;
    }
    @Override
    public void stop() {
        direction = -1;
    }
    @Override
    protected void update(float tpf) {
        super.update(tpf);
        if (direction != 0) {
            volumeFactor += fadespeed*direction;
            if (volumeFactor >= 1f) {
                volumeFactor = 1f;
                direction = 0;
            }
            else if (volumeFactor <= 0f) {
                volumeFactor = 0f;
                direction = 0;
                super.stop();
            }
            updateSourceParam(AudioParam.Volume);
        }
    }
    
}
