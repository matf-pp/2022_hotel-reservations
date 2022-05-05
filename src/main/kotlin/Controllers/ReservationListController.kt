package Controllers

import ReservationThings.Reservation
import Rooms.Room
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.cell.PropertyValueFactory
import java.net.URL
import java.time.LocalDate
import java.util.*

class ReservationListController : Initializable{

    private val reservations = FXCollections.observableArrayList<Reservation>()

    fun add_reservation_to_table(reservation: Reservation){
        reservations.add(reservation)
    }

    @FXML
    private lateinit var btAbout: Button

    @FXML
    private lateinit var btHome: Button

    @FXML
    private lateinit var btReservations: Button

    @FXML
    private lateinit var tbReservations: TableView<Reservation>

    @FXML
    private lateinit var tcDateFrom: TableColumn<Reservation, LocalDate>

    @FXML
    private lateinit var tcDateTo: TableColumn<Reservation, LocalDate>

    @FXML
    private lateinit var tcFirstName: TableColumn<Reservation, String>

    @FXML
    private lateinit var tcLastName: TableColumn<Reservation, String>

    @FXML
    private lateinit var tcSelectedRoom: TableColumn<Reservation, Room>

    @FXML
    private lateinit var tcTotalNights: TableColumn<Reservation, Int>

    @FXML
    private lateinit var tcTotalPrice: TableColumn<Reservation, Double>

    @FXML
    fun btAboutActionOpen(event: ActionEvent) {
        HeadController.setScene("about")
    }

    @FXML
    fun btHomeActionOpen(event: ActionEvent) {
        HeadController.setScene("hotel")
    }

    @FXML
    fun btReservationsActionOpen(event: ActionEvent) {
        HeadController.setScene("reservationsList")
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        tcFirstName.cellValueFactory = PropertyValueFactory("first_name")
        tcLastName.cellValueFactory = PropertyValueFactory("last_name")
        tcDateFrom.cellValueFactory = PropertyValueFactory("date_from")
        tcDateTo.cellValueFactory = PropertyValueFactory("date_to")
        tcSelectedRoom.cellValueFactory = PropertyValueFactory("selected_room")
        tcTotalNights.cellValueFactory = PropertyValueFactory("length_of_stay")
        tcTotalPrice.cellValueFactory = PropertyValueFactory("final_price")

        tbReservations.items = reservations
    }

}
