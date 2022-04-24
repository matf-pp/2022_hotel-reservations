import Controllers.HeadController
import Rooms.Room
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Button
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
    fun btActionConfrimReservation(event: ActionEvent) {

    }

    @FXML
    fun btActionExit(event: ActionEvent) {
        HeadController.stage.scene = HeadController.scene_map["hotel"]
    }

}
