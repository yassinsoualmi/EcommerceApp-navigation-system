import com.electronicstore.ui.SimpleMainWindow;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SimpleMainWindow window = new SimpleMainWindow();
            window.setVisible(true);
        });
    }
}
