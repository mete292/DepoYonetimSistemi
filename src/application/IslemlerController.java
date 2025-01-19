package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class IslemlerController {

	
	public void urunekle(ActionEvent event) throws IOException{
		Stage stage=new Stage();
		Parent root=FXMLLoader.load(getClass().getResource("Urunekle.fxml"));
		Scene scene=new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void uruneksilt(ActionEvent event) throws IOException{
		Stage stage=new Stage();
		Parent root=FXMLLoader.load(getClass().getResource("UrunEksilt.fxml"));
		Scene scene=new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void urunguncelle(ActionEvent event) throws IOException{
		Stage stage=new Stage();
		Parent root=FXMLLoader.load(getClass().getResource("UrunGuncelle.fxml"));
		Scene scene=new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void urunyazdir(ActionEvent event) throws IOException{
		Stage stage=new Stage();
		Parent root=FXMLLoader.load(getClass().getResource("UrunYazdir.fxml"));
		Scene scene=new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void urunsirala(ActionEvent event) throws IOException{
		Stage stage=new Stage();
		Parent root=FXMLLoader.load(getClass().getResource("UrunSirala.fxml"));
		Scene scene=new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void urunsorgula(ActionEvent event) throws IOException{
		Stage stage=new Stage();
		Parent root=FXMLLoader.load(getClass().getResource("UrunSorgu.fxml"));
		Scene scene=new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void tukenengoster(ActionEvent event) throws IOException{
		Stage stage=new Stage();
		Parent root=FXMLLoader.load(getClass().getResource("Tukenengoster.fxml"));
		Scene scene=new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void kategorigoster(ActionEvent event) throws IOException{
		Stage stage=new Stage();
		Parent root=FXMLLoader.load(getClass().getResource("Kategorigoster.fxml"));
		Scene scene=new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	



	

}
