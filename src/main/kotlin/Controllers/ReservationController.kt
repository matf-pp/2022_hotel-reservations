package Controllers

import Controllers.HeadController
import ReservationThings.Food
import ReservationThings.Offer
import ReservationThings.Reservation
import Rooms.Room
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.input.InputMethodEvent
import javafx.scene.input.KeyEvent
import javafx.scene.input.MouseEvent
import javafx.scene.paint.Color
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class ReservationController {

    lateinit var  selectedRoom : Room

    lateinit var dateFrom : LocalDate
    lateinit var dateTo : LocalDate
    lateinit var food1: Food

    fun fill_fields(date1 : LocalDate, date2 : LocalDate, food : ReservationThings.Food, price_room_food : String) {
        var type: String = if (selectedRoom is Rooms.Basic) "Basic"
        else if (selectedRoom is Rooms.Superior) "Superior"
        else "Premium Apartment"
        tfRoom.text = "${selectedRoom.id}, $type"
        tfNumBeds.text = selectedRoom.num_beds.toString()
        tfDateFrom.text = date1.toString()
        tfDateTo.text = date2.toString()
        tfTotalNights.text = ChronoUnit.DAYS.between(date1, date2).toString()
        tfTotalPrice.text = price_room_food
        lock_cb()

        // TODO baca neki exception sada
        //var numMassage : Int = if(rbMassage.isSelected) cbMassage.value as Int else 0
        dateFrom = date1
        dateTo = date2
        food1 = food
        remove_focus()
    }

    private fun remove_focus(){
        lbCheckEmail.requestFocus()
    }

    private fun initialize(){
        cbMassage.items.add(1)
        cbMassage.items.add(2)
        cbMassage.items.add(3)
        cbMassage.items.add(4)
        cbMassage.items.add(5)
        indikator = 1
    }

    private fun unlock_cb(){
        cbMassage.isDisable = false
    }
    private fun lock_cb(){
        cbMassage.isDisable = true
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
        else if (color.equals("yellow")){
            label.textFill = Color.color(0.9, 1.0, 0.2)
            label.text = text
        }
        else if(color.equals("black")){
            label.textFill = Color.color(0.0, 0.0, 0.0)
            label.text = text
        }
        else {
            println("Available colors are red, green, yellow and by default black")
        }
    }

    private fun check_user_input(){
        var checkFirstName = false
        var checkLastName = false
        var checkID = false
        var checkEmail = false
        var checkPhone = false

        // FIRST NAME
        if(tfFirstName.text.equals("")){
            setLabelTextAndColor(lbCheckFirstName, "EMPTY", "yellow")
        }
        else{
            checkFirstName = true
            setLabelTextAndColor(lbCheckFirstName, "OK", "green")
        }

        // LAST NAME
        if(tfLastName.text.equals("")){
            setLabelTextAndColor(lbCheckLastName, "EMPTY", "yellow")
        }
        else{
            checkLastName = true
            setLabelTextAndColor(lbCheckLastName, "OK", "green")
        }

        // EMAIL
        val patternEmail = Regex("^\\w+@([a-zA-Z_]+\\.)*\\w+\\.\\w{2,3}$")
        if(tfEmail.text.equals("")){
            setLabelTextAndColor(lbCheckEmail, "EMPTY", "yellow")
        }
        else if(!patternEmail.containsMatchIn(tfEmail.text)){
            setLabelTextAndColor(lbCheckEmail, "NOT VALID", "red")
        }
        else{
            checkEmail = true
            setLabelTextAndColor(lbCheckEmail, "OK", "green")
        }

        // PHONE
        val patternPhone = Regex("^[0-9]{6,}$")
        if(tfPhone.text.equals("")){
            setLabelTextAndColor(lbCheckPhone, "EMPTY", "yellow")
        }
        else if(!patternPhone.containsMatchIn(tfPhone.text)){
            setLabelTextAndColor(lbCheckPhone, "NOT VALID", "red")
        }
        else{
            checkPhone = true
            setLabelTextAndColor(lbCheckPhone, "OK", "green")
        }

        // ID NUMBER
        val patternID = Regex("^\\d{8}$")
        if(tfIdNumber.text.equals("")){
            setLabelTextAndColor(lbCheckIDNumber, "EMPTY", "yellow")
        }
        else if(!patternID.containsMatchIn(tfIdNumber.text)){
            setLabelTextAndColor(lbCheckIDNumber, "NOT VALID", "red")
        }
        else{
            checkID = true
            setLabelTextAndColor(lbCheckIDNumber, "OK", "green")
        }

        // RESERVE CHECK
        btConfirmReservation.isDisable = !(checkFirstName && checkLastName && checkEmail && checkID && checkPhone)

    }
    //indikator za cbMassage, zbog inicijalizovanja - da ne dodaje svaki put nove opcije u cb
    private var indikator = 0

    @FXML
    private lateinit var btConfirmReservation: Button

    @FXML
    private lateinit var btExit: Button

    @FXML
    private lateinit var tfDateFrom: TextField

    @FXML
    private lateinit var tfDateTo: TextField

    @FXML
    private lateinit var tfEmail: TextField

    @FXML
    private lateinit var tfFirstName: TextField

    @FXML
    private lateinit var tfIdNumber: TextField

    @FXML
    private lateinit var tfLastName: TextField

    @FXML
    private lateinit var tfNumBeds: TextField

    @FXML
    private lateinit var tfPhone: TextField

    @FXML
    private lateinit var tfRoom: TextField

    @FXML
    private lateinit var tfTotalNights: TextField

    @FXML
    private lateinit var tfTotalPrice: TextField

    @FXML
    private lateinit var cbMassage: ChoiceBox<Any>

    @FXML
    private lateinit var rbMassage: RadioButton

    @FXML
    private lateinit var rbParking: RadioButton

    @FXML
    private lateinit var rbParty: RadioButton

    @FXML
    private lateinit var rbWellness: RadioButton

    @FXML
    private lateinit var lbCheckEmail: Label

    @FXML
    private lateinit var lbCheckFirstName: Label

    @FXML
    private lateinit var lbCheckIDNumber: Label

    @FXML
    private lateinit var lbCheckLastName: Label

    @FXML
    private lateinit var lbCheckPhone: Label

    @FXML
    fun btActionConfrimReservation(event: ActionEvent) {
        val name = tfFirstName.text
        val last_name = tfLastName.text
        val id = tfIdNumber.text
        val offer = Offer(food1, selectedRoom.num_beds, rbParking.isSelected,
            rbWellness.isSelected, 0, rbParty.isSelected)
        val new_reservation = Reservation(name, last_name, id, dateFrom, dateTo, selectedRoom, selectedRoom.id, offer)
        HeadController.setScene("hotel")
    }

    @FXML
    fun btActionExit(event: ActionEvent) {
        HeadController.stage.scene = HeadController.scene_map["hotel"]
    }
    @FXML
    fun rbActionMassage(event: ActionEvent) {
        if (rbMassage.isSelected) {
            if (indikator == 0)
                initialize()
            unlock_cb()
        }
        else{
            lock_cb()
        }
    }

    @FXML
    fun rbActionParking(event: ActionEvent) {
        var price_room_food : Double = tfTotalPrice.text.toDouble()
        if(rbParking.isSelected){
            tfTotalPrice.text = (price_room_food + Offer.price_parking * ChronoUnit.DAYS.between(dateFrom, dateTo)).toString()
        }
        else{
            tfTotalPrice.text = (price_room_food - Offer.price_parking * ChronoUnit.DAYS.between(dateFrom, dateTo)).toString()
        }
    }

    @FXML
    fun rbActionParty(event: ActionEvent) {
        var price_room_food : Double = tfTotalPrice.text.toDouble()
        if(rbParty.isSelected){
            tfTotalPrice.text = (price_room_food + Offer.price_party * selectedRoom.num_beds).toString()
        }
        else{
            tfTotalPrice.text = (price_room_food -  Offer.price_party * selectedRoom.num_beds).toString()
        }
    }

    @FXML
    fun rbActionWellness(event: ActionEvent) {
        var price_room_food : Double = tfTotalPrice.text.toDouble()
        var length_of_stay = ChronoUnit.DAYS.between(dateFrom, dateTo)
        if(rbWellness.isSelected){
            tfTotalPrice.text = (price_room_food + Offer.price_wellness * selectedRoom.num_beds * length_of_stay).toString()
        }
        else{
            tfTotalPrice.text = (price_room_food - Offer.price_wellness * selectedRoom.num_beds * length_of_stay).toString()
        }
    }

    @FXML
    fun cbActionMassage(event: MouseEvent) {
        var price_room_food : Double = tfTotalPrice.text.toDouble()
        var length_of_stay = ChronoUnit.DAYS.between(dateFrom, dateTo)
        if(cbMassage.value != null){
            tfTotalPrice.text = (price_room_food + Offer.price_massage1 * cbMassage.value as Int).toString()
        }
        else{
            //tfTotalPrice.text = (price_room_food - Offer.price_wellness * selectedRoom.num_beds * length_of_stay).toString()
        }
    }

    @FXML
    fun tfActionEmail(event: KeyEvent) {
        check_user_input()
    }

    @FXML
    fun tfActionFirstName(event: KeyEvent) {
        check_user_input()
    }

    @FXML
    fun tfActionIDNumber(event: KeyEvent) {
        check_user_input()
    }

    @FXML
    fun tfActionLastName(event: KeyEvent) {
        check_user_input()
    }

    @FXML
    fun tfActionPhone(event: KeyEvent) {
        check_user_input()
    }
}
