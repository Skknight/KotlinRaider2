import constants.Directions

class ROOMS {
    var cont: String? = null
    internal var enemy = 0
    internal var enmaku = 0
    internal var takara = 0
    internal var sword = 0
    internal var boss = 0
    internal var dir = arrayOf(Directions.None, Directions.Left, Directions.Right, Directions.Up, Directions.Down)
    internal var exit = arrayOfNulls<Directions>(4)

    internal var status = 0
}