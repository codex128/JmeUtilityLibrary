/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es.components;

import codex.jmeutil.es.factory.ModelFactory;
import com.jme3.bounding.BoundingBox;
import com.jme3.collision.Collidable;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import com.simsilica.es.EntityData;
import com.simsilica.es.EntityId;

/**
 *
 * @author gary
 */
public class BoxCollider implements ColliderComponent {
	
	private final Vector3f demensions = new Vector3f(1f, 1f, 1f);
	private final Vector3f offset = new Vector3f(0f, 0f, 0f);
	
	public BoxCollider() {}
	public BoxCollider(Vector3f demensions) {
		this.demensions.set(demensions);
	}
	public BoxCollider(Vector3f demensions, Vector3f offset) {
		this(demensions);
		this.offset.set(offset);
	}
	
	public Vector3f getDemensions() {
		return demensions;
	}
	public Vector3f getOffset() {
		return offset;
	}
	@Override
	public Collidable getCollidable(EntityData ed, EntityId id, Spatial spatial, Vector3f position) {
		Size size = ModelFactory.getArgumentComponent(ed, id, new Size(demensions));
		return new BoundingBox(position.add(offset), size.getSize().x, size.getSize().y, size.getSize().z);
	}
	@Override
	public String toString() {
		return "BoxCollider["+demensions+"]";
	}
	
}
