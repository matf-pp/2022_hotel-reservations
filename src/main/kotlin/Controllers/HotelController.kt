package Controllers

import Rooms.Basic
import Rooms.PremiumApartment
import Rooms.Room
import Rooms.Superior
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.stage.Stage
import javafx.scene.control.RadioButton
import javafx.scene.control.ToggleGroup


class HotelController : HeadController(){

    var rooms_mapping : MutableMap<String, Room> = emptyMap<String, Room>().toMutableMap()

    @FXML
    private lateinit var btAbout: Button

    @FXML
    private lateinit var btBasic: Button

    @FXML
    private lateinit var btHome: Button

    @FXML
    private lateinit var btPremium: Button

    @FXML
    private lateinit var btReservations: Button

    @FXML
    private lateinit var btSuperior: Button

    @FXML
    private lateinit var rbBasic2: RadioButton

    @FXML
    private lateinit var rbBasic3: RadioButton

    @FXML
    private lateinit var rbBasic4: RadioButton

    @FXML
    private lateinit var rbSuperior2: RadioButton

    @FXML
    private lateinit var rbSuperior3: RadioButton

    @FXML
    private lateinit var rbSuperior4: RadioButton

    @FXML
    private lateinit var rbPremium : RadioButton

    fun initRadioButton(){
        val tgBasic = ToggleGroup()
        val tgSuperior = ToggleGroup()
        val tgPremium = ToggleGroup()

        rbBasic2.isSelected = true
        rbSuperior2.isSelected = true
        rbPremium.isSelected = true

        rbBasic2.toggleGroup = tgBasic
        rbBasic3.toggleGroup = tgBasic
        rbBasic4.toggleGroup = tgBasic

        rbSuperior2.toggleGroup = tgSuperior
        rbSuperior3.toggleGroup = tgSuperior
        rbSuperior4.toggleGroup = tgSuperior

        rbPremium.toggleGroup = tgPremium
    }

    fun addRoomAndMapIt(id : String, room : Room){
        rooms_mapping[id] = room
    }

    lateinit var basicController: BasicController
    lateinit var premiumApartmentController: PremiumApartmentController
    lateinit var superiorController: SuperiorController

    @FXML
    private fun btBasicActionOpen(event: ActionEvent) {
        if(rbBasic2.isSelected) {
            basicController.room = rooms_mapping["basic_two"] as Basic
            basicController.setNumLabel(2)
            HeadController.setScene("basic")
        }
        if(rbBasic3.isSelected){
            basicController.room = rooms_mapping["basic_three"] as Basic
            basicController.setNumLabel(3)
            HeadController.setScene("basic")
        }
        if(rbBasic4.isSelected){
            basicController.room = rooms_mapping["basic_four"] as Basic
            basicController.setNumLabel(4)
            HeadController.setScene("basic")
        }
    }
    @FXML
    private fun btSuperiorActionOpen(event: ActionEvent) {
        if(rbSuperior2.isSelected) {
            superiorController.room = rooms_mapping["superior_two"] as Superior
            superiorController.setNumLabel(2)
            HeadController.setScene("superior")
        }
        if(rbSuperior3.isSelected){
            superiorController.room = rooms_mapping["superior_three"] as Superior
            superiorController.setNumLabel(3)
            HeadController.setScene("superior")
        }
        if(rbSuperior4.isSelected){
            superiorController.room = rooms_mapping["superior_four"] as Superior
            superiorController.setNumLabel(4)
            HeadController.setScene("superior")
        }
    }
    @FXML
    private fun btPremiumActionOpen(event: ActionEvent){
        premiumApartmentController.room = rooms_mapping["premium_room"] as PremiumApartment
        premiumApartmentController.setNumLabel(4)
        HeadController.setScene("premium")
    }
    @FXML
    fun btHomeActionOpen(event: ActionEvent) {
        HeadController.setScene("hotel")
    }
    @FXML
    fun btReservationsActionOpen(event: ActionEvent) {
        HeadController.setScene("reservationsList")
    }

    @FXML
    fun btAboutActionOpen(event: ActionEvent) {
        HeadController.setScene("about")
    }

}