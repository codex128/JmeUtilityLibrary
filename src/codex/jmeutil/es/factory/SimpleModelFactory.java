/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.es.factory;

import codex.jmeutil.es.VisualState;
import codex.jmeutil.es.components.Color;
import codex.jmeutil.es.components.MeshSamples;
import codex.jmeutil.es.components.Size;
import codex.jmeutil.es.components.Visual;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Cylinder;
import com.jme3.scene.shape.Sphere;

/**
 * A simple implementation of ModelFactory.
 * 
 * Is able to construct boxes.
 * 
 * @author gary
 */
public class SimpleModelFactory implements ModelFactory {
	
	public static final String
			NODE = "simple_node",
			BOX = "simple_box",
			CYLINDER = "simple_cylinder",
			SPHERE = "simple_sphere";
	
	@Override
	public Spatial createModel(ModelManufactureTools tools) {
		switch (tools.ed.getComponent(tools.entity, Visual.class).getModel()) {
			case NODE:
				return createNode(tools);
			case BOX:
				return createBox(tools);
			case CYLINDER:
				return createCylinder(tools);
			case SPHERE:
				return createSphere(tools);
			default:
				return createOtherModel(tools);
		}
	}
	
	protected Spatial createNode(ModelManufactureTools tools) {
		return new Node();
	}
	protected Spatial createBox(ModelManufactureTools tools) {
		Size size = ModelFactory.getArgumentComponent(tools, new Size(new Vector3f(1f, 1f, 1f)));
		Color color = ModelFactory.getArgumentComponent(tools, new Color(ColorRGBA.randomColor()));
		Box box = new Box(size.getSize().x, size.getSize().y, size.getSize().z);
		Geometry geometry = new Geometry(BOX, box);
		geometry.setMaterial(VisualState.createSimpleMaterial(tools.assetManager, color.getColor()));
		return geometry;
	}
	protected Spatial createCylinder(ModelManufactureTools tools) {
		MeshSamples samples = ModelFactory.getArgumentComponent(tools, new MeshSamples(2, 16));
		Size size = ModelFactory.getArgumentComponent(tools, new Size(1f, 2f, 1f));
		Color color = ModelFactory.getArgumentComponent(tools, new Color(ColorRGBA.randomColor()));
		Mesh mesh = new Cylinder(samples.getAxis(), samples.getRadial(), size.getSize().x, size.getSize().z, size.getSize().y, true, false);
		Geometry cylinder = new Geometry(CYLINDER, mesh);
		cylinder.setMaterial(VisualState.createSimpleMaterial(tools.assetManager, color.getColor()));
		return cylinder;
	}
	protected Spatial createSphere(ModelManufactureTools tools) {
		MeshSamples samples = ModelFactory.getArgumentComponent(tools, new MeshSamples(2, 16));
		Size size = ModelFactory.getArgumentComponent(tools, new Size(1f, 2f, 1f));
		Color color = ModelFactory.getArgumentComponent(tools, new Color(ColorRGBA.randomColor()));
		Mesh mesh = new Sphere(samples.getAxis(), samples.getRadial(), size.getSize().x);
		Geometry cylinder = new Geometry(CYLINDER, mesh);
		cylinder.setMaterial(VisualState.createSimpleMaterial(tools.assetManager, color.getColor()));
		return cylinder;
	}
	
	protected Spatial createOtherModel(ModelManufactureTools tools) {
		return null;
	}
	
}
