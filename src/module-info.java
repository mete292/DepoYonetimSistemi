module depoo {
	requires javafx.controls;
	requires javafx.fxml;
	requires org.controlsfx.controls;
	
	opens application to javafx.graphics, javafx.fxml;
}
