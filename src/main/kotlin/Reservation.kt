import javafx.scene.layout.Pane
import java.time.LocalDate
import java.time.Period
import java.time.temporal.ChronoUnit

class Reservation(var ime : String,
                  var prezime : String,
                  var dat_od: LocalDate = Reservation.static_dat_od,
                  var dat_do : LocalDate = Reservation.static_dat_do,
                  var duzina_trajanja: Long = Reservation.static_duzina_trajanja!!,
                  var izabrana_soba: Room = static_izabrana_soba,
                  var izabrani_paket : Package? = null,
                  var id : Int = static_id!!
                  )
{

    private var konacna_cena : Double = calculateFinalPrice()

    fun calculateFinalPrice() : Double {
        if(izabrani_paket == null) {
            return duzina_trajanja * izabrana_soba.price_per_night
        }
        else {
            val dorucak = if(izabrani_paket!!.dorucak) 100.0 else 0.0
            val rucak = if(izabrani_paket!!.rucak) 100.0 else 0.0
            val vecera = if(izabrani_paket!!.vecera) 100.0 else 0.0
            val cena_po_noci = izabrani_paket!!.popust_po_nocenju * izabrana_soba.price_per_night
            return cena_po_noci * duzina_trajanja + dorucak + rucak + vecera
        }
    }

    fun getKonacnaCena() : Double = konacna_cena

    fun setPackage(paket : Package){
        izabrani_paket = paket
        konacna_cena = calculateFinalPrice()
        // treba i da se zameni deo na GUI koji ce da prikaze izmenu
    }

    fun reserve(){
        var lista_id = izabrana_soba.availability?.get(id)

        val datumi = mutableListOf<LocalDate>()
        for(i in 1..duzina_trajanja){
            val period = Period.of(0, 0, i.toInt())
            val date = dat_od.plus(period)
            datumi.add(date)
        }
        // TODO kada Anja izmeni deo da mogu da lista unutar mape bude mutable, tada treba da se doda ovde na osnovu ID-ja potreban datum
        //datumi.forEach { date -> lista_id.add(date)}
    }

    companion object {
        lateinit var static_izabrana_soba : Room
        lateinit var static_dat_od : LocalDate
        lateinit var static_dat_do : LocalDate
        var static_id : Int? = null
        var static_duzina_trajanja : Long? = 0

        fun show_formular() : Pane? {
            // TODO
            return null
        }

        fun checkAvailability(room: Room, dat_od : LocalDate, dat_do : LocalDate){
            var id =  -1
            val duzina_boravka = ChronoUnit.DAYS.between(dat_od, dat_do)
            var datumi = mutableListOf<LocalDate>()

            for(i in 1..duzina_boravka){
                val period = Period.of(0, 0, i.toInt())
                val date = dat_od.plus(period)
                datumi.add(date)
            }
            room.availability?.forEach { (key, value) ->
                var counter = 0
                for(potrebanDatum in datumi){
                    if(!value.contains(potrebanDatum)){
                        counter++
                    }
                }
                if(counter == duzina_boravka.toInt()){
                    id = key
                }
            }
            if(id != -1) {
                static_izabrana_soba = room
                static_dat_od = dat_od
                static_dat_do = dat_do
                static_duzina_trajanja = duzina_boravka
                static_id = id
            }
        }
    }
}