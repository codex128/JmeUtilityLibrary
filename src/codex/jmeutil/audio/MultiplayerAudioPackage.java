/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.audio;

import java.util.HashMap;
import java.util.function.Consumer;

/**
 *
 * @author gary
 */
public class MultiplayerAudioPackage {
    
    HashMap<String, MultiplayerSpeaker> audio = new HashMap<>();
    
    public boolean addAudio(String name, MultiplayerSpeaker speaker) {
        return audio.putIfAbsent(name, speaker) == null;
    }
    public MultiplayerSpeaker getAudio(String name) {
        return audio.get(name);
    }
    public MultiplayerSpeaker removeAudio(String name) {
        return audio.remove(name);
    }
    public void clearAllAudio() {
        audio.clear();
    }
    
    public void forEachAudio(Consumer<MultiplayerSpeaker> foreach) {
        audio.values().stream().forEach(foreach);
    }
    public void update(float tpf) {
        forEachAudio(a -> a.getSpeaker().manualUpdate(tpf));
    }
    
    public void registerToAll(Object source) {
        audio.values().stream().forEach(s -> s.addSource(source));
    }
    public void removeFromAll(Object source) {
        audio.values().stream().forEach(s -> s.removeSource(source));
    }
    
}
