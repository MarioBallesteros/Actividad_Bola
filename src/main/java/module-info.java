module com.example.actividad_bola {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.logging;

    opens com.example.actividad_bola to javafx.fxml;
    exports com.example.actividad_bola.Servidor;
    opens com.example.actividad_bola.Servidor to javafx.fxml;
    exports com.example.actividad_bola.Elementos;
    opens com.example.actividad_bola.Elementos to javafx.fxml;
    exports com.example.actividad_bola;
}