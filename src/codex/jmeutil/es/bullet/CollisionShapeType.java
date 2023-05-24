/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package codex.jmeutil.es.bullet;

/**
 *
 * @author gary
 */
public enum CollisionShapeType {
	
	Default("Default-Shape"),
	Box("Box-Shape"),
	DynamicMesh("Dynamic-Mesh-Shape"),
	MergedBox("Merged-Box-Shape"),
	MergedHull("Merged-Hull-Shape"),
	MergedMesh("Merged-Mesh-Shape"),
	Mesh("Mesh-Shape"),
	VHACD("VHACD-Shape");
	
	private String name;	
	private CollisionShapeType(String name) {
		this.name = name;
	}	
	@Override
	public String toString() {
		return name;
	}
	
}
