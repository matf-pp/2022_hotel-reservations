import javafx.scene.Scene

abstract class Room {
    var price_per_night : Double? = null
    var pictures : MutableList<String>? = null
    var availability : MutableMap<String, Int>? = null
    var num_beds : Int? = null

    constructor(price_per_night: Double, num_beds: Int, pictures: MutableList<String>? = null, availability: MutableMap<String,Int>? = null){
        this.price_per_night = price_per_night
        this.num_beds = num_beds
        this.availability = availability
        this.pictures = pictures
    }

    abstract fun detailed_view() : Scene
}