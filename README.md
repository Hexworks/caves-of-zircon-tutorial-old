# Norn

A roguelike based on the  [How to Make a Roguelike](https://hexworks.org/posts/tutorials/2018/11/04/how-to-make-a-roguelike.html)
tutorial.

Upcoming TODOs:
* targeted spells
* spells UI
* energy system
* removing Caves of Zircon systems not being used
* NPC actions only during their turn / entity isYourTurn() functionality in general

Useful notes:
* Event subscription happens in Views
* Logging functions are in Functions
* Systems: behaviors update internal state, facets let the world interact with you. Should never hold state
* Attributes for metadata / state. Dumb data structure - should never have behavior
* Entity: collection of systems and attributes
* Each entity must have an entity type
* 