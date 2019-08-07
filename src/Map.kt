import constants.Directions
import java.util.*

/**
 * マップのクラス
 * ５x５のマップを作成
 */

class Map {
    var random = Random()
    //ルームの2次元配列
    val roomArray = Array(10, {arrayOf<ROOMS>(ROOMS(), ROOMS(), ROOMS(), ROOMS(), ROOMS(), ROOMS(), ROOMS(), ROOMS(), ROOMS(), ROOMS())})

    var x = 2
    var y = 0

    /**
     * マップのコンストラクタ
     */
    fun map() {
        //敵のカウンタ
        var d = 0
        //宝のカウンタ
        var f = 20
        //剣のカウンタ
        var z = 0
        //煙幕のカウンタ
        var c = 0
        //ボスのカウンタ
        var b = 0
        //何もないのカウンタ
        var e = 0

        //それそれのルームの入口と出口の設定
        var i = 0
        while (i < 5) {
            for (j in 0..4) {
                var room = ROOMS()
                roomArray[i][j] = room
                //コンテンツ
                room.cont = inside()
                room.status = random.nextInt(8) + 1

                //出口の設定
                var v = 0
                while (v < 4) {
                    var directions:Directions = room.dir[random.nextInt(5)]
                    room.exit[v] = directions

                    if (i == 0) {
                        if (directions === Directions.Up) {
                            room.exit[v] = Directions.None
                        }
                    }
                    if (j == 0) {
                        if (directions === Directions.Left) {
                            room.exit[v] = Directions.None
                        }
                    }
                    if (i == 4) {
                        if (directions === Directions.Down) {
                            room.exit[v] = Directions.None
                        }
                    }
                    if (j == 4) {
                        if (directions === Directions.Right) {
                            room.exit[v] = Directions.None
                        }
                    }

                    //出口確認
                    var cont = 0
                    if (directions === Directions.None) {
                        cont++
                    }
                    if (cont >=  1) {
                        v = 0
                    }
                    v++
                }

                //部屋の中にプレイヤーがいる
                if ((i == y) and (j == x)) {
                    room.cont = "Player"
                    room.enmaku = 1
                    continue
                }

                //部屋の中に宝がある
                if (room.cont.equals("loot") && f >= 0) {
                    room.takara = random.nextInt(3) + 1
                    f--
                }
                //部屋の中に敵がいる
                else if (room.cont.equals("enemy") && d != 2) {
                    room.enemy = 1
                    d++
                } //部屋の中に煙幕がある
                else if (room.cont.equals("enmaku") && c != 4) {
                    room.enmaku = 1
                    c++
                }
                //部屋の中に剣がある
                else if (room.cont.equals("sword") && z != 2) {
                    room.sword = 1
                    z++
                    if (i == 5 && z != 0) {
                        i = 0
                    }
                }
                //部屋の中にボスがいる
                else if (room.cont.equals("enemy") && d == 2 && b != 1) {
                    room.cont = "boss"
                    room.boss = 1
                    room.takara = 4
                    b++
                }
                //部屋の中に何もない
                else if (room.cont.equals("empty") && e <= 6) {
                    e++
                }
                //部屋の中に宝がある
                else {
                    room.cont = "loot"
                    room.takara = random.nextInt(3) + 3
                }
            }
            //部屋の中にある物の確認（量）
            if (i == 4 && b <= 0) {
                i = 0
            }
            i++
        }
    }

    /**
     * 部屋の中にの設定
     */
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
