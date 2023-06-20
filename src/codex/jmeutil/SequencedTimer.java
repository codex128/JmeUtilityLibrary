/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil;

/**
 *
 * @author gary
 */
public class SequencedTimer extends Timer {
    
    float[] sequence;
    int index = 0;
    
    public SequencedTimer(float... sequence) {
        super(0f);
        this.sequence = sequence;
    }
    
    @Override
    public float getDuration() {
        return sequence[index];
    }
    @Override
    public int getCycleNum() {
        return index;
    }
    @Override
    public int getCycleMax() {
        return sequence.length;
    }
    @Override
	public void update(float tpf) {
		if (index < sequence.length && start && (time += tpf) >= getDuration() && getDuration() >= 0f) {
			time = getDuration();
            if (++index < sequence.length) {
                time = 0f;
            }
            else {
                start = false;  
            }
            notifyListeners(l -> l.onTimerFinish(this));
		}
	}
    
    public float[] getDurationSequence() {
        return sequence;
    }
    
}
