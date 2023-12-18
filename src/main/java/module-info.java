module com.example.actividad_bola {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.actividad_bola to javafx.fxml;
    exports com.example.actividad_bola;
}