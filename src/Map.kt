import constants.Directions
import java.util.*

class Map {
    var random = Random()
    val roomArray = Array(10, {arrayOf<ROOMS>(ROOMS(), ROOMS(), ROOMS(), ROOMS(), ROOMS(), ROOMS(), ROOMS(), ROOMS(), ROOMS(), ROOMS())})

    var x = 2
    var y = 0

    fun map() {
        //enemy
        var d = 0
        //loot
        var f = 20
        //sword
        var z = 0
        //enmaku
        var c = 0
        //Boss
        var b = 0
        //empty
        var e = 0

        var i = 0
        while (i < 5) {
            for (j in 0..4) {
                var room = ROOMS()
                roomArray[i][j] = room

                room.cont = inside() //contents
                room.status = random.nextInt(8) + 1
                //possible exits
                var v = 0
                while (v < 4) {
                    var directions:Directions = room.dir[random.nextInt(5)]
                    room.exit[v] = directions

                    if (i == 0) {
                        if (directions == Directions.Up) {
                            room.exit[v] = Directions.None
                        }
                    }
                    if (j == 0) {
                        if (directions == Directions.Left) {
                            room.exit[v] = Directions.None
                        }
                    }
                    if (i == 4) {
                        if (directions == Directions.Down) {
                            room.exit[v] = Directions.None
                        }
                    }
                    if (j == 4) {
                        if (directions == Directions.Right) {
                            room.exit[v] = Directions.None
                        }
                    }

                    // check
                    var cont = 0
                    if (directions == Directions.None) {
                        cont++
                    }

                    if (cont >= 1) {
                        v = 0
                    }
                    v++

                }


                if ((i == y) and (j == x)) {//checks
                    room.cont = "Player"
                    room.enmaku = 1
                    //println(" PLAYER ")
                    continue
                }


                if (room.cont.equals("loot") && f >= 0) {
                    room.takara = random.nextInt(3) + 1
                    //println(" Loot:"+room.takara)
                    f--
                } else if (room.cont.equals("enemy") && d != 2) {
                    room.enemy = 1
                    //println(" "+room.cont+ " ")
                    d++
                } else if (room.cont.equals("enmaku") && c != 4) {
                    room.enmaku = 1
                    //println( " enmaku ");
                    c++
                } else if (room.cont.equals("sword") && z != 2) {
                    room.sword = 1
                    //println(" sword ");
                    z++
                    if (i == 5 && z != 0) {
                        i = 0
                    }
                } else if (room.cont.equals("enemy") && d == 2 && b != 1) {
                    room.cont = "boss"
                    room.boss = 1
                    room.takara = 4
                    //println(" BOSS ")
                    //roomArray[i][j].contents();
                    b++
                } else if (room.cont.equals("empty") && e <= 6) {
                    //println(" Empty ")
                    e++
                } else {
                    room.cont = "loot"
                    room.takara = random.nextInt(3) + 3
                    //println(" Loot: "+room.takara);
                }
            }
            //printlnln("");
            if (i == 4 && b <= 0) {
                i = 0
            }
            i++
        }
    }

    fun inside(): String {
        var contents: String?
        val random = Random()
        val name: Int

        name = random.nextInt(5) + 1
        if (name == 1) {
            contents = "loot"
        } else if (name == 2) {
            contents = "enemy"
        } else if (name == 3) {
            contents = "enmaku"
        } else if (name == 4) {
            contents = "sword"
        } else {
            contents = "empty"
        }

        return contents
    }

}
