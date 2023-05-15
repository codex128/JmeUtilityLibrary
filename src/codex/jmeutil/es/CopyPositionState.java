/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es;

import codex.jmeutil.es.components.CopyPosition;
import codex.jmeutil.es.components.Position;
import com.jme3.app.Application;
import com.simsilica.es.Entity;
import com.simsilica.es.EntitySet;

/**
 *
 * @author gary
 */
public class CopyPositionState extends ESAppState {
	
	EntitySet copiers;
	
	@Override
	protected void init(Application app) {
		copiers = ed.getEntities(Position.class, CopyPosition.class);
	}
	@Override
	protected void cleanup(Application app) {
		copiers.release();
		copiers = null;
	}
	@Override
	protected void onEnable() {}
	@Override
	protected void onDisable() {}
	@Override
	public void update(float tpf) {
		copiers.applyChanges();
		for (Entity e : copiers) {
			CopyPosition cp = e.get(CopyPosition.class);
			Position pos = ed.getComponent(cp.getCopiedEntity(), Position.class);
			if (pos != null) {
				e.set(new Position(cp.getConstraint().apply(pos.getPosition(), e.get(Position.class).getPosition())));
			}
		}
	}
	
}
