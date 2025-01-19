package application;

import java.io.PrintStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class UrunSiralaController {
	@FXML
	TextArea textarea;
	@FXML
    public void initialize() {
  
        PrintStream printStream = new PrintStream(new textareyazıdr(textarea));
        System.setOut(printStream);
        System.setErr(printStream); 
    }
	public void sirala(ActionEvent event) {
		UrunIslemleri.getInstance().urunSirala();
		
	}
	public void katogerisirala(ActionEvent event) {
		UrunIslemleri.getInstance().urunkatogeriSirala();
	}
	

}
