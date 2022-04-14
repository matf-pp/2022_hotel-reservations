import Rooms.Room
import javafx.scene.Scene

class Hotel(
           val broj_soba : Int,
           val naziv : String ,
           val mesto: String,
           val adresa : String
           )
{
    var sobe : MutableList<Room> = emptyList<Room>().toMutableList()
    var rezervacije : MutableMap<Room, MutableList<Reservation>> = emptyMap<Room, MutableList<Reservation>>().toMutableMap()

    fun dodaj_sobu(room: Room) {
        sobe.add(room)
    }

    // Videcemo kako ce da se koristi
    fun dodaj_rezervaciju(room : Room, reservation: Reservation){
        rezervacije[room]?.add(reservation)
    }

    // TODO
    fun show_hotel() : Scene? {
        return null
    }

}