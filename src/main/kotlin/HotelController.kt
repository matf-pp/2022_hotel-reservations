import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.Label

class BasicFXMLController {
    @FXML
    private lateinit var welcomeLabel : Label

    @FXML
    private fun handleButtonAction(event: ActionEvent) {
        welcomeLabel.text = "Welcome"
    }
}