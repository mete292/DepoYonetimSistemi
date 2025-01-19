package application;

import javafx.event.ActionEvent;
import java.io.PrintStream;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class KategorigosterController {
	@FXML
	TextArea textarea;
	@FXML
	TextField kategori;
	@FXML
	 public void initialize() {
	        
	        PrintStream printStream = new PrintStream(new textareyazıdr(textarea));
	        System.setOut(printStream);
	        System.setErr(printStream); 
	    }
	 private void showAlert(String title, String message) {
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle(title);
	        alert.setHeaderText(null);
	        alert.setContentText(message);
	        alert.showAndWait();
	    }
	
	 public void goster(ActionEvent event ) {
		 String kategori1=kategori.getText();
		 
		 if (kategori1==null||kategori1.trim().isEmpty()) {
				showAlert("HATA!", "ürün adı giriniz");
				return;
				
			}
			if (!kategori1.matches("[a-zA-Z ]+")) {
				showAlert("HATA!", "ürün adi sadece harflerden oluşmalıdır");
				return;
				
			}
		
				UrunIslemleri.getInstance().kategorigoster(kategori1);
		
	 }
}
