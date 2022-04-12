import javafx.scene.Scene

class Superior(num_beds : Int) : Room(num_beds) {
    var list_id  = dodaj_id()

    private fun dodaj_id() : MutableList<Int>{
        var lista_id = mutableListOf<Int>()
        for (i in 201..236){
            lista_id.add(i)
            super.availability?.put(i, emptyList())
        }
        return lista_id
    }

    override var price_per_night : Double = when(this.num_beds){
        2 -> 6000.0
        3 -> 8500.0
        4 -> 11150.0
        else -> 0.0
    }

    override fun detailed_view(): Scene {
        TODO("Not yet implemented")
    }
}