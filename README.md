# JmeUtilityLibrary
General use library for JMonkeyEngine 3.

# Key Features
* OrbitalCamera
* ES Package
* SceneGraphIterator
* Anim Package (alpha)

# OrbitalCamera
Utility for third person views.

OrbitalCamera can be used right out of the box. No need to set up
inputs or anything.
```<Java>
OrbitalCamera orbitCam = new OrbitalCamera(camera, inputMapper);
playerNode.addControl(orbitCam);
inputMapper.activateGroup(OrbitalCamera.INPUT_GROUP);
```
Automatic input bindings can be disabled.
```
OrbitalCamera orbitCam = new OrbitalCamera(camera);
playerNode.addControl(orbitCam);
```
The camera can be manually rotated around the subject.
```
orbitCam.rotateHorizontally(1f);
orbitCam.rotateVertically(-1f);
```
This utility also provides support for variable up directions.
```
orbitCam.setUpDirection(new Vector3f(...));
```

# ES package
The es package provides utilities for the Zay-ES library.

Features:
* EntityState initializes and manages the EntityData for the project.
* VisualState automatically handles all the visual aspects of your project
* ModelFactories generate models for entities based on their components
* PositionUpdateState, RotationUpdateState, and ScaleUpdateState update the
different transform aspects of spatials to match their entities.
* Variety of common components (Position, Rotation, Scale, Visual, etc.)
* Rudimentary support for physics.
* ESAppState provides easier access to common application fields.

To get started, attach these states before any other states:
```
stateManager.attachAll(
    new EntityData(),
    new VisualState());
```
It is suggested that transform update states also be added (their order is not strict):
```
stateManager.attachAll(
    new PositionUpdateState(),
    new RotationUpdateState(),
    new ScaleUpdateState());
```

**Systems**
* EntityState : Manages EntityData.
* CopyPositionState : Allows one entity to copy the position of another.
* TransformUpdateState : Superclass for transform updating systems.
* VisualState : Oversees creation and deletion of spatials and scenes.

**Components**
* Color : Used mainly by ModelFactories to get the color to use when constructing materials.
* GameObject : An type identifier component.
* Position : Describes the local position of the entity.
* Rotation : Describes the local rotation of the entity.
* Scale : Describes the local rotation of the entity.
* Size : Used by ModelFactories to calculate the size (not scale) to generate the model at.
* SpatialCullHint : Used by SpatialCullState to calculate culling.
* Visual : Any entity that needs a spatial must have this component.

# SceneGraphIterator
Simple utility for iterating through the scene graph.

Usage:
```
Spatial scene = ...;
SceneGraphIterator iterator = new SceneGraphIterator(scene);
for (Spatial spatial : iterator) {
    ...
}
```

Ignore children of the current spatial:
```
iterator.ignoreChildren();
```

# Anim Package (alpha)
The anim package provides one utility for animation using AnimComposer
The utility organizes sets of AnimStates by priority.

This utility is still under development, and their might be some underlying bugs.

Usage:
```<Java>
Spatial person = ...;
AnimState idle = new AnimState("idle", true);
idle.add("body", "idle_body");
idle.add("legs", "idle_legs");
AnimState run = new SynchronizedAnimState("run");
run.add("body", "run_body");
run.add("legs", "run_legs");
AnimState chop = new AnimState("chop");
chop.add("body", "chop_sword_once");
AnimStackControl stack = new AnimStackControl();
stack.add(idle);
stack.add(run);
stack.add(chop);
person.addControl(stack);
```
When the character starts running or is running (does not matter) you can trigger the running animation on both layers ("body" and "legs"):
```<java>
stack.enableState("run", true);
```
And when the character stops running or is not running:
```
stack.enableState("run" false);
```
For the sword chopping AnimState, AnimationStackControl will only play it on the "body" layer and leave the "legs" layer alone. This allows the character to be running and chopping *or* idle and chopping at the same time.

**Node**: AnimStackControl only functions if the spatial it is controlling also has an AnimComposer.