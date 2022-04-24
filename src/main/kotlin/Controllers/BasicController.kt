package Controllers

import Rooms.Basic
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.DatePicker
import javafx.scene.control.Label
import javafx.scene.control.ProgressIndicator

class BasicController {

    private val numberOfPersonsBegin : String = "Number of persons: "

    lateinit var room : Basic

    @FXML
    private lateinit var btAbout: Button

    @FXML
    private lateinit var btCheckAvailability: Button

    @FXML
    private lateinit var btCheckout: Button

    @FXML
    private lateinit var btHome: Button

    @FXML
    private lateinit var btReservations: Button

    @FXML
    private lateinit var dtDateFrom: DatePicker

    @FXML
    private lateinit var dtDateTo: DatePicker

    @FXML
    private lateinit var lbNumOfPersons: Label

    @FXML
    private lateinit var lbSuccess: Label

    @FXML
    private lateinit var pbSuccess: ProgressIndicator

    public fun setNumLabel(num : Int) {
        lbNumOfPersons.text = "$numberOfPersonsBegin $num"
    }

    @FXML
    fun btAboutActionOpen(event: ActionEvent) {

    }
    @FXML
    fun btActionCheckAvailability(event: ActionEvent) {
        // TODO delete and place it in btActionCheckout
        HeadController.reservation.selectedRoom = room
        HeadController.setScene("reservation")

    }

    @FXML
    fun btActionCheckout(event: ActionEvent) {

    }

    @FXML
    fun btHomeActionOpen(event: ActionEvent) {
        lbNumOfPersons.text = ""
        HeadController.setScene("hotel")
    }

    @FXML
    fun btReservationsActionOpen(event: ActionEvent) {

    }

}
