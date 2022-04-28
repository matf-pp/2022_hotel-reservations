package Controllers

import Controllers.HeadController
import Rooms.Room
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.ChoiceBox
import javafx.scene.control.RadioButton
import javafx.scene.control.TextField

class ReservationController {

    public lateinit var  selectedRoom : Room

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

    }

    @FXML
    fun rbActionParking(event: ActionEvent) {

    }

    @FXML
    fun rbActionParty(event: ActionEvent) {

    }

    @FXML
    fun rbActionWellness(event: ActionEvent) {

    }
}
