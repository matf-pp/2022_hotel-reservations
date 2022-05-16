package Controllers

import Controllers.ReservationController
import ReservationThings.Food
import ReservationThings.Reservation
import Rooms.Room
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

// ovde idu i ostali atributi
open class HeadController(){
    companion object {
        lateinit var stage : Stage
        var scene_map : MutableMap<String, Scene> = emptyMap<String, Scene>().toMutableMap()

        lateinit var foodBreakfast : Food
        lateinit var foodHalf : Food
        lateinit var foodFull : Food

        lateinit var reservation : ReservationController
        lateinit var reservationList : ReservationListController
        var file = File(getPathName().toString())

        fun add_windows_to_map(id : String, room_scene : Scene) {
            scene_map[id] = room_scene
        }

        fun setScene(id : String) {
            try {
                stage.scene = scene_map[id]
            }
            catch (e : Exception){
                println(e.message)
            }
        }

        fun getPathName() : String?{
            var path : String? = null
            val p : Process
            try {
                p = Runtime.getRuntime().exec("whoami")
                val br = BufferedReader(
                    InputStreamReader(p.inputStream)
                )

                path = "/home/${br.readText().trim()}/Desktop/my_reservations.txt"

                p.waitFor();
                p.destroy();
            }
            catch (e : Exception){
                println(e.toString())
            }

            return path
        }

        fun setterStage(stage_parameter: Stage) {
            stage = stage_parameter
        }
        fun getterStage() : Stage{
            return stage
        }
    }
}