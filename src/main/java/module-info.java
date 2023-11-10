module pl.javahowtoprogramgui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens pl.javahowtoprogramgui.section_4.e1 to javafx.fxml;
    exports pl.javahowtoprogramgui.section_4.e1;

    opens pl.javahowtoprogramgui.section_4.e4_1 to javafx.fxml;
    exports pl.javahowtoprogramgui.section_4.e4_1;

    opens pl.javahowtoprogramgui.section_4.e4_2 to javafx.fxml;
    exports pl.javahowtoprogramgui.section_4.e4_2;

    opens pl.javahowtoprogramgui.section_4.e4_3 to javafx.fxml;
    exports pl.javahowtoprogramgui.section_4.e4_3;
}