/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.scene;

import com.jme3.scene.Spatial;
import java.util.HashMap;

/**
 *
 * @author gary
 * @param <T>
 */
public class ObjectTypeFunction <T> implements SceneKeywordFunction<T> {

	private final String keyword;
	private HashMap<T, SceneKeywordFunction<T>> types = new HashMap<>();
	
	public ObjectTypeFunction(String keyword) {
		this.keyword = keyword;
	}
	
	@Override
	public String getKeyword() {
		return keyword;
	}
	@Override
	public void accept(T value, Spatial spatial) {
		SceneKeywordFunction<T> type = types.get(value);
		if (type != null) type.accept(value, spatial);
	}
	
	public void addType(T key, SceneKeywordFunction<T> type) {
		types.put(key, type);
	}
	
}
