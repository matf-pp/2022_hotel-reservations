package Controllers

import ReservationThings.Reservation
import Rooms.Basic
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.paint.Color
import java.time.LocalDate
import java.time.temporal.ChronoUnit

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
    private lateinit var rbFullBoard: RadioButton

    @FXML
    private lateinit var rbHalfBoard: RadioButton

    @FXML
    private lateinit var tfPrice: TextField

    @FXML
    private lateinit var tfSelectedDateFrom: TextField

    @FXML
    private lateinit var tfSelectedDateTo: TextField

    @FXML
    fun btAboutActionOpen(event: ActionEvent) {

    }
    @FXML
    fun btActionCheckAvailability(event: ActionEvent) {
        val dateFrom: LocalDate = dtDateFrom.value
        val dateTo: LocalDate = dtDateTo.value

        val id = room.find_id(dateFrom, dateTo)
        if(id != -1){
            unlock()
            tfSelectedDateFrom.text = dateFrom.toString()
            tfSelectedDateTo.text = dateTo.toString()
            setLabelTextAndColor(lbSuccess, "Success", "green")
            tfPrice.text = calculate_final_price_room_food().toString()
        }
        else {
            lock()
            tfSelectedDateFrom.text = ""
            tfSelectedDateTo.text = ""
            setLabelTextAndColor(lbSuccess, "No available rooms!", "red")
            tfPrice.text = ""
        }
        resetDates()
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
    private fun resetDates(){
        dtDateFrom.isDisable = false
        dtDateTo.isDisable = true
        dtDateFrom.value = null
        dtDateTo.value = null
        btCheckAvailability.isDisable = true
    }

    @FXML
    fun dtActionDateFrom(event: ActionEvent) {
        try {
            if(ChronoUnit.DAYS.between(LocalDate.now(), dtDateFrom.value) < 0){
                setLabelTextAndColor(lbSuccess, "Date from is in past", "red")
                dtDateTo.value = null
                dtDateTo.isDisable = true
                btCheckAvailability.isDisable = true
            }
            else{
                setLabelTextAndColor(lbSuccess)
                dtDateTo.value = null
                dtDateTo.isDisable = false
            }
        }
        catch (e : Exception){

        }
    }
    @FXML
    fun dtActionDateTo(event: ActionEvent) {
        try {
            if(ChronoUnit.DAYS.between(dtDateFrom.value, dtDateTo.value) < 1){
                setLabelTextAndColor(lbSuccess, "Wrong date period", "red")
                btCheckAvailability.isDisable = true
            }
            else{
                setLabelTextAndColor(lbSuccess)
                btCheckAvailability.isDisable = false
            }
        }
        catch (e : Exception){

        }
    }
    fun set_toggle_food() {
        val tgFood = ToggleGroup()

        rbBreakfast.isSelected = true

        rbFullBoard.toggleGroup = tgFood
        rbHalfBoard.toggleGroup = tgFood
        rbBreakfast.toggleGroup = tgFood
    }

    fun setNumLabel(num : Int) {
        lbNumOfPersons.text = "$numberOfPersonsBegin $num"
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

    private fun setLabelTextAndColor(label: Label , text : String = "",color : String = "black"){
        if (color.equals("red")){
            label.textFill = Color.color(1.0, 0.0, 0.0)
            label.text = text
        }
        else if (color.equals("green")){
            label.textFill = Color.color(0.0, 1.0, 0.0)
            label.text = text
        }
        else if(color.equals("black")){
            label.textFill = Color.color(0.0, 0.0, 0.0)
            label.text = text
        }
        else {
            println("Available colors are red green and by default black")
        }
    }

    private fun calculate_final_price_room_food() : Double{
        val localDateFrom = LocalDate.parse(tfSelectedDateFrom.text)
        val localDateTo = LocalDate.parse(tfSelectedDateTo.text)

        var cena : Double = 0.0
        val duzina_ostajanja = ChronoUnit.DAYS.between(localDateFrom, localDateTo)
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

}
