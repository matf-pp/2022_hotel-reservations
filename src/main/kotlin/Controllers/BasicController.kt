package Controllers

import ReservationThings.Reservation
import Rooms.Basic
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.*
import java.time.LocalDate

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
    private lateinit var lbPrice: Label

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
        // TODO nastaviti kada Anja ispravi
        if(dtDateFrom.value != null && dtDateTo.value != null) {
            val dateFrom: LocalDate = dtDateFrom.value
            val dateTo: LocalDate = dtDateTo.value

            if(room.find_id(dateFrom, dateTo) != -1){
                //lbPrice.text = Reservation.preCalculateFinalPriceWithoutOffer(dateFrom, dateTo, room).toString()

            }
        }
    }

    @FXML
    fun btActionCheckout(event: ActionEvent) {
        HeadController.reservation.selectedRoom = room
        HeadController.setScene("reservation")
    }

    @FXML
    fun btHomeActionOpen(event: ActionEvent) {
        lbNumOfPersons.text = ""
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
