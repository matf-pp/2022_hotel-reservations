package Rooms

import Rooms.Room
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import java.time.LocalDate
import java.time.Period
import kotlin.math.abs

class Superior(override var num_beds : Int) : Room(num_beds) {
    var list_id  = dodaj_id(num_beds)

    private fun dodaj_id(num_beds: Int) : MutableList<Int>{
        var lista_id = mutableListOf<Int>()
        var empty_list = mutableListOf<LocalDate>()
        if(num_beds == 2){
            for (i in 201..212) {
                lista_id.add(i)
                super.availability?.put(i, empty_list)
            }
        }

        else if(num_beds == 3) {
            for (i in 213..224) {
                lista_id.add(i)
                super.availability?.put(i, empty_list)
            }
        }
        else {
            for (i in 225..236) {
                lista_id.add(i)
                super.availability?.put(i, empty_list)
            }
        }
        return lista_id
    }


    override var price_per_night : Double = when(this.num_beds){
        2 -> 6000.0
        3 -> 8500.0
        4 -> 11150.0
        else -> 0.0
    }


    override fun find_id(date1 : LocalDate, date2 : LocalDate) : Int {
        var length_of_stay: Int = abs(date2.compareTo(date1))
        var counter: Int = 0
        var final_id: Int = -1
        var dates_list = mutableListOf<LocalDate>()

        for (i in 0..(length_of_stay-1)) {
            val period = Period.of(0, 0, i)
            val date = date1.plus(period)
            dates_list.add(date)
        }
        for (j in list_id) {
            for (date in dates_list) {
                if (super.availability?.get(j)?.contains(date) == false)
                    counter++
            }
            if (counter == length_of_stay) {
                final_id = j
                break
            }
        }
        super.id = final_id
        return final_id
    }
}