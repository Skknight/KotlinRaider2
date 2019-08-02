package data

/**
 * プレイヤーのデータクラス
 * 持っているの　剣、煙幕、宝、レーダー
 */
data class Player (var playerHP: Int,
                   var playerWeapon: Int,
                   var smoke: Int,
                   var takara: Int,
                   var radar: Int)

