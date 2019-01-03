package org.hexworks.cavesofzircon.world

import org.hexworks.zircon.api.data.impl.Size3D

class Game(val world: World,
           val worldSize: Size3D = world.actualSize(),
           val visibleSize: Size3D = world.visibleSize())
