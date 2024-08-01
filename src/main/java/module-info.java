module com.example.path_of_all_flight {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.path_of_all_flight to javafx.fxml;
    exports com.example.path_of_all_flight;
}