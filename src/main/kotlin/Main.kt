import javafx.application.Application
import javafx.application.Application.launch
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage


fun main(args: Array<String>)
{
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








    launch(MainWindow::class.java)
}

class MainWindow : Application()
{
    val WIDTH = 400.0
    val HEIGTH = 400.0

    override fun start(primaryStage: Stage)
    {

        val fxmlLoader = FXMLLoader(MainWindow::class.java.getResource("HotelView.fxml"))

        val scene = Scene(fxmlLoader.load(), 320.0, 240.0)
        primaryStage.title = "Hello!"
        primaryStage.scene = scene
        primaryStage.show()
    }
}