package application;

import java.io.IOException;
import java.io.PrintStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UrunGuncelleController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
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
		 public void degis(ActionEvent event) throws IOException{
			    Parent root=FXMLLoader.load(getClass().getResource("kategoriguncelle.fxml"));
				stage=(Stage)((Node)event.getSource()).getScene().getWindow();
				scene=new Scene(root);
				stage.setScene(scene);
				stage.show();
			}
		public void eksilt(ActionEvent event) {
			
			
			String ad=urunadi.getText();
			String adetstr=urunadeti.getText();
			

			if (ad==null||ad.trim().isEmpty()) {
				showAlert("HATA!", "ürün adı giriniz");
				return;
				
			}
			if (!ad.matches("[a-zA-Z ]+")) {
				showAlert("HATA!", "ürün adi sadece harflerden oluşmalıdır");
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
				UrunIslemleri.getInstance().urunGuncelle(ad, adet);
				
			} catch (NumberFormatException e) {
				showAlert("HATA!", "ürün adeti geçerli bir sayi olmalidir");
				
				
			}
			
			
		}
		

	}



