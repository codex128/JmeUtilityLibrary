/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.vfx;

import com.jme3.shader.VarType;

/**
 *
 * @author gary
 */
public interface MaterialInfluencer {
	
	/**
	 * Returns the name of the influenced parameter
	 * @return 
	 */
	public abstract String getParameterName();
	/**
	 * Returns the <code>VarType</code> of the influenced parameter
	 * @return 
	 */
	public abstract VarType getParameterType();
	/**
	 * Returns the value of the influenced parameter.
	 * @param tpf
	 * @return 
	 */
	public abstract Object getParameterValue(float tpf);
	
}
