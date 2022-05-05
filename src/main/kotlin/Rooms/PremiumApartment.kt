package Rooms

import java.time.LocalDate
import java.time.Period
import kotlin.math.abs

class PremiumApartment() : Room() {
    // ne prima num_beds jer su premium apartmani iskljucivo za 4 osobe
    var list_id  = dodaj_id()

    private fun dodaj_id() : MutableList<Int>{
        val lista_id = mutableListOf<Int>()
        for (i in 301..310){
            lista_id.add(i)
            val empty_list = mutableListOf<LocalDate>()
            super.availability[i] = empty_list
        }
        return lista_id
    }

    override var price_per_night : Double = 14750.0

    override fun find_id(date1 : LocalDate, date2 : LocalDate) : Int {
        var length_of_stay: Int = abs(date2.compareTo(date1))
        var counter: Int = 0
        var final_id: Int = -1
        var dates_list = mutableListOf<LocalDate>()

        for (i in 0..length_of_stay-1) {
            val period = Period.of(0, 0, i)
            val date = date1.plus(period)
            dates_list.add(date)
        }
        for (j in list_id) {
            counter = 0
            for (date in dates_list) {
                if (super.availability[j]?.contains(date) == false)
                    counter++
            }
            if (counter == length_of_stay ) {
                final_id = j
                break
            }
        }
        super.id = final_id
        return final_id
    }
    override fun add_date(id : Int, date : LocalDate){
        availability[id]?.add(date)
    }

    override fun toString(): String {
        return "Premium"
    }

}