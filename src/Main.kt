import java.util.*
import kotlin.system.exitProcess

/**
 * Kotlin version of Java Game
 * Javaで作ったゲーム　ーー＞　Kotlin
 */

/**
 * Scanner　を作成
 * Kotlinraider　クラスを作成
 */
val USERINPUT = Scanner(System.`in`)
val game = Kotlinraider()

/**
 * mainメソッド。
 */
fun main() {
    //マインメニューを作成
    game.welcome()
    var status = false
    do when (USERINPUT.nextLine()) {
        // １を入力したら、New Game のメソッドを実行します。
            "1" -> {
            newGame()
            status = true
        }
        //　２を入力したら、Kotlinraider　のクラスにある Rule のメソッドを実行します。
        "2" -> {
            game.rule()
            status = true
        }
        //  プログラム終了
        "3" -> exitProcess(0)
    } while (!status)
}

/**
 * New Game　のメソッド。
 */
fun newGame() {
    //Console　にスペースを作成
    for (i in 0..20) {
        println("")
    }
    //Move　のクラスを作成
    val movement = Move()
    //Moving （プレイヤー動く） のメソッドを作成して、プレイヤーHPを無くなるまでに実行します。
    while (Kotlinraider.player1.playerHP!= 0) {
        movement.moving()
    }
}