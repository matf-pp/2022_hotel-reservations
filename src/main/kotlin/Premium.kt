import javafx.scene.Scene

class Premium(num_beds : Int) : Room(num_beds) {
    var list_id  = dodaj_id()

    private fun dodaj_id() : MutableList<Int>{
        var lista_id = mutableListOf<Int>()
        for (i in 301..318){
            lista_id.add(i)
            super.availability?.put(i, emptyList())
        }
        return lista_id
    }

    override var price_per_night : Double = when(this.num_beds){
        2 -> 8000.0
        3 -> 11300.0
        4 -> 14750.0
        else -> 0.0
    }

    override fun detailed_view(): Scene {
        TODO("Not yet implemented")
    }
}