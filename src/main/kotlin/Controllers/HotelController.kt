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

class HotelController : HeadController(){

    var rooms_mapping : MutableMap<String, Room> = emptyMap<String, Room>().toMutableMap()

    fun addRoomAndMapIt(id : String, room : Room){
        rooms_mapping[id] = room
    }

    lateinit var basicController: BasicController
    lateinit var premiumApartmentController: PremiumApartmentController
    lateinit var superiorController: SuperiorController

    @FXML
    private lateinit var welcomeLabel : Label

    @FXML
    private fun btBasicActionOpen(event: ActionEvent) {
        // TODO set room property to 1, 2, od 3 bed acordingly
        basicController.room = rooms_mapping["basic_two"] as Basic
        HeadController.stage.scene = scene_map["basic"]
    }
    @FXML
    private fun btSuperiorActionOpen(event: ActionEvent) {
        // TODO set room property to 1, 2, od 3 bed acordingly
        superiorController.room = rooms_mapping["superior_two"] as Superior
        HeadController.stage.scene = scene_map["superior"]
    }
    @FXML
    private fun btPremiumActionOpen(event: ActionEvent){
        // TODO set room property to 1, 2, od 3 bed acordingly
        premiumApartmentController.room = rooms_mapping["premium_room"] as PremiumApartment
        HeadController.stage.scene = scene_map["premium"]
    }
}