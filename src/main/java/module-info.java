module ma.rsmi.bibleo {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires org.apache.commons.text;

    opens ma.rsmi.bibleo to javafx.fxml;

    exports ma.rsmi.bibleo;
    exports demo;

}