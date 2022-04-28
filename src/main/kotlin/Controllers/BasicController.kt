package Controllers

import ReservationThings.Reservation
import Rooms.Basic
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.*
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class BasicController {

    fun set_toggle_food() {
        var tgFood = ToggleGroup()

        rbBreakfast.isSelected = true

        rbFullBoard.toggleGroup = tgFood
        rbHalfBoard.toggleGroup = tgFood
        rbBreakfast.toggleGroup = tgFood
    }

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
    private lateinit var rbFullBoard: RadioButton

    @FXML
    private lateinit var rbHalfBoard: RadioButton

    @FXML
    private lateinit var tfPrice: TextField

    public fun setNumLabel(num : Int) {
        lbNumOfPersons.text = "$numberOfPersonsBegin $num"
    }

    @FXML
    fun btAboutActionOpen(event: ActionEvent) {

    }

    private fun unlock(){
        rbBreakfast.isDisable = false
        rbHalfBoard.isDisable = false
        rbFullBoard.isDisable = false
        lbPrice.isDisable = false
        tfPrice.isDisable = false
        btCheckout.isDisable = false
    }
    private fun lock(){
        tfPrice.text = ""

        rbBreakfast.isDisable = true
        rbHalfBoard.isDisable = true
        rbFullBoard.isDisable = true
        lbPrice.isDisable = true
        tfPrice.isDisable = true
        btCheckout.isDisable = true 
    }

    private fun calculate_final_price_room_food() : Double{
        var cena : Double = 0.0
        val duzina_ostajanja = ChronoUnit.DAYS.between(dtDateFrom.value, dtDateTo.value)
        if (rbBreakfast.isSelected){
            cena += HeadController.foodBreakfast.get_total_price() * room.num_beds * duzina_ostajanja
        }
        else  if (rbHalfBoard.isSelected){
            cena += HeadController.foodHalf.get_total_price() * room.num_beds * duzina_ostajanja
        }
        else  if (rbFullBoard.isSelected){
            cena += HeadController.foodFull.get_total_price() * room.num_beds * duzina_ostajanja
        }
        return cena + room.price_per_night * duzina_ostajanja
    }

    @FXML
    fun btActionCheckAvailability(event: ActionEvent) {
        if(dtDateFrom.value != null && dtDateTo.value != null) {
            val dateFrom: LocalDate = dtDateFrom.value
            val dateTo: LocalDate = dtDateTo.value

            val id = room.find_id(dateFrom, dateTo)
            if(id != -1){
                unlock()
                tfPrice.text = calculate_final_price_room_food().toString()
            }
            else {
                lock()
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
        if (rbBreakfast.isSelected){
            tfPrice.text = calculate_final_price_room_food().toString()
        }
    }
    @FXML
    fun rbActionFullBoard(event: ActionEvent) {
        if (rbFullBoard.isSelected){
            tfPrice.text = calculate_final_price_room_food().toString()
        }
    }
    @FXML
    fun rbActionHalfBoard(event: ActionEvent) {
        if (rbHalfBoard.isSelected){
            tfPrice.text = calculate_final_price_room_food().toString()
        }
    }

}
