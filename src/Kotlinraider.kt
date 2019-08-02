import data.EnemySetup
import java.util.Scanner
import data.Player
import kotlin.system.exitProcess

class Kotlinraider {
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
        println("-----------------------")
        println("  　　　  ゲームのルール    ")
        println("-----------------------")
        println("")
        println("１）ゲームの目的")
        println("２） ダンジョンの内容")
        println("３）アイテム")
        println("４）敵との戦闘")
        println("５）ボス")
        println("６）スコア")
        println("７）Exit")
        println("")
        println("入力してください: ")
        do {
            when (USERINPUT.nextLine()) {
                "1"//ゲームの目的
                -> println(
                        """
                        |プレイヤーの目的は敵をやり過ごしながらダンジョン内の宝を集め、脱出することである。
                        |ダンジョンから脱出するにはダンジョンのどこかに潜むボスを倒して鍵を入手し、スタート地点に戻る必要がある。
                        |ゲーム終了時に集めた宝の数とゲーム中のプレイヤーの行動によって「スコア」が算出される。
                        """.trimMargin()
                )
                "2"//ダンジョンの内容
                -> println(
                        """
                        |ダンジョン内の部屋の数は、スタート地点とボス部屋を含めて「25」である。
                        |ダンジョン内ではボスの他にも「敵」と遭遇することがある。
                        |各部屋には「隠密性」というステータスが設定されており、道中で拾える「煙幕」があれば敵との戦闘を避けることができる。
                        |部屋ごとに進める方向が決まっている。一方通行になっていて前にいた部屋に戻れなくなることもあるため注意。
                        |原則、今いる部屋以外の情報は開示されない。しかしプレイヤーは制限付きで周囲4方向の部屋の情報を開示できる「レーダー」を使用することができる。
                        |また、ダンジョン内では様々な「アイテム」を拾うことができる。
                        """.trimMargin()
                )
                "3"//アイテム
                -> println(
                        """
                        |ダンジョン内では様々なアイテムがランダムに配置されている。拾えるアイテムは3種類ある。
                        |「煙幕」
                        |煙幕は一つ一つに個別に数値が設定されている。各部屋の「隠密性」の数値と所持している煙幕の数値の合計値が「10」を越えたとき、煙幕を消費して敵との戦闘から逃げ出すことができる。
                        |「宝」
                        |取れば取るほど、ゲーム終了時のスコアが加算されるアイテム。部屋によって１つしかなければ、3つ以上置かれていることも。ボス部屋でボスを倒せば、宝が５つ手に入る。敵との戦闘が成立すると宝が2つ奪われる。\r\n" +
                        |「武器」
                        |取れば取るほど、プレイヤーの攻撃力が上がる。ダンジョン内に配置されている武器は2つである。 
                        """.trimMargin()
                )
                "4"//敵との戦闘
                -> println(
                        """
                        |敵との戦闘はサイコロによってダメージを算出する1対1のコマンド式である。
                        |プレイヤーの初期HPは「150」、道中の敵のHPは「150」、ボスのHPは、ボスのHPは「400」だが、条件を満たすとHPが「30」になる。
                        |ダメージは所持しているサイコロで出した数値×(武器の本数+1)で算出される。
                        |[例]:武器を「1」本所持し、サイコロが「4」を出したとき、ダメージは4×(1+1)で「8」である。
                        |敵を倒した場合、敵はその部屋から消えるが、敵から逃げた場合は消えない。
                        |敵との戦闘が成立した場合、宝が2個奪われる。
                        |プレイヤーのHPが「0」になるとゲームが終了し、その時点のスコアが算出される。
                        """.trimMargin()
                )
                "5"//BOSS
                -> println(
                        """
                        |ボスの部屋には表口と裏口が設定されている。
                        |プレイヤーが表口からは入れば、HP「200」のボスと戦闘になる。
                        |プレイヤーが裏口からは入ってかつ煙幕を所持していると煙幕を消費し、HP「25」 のボスと戦闘になる。
                        |ボスとの戦闘に勝利すると、宝５つとダンジョンから脱出する鍵を手に入れる。この状態でスタート地点に戻ることでダンジョンから脱出できる。
                        |ダンジョンから脱出するとゲームが終了し、スコアが算出される。
                        """.trimMargin()
                )
                "6"//スコア
                -> println(
                        """
                        |ゲーム終了時にスコアが算出される。スコアの算出方法は以下の通りである
                        |・プレイヤーが所持している宝の数×100点をスコアに加算
                        |・ボス撃破ボーナスの500点をスコアに加算
                        |・脱出ボーナスの200点をスコアに加算
                        |・レーダーは3回以上使用した場合、使用回数×100点をスコアから減算
                        """.trimMargin()
                )
                "7" -> {
                    main()
                }
            }
            println("Press Enter key")
            USERINPUT.nextLine()
            rule()
        } while (status == false)
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
        exitProcess(0)
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