import javafx.scene.layout.Pane


class Reservation(var ime : String, var prezime : String, var dat_od: String = Reservation.static_dat_od!!, var dat_do : String = Reservation.static_dat_do!!, var duzina_trajanja: Int = Reservation.static_duzina_trajanja!!,
                  var izabrana_soba: Room = static_izabrana_soba, var izabrani_paket : Package? = null){

    var konacna_cena : Double = calculateFinalPrice()

    fun calculateFinalPrice() : Double {
        if(izabrani_paket == null) {
            return duzina_trajanja * izabrana_soba.price_per_night!!
        }
        else {
            val dorucak = if(izabrani_paket!!.dorucak) 100.0 else 0.0
            val rucak = if(izabrani_paket!!.rucak) 100.0 else 0.0
            val vecera = if(izabrani_paket!!.vecera) 100.0 else 0.0
            val cena_po_noci = izabrani_paket!!.popust_po_nocenju * izabrana_soba.price_per_night!!
            return cena_po_noci * duzina_trajanja + dorucak + rucak + vecera
        }
    }

    fun setPackage(paket : Package){
        izabrani_paket = paket
        konacna_cena = calculateFinalPrice()
    }


    fun reserve(){
        // TODO
    }

    companion object {
        lateinit var static_izabrana_soba : Room
        var static_dat_od : String? = null
        var static_dat_do : String? = null
        var static_duzina_trajanja : Int? = null

        fun show_formular() : Pane? {
            // TODO
            return null
        }

        fun checkAvailability(room: Room, dat_od : String?, dat_do : String?) : Int{
            var id =  -1
            // ovde ide provera

            if(id != -1) {
                static_izabrana_soba = room
                static_dat_od = dat_od
                static_dat_do = dat_do
                // TODO ovo treba funkcija da sredi
                static_duzina_trajanja = 10
                return id
            }
            else {
                return  id
            }
        }
    }
}