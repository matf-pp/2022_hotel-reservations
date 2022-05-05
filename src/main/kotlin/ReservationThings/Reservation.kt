package ReservationThings

import Controllers.HeadController
import Rooms.Room
import java.io.File
import java.time.LocalDate
import java.time.Period
import java.time.chrono.ChronoLocalDate
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
    var length_of_stay : Int = ChronoUnit.DAYS.between(date_from, date_to).toInt()

    init {
        final_price = calculateFinalPrice()
        HeadController.reservationList.add_reservation_to_table(this)
        reserve()
    }

    fun add_reservation_to_file(){
        var file = File("reservations.txt")
        if(file.exists()){
            file.appendText("$id_room, $date_from, $date_to, $first_name, $last_name, $id_number, ${offer.toString()}\n")
        }
        else{
            println("Ne postoji fajl")
        }
    }

    fun calculateFinalPrice() : Double {
       return length_of_stay * (offer.offer_price_per_day() + selected_room.price_per_night)
            + offer.party_price + offer.massage_price
    }

    override fun toString(): String {
        var result = ""
        result = "Ime: $first_name\n" +
                "Prezime: $last_name\n" +
                "Broj licne karte: $id_number\n" +
                "Datum dolaska: $date_from\n" +
                "Datum odlaska: $date_to\n" +
                "Tip sobe: $selected_room\n" +
                "Tip paketa: ${offer.toString()}\n" +
                "Ukupna cena: $final_price\n"
        return result
    }

    fun reserve(){
        val dates_list = mutableListOf<LocalDate>()

        for (i in 0 .. length_of_stay-1) {
            val period = Period.of(0, 0, i)
            val date = date_from.plus(period)
            dates_list.add(date)
        }

        for (date in dates_list) {
            this.selected_room.availability[id_room]?.add(date)
        }
        println(selected_room.availability)
    }



    companion object {

        fun preCalculateFinalPrice(pre_dat_od : LocalDate, pre_dat_do : LocalDate, pre_selected_room : Room, pre_offer : Offer) : Double{
            val duzina_trajanja = abs((pre_dat_od.compareTo(pre_dat_do)))
            return duzina_trajanja * (pre_offer.offer_price_per_day() + pre_selected_room.price_per_night) + pre_offer.party_price + pre_offer.massage_price
        }
        fun preCalculateFinalPriceWithoutOffer(pre_dat_od : LocalDate, pre_dat_do : LocalDate, pre_selected_room : Room) : Double{
            val duzina_trajanja = ChronoUnit.DAYS.between(pre_dat_od, pre_dat_do)
            println(duzina_trajanja)
            return duzina_trajanja * (pre_selected_room.price_per_night)
        }
    }

}