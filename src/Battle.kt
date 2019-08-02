import java.util.*

class Battle {
    val USERINPUT = Scanner(System.`in`)
    var bossHP: Int = 0
    var attackNum: Int = 0
    var PlayerHP: Int = 0
    var EnemyHP: Int = 0
    var sword = 0
    var enemyAT = 1.05
    var weaponAT = 1.5
    var dualweapon = 1.7

    fun battle() {
        PlayerHP = Kotlinraider.player1.playerHP
        EnemyHP = Kotlinraider.enemies.enemyHP
        sword = Kotlinraider.player1.playerWeapon

        while (PlayerHP >= 0) {
            println("エンターを押して攻撃")
            USERINPUT.nextLine()

            diceroll()
            if (sword == 2) {
                println("攻撃力増加")
                attackNum = (attackNum.toDouble() * weaponAT).toInt()
            } else if (sword == 3) {
                println("攻撃力更に増加")
                attackNum = (attackNum.toDouble() * dualweapon).toInt()
            } else {
                attackNum = attackNum * 1
            }

            println("$attackNum Damage")
            EnemyHP = EnemyHP - attackNum
            if (EnemyHP <= 0) {
                println("敵のHP：0")
                println("敵を倒した")
            } else {
                println("敵のHP：$EnemyHP")
            }
            Thread.sleep(1000)

            if (EnemyHP <= 0) {
                println("")
                println("勝利")
                println("")
                break
            } else {
                println("敵の攻撃")
                diceroll()
                println((attackNum * enemyAT).toString() + " Damage")
                PlayerHP = (PlayerHP.toDouble() - attackNum * enemyAT).toInt()

                if (PlayerHP <= 0) {
                    println("プレイヤーのHP：0")
                    Kotlinraider().loseCondition()
                    break
                } else {
                    println("プレイヤーのHP： $PlayerHP")
                }

            }
            println("")

            continue
        }
        Kotlinraider.player1.playerHP = PlayerHP
        Kotlinraider.enemies.enemyHP = EnemyHP
    }

    fun bossBattle() {
        PlayerHP = Kotlinraider.player1.playerHP
        bossHP = Kotlinraider.enemies.bossHP
        sword = Kotlinraider.player1.playerWeapon

        while (bossHP >= 0) {
            println("エンターで攻撃")
            USERINPUT.nextLine()

            diceroll()
            if (sword == 2) {
                println("Sword makes double damage")
                attackNum = (attackNum.toDouble() * weaponAT).toInt()
            } else if (sword == 3) {
                println("Sword makes triple damage")
                attackNum = (attackNum.toDouble() * dualweapon).toInt()
            } else {
                attackNum = attackNum * 1
            }

            println("$attackNum Damage")
            bossHP = bossHP - attackNum

            Thread.sleep(1000)

            if (bossHP <= 0) {
                println("")
                println("BOSSのHP: 0")
                println("ボスを倒した")
                println("出口へ急ごう")
                println("")
                Move.flag()

                break
            } else {
                println("ボスのHP：$bossHP")
                println("BOSSの攻撃")
                diceroll()
                println("$attackNum Damage")
                PlayerHP = (PlayerHP.toDouble() - attackNum * enemyAT).toInt()

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
        Kotlinraider.player1.playerHP = PlayerHP
        Kotlinraider.enemies.bossHP = bossHP
    }

    fun diceroll() {
        val random = Random()
        attackNum = random.nextInt(50) + 50 + 1
    }
}