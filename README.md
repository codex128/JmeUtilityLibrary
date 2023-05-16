# JmeUtilityLibrary
General use library for JMonkeyEngine 3.

# Anim Package
The anim package provides one utility for animation using AnimComposer
The utility organizes sets of AnimStates by priority.

Usage:
```<java>
AnimationStackControl stack = new AnimationStackControl();
mySpatial.addControl(stack);
// idle
AnimState idle = stack.add(new AnimState("idle"));
idle.add("body", "idle_body");
idle.add("legs", "idle_legs");
// running
AnimState run = stack.add(new AnimState("run", false));
run.add("body", "run_body");
run.add("legs", "run_legs");
// chop with sword
AnimState chop = stack.add(new AnimState("chop", false));
chop.add("body", "sword_chop");
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