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
    val final_price : Double
    private var length_of_stay : Int = abs((this.date_from.compareTo(date_to)))

    init {
        final_price = calculateFinalPrice()
        reserve()
    }

    fun calculateFinalPrice() : Double {
       return length_of_stay * (offer.offer_price_per_day() + selected_room.price_per_night)
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

    companion object {
        fun preCalculateFinalPrice(pre_dat_od : LocalDate, pre_dat_do : LocalDate ,pre_selected_room : Room, pre_offer : Offer) : Double{
            val duzina_trajanja = abs((pre_dat_od.compareTo(pre_dat_do)))
            return duzina_trajanja * (pre_offer.offer_price_per_day() + pre_selected_room.price_per_night)
        }
    }

}