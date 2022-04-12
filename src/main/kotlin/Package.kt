import javafx.scene.layout.Pane

class Package(var dorucak : Boolean, var rucak : Boolean, var vecera : Boolean){
    var popust_po_nocenju : Double = 1.0
    var specijalna_akcija: String? = null

    companion object {
        fun show_package() : Pane?{
            return null
        }
    }

}