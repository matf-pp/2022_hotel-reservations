package Controllers

import Rooms.Basic
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.stage.Stage

class BasicController : HeadController() {
    lateinit var room: Basic

    @FXML
    private lateinit var lbAleksa : Label

    @FXML
    private fun btKlikniMeAction(event: ActionEvent){
        lbAleksa.text = lbAleksa.text + " najjaci!"
    }

    @FXML
    private fun btGoBackAction(event: ActionEvent) {
        HeadController.stage.scene = HeadController.scene_map["hotel"]
    }
    // TODO ovde idu akcije, dugmici koji ce da se izvrsavaju nad room
}