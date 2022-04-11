package javafx.example

import javafx.application.Application
import javafx.application.Application.launch
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
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
        val layout = VBox()

        // Ovde ide nas GUI
        layout.apply {
            children.add(Label("Hello, World!"))
        }



        // do ovde
        primaryStage.run {
            scene = Scene(layout, WIDTH, HEIGTH)
            show()
        }
    }
}