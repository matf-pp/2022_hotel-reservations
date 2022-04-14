package Rooms

import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import java.time.LocalDate

abstract class Room() {
    open var price_per_night : Double = 0.0
    var pictures : MutableList<String>? = null
    var availability : MutableMap<Int, MutableList<LocalDate>>? = null
    open var num_beds : Int = 0

    constructor(num_beds : Int) : this() {
        this.num_beds = num_beds
    }

    abstract fun find_id(date1 : LocalDate, date2 : LocalDate) : Int
}