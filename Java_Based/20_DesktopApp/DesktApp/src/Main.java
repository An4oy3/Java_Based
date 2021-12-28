import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Main {
    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setSize(300, 300);
        JPanel form1 = new MainForm().getMainPanel();
        frame.add(form1);


        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
