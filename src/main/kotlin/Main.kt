import Controllers.*
import ReservationThings.Food
import ReservationThings.Offer
import ReservationThings.Reservation
import ReservationThings.SpecialOffer
import Rooms.Basic
import Rooms.PremiumApartment
import Rooms.Room
import Rooms.Superior
import javafx.application.Application
import javafx.application.Application.launch
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import java.io.File
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

        fun read_from_file(){
            val file = File("reservations.txt")
            val linije = file.readLines()
            for (linija in linije){
                val tokeni = linija.split(", ")
                val id_room = tokeni[0].trim().toInt()
                val datum1 = tokeni[1].trim().split("-")
                val datum2 = tokeni[2].trim().split("-")
                val date_from = LocalDate.of(datum1[0].trim().toInt(), datum1[1].trim().toInt(), datum1[2].trim().toInt())
                val date_to = LocalDate.of(datum2[0].trim().toInt(), datum2[1].trim().toInt(), datum2[2].trim().toInt())
                val first_name = tokeni[3].trim()
                val last_name = tokeni[4].trim()
                val id_number = tokeni[5].trim()
                val food = Food(tokeni[6].trim().toBoolean(), tokeni[7].trim().toBoolean(), tokeni[8].trim().toBoolean())
                val num_beds = tokeni[9].trim().toInt()
                val parking = tokeni[10].trim().toBoolean()
                val wellness = tokeni[11].trim().toBoolean()
                val massage = tokeni[12].trim().toInt()
                val party = tokeni[13].trim().toBoolean()

                val room : Room = when(num_beds){
                    2 -> if(id_room < 200) basic_room_two
                            else  superior_room_two
                    3 -> if(id_room < 200) basic_room_three
                    else  superior_room_three

                    else -> if(id_room < 200) basic_room_four
                                else if(id_room < 300) superior_room_four
                                        else premium_apartment

                }
                val offer = Offer(food, num_beds, parking, wellness, massage, party)
                var new_reservation = Reservation(first_name, last_name, id_number, date_from, date_to, room, id_room, offer)
            }
        }

        // fxml loaders
        val hotelFXML = FXMLLoader(MainWindow::class.java.getResource("HotelView.fxml"))
        val basicFXML = FXMLLoader(MainWindow::class.java.getResource("BasicView.fxml"))
        val superiorFXML = FXMLLoader(MainWindow::class.java.getResource("SuperiorView.fxml"))
        val premiumFXML = FXMLLoader(MainWindow::class.java.getResource("PremiumApartmentView.fxml"))
        val reservationFXML = FXMLLoader(MainWindow::class.java.getResource("ReservationView.fxml"))
        val aboutFXML = FXMLLoader(MainWindow::class.java.getResource("AboutView.fxml"))
        val reservationsListFXML = FXMLLoader(MainWindow::class.java.getResource("ReservationsListView.fxml"))

        // load controller instances and roots for each room
        val hotelRoot : Parent = hotelFXML.load()
        val basicRoot : Parent = basicFXML.load()
        val superiorRoot : Parent = superiorFXML.load()
        val premiumRoot : Parent = premiumFXML.load()
        val reservationRoot : Parent = reservationFXML.load()
        val aboutRoot : Parent = aboutFXML.load()
        val reservationsListRoot : Parent = reservationsListFXML.load()

        // init static/global head controller
        HeadController.setterStage(primaryStage)
        HeadController.add_windows_to_map("hotel" , Scene(hotelRoot,  WIDTH, HEIGTH))
        HeadController.add_windows_to_map("basic" , Scene(basicRoot, WIDTH, HEIGTH))
        HeadController.add_windows_to_map("superior" , Scene(superiorRoot, WIDTH, HEIGTH))
        HeadController.add_windows_to_map("premium" , Scene(premiumRoot , WIDTH, HEIGTH))
        HeadController.add_windows_to_map("reservation", Scene(reservationRoot, 600.0, 811.0))
        HeadController.add_windows_to_map("about", Scene(aboutRoot, WIDTH, HEIGTH))
        HeadController.add_windows_to_map("reservationsList", Scene(reservationsListRoot, WIDTH, HEIGTH))

        // get controllers
        val hotelController : HotelController = hotelFXML.getController()
        val basicController : BasicController = basicFXML.getController()
        val premiumController : PremiumApartmentController= premiumFXML.getController()
        val superiorController : SuperiorController = superiorFXML.getController()
        val reservationController : ReservationController = reservationFXML.getController()
        val aboutController : AboutController = aboutFXML.getController()
        val reservationListController : ReservationListController = reservationsListFXML.getController()
        println(reservationListController)

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
        // set reservation list controller
        HeadController.reservationList = reservationListController

        // init radio buttons
        hotelController.initRadioButton()

        // init radio buttons for food controllers
        basicController.set_toggle_food()
        superiorController.set_toggle_food()
        premiumController.set_toggle_food()

        // read data
        read_from_file()

        val scene = HeadController.scene_map["hotel"]
        primaryStage.title = "Bingo hotel"
        primaryStage.scene = scene
        primaryStage.show()
    }
}