# OOP (Object Oriented Programming) Simulation
## Overview
A graphical simulation of a world and its inhabitants, using advanced software engineering (object oriented) concepts. All actors have a function within the simulation. The inhabitants (*gatherers*) will gather fruit from trees and deposit them on *stockpiles*. *Thieves* will be stealing fruit from the stockpiles, placing it in their *hoards*. Once both gatherers and theives finish gaining all fruits, they will rest in front of *fences*. 

The UML design overview of the objects, attributes, methods, and associations:
[ObjectOrientedDesign.pdf](https://github.com/EisakuDanielTanaka/OOP-Simulation/files/8250143/ObjectOrientedDesign.pdf)

The behavior of the simulation is completely determined by the *world file*, any csv file that is located within the folder "/res/worlds". It specifices the actor and their placed location at the beginning of the simulation. When all gatherers and thieves reaches a fence, the simulation halts. 

## Running the simulation
```
usage: Simulation <tick rate> <max number of ticks> <world file>
```
The simulation proceeds in *ticks*, where actors make an action in discrete *ticks*. The tick rate (time between ticks) is determined by the command line parameter. The simulation takes three command-line arguments in the order of the *tick rate* (in milliseconds), *maximum number of ticks* (simulation halts when the number of ticks reaches this number), and the *world file*. 
Example (default): 
```
Simulation 100 1000 res/worlds/product.csv
```
For ease of running the simulation, all arguments are stored in ```res/args.txt```. Exceptions will be thrown if 3 arguments are not provided or if the arguments are not valid non-negative integers. 

When the program finishes, the number of elapsed ticks and the amount of fruit at each location are printed to std out.

## Actors
* Trees: Begins with 3 fruits, and the number of fruits the tree holds is rendered at the top-left of its image. When the gatherer lands on the tree, it takes one fruit away from it and they will be at a state of "holding" a fruit. 
* Golden Trees: It has an infinite reserve of fruit, and no number is drawn. Gatherer "holds" a fruit upon stepping on this. 
* Stockpiles: Begins with 0 fruits, and the number is rendered on the top-left. When the gatherer lands on the stockpile, they are no longer at a state of holding a fruit, and one fruit is added on the fruit count of the stockpile. 
* Hoards: Begins with 0 fruits. The thieves "steal"s fruit from the stockpile to place them on to hoards. 
* Pads: Take no action, but are objects placed around the field.
* Fences: Stationary and takes no action upon a tick. Gatherers and thieves will notice to stop when in front of them. 
* Signs: Signs of up, down, left, and right are placed around the field. When actors step on them, they will turn to the corresponding direction. 
* Mitosis Pools: 

## What I learned from this project
- inheritance
- exceptions
- overriding
- Object oriented design: Inhertiance and interfaces
