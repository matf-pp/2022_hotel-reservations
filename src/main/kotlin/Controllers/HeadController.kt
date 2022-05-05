package Controllers

import Controllers.ReservationController
import ReservationThings.Food
import ReservationThings.Reservation
import Rooms.Room
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import java.io.File

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

        val path = javaClass.classLoader.getResource("reservations.txt").path
        val file = File(path)
        val path_url = file.toURI().toURL()

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

        fun setterStage(stage_parameter: Stage) {
            stage = stage_parameter
        }
        fun getterStage() : Stage{
            return stage
        }
    }
}