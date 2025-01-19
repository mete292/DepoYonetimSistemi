package application;
import java.io.OutputStream;
import java.io.PrintStream;

import javafx.scene.control.TextArea;

public class textareyazıdr extends OutputStream {
    private TextArea textArea;

    public textareyazıdr(TextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void write(int b) {
        textArea.appendText(String.valueOf((char) b));
    }
}
