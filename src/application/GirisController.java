package application;

import java.io.IOException;

import org.controlsfx.control.Notifications;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GirisController {
	@FXML private TextField kullaniciadi;
	@FXML private PasswordField sifre;
	public void login(ActionEvent event) throws IOException {
		if (kullaniciadi.getText().equals("admin")&& (sifre.getText().equals("12345"))) {
			Stage stage=new Stage();
			Parent root=FXMLLoader.load(getClass().getResource("Islemler.fxml"));
			Scene scene=new Scene(root);
			stage.setScene(scene);
			stage.show();
			((Node)event.getSource()).getScene().getWindow().hide();
			
		}
		else {
			Notifications notification=Notifications.create();
			notification.title("HATA!");
			notification.text("hatalı kullanıcı adı veya sifre");
			notification.hideAfter(Duration.seconds(5));
			notification.position(Pos.BASELINE_RIGHT);
			notification.show();
			
			
		}
		
		
	}

}
