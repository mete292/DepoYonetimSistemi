package application;

import java.io.PrintStream;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class Tukenengostercontroller {
	@FXML
	TextArea textarea;
	public void initialize() {
		PrintStream printstream=new PrintStream(new textareyazıdr(textarea));
		System.setErr(printstream);
		System.setOut(printstream);
		
	}
	public void goster(ActionEvent event) {
		UrunIslemleri.getInstance().tukenenUrunleriOku();
		
	}

}
