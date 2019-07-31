import constants.Directions
import java.util.*

class Move {
    var USERINPUT = Scanner(System.`in`)
    companion object {
        var flag: Int = 0

        fun flag() {
            flag = 1
            return
        }
    }

    fun moving() {
        val map1 = Map()
        map1.map()
        val bat1 = Battle()
        val random = Random()
        val `in` = Scanner(System.`in`)

        var gameActive = true
        var x = map1.x
        var y = map1.y

        while (gameActive) {

            //map
            println("PlayerHP is:" + Kotlinraider.player1.playerHP)
            println("Smoke Bombs:  " + Kotlinraider.player1.smoke)
            println("Treasure: " + Kotlinraider.player1.takara)
            println("Radar: " + Kotlinraider.player1.radar)
            println("")
            println("Mini Map:")
            for (i in 0..4) {// prints columns
                for (j in 0..4) {//prints rows
                    if ((i == y) and (j == x)) {//checks
                        print(" o  ")
                    } else {
                        print("XXX ")
                    }
                }
                println("")
            }
            //directions
            //Thread.sleep(1000)
            var left = 0
            var right = 0
            var up = 0
            var down = 0

            var room= map1.roomArray[y][x]

            for (i in 0..3) {
                var door = room.exit[i]
                if (door == Directions.left) {
                    left++
                }
                if (door == Directions.right) {
                    right++
                }
                if (door == Directions.up) {
                    up++
                }
                if (door == Directions.down) {
                    down++
                }
            }

            println("")
            if (left >= 1) {
                println("You can move left")
            }
            if (right >= 1) {
                println("You can move right")
            }
            if (up >= 1) {
                println("You can move up")
            }
            if (down >= 1) {
                println("You can move down")
            }
            println("")

            //Player
            if (map1.roomArray[y][x]!!.cont === "Player") {
                if (flag == 0) {
                    println("")
                    println("You are at the start")
                    println("")
                } else {
                    println("")
                    println("You did it!")
                    Kotlinraider().winCondition()
                }
            }

            //Battle
            if (map1.roomArray[y][x]!!.cont === "enemy") {
                if (Kotlinraider.enemies.enemyHP <= 0) {
                    println("")
                    println("Enemy is dead")
                    println("")
                } else {
                    if (Kotlinraider.player1.smoke === 0) {
                        println("")
                        println("You have no smokes")
                        println("Prepare to fight")
                        if (bat1.EnemyHP >= 0) {
                            println("")
                            println("ENEMY")
                            bat1.battle()
                        } else {
                            println("")
                            println("Enemy is dead")
                            println("")
                        }
                    } else if (Kotlinraider.player1.smoke >= 0) {
                        println("")
                        println("Enemy in the room")
                        println("Use smoke? (y/n)")
                        val sm = `in`.next()
                        if (sm == "y") {
                            var stealth = 0
                            if (Kotlinraider.player1.smoke !== 0) {
                                stealth = random.nextInt(4) + 5
                                if (map1.roomArray[y][x]!!.status <= stealth) {
                                    System.out.println("Room stealth status is: " + map1.roomArray[y][x]!!.status)
                                    println("Your stealth status is: $stealth")
                                    Kotlinraider.player1.smoke--
                                    System.out.println("Smoke left: ${Kotlinraider.player1.smoke}")
                                    println("")
                                    println("You distracted the enemy! Get out of there!")
                                    println("")
                                } else {
                                    println("Your Status is too visible")
                                    println("You have to fight")
                                    if (bat1.EnemyHP >= 0) {
                                        println("")
                                        println("ENEMY")
                                        bat1.battle()
                                    } else {
                                        println("")
                                        println("Enemy is dead")
                                        println("")
                                    }
                                }
                            }
                        } else if (sm == "n") {
                            println("")
                            println("Prepare to fight")
                            if (bat1.EnemyHP >= 0) {
                                println("")
                                println("ENEMY")
                                bat1.battle()
                            } else {
                                println("")
                                println("Enemy is dead")
                                println("")
                            }
                        } else {
                            println("Incorrect input")
                            println("")
                            continue
                        }
                    }
                }
            }

            //boss
            if (map1.roomArray[y][x]!!.cont === "boss") {
                if (Kotlinraider.enemies.bossHP <= 0) {
                    println("")
                    println("The boss is dead")
                    println("")
                } else {
                    if (Kotlinraider.enemies.bossHP <= 0) {
                        println("")
                        println("The boss is dead")
                        println("")
                        break
                    }
                    if (Kotlinraider.player1.smoke <= 3) {
                        println("")
                        println("Boss Room")
                        println("You have not enough smokes to distract him")
                        println("Prepare to fight the Boss")
                        if (bat1.bossHP >= 0) {
                            println("")
                            println("BOSS")
                            bat1.bossBattle()
                        } else {
                            println("")
                            println("The boss is dead")
                            println("")
                        }
                    }

                    if (Kotlinraider.player1.smoke >= 3) {
                        println("")
                        println("Boss Room")
                        println("You have enough smokes to distract him and fight")
                        println("Use smoke? (y/n)")
                        val sms = `in`.next()
                        if (sms == "y") {
                            var stealth = 0
                            if (Kotlinraider.player1.smoke >= 3) {
                                stealth = 10
                                if (map1.roomArray[y][x]!!.status <= stealth) {
                                    println("Your stealth status is: $stealth")
                                    Kotlinraider.player1.smoke = 0
                                    System.out.println("Smoke left: " + Kotlinraider.player1.smoke)
                                    Kotlinraider.enemies.bossHP = 200
                                    println("Boss can't see you!")
                                    println("Boss HP is cut in half!")
                                    println("")
                                    bat1.bossBattle()
                                } else {
                                    println("Your Status is too visible")
                                    println("You have to fight him normaly")
                                    if (bat1.bossHP >= 0) {
                                        println("")
                                        println("Boss")
                                        bat1.bossBattle()
                                    } else {
                                        println("")
                                        println("Boss is dead")
                                        println("")
                                    }
                                }
                            }
                        } else if (sms == "n") {
                            println("Prepare to fight")
                            if (bat1.bossHP >= 0) {
                                println("")
                                println("BOSS")
                                bat1.bossBattle()
                            } else {
                                println("")
                                println("The Boss is dead")
                                println("")
                            }
                        } else {
                            println("")
                            println("Incorrect input")
                            println("")
                            continue
                        }
                    }
                }
            }

            //weapon
            if (map1.roomArray[y][x]!!.cont === "sword") {
                if (map1.roomArray[y][x]?.sword !== 0) {
                    Kotlinraider.player1.playerWeapon = 2
                    println("")
                    println("You got a Sword")
                    println("")
                    map1.roomArray[y][x]!!.sword = 0
                } else {
                    println("")
                    println("There was a sword here")
                    println("")
                }
            }

            //loot
            if (map1.roomArray[y][x]!!.cont === "loot") {
                if (map1.roomArray[y][x]!!.takara !== 0) {
                    println("")
                    println("Treasure!")
                    Kotlinraider.player1.takara += map1.roomArray[y][x]!!.takara
                    println("You found: "+ map1.roomArray[y][x]!!.takara)
                    map1.roomArray[y][x]!!.takara = 0
                    println("You now have ${Kotlinraider.player1.takara} pieces of Gold")
                    println("")
                } else {
                    println("")
                    println("There was treasure here")
                    println("")
                }
            }
            //smoke
            if (map1.roomArray[y][x]!!.cont === "enmaku") {
                if (map1.roomArray[y][x]!!.enmaku !== 0) {
                    println("Found a Smoke Bomb")
                    Kotlinraider.player1.smoke++
                    println("You now have: " + Kotlinraider.player1.smoke + " Smoke Bombs")
                    println("")
                    map1.roomArray[y][x]!!.enmaku = 0
                } else {
                    println("")
                    println("Room is Empty")
                    println("")
                }
            }
            //empty
            if (map1.roomArray[y][x]!!.cont === "empty") {
                println("")
                println("Room is Empty")
                println("")
            }
            //check boss
            for (t in 0..4) {
                for (g in 0..4) {
                    if ((t == y) and (g == x)) {
                        continue
                    } else if ((t == y) and (g == x - 1)) {
                        if (map1.roomArray[t][g]!!.cont.equals("boss")) {
                            print("Boss feels close")
                            println("")
                        } else {
                            print("")
                        }
                    } else if ((t == y) and (g == x + 1)) {
                        if (map1.roomArray[t][g]!!.cont.equals("boss")) {
                            print("Boss feels close")
                            println("")
                        } else {
                            print("")
                        }
                    } else if ((t == y + 1) and (g == x)) {
                        if (map1.roomArray[t][g]!!.cont.equals("boss")) {
                            print("Boss feels close")
                            println("")
                        } else {
                            print("")
                        }
                    } else if ((t == y - 1) and (g == x)) {
                        if (map1.roomArray[t][g]!!.cont.equals("boss")) {
                            print("Boss feels close")
                            println("")
                        } else {
                            print("")
                        }
                    } else {
                    }
                }
            }

            //move and reset);
            println("")
            println("Enter u,d,l,r (up, down, left, right)")
            println("Enter radar to use")
            println("Enter 1 to reset")
            val move = `in`.next()

            for (i in 0..20) {
                println("")
            }

            println("------------------------")

            //Quits
            if (move == "1") {
                println("GAME RESET!")
                newGame()
                //gameActive = false
                //break
            }
            //movement commands
            var moves = true
            while (moves) {
                var ok = 0
                if (move == "u") {

                    for (v in 0..3) {
                        if (map1.roomArray[y][x]!!.exit[v]!!.equals(Directions.up)) {
                            ok = 1
                            continue
                        }
                    }
                    if (ok == 1) {
                        println("Moved Up")
                        y--
                        moves = false
                    } else {
                        println("Can't move that way")
                        println("Try again")
                        //break;
                        moves = false
                    }
                } else if (move == "d") {
                    ok = 0
                    for (v in 0..3) {
                        if (map1.roomArray[y][x]!!.exit[v]!!.equals(Directions.down)) {
                            ok = 1
                            continue
                        }
                    }
                    if (ok == 1) {
                        println("Moved Down")
                        y++
                        moves = false
                    } else {
                        println("Can't move that way")
                        println("Try again")
                        //break;
                        moves = false
                    }
                } else if (move == "l") {
                    ok = 0
                    for (v in 0..3) {
                        if (map1.roomArray[y][x]!!.exit[v]!!.equals(Directions.left)) {
                            ok = 1
                            continue
                        }
                    }
                    if (ok == 1) {
                        println("Moved left")
                        x--
                        moves = false
                    } else {
                        println("Can't move that way")
                        println("Try again")
                        //break;
                        moves = false
                    }
                } else if (move == "r") {
                    ok = 0
                    for (v in 0..3) {
                        if (map1.roomArray[y][x]!!.exit[v]!!.equals(Directions.right)) {
                            ok = 1
                            continue
                        }
                    }
                    if (ok == 1) {
                        println("Moved Right")
                        x++
                        moves = false
                    } else {
                        println("Can't move that way")
                        println("Try again")
                        //break;
                        moves = false
                    }
                } else if (move == "radar") {
                    for (d in 0..4) {
                        for (f in 0..4) {
                            if ((d == y) and (f == x)) {
                                print("PLA ")
                                continue
                            } else if ((d == y) and (f == x - 1)) {
                                if (map1.roomArray[d][f]!!.cont.equals("Player")) {
                                    print("Start ")
                                } else {
                                    print(map1.roomArray[d][f]!!.cont + " ")
                                }
                            } else if ((d == y) and (f == x + 1)) {
                                if (map1.roomArray[d][f]!!.cont.equals("Player")) {
                                    print("Start ")
                                } else {
                                    print(map1.roomArray[d][f]!!.cont + " ")
                                }
                            } else if ((d == y + 1) and (f == x)) {
                                if (map1.roomArray[d][f]!!.cont.equals("Player")) {
                                    print("Start ")
                                } else {
                                    print(map1.roomArray[d][f]!!.cont + " ")
                                }
                            } else if ((d == y - 1) and (f == x)) {
                                if (map1.roomArray[d][f]!!.cont.equals("Player")) {
                                    print("Start ")
                                } else {
                                    print(map1.roomArray[d][f]!!.cont + " ")
                                }
                            } else {
                                print("XXX ")
                            }

                        }
                        println("")
                    }
                    Kotlinraider.player1.radar--
                    println("")
                    println("You have: " + Kotlinraider.player1.radar + " more radars")
                    moves = false
                    Thread.sleep(3000)
                } else {
                    println("")
                    println("ERROR, Invalid input")
                    println("")
                    //break;
                    moves = false
                }
            }
            println("------------------------")
        }
    }
}