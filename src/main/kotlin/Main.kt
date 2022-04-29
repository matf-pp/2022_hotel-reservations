import Controllers.*
import ReservationThings.Food
import ReservationThings.SpecialOffer
import Rooms.Basic
import Rooms.PremiumApartment
import Rooms.Superior
import javafx.application.Application
import javafx.application.Application.launch
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import java.time.LocalDate

fun main(args: Array<String>)
{
    launch(MainWindow::class.java)
}

class MainWindow : Application()
{
    val WIDTH = 1080.0
    val HEIGTH = 650.0
    var local_date : LocalDate = LocalDate.of(2020, 5, 4)

    override fun start(primaryStage: Stage)
    {
        // initialization
        var basic_room_two = Basic(2)
        var basic_room_three  = Basic(3)
        var basic_room_four  = Basic(4)

        var superior_room_two = Superior(2)
        var superior_room_three = Superior(3)
        var superior_room_four = Superior(4)

        var premium_apartment  = PremiumApartment()

        var valentines_day : SpecialOffer = SpecialOffer(Food(false,true,false), 2,true,true,
            2,true,1.0,0.5,0.9,0.8,)
        var family_package_4 : SpecialOffer = SpecialOffer(Food(false,false,true),4,true,true,2,
            false, 0.0, 0.0, 0.5,0.7)
        var family_package_3 : SpecialOffer = SpecialOffer(Food(false,false,true),3,true,true,2,
            false, 0.0, 0.0, 0.66,0.7)
        var birthday_package = SpecialOffer(Food(false,false,true), 2,true,true,2,true,
            0.0,0.0,0.6,0.7)

        // init food and set to head controller
        val foodBreakfast = Food(true, false, false)
        val foodHalf = Food(false, true, false)
        val foodFull = Food(false, false, true)

        HeadController.foodBreakfast = foodBreakfast
        HeadController.foodHalf = foodHalf
        HeadController.foodFull = foodFull

        // fxml loaders
        val hotelFXML = FXMLLoader(MainWindow::class.java.getResource("HotelView.fxml"))
        val basicFXML = FXMLLoader(MainWindow::class.java.getResource("BasicView.fxml"))
        val superiorFXML = FXMLLoader(MainWindow::class.java.getResource("SuperiorView.fxml"))
        val premiumFXML = FXMLLoader(MainWindow::class.java.getResource("PremiumApartmentView.fxml"))
        val reservationFXML = FXMLLoader(MainWindow::class.java.getResource("ReservationView.fxml"))

        // load controller instances and roots for each room
        val hotelRoot : Parent = hotelFXML.load()
        val basicRoot : Parent = basicFXML.load()
        val superiorRoot : Parent = superiorFXML.load()
        val premiumRoot : Parent = premiumFXML.load()
        val reservationRoot : Parent = reservationFXML.load()

        // init static/global head controller
        HeadController.setterStage(primaryStage)
        HeadController.add_windows_to_map("hotel" , Scene(hotelRoot,  WIDTH, HEIGTH))
        HeadController.add_windows_to_map("basic" , Scene(basicRoot, WIDTH, HEIGTH))
        HeadController.add_windows_to_map("superior" , Scene(superiorRoot, WIDTH, HEIGTH))
        HeadController.add_windows_to_map("premium" , Scene(premiumRoot , WIDTH, HEIGTH))
        HeadController.add_windows_to_map("reservation", Scene(reservationRoot, 600.0, 811.0))

        // get controllers
        var hotelController : HotelController = hotelFXML.getController()
        var basicController : BasicController = basicFXML.getController()
        var premiumController : PremiumApartmentController= premiumFXML.getController()
        var superiorController : SuperiorController = superiorFXML.getController()
        var reservationController : ReservationController = reservationFXML.getController()


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

        // set reservation controller to be global (beacause the selected room will be chosen from room controllers)
        HeadController.reservation = reservationController

        // init radio buttons
        hotelController.initRadioButton()

        // init radio buttons for food controllers
        basicController.set_toggle_food()
        // todo superior
        // todo preimum

        val scene = HeadController.scene_map["hotel"]
        primaryStage.title = "Bingo hotel"
        primaryStage.scene = scene
        primaryStage.show()
    }
}