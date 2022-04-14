package Controllers

import Rooms.Superior
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.Scene

class SuperiorController : HeadController(){
    lateinit var room: Superior

    @FXML
    private fun btGoBackAction(event: ActionEvent) {
        HeadController.stage.scene = HeadController.scene_map["hotel"]
    }

    // TODO ovde idu akcije, dugmici koji ce da se izvrsavaju nad room
}