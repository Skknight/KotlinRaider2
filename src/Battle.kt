import java.util.*

/**
 * 戦闘のクラス
 * 敵と戦い
 * ボスと戦い
 */

class Battle {
    //設定
    val USERINPUT = Scanner(System.`in`)
    var bossHP: Int = 0
    var attackNum: Int = 0
    var PlayerHP: Int = 0
    var EnemyHP: Int = 0
    var sword = 0
    //ダメージ設定
    var enemyAT = 1.05
    var weaponAT = 1.5
    var dualweapon = 1.7

    /**
     * 普通の敵と戦闘のメソッド
     */
    fun battle() {
        //HPと剣のコール
        PlayerHP = Kotlinraider.player1.playerHP
        EnemyHP = Kotlinraider.enemies.enemyHP
        sword = Kotlinraider.player1.playerWeapon

        //普通の敵と戦い
        while (PlayerHP >= 0) {
            println("エンターを押して攻撃")
            USERINPUT.nextLine()

            //ランダムダメージ
            diceroll()
            if (sword == 2) {
                //二重ダメージ
                println("攻撃力増加")
                attackNum = (attackNum.toDouble() * weaponAT).toInt()
            } else if (sword == 3) {
                //三重ダメージ
                println("攻撃力更に増加")
                attackNum = (attackNum.toDouble() * dualweapon).toInt()
            } else {
                attackNum *= 1
            }

            //印刷
            println("${attackNum} ダメージ")
            EnemyHP -= attackNum
            if (EnemyHP <= 0) {
                println("敵のHP：0")
                println("敵を倒した")
            } else {
                println("敵のHP：$EnemyHP")
            }

            //ちょっと待つ
            Thread.sleep(1000)

            //勝ちの場合
            if (EnemyHP <= 0) {
                println("")
                println("勝利")
                println("")
                break
            } else {
                //まだ戦う
                println("敵の攻撃")
                diceroll()
                println((attackNum * enemyAT).toString() + " Damage")
                PlayerHP = (PlayerHP.toDouble() - attackNum * enemyAT).toInt()

                //プレイヤーHPを無くなる場合
                if (PlayerHP <= 0) {
                    println("プレイヤーのHP：0")
                    Kotlinraider().loseCondition()
                    break
                } else {
                    //まだ生っている
                    println("プレイヤーのHP： $PlayerHP")
                }

            }
            println("")
            continue
        }
        //戦闘結果の保村
        Kotlinraider.player1.playerHP = PlayerHP
        Kotlinraider.enemies.enemyHP = EnemyHP
    }

    /**
     * ボスと戦闘のメソッド
     */
    fun bossBattle() {
        //HPと剣のコール
        PlayerHP = Kotlinraider.player1.playerHP
        bossHP = Kotlinraider.enemies.bossHP
        sword = Kotlinraider.player1.playerWeapon

        //ボスと戦い
        while (bossHP >= 0) {
            println("エンターで攻撃")
            USERINPUT.nextLine()

            //ランダムダメージ
            diceroll()
            if (sword == 2) {
                //二重ダメージ
                println("剣は二重ダメージを与える")
                attackNum = (attackNum.toDouble() * weaponAT).toInt()
            } else if (sword == 3) {
                //三重ダメージ
                println("剣は三重ダメージを与える")
                attackNum = (attackNum.toDouble() * dualweapon).toInt()
            } else {
                attackNum *= 1
            }

            //印刷
            println("${attackNum} ダメージ")
            bossHP -= attackNum

            //ちょっと待つ
            Thread.sleep(1000)

            //勝ち場合
            if (bossHP <= 0) {
                println("")
                println("ボスのHP: 0")
                println("ボスを倒した")
                println("")
                println("{{{{{出口へ急ごう}}}}}")
                println("")
                println("")
                Move.flag()
                break
            }
            //ボスはまだ生きっている
            else {
                println("ボスのHP：$bossHP")
                println("ボスの攻撃")
                diceroll()
                println("${attackNum} ダメージ")
                PlayerHP = (PlayerHP.toDouble() - attackNum * enemyAT).toInt()

                //プレイヤーHPが無くなる場合
                if (PlayerHP <= 0) {
                    println("プレイヤーのHP:０")
                    Kotlinraider().loseCondition()
                    break
                } else {
                    println("プレイヤーHP:$PlayerHP")
                }

            }
            println("")
            continue
        }
        //戦闘結果の保村
        Kotlinraider.player1.playerHP = PlayerHP
        Kotlinraider.enemies.bossHP = bossHP
    }

    /**
     * ランダムダメージのメソッド
     */
    fun diceroll() {
        val random = Random()
        attackNum = random.nextInt(50) + 50 + 1
    }
}