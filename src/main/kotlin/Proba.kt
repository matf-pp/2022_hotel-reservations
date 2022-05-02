import java.io.File
import java.time.LocalDate
import java.time.Period
import java.time.temporal.ChronoUnit
import kotlin.math.abs

class Proba {
 //ovde samo testiramo ponasanje pojedinacnih funkcija u kotlinu, nije relevantno za projekat
}

fun main() {
    var duzina : Int = 0
    var duzina2 : Long = 0
    var dat1 : LocalDate = LocalDate.of(2022,4,6)
    var dat2 : LocalDate = LocalDate.of(2022,4,21)
    duzina = dat1.compareTo(dat2)
    duzina2 = ChronoUnit.DAYS.between(dat1, dat2)


    //println(duzina)
    //println(duzina2)
    //println(abs(duzina))

    var lista = mutableListOf<LocalDate>()
    //lista.add(11)
    //lista.add(12)
    //lista.add(13)

    //var mapa : MutableMap<Int, MutableList<Int>> = emptyMap<Int, MutableList<Int>>() as MutableMap<Int, MutableList<Int>>
    //mapa.put(key = 1, value = lista)
    //mapa.put(key = 2, value = lista)
    //mapa.put(key = 4, value = lista)

    //var list : MutableList<Int>? = mapa.get(1)

    for (i in 0..3) {
        val period = Period.of(0, 0, i)
        val date = dat1.plus(period)
        lista.add(date)
    }

    println(lista)



    fun dodaj_u_fajl(){
        var file = File("reservations.txt")
        if(file.exists()){
            println("Postoji fajl")
            file.writeText("anja\n")
            file.appendText("nikola")
            file.appendText("")
        }
        else{
            println("Ne postoji fajl")
        }
    }
    //dodaj_u_fajl()

    fun add_reservation_to_file(){
        var file = File("reservations.txt")
        var reservation_string = ""
        if(file.exists()){
            println("Postoji fajl")
            file.appendText("5: ")
            var lista = mutableListOf<LocalDate>()
            for (i in 0..duzina2.toInt()-1) {
                val period = Period.of(0, 0, i)
                val date = dat1.plus(period)
                lista.add(date)
            }
            for(i in 0..duzina2.toInt()-1){
                if(i<duzina2.toInt()-1)
                    file.appendText(lista[i].toString().plus(", "))
                else
                    file.appendText(lista[i].toString().plus("\n"))
            }
        }

    }
    add_reservation_to_file()


}