/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.audio;

import com.jme3.asset.AssetManager;
import java.util.LinkedList;

/**
 *
 * @author gary
 */
public class MultiplayerSpeaker {
    
    SFXSpeaker speaker;
    LinkedList<Source> sources = new LinkedList<>();
    
    public MultiplayerSpeaker(SFXSpeaker speaker) {
        this.speaker = speaker;
    }
    
    private Source getSource(Object source) {
        return sources.stream().filter(s -> s.source == source).findAny().orElse(null);
    }
    private void refresh() {
        if (!sources.isEmpty() && sources.stream().anyMatch(s -> s.emitting)) {
            if (!speaker.isPlaying()) speaker.play();
        }
        else if (speaker.isPlaying()) {
            speaker.stop();
        }
    }
    
    public void addSource(Object source) {
        if (!sources.stream().anyMatch(s -> s.source == source)) {
            sources.add(new Source(source));
            refresh();
        }
    }
    public void removeSource(Object source) {
        Source src = getSource(source);
        if (src != null) {
            sources.remove(src);
            refresh();
        }
    }
    public void setSourceEmitting(Object source, boolean emitting) {
        Source src = getSource(source);
        if (src != null) {
            src.emitting = emitting;
            refresh();
        }
    }
    public boolean isEmitting(Object source) {
        Source src = getSource(source);
        if (src != null) return src.emitting;
        else return false;
    }
    public void clearAllEmitters() {
        sources.clear();
        refresh();
    }
    
    public SFXSpeaker getSpeaker() {
        return speaker;
    }
    
    private static final class Source {
        Object source;
        boolean emitting = false;
        private Source(Object source) {
            this.source = source;
        }
    }
    
}
