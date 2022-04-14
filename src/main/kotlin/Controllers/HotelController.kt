package Controllers

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.stage.Stage

class HotelController {

    companion object {
        lateinit var Stage : Stage
    }

    lateinit var basicController: BasicController
    lateinit var premiumApartmentController: PremiumApartmentController
    lateinit var superiorController: SuperiorController

    @FXML
    private lateinit var welcomeLabel : Label

    @FXML
    private fun btBasicActionOpen(event: ActionEvent) {
        Stage.scene = Scene(basicController.room.root, 500.0, 500.0)
    }
    @FXML
    private fun btPremiumActionOpen(event: ActionEvent){
        Stage.scene = Scene(premiumApartmentController.room.root,500.0, 500.0)
    }
}