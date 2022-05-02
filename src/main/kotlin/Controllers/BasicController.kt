package Controllers

import Rooms.Basic
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.*
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.input.MouseEvent
import javafx.scene.paint.Color
import javafx.scene.shape.Polygon
import javafx.util.StringConverter
import java.io.File
import java.net.URL
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*

class BasicController : Initializable{


    private val numberOfPersonsBegin : String = "Number of persons: "
    lateinit var datFrom : LocalDate
    lateinit var datTo : LocalDate

    lateinit var room : Basic
    private var final_id = -1

    private val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    // images
    var images = mutableListOf<Image>()
    private var image_counter = 0

    // changes if pictures are added/removed, everyhthing else is automated
    private var numOfPictures = 2

    @FXML
    private lateinit var imwPicture: ImageView

    @FXML
    private lateinit var btAbout: Button

    @FXML
    private lateinit var btCheckAvailability: Button

    @FXML
    private lateinit var btCheckout: Button

    @FXML
    private lateinit var btHome: Button

    @FXML
    private lateinit var btReservations: Button

    @FXML
    private lateinit var dtDateFrom: DatePicker

    @FXML
    private lateinit var dtDateTo: DatePicker

    @FXML
    private lateinit var lbNumOfPersons: Label

    @FXML
    private lateinit var lbSuccess: Label

    @FXML
    private lateinit var lbPrice: Label

    @FXML
    private lateinit var pbSuccess: ProgressIndicator

    @FXML
    private lateinit var rbBreakfast: RadioButton

    @FXML
    private lateinit var rbFullBoard: RadioButton

    @FXML
    private lateinit var rbHalfBoard: RadioButton

    @FXML
    private lateinit var tfPrice: TextField

    @FXML
    private lateinit var tfSelectedDateFrom: TextField

    @FXML
    private lateinit var tfSelectedDateTo: TextField

    @FXML
    private lateinit var btLeft: Polygon

    @FXML
    private lateinit var btRight: Polygon

    private fun get_image(direction : String) : Image?{
        var image : Image? = null
        if(direction == "left"){
            if (image_counter == 0){
                image_counter = numOfPictures - 1
            }
            else {
                image_counter -= 1
            }
            image = images[image_counter]
        }
        else if(direction == "right"){
            if (image_counter == numOfPictures - 1){
                image_counter = 0
            }
            else {
                image_counter += 1
            }
            image = images[image_counter]
        }
        else{
            println("left or right only!")
        }
        return image
    }

    @FXML
    fun btActionLeft(event: MouseEvent) {
        imwPicture.image = get_image("left")
    }

    @FXML
    fun btActionRight(event: MouseEvent) {
        imwPicture.image = get_image("right")
    }

    @FXML
    fun btAboutActionOpen(event: ActionEvent) {

    }
    @FXML
    fun btActionCheckAvailability(event: ActionEvent) {
        val dateFrom: LocalDate = dtDateFrom.value
        val dateTo: LocalDate = dtDateTo.value
        datTo = dtDateTo.value
        datFrom = dtDateFrom.value


        final_id = room.find_id(dateFrom, dateTo)
        if(final_id != -1){
            pbSuccess.progress = 1.0
            unlock()
            tfSelectedDateFrom.text = dtDateFrom.value.format(dateFormatter)
            tfSelectedDateTo.text = dtDateTo.value.format(dateFormatter)
            setLabelTextAndColor(lbSuccess, "Success", "green")
            tfPrice.text = calculate_final_price_room_food().toString()
            lbNumOfPersons.requestFocus()
        }
        else {
            pbSuccess.progress = 1.0
            lock()
            tfSelectedDateFrom.text = ""
            tfSelectedDateTo.text = ""
            setLabelTextAndColor(lbSuccess, "No available rooms!", "red")
            tfPrice.text = ""
        }
        resetDates()
    }

    @FXML
    fun btActionCheckout(event: ActionEvent) {
        HeadController.reservation.selectedRoom = room
        HeadController.setScene("reservation")
        HeadController.reservation.fill_fields(datFrom, datTo,
            ReservationThings.Food(rbBreakfast.isSelected, rbHalfBoard.isSelected, rbFullBoard.isSelected), tfPrice.text)
        hard_reset()
    }

    @FXML
    fun btHomeActionOpen(event: ActionEvent) {
        lbNumOfPersons.text = ""
        hard_reset()
        HeadController.setScene("hotel")
    }
    @FXML
    fun btReservationsActionOpen(event: ActionEvent) {

    }
    @FXML
    fun rbActionBreakfast(event: ActionEvent) {
        if (rbBreakfast.isSelected){
            tfPrice.text = calculate_final_price_room_food().toString()
        }
    }
    @FXML
    fun rbActionFullBoard(event: ActionEvent) {
        if (rbFullBoard.isSelected){
            tfPrice.text = calculate_final_price_room_food().toString()
        }
    }
    @FXML
    fun rbActionHalfBoard(event: ActionEvent) {
        if (rbHalfBoard.isSelected){
            tfPrice.text = calculate_final_price_room_food().toString()
        }
    }

    @FXML
    fun dtActionDateFrom(event: ActionEvent) {
        try {
            if(ChronoUnit.DAYS.between(LocalDate.now(), dtDateFrom.value) < 0){
                setLabelTextAndColor(lbSuccess, "Date from is in past", "red")
                dtDateTo.value = null
                dtDateTo.isDisable = true
                btCheckAvailability.isDisable = true
                pbSuccess.progress = 0.0
            }
            else{
                pbSuccess.progress = 0.33
                setLabelTextAndColor(lbSuccess)
                dtDateTo.value = null
                dtDateTo.isDisable = false
                tfSelectedDateFrom.text = ""
                tfSelectedDateTo.text = ""
                tfPrice.text = ""
                lbPrice.isDisable = true
                tfPrice.isDisable = true
                rbFullBoard.isDisable = true
                rbHalfBoard.isDisable = true
                rbBreakfast.isDisable = true
                btCheckout.isDisable = true
                rbBreakfast.isSelected = true
            }
        }
        catch (e : Exception){

        }
    }
    @FXML
    fun dtActionDateTo(event: ActionEvent) {
        try {
            if(ChronoUnit.DAYS.between(dtDateFrom.value, dtDateTo.value) < 1){
                setLabelTextAndColor(lbSuccess, "Wrong date period", "red")
                btCheckAvailability.isDisable = true
            }
            else{
                setLabelTextAndColor(lbSuccess)
                btCheckAvailability.isDisable = false
                pbSuccess.progress = 0.66
            }
        }
        catch (e : Exception){

        }
    }

    // non FXML functions
    private fun resetDates(){
        dtDateFrom.isDisable = false
        dtDateTo.isDisable = true
        dtDateFrom.value = null
        dtDateTo.value = null
        btCheckAvailability.isDisable = true
    }

    private fun hard_reset(){
        resetDates()
        setLabelTextAndColor(lbSuccess)
        tfSelectedDateFrom.text = ""
        tfSelectedDateTo.text = ""
        lock()
        pbSuccess.progress = 0.0
    }
    fun set_toggle_food() {
        val tgFood = ToggleGroup()

        rbBreakfast.isSelected = true

        rbFullBoard.toggleGroup = tgFood
        rbHalfBoard.toggleGroup = tgFood
        rbBreakfast.toggleGroup = tgFood
    }

    fun setNumLabel(num : Int) {
        lbNumOfPersons.text = "$numberOfPersonsBegin $num"
    }

    private fun unlock(){
        rbBreakfast.isDisable = false
        rbHalfBoard.isDisable = false
        rbFullBoard.isDisable = false
        lbPrice.isDisable = false
        tfPrice.isDisable = false
        btCheckout.isDisable = false
    }
    private fun lock(){
        tfPrice.text = ""

        rbBreakfast.isDisable = true
        rbHalfBoard.isDisable = true
        rbFullBoard.isDisable = true
        lbPrice.isDisable = true
        tfPrice.isDisable = true
        btCheckout.isDisable = true
    }

    private fun setLabelTextAndColor(label: Label , text : String = "",color : String = "black"){
        if (color.equals("red")){
            label.textFill = Color.color(1.0, 0.0, 0.0)
            label.text = text
        }
        else if (color.equals("green")){
            label.textFill = Color.color(0.0, 1.0, 0.0)
            label.text = text
        }
        else if(color.equals("black")){
            label.textFill = Color.color(0.0, 0.0, 0.0)
            label.text = text
        }
        else {
            println("Available colors are red green and by default black")
        }
    }

    private fun calculate_final_price_room_food() : Double{
        val localDateFrom = LocalDate.parse(tfSelectedDateFrom.text, dateFormatter)
        val localDateTo = LocalDate.parse(tfSelectedDateTo.text, dateFormatter)

        var cena : Double = 0.0
        val duzina_ostajanja = ChronoUnit.DAYS.between(localDateFrom, localDateTo)
        if (rbBreakfast.isSelected){
            cena += HeadController.foodBreakfast.get_total_price() * room.num_beds * duzina_ostajanja
        }
        else  if (rbHalfBoard.isSelected){
            cena += HeadController.foodHalf.get_total_price() * room.num_beds * duzina_ostajanja
        }
        else  if (rbFullBoard.isSelected){
            cena += HeadController.foodFull.get_total_price() * room.num_beds * duzina_ostajanja
        }
        return cena + room.price_per_night * duzina_ostajanja
    }

    private fun loadImages(){
        for (i in 1..numOfPictures){
            val file = File("src/main/resources/images/basic_detailed/basic_detailed_$i.jpg")
            images.add(Image(file.toURI().toString()))
        }
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {

        loadImages()

        val converter = object : StringConverter<LocalDate?>() {
            override fun toString(date: LocalDate?): String? {
                return if (date != null) dateFormatter.format(date) else ""
            }

            override fun fromString(string: String): LocalDate? {
                return if (string.isNotEmpty()) LocalDate.parse(string, dateFormatter) else null
            }
        }
        dtDateFrom.converter = converter
        dtDateTo.converter = converter
    }

}
