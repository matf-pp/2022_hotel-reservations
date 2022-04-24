import Controllers.*
import Rooms.Basic
import Rooms.PremiumApartment
import Rooms.Superior
import javafx.application.Application
import javafx.application.Application.launch
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.ToggleGroup
import javafx.stage.Stage

fun main(args: Array<String>)
{
    launch(MainWindow::class.java)
}

class MainWindow : Application()
{
    val WIDTH = 1063.0
    val HEIGTH = 650.0

    override fun start(primaryStage: Stage)
    {
        // initialization
        var basic_room_two = Basic(2)
        var basic_room_three : Basic = Basic(3)
        var basic_room_four : Basic = Basic(4)

        var superior_room_two = Superior(2)
        var superior_room_three = Superior(3)
        var superior_room_four = Superior(4)

        var premium_apartment  = PremiumApartment()

        var valentines_day : SpecialOffer = SpecialOffer(Food(false,true,false), 2,true,true,
            false,true,1.0,0.5,0.9,0.8,)
        var family_package_4 : SpecialOffer = SpecialOffer(Food(false,false,true),4,true,true,false,
            false, 0.0, 0.0, 0.5,0.7)
        var family_package_3 : SpecialOffer = SpecialOffer(Food(false,false,true),3,true,true,false,
            false, 0.0, 0.0, 0.66,0.7)
        var birthday_package = SpecialOffer(Food(false,false,true), 2,true,true,true,true,
            0.0,0.0,0.6,0.7)

        // fxml loaders
        val hotelFXML = FXMLLoader(MainWindow::class.java.getResource("HotelView.fxml"))
        val basicFXML = FXMLLoader(MainWindow::class.java.getResource("BasicView.fxml"))
        val superiorFXML = FXMLLoader(MainWindow::class.java.getResource("SuperiorView.fxml"))
        val premiumFXML = FXMLLoader(MainWindow::class.java.getResource("PremiumApartmentView.fxml"))

        // load controller instances and roots for each room

        val hotelRoot : Parent = hotelFXML.load()
        val basicRoot : Parent = basicFXML.load()
        val superiorRoot : Parent = superiorFXML.load()
        val premiumRoot : Parent = premiumFXML.load()

        // init static head controller
        HeadController.setterStage(primaryStage)
        HeadController.add_windows_to_map("hotel" , Scene(hotelRoot,  WIDTH, HEIGTH))
        HeadController.add_windows_to_map("basic" , Scene(basicRoot, WIDTH, HEIGTH))
        HeadController.add_windows_to_map("superior" , Scene(superiorRoot, WIDTH, HEIGTH))
        HeadController.add_windows_to_map("premium" , Scene(premiumRoot , WIDTH, HEIGTH))

        // get controllers
        var hotelController : HotelController = hotelFXML.getController()
        var basicController : BasicController = basicFXML.getController()
        var premiumController : PremiumApartmentController= premiumFXML.getController()
        var superiorController : SuperiorController = superiorFXML.getController()

        // init hotel controller
        hotelController.addRoomAndMapIt("basic_two" , basic_room_two)
        hotelController.addRoomAndMapIt("basic_three" , basic_room_three)
        hotelController.addRoomAndMapIt("basic_four" , basic_room_four)

        hotelController.addRoomAndMapIt("superior_two" , superior_room_two)
        hotelController.addRoomAndMapIt("superior_three" , superior_room_three)
        hotelController.addRoomAndMapIt("superior_four" , superior_room_four)

        hotelController.addRoomAndMapIt("premium_room" , premium_apartment)

        hotelController.basicController = basicController
        hotelController.superiorController = superiorController
        hotelController.premiumApartmentController = premiumController

        // init radio buttons
        hotelController.initRadioButton()

        val scene = HeadController.scene_map["hotel"]
        primaryStage.title = "Bingo hotel"
        primaryStage.scene = scene
        primaryStage.show()
    }
}