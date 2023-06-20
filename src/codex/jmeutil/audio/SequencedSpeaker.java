/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.audio;

/**
 *
 * @author gary
 */
public class SequencedSpeaker implements SpeakerListener {
    
    SFXSpeaker[] tracks;
    int index = 0;
    
    public SequencedSpeaker(SFXSpeaker... tracks) {
        this.tracks = tracks;
        initializeTracks(this.tracks);
    }
    
    private void initializeTracks(SFXSpeaker... tracks) {
        for (int i = 0; i < tracks.length; i++) {
            if (i < tracks.length-1) {
                tracks[i].getModel().setLooping(false);
            }
            tracks[i].addListener(this);
        }
    }  
    
    public void manualUpdate(float tpf) {
        for (SFXSpeaker s : tracks) {
            s.manualUpdate(tpf);
        }
    }    
    public void play() {
        tracks[index].play();
    }
    public void pause() {
        tracks[index].pause();
    }
    public void stop() {
        tracks[index].stop();
        index = 0;
    }

    @Override
    public void onSpeakerPlay(SFXSpeaker speaker) {}
    @Override
    public void onSpeakerPause(SFXSpeaker speaker) {}
    @Override
    public void onSpeakerStop(SFXSpeaker speaker) {
        if (++index < tracks.length) {
            play();
        }
    }
    
}
