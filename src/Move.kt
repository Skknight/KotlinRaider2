import constants.Directions
import java.util.*

class Move {
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

        val gameActive = true
        var x = map1.x
        var y = map1.y

        while (gameActive) {
            //map
            println("プレイヤーHP： " + Kotlinraider.player1.playerHP)
            println("煙幕:  " + Kotlinraider.player1.smoke)
            println("宝: " + Kotlinraider.player1.takara)
            println("レーダー: " + Kotlinraider.player1.radar)
            println("")
            println("マップ:")
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
            var left = 0
            var right = 0
            var up = 0
            var down = 0

            val room = map1.roomArray[y][x]

            for (i in 0..3) {
                val door = room.exit[i]
                if (door == Directions.Left) {
                    left++
                }
                if (door == Directions.Right) {
                    right++
                }
                if (door == Directions.Up) {
                    up++
                }
                if (door == Directions.Down) {
                    down++
                }
            }

            println("")
            if (left >= 1) {
                println("左に行けます")
            }
            if (right >= 1) {
                println("右に行けます")
            }
            if (up >= 1) {
                println("上に行けます")
            }
            if (down >= 1) {
                println("下に行けます")
            }
            println("")

            //Player
            if (room.cont === "Player") {
                if (flag == 0) {
                    println("")
                    println("入口にいます")
                    println("")
                } else {
                    println("")
                    println("できた！！")
                    Kotlinraider().winCondition()
                }
            }

            //Battle
            if (room.cont === "enemy") {
                if (Kotlinraider.enemies.enemyHP <= 0) {
                    println("")
                    println("敵はもう死んでいる")
                    println("")
                } else {
                    if (Kotlinraider.player1.smoke === 0) {
                        println("")
                        println("煙幕もうない")
                        println("戦うしかない")
                        if (bat1.EnemyHP >= 0) {
                            println("")
                            println("敵！")
                            bat1.battle()
                        } else {
                            println("")
                            println("敵はもう死んでいる")
                            println("")
                        }
                    } else if (Kotlinraider.player1.smoke >= 0) {
                        println("")
                        println("部屋の中に敵がいます")
                        println("煙幕を使うか? (y/n)")
                        val sm = `in`.next()
                        if (sm == "y") {
                            var stealth = 0
                            if (Kotlinraider.player1.smoke !== 0) {
                                stealth = random.nextInt(4) + 5
                                if (room.status <= stealth) {
                                    println("部屋のステルス状態は: " + room.status)
                                    println("お前のステルス状態は: $stealth")
                                    Kotlinraider.player1.smoke--
                                    println("煙幕: ${Kotlinraider.player1.smoke}")
                                    println("")
                                    println("敵をそらった！ 早く逃げろ！")
                                    println("")
                                } else {
                                    println("ステルス状態はが足りない")
                                    println("戦うしかない")
                                    if (bat1.EnemyHP >= 0) {
                                        println("")
                                        println("敵")
                                        bat1.battle()
                                    } else {
                                        println("")
                                        println("敵はもう死んでいる")
                                        println("")
                                    }
                                }
                            }
                        } else if (sm == "n") {
                            println("")
                            println("戦うしかない")
                            if (bat1.EnemyHP >= 0) {
                                println("")
                                println("敵")
                                bat1.battle()
                            } else {
                                println("")
                                println("敵はもう死んでいる")
                                println("")
                            }
                        } else {
                            println("誤った入力")
                            println("")
                            continue
                        }
                    }
                }
            }

            //boss
            if (room.cont === "boss") {
                if (Kotlinraider.enemies.bossHP <= 0) {
                    println("")
                    println("ボスはもう死んでいる")
                    println("")
                } else {
                    if (Kotlinraider.enemies.bossHP <= 0) {
                        println("")
                        println("ボスはもう死んでいる")
                        println("")
                        break
                    }
                    if (Kotlinraider.player1.smoke < 3) {
                        println("")
                        println("ボスルーム")
                        println("You have not enough smokes to distract him")
                        println("Prepare to fight the Boss")
                        if (bat1.bossHP >= 0) {
                            println("")
                            println("BOSS")
                            bat1.bossBattle()
                        } else {
                            println("")
                            println("ボスはもう死んでいる")
                            println("")
                        }
                    }

                    if (Kotlinraider.player1.smoke >= 3) {
                        println("")
                        println("ボスルーム")
                        println("You have enough smokes to distract him and fight")
                        println("Use smoke? (y/n)")
                        val sms = `in`.next()
                        if (sms == "y") {
                            var stealth = 0
                            if (Kotlinraider.player1.smoke >= 3) {
                                stealth = 10
                                if (room.status <= stealth) {
                                    println("Your stealth status is: $stealth")
                                    Kotlinraider.player1.smoke = 0
                                    println("Smoke left: " + Kotlinraider.player1.smoke)
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
                                        println("ボスはもう死んでいる")
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
                                println("ボスはもう死んでいる")
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
            if (room.cont === "sword") {
                if (room.sword !== 0) {
                    Kotlinraider.player1.playerWeapon = 2
                    println("")
                    println("You got a Sword")
                    println("")
                    room.sword = 0
                } else {
                    println("")
                    println("There was a sword here")
                    println("")
                }
            }

            //loot
            if (room.cont === "loot") {
                if (room.takara !== 0) {
                    println("")
                    println("Treasure!")
                    Kotlinraider.player1.takara += room.takara
                    println("You found: "+ room.takara)
                    room.takara = 0
                    println("You now have ${Kotlinraider.player1.takara} pieces of Gold")
                    println("")
                } else {
                    println("")
                    println("There was treasure here")
                    println("")
                }
            }
            //smoke
            if (room.cont === "enmaku") {
                if (room.enmaku !== 0) {
                    println("煙幕を手に入れた")
                    Kotlinraider.player1.smoke++
                    println(""+Kotlinraider.player1.smoke + " の煙幕があります")
                    println("")
                    room.enmaku = 0
                } else {
                    println("")
                    println("Room is Empty")
                    println("")
                }
            }
            //empty
            if (room.cont === "empty") {
                println("")
                println("部屋の中に何もない")
                println("")
            }
            //check boss
            for (t in 0..4) {
                for (g in 0..4) {
                    var bossRoom = map1.roomArray[t][g]
                    if ((t == y) and (g == x)) {
                        continue
                    } else if ((t == y) and (g == x - 1)) {
                        if (bossRoom.cont.equals("boss")) {
                            print("Boss feels close")
                            println("")
                        } else {
                            print("")
                        }
                    } else if ((t == y) and (g == x + 1)) {
                        if (bossRoom.cont.equals("boss")) {
                            print("Boss feels close")
                            println("")
                        } else {
                            print("")
                        }
                    } else if ((t == y + 1) and (g == x)) {
                        if (bossRoom.cont.equals("boss")) {
                            print("Boss feels close")
                            println("")
                        } else {
                            print("")
                        }
                    } else if ((t == y - 1) and (g == x)) {
                        if (bossRoom.cont.equals("boss")) {
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
            }
            //movement commands
            var moves = true
            while (moves) {
                var ok = 0
                if (move == "u") {
                    for (v in 0..3) {
                        val enteredDoor = room.exit[v]
                        if (enteredDoor == Directions.Up) {
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
                        moves = false
                    }
                } else if (move == "d") {
                    ok = 0
                    for (v in 0..3) {
                        val enteredDoor = room.exit[v]
                        if (enteredDoor == Directions.Down) {
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
                        moves = false
                    }
                } else if (move == "l") {
                    ok = 0
                    for (v in 0..3) {
                        val enteredDoor = room.exit[v]
                        if (enteredDoor == Directions.Left) {
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
                        moves = false
                    }
                } else if (move == "r") {
                    ok = 0
                    for (v in 0..3) {
                        val enteredDoor = room.exit[v]
                        if (enteredDoor == Directions.Right) {
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
                        moves = false
                    }
                } else if (move == "radar") {
                    for (d in 0..4) {
                        for (f in 0..4) {
                            val scannedRoom = map1.roomArray[d][f]
                            if ((d == y) and (f == x)) {
                                print("PLA ")
                                continue
                            } else if ((d == y) and (f == x - 1)) {
                                if (scannedRoom.cont.equals("Player")) {
                                    print("Start ")
                                } else {
                                    print(scannedRoom.cont + " ")
                                }
                            } else if ((d == y) and (f == x + 1)) {
                                if (scannedRoom.cont.equals("Player")) {
                                    print("Start ")
                                } else {
                                    print(scannedRoom.cont + " ")
                                }
                            } else if ((d == y + 1) and (f == x)) {
                                if (scannedRoom.cont.equals("Player")) {
                                    print("Start ")
                                } else {
                                    print(scannedRoom.cont + " ")
                                }
                            } else if ((d == y - 1) and (f == x)) {
                                if (scannedRoom.cont.equals("Player")) {
                                    print("Start ")
                                } else {
                                    print(scannedRoom.cont + " ")
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
                    moves = false
                }
            }
            println("------------------------")
        }
    }
}