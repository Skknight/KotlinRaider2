import constants.Directions
import java.util.*

@Suppress("DEPRECATED_IDENTITY_EQUALS")

/**
 * Moveのクラス
 * プレイヤーを生きている時
 */

class Move {
    /**
     * 勝ちフラグ
     */
    companion object {
        var flag: Int = 0
        fun flag() {
            flag = 1
            return
        }
    }

    /**
     * movingのメソッド
     * プレイヤーを動く時
     */
    fun moving() {
        //マップを作成
        val map1 = Map()
        map1.map()

        //戦闘設定を作戦
        val bat1 = Battle()
        val random = Random()

        //scanner
        val `in` = Scanner(System.`in`)

        //プレイヤーの開始位置
        var x = map1.x
        var y = map1.y

        //ゲームを実行している時
        val gameActive = true
        while (gameActive) {
            //ゲームUIの画面
            println("プレイヤーHP： ${Kotlinraider.player1.playerHP}")
            println("剣: ${Kotlinraider.player1.playerWeapon}")
            println("煙幕: ${Kotlinraider.player1.smoke}")
            println("宝: ${Kotlinraider.player1.takara}")
            println("レーダー: ${Kotlinraider.player1.radar}")
            println("")
            println("マップ:")
            //マップを印刷
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

            //行ける方法設定
            var left = 0
            var right = 0
            var up = 0
            var down = 0

            //部屋の中にどこに出口があるます
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

            //部屋の出口の印刷
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

            //入口と出口の印刷
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

            //戦闘
            fun enemyBattle(){
                if (bat1.EnemyHP >= 0) {
                    println("")
                    println("敵！")
                    bat1.battle()
                } else {
                    println("")
                    println("敵はもう死んでいる")
                    println("")
                }
            }
            if (room.cont === "enemy") {
                //敵のHPがゼロの場合
                if (Kotlinraider.enemies.enemyHP <= 0) {
                    println("")
                    println("敵はもう死んでいる")
                    println("")
                } else {
                    //煙幕がない場合
                    if (Kotlinraider.player1.smoke === 0) {
                        println("")
                        println("煙幕もうない")
                        println("戦うしかない")
                        enemyBattle()
                    } else
                        //煙幕がある場合
                        if (Kotlinraider.player1.smoke >= 0) {
                        println("")
                        println("敵だ！！")
                        println("部屋の中に敵がいます")
                        //煙幕を使う選択
                        println("煙幕を使うか? (y/n)")
                        val sm = `in`.next()
                        //煙幕を使う時
                        if (sm == "y") {
                            //ステルス状態の実行
                            var stealth: Int
                            if (Kotlinraider.player1.smoke !== 0) {
                                stealth = random.nextInt(4) + 5
                                //ステルス状態が大丈夫時
                                if (room.status <= stealth) {
                                    println("部屋のステルス状態は: ${room.status} ")
                                    println("お前のステルス状態は: $stealth")
                                    Kotlinraider.player1.smoke--
                                    println("煙幕: ${Kotlinraider.player1.smoke}")
                                    println("")
                                    println("敵をそらった！ 早く逃げろ！")
                                    println("")
                                    //ステルス状態が大丈夫じゃない時
                                } else {
                                    println("部屋のステルス状態は: ${room.status} ")
                                    println("お前のステルス状態は: $stealth")
                                    println("ステルス状態はが足りない")
                                    println("戦うしかない")
                                    enemyBattle()
                                }
                            }
                            //煙幕を使わない時
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
                            //入力エラー
                        } else {
                            println("誤った入力")
                            println("")
                            continue
                        }
                    }
                }
            }

            //ボスと戦闘
            fun bossBattle(){
                if (bat1.bossHP >= 0) {
                    println("")
                    println("ボス！")
                    bat1.bossBattle()
                } else {
                    println("")
                    println("ボスはもう死んでいる")
                    println("")
                }
            }
            if (room.cont === "boss") {
                //ボスのHPがゼロの場合
                if (Kotlinraider.enemies.bossHP <= 0) {
                    println("")
                    println("ボスはもう死んでいる")
                    println("")
                } else {
                    //煙幕がない場合
                    if (Kotlinraider.player1.smoke < 3) {
                        println("")
                        println("ボスルーム")
                        println("お前はボスをそらすのに十分な煙幕がない")
                        println("ボスを戦うしかない")
                        bossBattle()
                    }
                    //煙幕がある場合
                    if (Kotlinraider.player1.smoke >= 3) {
                        println("")
                        println("ボスルーム")
                        println("お前はボスをそらすのに十分な煙幕があります！（煙幕：３）")
                        //煙幕を使う選択
                        println("使うか? (y/n)")
                        val sms = `in`.next()
                        //煙幕を使う時
                        if (sms == "y") {
                            var stealth: Int
                            if (Kotlinraider.player1.smoke >= 3) {
                                stealth = 10
                                if (room.status <= stealth) {
                                    Kotlinraider.player1.smoke = 0
                                    Kotlinraider.enemies.bossHP = 200
                                    Kotlinraider.player1.smoke = 150
                                    println("ボスはお前を見えない！!")
                                    println("ボスHPが半分にカット！")
                                    println("プレイヤーHP回復！!")
                                    println("")
                                    bat1.bossBattle()
                                } else {
                                    println("ステルス状態はが足りない")
                                    println("普通に戦うしかない")
                                    bossBattle()
                                }
                            }
                            //煙幕を使わない時
                        } else if (sms == "n") {
                            println("戦うしかない")
                            if (bat1.bossHP >= 0) {
                                println("")
                                println("ボス！")
                                bat1.bossBattle()
                            } else {
                                println("")
                                println("ボスはもう死んでいる")
                                println("")
                            }
                            //入力エラー
                        } else {
                            println("")
                            println("誤った入力")
                            println("")
                            continue
                        }
                    }
                }
            }

            //剣を見つかる設定
            if (room.cont === "sword") {
                if (room.sword !== 0) {
                    Kotlinraider.player1.playerWeapon = 2
                    println("")
                    println("剣が見つけた")
                    println("")
                    room.sword = 0
                } else {
                    println("")
                    println("部屋の中に剣がありました")
                    println("")
                }
            }

            //宝を見つかる設定
            if (room.cont === "loot") {
                if (room.takara !== 0) {
                    println("")
                    println("宝!")
                    Kotlinraider.player1.takara += room.takara
                    println("見つけたのは：${room.takara}")
                    room.takara = 0
                    println("今 ${Kotlinraider.player1.takara} の宝が持っています")
                    println("")
                } else {
                    println("")
                    println("部屋の中に宝がありました")
                    println("")
                }
            }

            //煙幕を見つかる設定
            if (room.cont === "enmaku") {
                if (room.enmaku !== 0) {
                    println("煙幕を手に入れた")
                    Kotlinraider.player1.smoke++
                    println(""+Kotlinraider.player1.smoke + " の煙幕があります")
                    println("")
                    room.enmaku = 0
                } else {
                    println("")
                    println("部屋の中に何もない")
                    println("")
                }
            }

            //部屋に何もない時
            if (room.cont === "empty") {
                println("")
                println("部屋の中に何もない")
                println("")
            }

            //「ボスは近いか』の設定
            for (t in 0..4) {
                for (g in 0..4) {
                    var bossRoom = map1.roomArray[t][g]
                    if ((t == y) and (g == x)) {
                        continue
                    } else
                        //左にいる場合
                        if ((t == y) and (g == x - 1)) {
                        if (bossRoom.cont.equals("boss")) {
                            println("ボスが近い")
                            println("レーダーを使うかな")
                            println("")
                        } else {
                            print("")
                        }
                    } else //右にいる場合
                        if ((t == y) and (g == x + 1)) {
                        if (bossRoom.cont.equals("boss")) {
                            println("ボスが近い")
                            println("レーダーを使うかな")
                            println("")
                        } else {
                            print("")
                        }
                    } else
                        //上にいる場合
                        if ((t == y + 1) and (g == x)) {
                        if (bossRoom.cont.equals("boss")) {
                            println("ボスが近い")
                            println("レーダーを使うかな")
                            println("")
                        } else {
                            print("")
                        }
                    } else
                        //下にいる場合
                        if ((t == y - 1) and (g == x)) {
                        if (bossRoom.cont.equals("boss")) {
                            println("ボスが近い")
                            println("レーダーを使うかな")
                            println("")
                        } else {
                            print("")
                        }
                    } else {
                    }
                }
            }

            //コントロール設定
            println("")
            println("コントロール w, a, s, d (それそれ：上、左、下、右)")
            println("レーダーを使うため r を入力")
            println("リセットなら１を入力")
            val move = `in`.next()

            // スペーシング
            for (i in 0..20) {
                println("")
            }

            println("------------------------")

            //リセット
            if (move == "1") {
                println("リセット!")
                newGame()
            }

            //コントロールのコマンド
            var moves = true
            while (moves) {
                var ok = 0
                //上にいる場合
                if (move == "w") {
                    for (v in 0..3) {
                        val enteredDoor = room.exit[v]
                        if (enteredDoor == Directions.Up) {
                            ok = 1
                            continue
                        }
                    }
                    if (ok == 1) {
                        println("上に動いた")
                        y--
                        moves = false
                    } else {
                        println("そちに行けない")
                        println("もう一度入力して")
                        moves = false
                    }
                } else
                    //下にいる場合
                    if (move == "s") {
                    ok = 0
                    for (v in 0..3) {
                        val enteredDoor = room.exit[v]
                        if (enteredDoor == Directions.Down) {
                            ok = 1
                            continue
                        }
                    }
                    if (ok == 1) {
                        println("下に動いた")
                        y++
                        moves = false
                    } else {
                        println("そちに行けない")
                        println("もう一度入力して")
                        moves = false
                    }
                } else
                    //左にいる場合
                    if (move == "a") {
                    ok = 0
                    for (v in 0..3) {
                        val enteredDoor = room.exit[v]
                        if (enteredDoor == Directions.Left) {
                            ok = 1
                            continue
                        }
                    }
                    if (ok == 1) {
                        println("左に動いた")
                        x--
                        moves = false
                    } else {
                        println("そちに行けない")
                        println("もう一度入力して")
                        moves = false
                    }
                } else
                    //右にいる場合
                    if (move == "d") {
                    ok = 0
                    for (v in 0..3) {
                        val enteredDoor = room.exit[v]
                        if (enteredDoor == Directions.Right) {
                            ok = 1
                            continue
                        }
                    }
                    if (ok == 1) {
                        println("右に動いた")
                        x++
                        moves = false
                    } else {
                        println("そちに行けない")
                        println("もう一度入力して")
                        moves = false
                    }
                } else
                    //レーダー設定
                    if (move == "r" && Kotlinraider.player1.radar != 0) {
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
                    println("今${Kotlinraider.player1.radar}つのレーダーが持っています")
                    moves = false
                    Thread.sleep(3000)
                } else {
                    println("")
                    println("エラー")
                    println("")
                    moves = false
                }
            }
            println("------------------------")
        }
    }
}