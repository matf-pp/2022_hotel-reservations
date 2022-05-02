package Rooms

import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import java.time.LocalDate

abstract class Room() {
    open var price_per_night : Double = 0.0
    var pictures : MutableList<String>? = null
    var availability = mutableMapOf <Int, MutableList<LocalDate>>()

    open var num_beds : Int = 0
    var id : Int = -1

    constructor(num_beds : Int) : this() {
        this.num_beds = num_beds
    }

    abstract fun add_date(id : Int, date : LocalDate)
    abstract fun find_id(date1 : LocalDate, date2 : LocalDate) : Int
}