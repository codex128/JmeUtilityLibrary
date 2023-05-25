
## 5/25/23
**Changes**
* Integrated physics into ES package.
* Changed the "component to world" interface, it now supports "world to component". This is important for physics integration.
* `EntityPhysics` interface provides information for updating and fetching physics body information. All physics bodies in the ES system must implement this interface.

**Concerns**
* `EntityPhysics` only supports several points of information, which would make it difficult for users to control or watch other facets with entities.
* Writing `ComponentUpdater`s is more difficult and time consuming than it ought to be.