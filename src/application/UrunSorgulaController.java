package application;



import java.io.PrintStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class UrunSorgulaController {
	@FXML
	TextArea textarea;
	@FXML
	TextField urunadi;
	private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
        }
	@FXML
    public void initialize() {
       
        PrintStream printStream = new PrintStream(new textareyazıdr(textarea));
        System.setOut(printStream);
        System.setErr(printStream); 
    }
	public void sorgula(ActionEvent event) {
		String ad=urunadi.getText();
		if (ad==null||ad.trim().isEmpty()) {
			showAlert("HATA!", "ürün adi giriniz");
			return;
			
		}
		if (!ad.matches("[a-zA-Z ]+")) {
			showAlert("HATA!", "ürün adi sadece harflerden oluşmalıdır");
			return;
			
		}
		UrunIslemleri.getInstance().urunSorgula(ad);
	}

}
