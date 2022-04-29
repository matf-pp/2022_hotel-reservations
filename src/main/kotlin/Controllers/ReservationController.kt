package Controllers

import Controllers.HeadController
import ReservationThings.Offer
import Rooms.Room
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.ChoiceBox
import javafx.scene.control.RadioButton
import javafx.scene.control.TextField
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class ReservationController {

    private lateinit var offer : ReservationThings.Offer
    lateinit var  selectedRoom : Room

    lateinit var dateFrom : LocalDate
    lateinit var dateTo : LocalDate

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

        var numMassage : Int = if(rbMassage.isSelected) cbMassage.value as Int else 0
        dateFrom = date1
        dateTo = date2
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
    fun btActionConfrimReservation(event: ActionEvent) {

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
}
