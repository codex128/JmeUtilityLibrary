/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codex.jmeutil.gui;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.scene.Node;
import java.util.HashMap;

/**
 *
 * @author gary
 */
public class GuiLayerManager extends BaseAppState {
    
    private static final String USERDATA_LAYER = "GuiLayerManager[guiLayerIndex]";
    
    private Node layerMaster = new Node("guiLayerMaster");
    private HashMap<Integer, Node> layers = new HashMap<>();
    private float interval = 1f;
    
    @Override
    protected void initialize(Application app) {
        if (app instanceof SimpleApplication) {
            Node guiNode = ((SimpleApplication)app).getGuiNode();
            guiNode.attachChild(layerMaster);
        }
    }
    @Override
    protected void cleanup(Application app) {
        layers.values().stream().forEach(n -> n.removeFromParent());
        layers.clear();
        layerMaster.removeFromParent();
    }
    @Override
    protected void onEnable() {}
    @Override
    protected void onDisable() {}
    
    /**
     * Gets the layer at the specified z index.
     * <p>Or creates a new layer.
     * @param z
     * @return 
     */
    public Node getLayer(int z) {
        return getLayer(z, false);
    }
    /**
     * Gets the layer at the specified z index.
     * @param z
     * @param sneaky if true, will not create a new layer if none is found.
     * @return 
     */
    public Node getLayer(int z, boolean sneaky) {
        Node n = layers.get(z);
        if (n == null && !sneaky) {
            n = createLayer(z);
        }
        return n;
    }
    /**
     * Removes the layer at the specified z index if it has no children.
     * @param z
     * @param force if true, remove the layer even if it has children
     * @return removed node
     */
    public Node removeLayer(int z, boolean force) {
        Node n = layers.get(z);
        if (n != null && (force || n.getChildren().isEmpty()) && layers.remove(z, n)) {
            n.setUserData(USERDATA_LAYER, null);
            return n;
        }
        return null;
    }
    
    private Node createLayer(int z) {
        Node n = new Node("guiLayer(z="+z+")");
        n.setLocalTranslation(0f, 0f, interval*z);
        n.setUserData(USERDATA_LAYER, z);
        layerMaster.attachChild(n);
        layers.put(z, n);
        return n;
    }
    private void refreshLayerPositions() {
        layers.values().stream().forEach(n -> n.setLocalTranslation(0f, 0f, this.interval*indexOf(n, true)));
    }
    
    /**
     * Sets the interval between layers.
     * <p>Recalculates all layer positions if the interval is changed.
     * @param interval 
     */
    public void setInterval(float interval) {
        assert interval > 0;
        if (this.interval == interval) return;
        this.interval = interval;
        refreshLayerPositions();
    }    
    public Node getLayerMaster() {
        return layerMaster;
    }
    public int getNumLayers() {
        return layers.size();
    }
    /**
     * Returns the interval between layers.
     * @return 
     */
    public float getInterval() {
        return interval;
    }
    
    /**
     * Returns the z index of the layer node.
     * <p>If the node is not currently a layer node, will return {@code Integer.MIN_VALUE}.
     * @param layer
     * @param failOnMiss if true and the node is not a layer node, then an exception will be thrown.
     * @return z index, or {@code Integer.MIN_VALUE}.
     */
    public static int indexOf(Node layer, boolean failOnMiss) {
        Integer z = layer.getUserData(USERDATA_LAYER);
        if (z != null) {
            return z;
        }
        if (failOnMiss) {
            throw new NullPointerException(layer+" is not a layer node or is missing user data!");
        }
        return Integer.MIN_VALUE;
    }
    /**
     * Returns true if the specified node is a layer node.
     * @param n
     * @return 
     */
    public static boolean isLayer(Node n) {
        return n.getUserData(USERDATA_LAYER) != null;
    }
    
}
