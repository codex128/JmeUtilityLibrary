/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package codex.jmeutil.es.factory;

import codex.jmeutil.es.bullet.EntityPhysics;
import codex.jmeutil.es.bullet.EntityRigidBodyControl;
import codex.jmeutil.es.components.Mass;
import codex.jmeutil.es.components.Physics;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.scene.Spatial;

/**
 *
 * @author gary
 */
public interface PhysicsFactory {
	
	public static final PhysicsFactory
			DEFAULT_FACTORY = (PhysicsManufactureTools tools) -> null;
	
	public static final String
			AUTO = "auto_physics_type",
			BOX = "def_box",
			DYNAMIC_MESH = "def_dyn_mesh",
			MERGED_BOX = "def_merged_box",
			MERGED_HULL = "def_merged_hull",
			MERGED_MESH = "def_merged_mesh",
			MESH = "def_mesh";
	
	public default EntityPhysics manufactureEntityPhysics(PhysicsManufactureTools tools) {
		String type = tools.ed.getComponent(tools.entity, Physics.class).getType();
		boolean auto = type.equals(AUTO);
		CollisionShape shape = (!auto ? getCollisionShape(type, tools.spatial) : null);
		EntityRigidBodyControl rigidbody;
		if (shape != null) {
			rigidbody = new EntityRigidBodyControl(shape, getMass(tools));
		}
		else if (auto) {
			rigidbody = new EntityRigidBodyControl(getMass(tools));
		}
		else {
			return createPhysics(tools);
		}
		tools.spatial.addControl(rigidbody);
		return rigidbody;
	}
	public EntityPhysics createPhysics(PhysicsManufactureTools tools);
	
	public default CollisionShape getCollisionShape(String type, Spatial spatial) {
		switch (type) {
			case BOX: return CollisionShapeFactory.createBoxShape(spatial);
			case DYNAMIC_MESH: return CollisionShapeFactory.createDynamicMeshShape(spatial);
			case MERGED_BOX: return CollisionShapeFactory.createMergedBoxShape(spatial);
			case MERGED_HULL: return CollisionShapeFactory.createMergedHullShape(spatial);
			case MERGED_MESH: return CollisionShapeFactory.createMergedMeshShape(spatial);
			case MESH: return CollisionShapeFactory.createMeshShape(spatial);
			default: return null;
		}
	}
	public default float getMass(PhysicsManufactureTools tools) {
		Mass mass = tools.ed.getComponent(tools.entity, Mass.class);
		if (mass != null) return mass.getMass();
		else return 0f;
	}
	
}
