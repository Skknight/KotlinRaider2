package data

data class Room (var cont: String? = null,
                   var enemy: Int,
                   var enmaku: Int,
                   var takara: Int,
                   var sword: Int,
                   var boss: Int)

internal var dir = arrayOf("none", "left", "right", "up", "down")
internal var exit = arrayOfNulls<String>(4)
internal var status = 0