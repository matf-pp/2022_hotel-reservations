package Controllers

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.TableView

class ReservationListController {

    @FXML
    private lateinit var btAbout: Button

    @FXML
    private lateinit var btHome: Button

    @FXML
    private lateinit var btReservations: Button

    @FXML
    private lateinit var tbReservations: TableView<Any>

    @FXML
    fun btAboutActionOpen(event: ActionEvent) {
        HeadController.setScene("about")
    }

    @FXML
    fun btHomeActionOpen(event: ActionEvent) {
        HeadController.setScene("hotel")
    }

    @FXML
    fun btReservationsActionOpen(event: ActionEvent) {
        HeadController.setScene("reservationsList")
    }

}
