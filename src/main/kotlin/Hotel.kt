import Rooms.Room
import javafx.scene.Scene

class Hotel(
           val broj_soba : Int,
           val naziv : String ,
           val mesto: String,
           val adresa : String
           )
{
    var rezervacije : MutableMap<Room, MutableList<Reservation>> = emptyMap<Room, MutableList<Reservation>>().toMutableMap()

    // Videcemo kako ce da se koristi
    fun dodaj_rezervaciju(room : Room, reservation: Reservation){
        rezervacije[room]?.add(reservation)
    }

    override fun toString(): String {
        return "$naziv\nMesto: $mesto\nAdresa: $adresa\n"
    }

    fun showReservations() : String {
        var result = ""
        rezervacije.forEach { (room, lista_rezervacija) ->
            result += room.toString()
            lista_rezervacija.forEach { res ->
                result += res.toString()
            }
            result += "\n"
        }
        return result
    }
}