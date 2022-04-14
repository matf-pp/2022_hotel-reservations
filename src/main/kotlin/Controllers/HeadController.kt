package Controllers

import Rooms.Room
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

// ovde idu i ostali atributi
open class HeadController(){
    companion object {
        lateinit var stage : Stage
        var scene_map : MutableMap<String, Scene> = emptyMap<String, Scene>().toMutableMap()

        fun add_windows_to_map(id : String, room_scene : Scene) {
            scene_map[id] = room_scene
        }

        fun setterStage(stage_parameter: Stage) {
            stage = stage_parameter
        }
        fun getterStage() : Stage{
            return stage
        }
    }
}