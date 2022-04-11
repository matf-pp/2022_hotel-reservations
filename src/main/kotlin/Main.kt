import javafx.application.Application
import javafx.application.Application.launch
import javafx.fxml.FXMLLoader
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

        val fxmlLoader = FXMLLoader(MainWindow::class.java.getResource("HotelView.fxml"))

        val scene = Scene(fxmlLoader.load(), 320.0, 240.0)
        primaryStage.title = "Hello!"
        primaryStage.scene = scene
        primaryStage.show()
    }
}