package application;

import java.io.PrintStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class UrunYazdirController {
	@FXML
	TextArea textarea;
	@FXML
    public void initialize() {
        
        PrintStream printStream = new PrintStream(new textareyazıdr(textarea));
        System.setOut(printStream);
        System.setErr(printStream); 
    }
	public void yazdir(ActionEvent event) {
		UrunIslemleri.getInstance().urunleriYazdir();
	}

}
