import javafx.scene.Scene
import java.text.DateFormat
import java.util.Date

class Basic(num_beds : Int) : Room (num_beds){
    var list_id  = dodaj_id()

    private fun dodaj_id() : MutableList<Int>{
        var lista_id = mutableListOf<Int>()
        for (i in 101..145){
            lista_id.add(i)
            super.availability?.put(i, emptyList())
        }
        return lista_id
    }

    override var price_per_night : Double = when(this.num_beds){
        2 -> 4000.0
        3 -> 5700.0
        4 -> 7400.0
        else -> 0.0
    }


    override fun detailed_view(): Scene {
         TODO("Not yet implemented")
     }

}
