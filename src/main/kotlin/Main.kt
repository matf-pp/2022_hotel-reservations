import Controllers.BasicController
import Controllers.HotelController
import Controllers.PremiumApartmentController
import Controllers.SuperiorController
import Rooms.Basic
import Rooms.PremiumApartment
import Rooms.Superior
import javafx.application.Application
import javafx.application.Application.launch
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

fun main(args: Array<String>)
{
    launch(MainWindow::class.java)
}

class MainWindow : Application()
{
    val WIDTH = 400.0
    val HEIGTH = 400.0

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

        // get controllers
        var hotelController : HotelController = hotelFXML.getController()
        var basicController : BasicController = basicFXML.getController()
        var premiumController : PremiumApartmentController= premiumFXML.getController()
        var superiorController : SuperiorController = superiorFXML.getController()


        // set root for every room that is instanciated
        // TODO uraditi i za ostale sobe
        basic_room_two.root = basicRoot
        premium_apartment.root = premiumRoot


        // Add room objects to controllers
        basicController.room = basic_room_two
        premiumController.room = premium_apartment

        // testing
        HotelController.Stage = primaryStage

        hotelController.basicController = basicController
        hotelController.superiorController = superiorController
        hotelController.premiumApartmentController = premiumController

        val scene = Scene(hotelRoot, WIDTH, HEIGTH)
        primaryStage.title = "Bingo hotel"
        primaryStage.scene = scene
        primaryStage.show()
    }
}