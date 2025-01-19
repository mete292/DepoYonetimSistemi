package application;

import java.io.PrintStream;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Uruneklecontroller {
	@FXML
	TextField urunkategorisi;
	@FXML
	TextField urunadi;
	@FXML
	TextField urunadeti;
	@FXML
	TextArea textarea;
	
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
	public void ekle(ActionEvent event) {
		
		String ad=urunadi.getText();
		String kategori=urunkategorisi.getText();
		String adetstr=urunadeti.getText();
		
		if (ad==null||ad.trim().isEmpty()) {
			showAlert("HATA!", "ürün adı giriniz");
			return;
			
		}
		if (!ad.matches("[a-zA-Z ]+")) {
			showAlert("HATA!", "ürün adi sadece harflerden oluşmalıdır");
			return;
			
		}
		if (kategori==null||kategori.trim().isEmpty()) {
			showAlert("HATA!", "ürün kategorisi giriniz");
			return;
			
		}
		if (!kategori.matches("[a-zA-Z ]+")) {
			showAlert("HATA!", "ürün kategorisi sadece harflerden oluşmasılıdrı");
			return;
			
		}

		if (adetstr==null||adetstr.trim().isEmpty()) {
			showAlert("HATA!", "ürün adeti giriniz");
			return;
			
		}
		
		try {
			int adet=Integer.parseInt(urunadeti.getText());
			if (adet<0) {
				showAlert("HATA!", "ürün adeti negatif olamaz");
				return;
			}
			UrunIslemleri.getInstance().urunEkle(ad,kategori, adet);
			
		}
			
		 catch (NumberFormatException e) {
			 	showAlert("HATA!", "ürün adeti geçerli bir sayı olmadlıdır");
		 }
		
	}
	

}
