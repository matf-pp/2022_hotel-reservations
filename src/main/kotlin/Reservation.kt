import javafx.scene.layout.Pane
import java.time.LocalDate
import java.time.Period
import java.time.temporal.ChronoUnit
import kotlin.math.abs

class Reservation(var first_name : String,
                  var last_name : String,
                  var id_number : String,
                  var date_from: LocalDate,
                  var date_to : LocalDate,
                  var selected_room: Room,
                  var id_room : Int,
                  var offer : Offer,
                  )
{
    private var length_of_stay : Int = abs((this.date_from.compareTo(date_to)))


    fun calculateFinalPrice() : Double {
       return length_of_stay * offer.offer_price_per_day()
    }

    fun reserve(){
        var dates_list = mutableListOf<LocalDate>()

        for (i in 0..(length_of_stay-1)) {
            val period = Period.of(0, 0, i)
            val date = date_from.plus(period)
            dates_list.add(date)
        }
        for (date in dates_list) {
            this.selected_room.availability?.get(id_room)?.add(date)
        }

    }
        // TODO kada Anja izmeni deo da mogu da lista unutar mape bude mutable, tada treba da se doda ovde na osnovu ID-ja potreban datum
        //datumi.forEach { date -> lista_id.add(date)}

}