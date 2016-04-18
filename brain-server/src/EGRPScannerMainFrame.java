import javax.swing.*;

/**
 * Класс главного окна приложения "EGRP-Scanner".
 *
 * @author sestricaalenyshka
 */
public class EGRPScannerMainFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame myFrame = new JFrame("EGRP-Scanner");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(400, 300);
        myFrame.setVisible(true);
    }
}
