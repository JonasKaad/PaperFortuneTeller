module com.jonaskaad.paperfortuneteller {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.jonaskaad.paperfortuneteller to javafx.fxml;
    exports com.jonaskaad.paperfortuneteller;
}