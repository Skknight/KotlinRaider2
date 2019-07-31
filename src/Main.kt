import java.util.*
import kotlin.system.exitProcess

//Kotlin version of Java Game
val USERINPUT = Scanner(System.`in`)
val game = Kotlinraider()

fun main() {
    game.welcome()

    var status = false
    do when (USERINPUT.nextLine()) {
        "1" -> {
            newGame()
            status = true
        }
        "2" -> {
            game.rule()
            status = true
        }
        "3" -> exitProcess(0)
    } while (!status)
}

fun newGame() {
    for (i in 0..20) {
        println("")
    }
    val movement = Move()
    while (Kotlinraider.player1.playerHP!= 0) {
        movement.moving()
    }
}