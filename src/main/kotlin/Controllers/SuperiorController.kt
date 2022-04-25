package Controllers

import Rooms.Superior
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.*

class SuperiorController {

    private val numberOfPersonsBegin : String = "Number of persons: "

    lateinit var room : Superior

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

    @FXML
    private lateinit var rbBreakfast: RadioButton

    @FXML
    private lateinit var rbDinner: RadioButton

    @FXML
    private lateinit var rbLunch: RadioButton

    @FXML
    private lateinit var tfPrice: TextField

    public fun setNumLabel(num : Int) {
        lbNumOfPersons.text = "$numberOfPersonsBegin $num"
    }

    @FXML
    fun btAboutActionOpen(event: ActionEvent) {

    }

    @FXML
    fun btActionCheckAvailability(event: ActionEvent) {

    }

    @FXML
    fun btActionCheckout(event: ActionEvent) {

    }

    @FXML
    fun btHomeActionOpen(event: ActionEvent) {
        HeadController.setScene("hotel")
    }

    @FXML
    fun btReservationsActionOpen(event: ActionEvent) {

    }
    @FXML
    fun rbActionBreakfast(event: ActionEvent) {

    }

    @FXML
    fun rbActionDinner(event: ActionEvent) {

    }

    @FXML
    fun rbActionLunch(event: ActionEvent) {

    }

}
