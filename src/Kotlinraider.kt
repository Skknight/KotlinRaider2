import data.EnemySetup
import java.util.Scanner
import data.Player

class Kotlinraider {
    val USERINPUT = Scanner(System.`in`)
    var score = 0;
    companion object {
        var player1 = Player(150, 1, 1, 0, 3)
        var enemies = EnemySetup(200, 400)
    }

    fun welcome() {
        println("-----------------------")
        println("    KOUKADAI RAIDER    ")
        println("-----------------------")
        println("ようこそ!")
        println()
        println(" １）     START        ")
        println(" ２）     ゲームのルール        ")
        println(" ３）     EXIT        ")
        println()
        print(" 入力してください:  ")
        println()
        println()
    }

    fun rule() {
        var status = false
        println("rule desu")
    }

    fun loseCondition() {
        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }

        if (player1.playerHP >= 0) {
            println("")
            println("GAME OVER")
            score()
        }
        System.exit(0)
    }

    fun winCondition() {
        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }

        if (Move.flag === 1) {
            println("")
            println("WIN")
        }
        score += 200
        score()
        println("")
        System.exit(0)
    }

    fun score() {
        player1.takara *= 100
        score += player1.takara
        if (Move.flag === 1) {
            score += 500
        }
        if (player1.radar < 3) {
            score -= (player1.radar * 100)
            println("Radars left: "+player1.radar)
        } else {
            println("Radars left: "+player1.radar)
        }
        println("Your score is: ")
        println(score)
        println("")
    }
}